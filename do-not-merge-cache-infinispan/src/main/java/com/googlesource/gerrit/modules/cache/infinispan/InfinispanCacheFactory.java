package com.googlesource.gerrit.modules.cache.infinispan;

import static java.util.concurrent.TimeUnit.SECONDS;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.flogger.FluentLogger;
import com.google.gerrit.common.Nullable;
import com.google.gerrit.extensions.events.LifecycleListener;
import com.google.gerrit.extensions.registration.DynamicMap;
import com.google.gerrit.server.cache.CacheDef;
import com.google.gerrit.server.cache.MemoryCacheFactory;
import com.google.gerrit.server.cache.PersistentCacheBaseFactory;
import com.google.gerrit.server.cache.PersistentCacheDef;
import com.google.gerrit.server.config.ConfigUtil;
import com.google.gerrit.server.config.GerritServerConfig;
import com.google.gerrit.server.config.ScheduleConfig;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import java.nio.file.Path;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.jgit.lib.Config;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.commons.dataconversion.MediaType;
import org.infinispan.commons.marshall.IdentityMarshaller;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;

@Singleton
public class InfinispanCacheFactory extends PersistentCacheBaseFactory
    implements LifecycleListener {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  static class PeriodicCachePruner implements Runnable {
    private final InfinispanCacheImpl<?, ?> cache;

    PeriodicCachePruner(InfinispanCacheImpl<?, ?> cache) {
      this.cache = cache;
    }

    @Override
    public String toString() {
      return "Infinispan Disk Cache Pruner (" + cache.getCacheName() + ")";
    }

    @Override
    public void run() {
      cache.prune();
    }
  }

  private final DefaultCacheManager cacheManager;
  private final DynamicMap<Cache<?, ?>> cacheMap;
  private final List<InfinispanCacheImpl<?, ?>> caches;
  private final ScheduleConfig.Schedule schedule;
  private final ScheduledExecutorService cleanup;
  private final boolean pruneOnStartup;
  private final int perCacheOpenFilesLimit;
  private final AtomicBoolean isDiskCacheReadOnly;

  @Inject
  public InfinispanCacheFactory(
      MemoryCacheFactory memCacheFactory,
      @GerritServerConfig Config config,
      DynamicMap<Cache<?, ?>> cacheMap,
      @Nullable @Named("CacheCleanupExecutor") ScheduledExecutorService cleanupExecutor,
      @Nullable @InfinispanDir Path infinispanDir,
      @Named("DiskCacheReadOnly") AtomicBoolean isDiskCacheReadOnly) {
    super(memCacheFactory, config, infinispanDir);
    this.cacheManager = initCacheManager();
    this.cacheMap = cacheMap;
    this.caches = new LinkedList<>();
    schedule =
        ScheduleConfig.createSchedule(config, "cachePruning")
            .orElseGet(
                () -> ScheduleConfig.Schedule.createOrFail(Duration.ofDays(1).toMillis(), "01:00"));
    logger.atInfo().log("Scheduling cache pruning with schedule %s", schedule);
    this.cleanup = cleanupExecutor;
    pruneOnStartup = config.getBoolean("cachePruning", null, "pruneOnStartup", true);
    this.perCacheOpenFilesLimit = config.getInt("cache", "openFiles", 128) / 24;
    this.isDiskCacheReadOnly = isDiskCacheReadOnly; // TODO: Use this
  }

  @Override
  public void start() {
    for (InfinispanCacheImpl<?, ?> cache : caches) {
      if (cleanup != null) {
        if (pruneOnStartup) {
          @SuppressWarnings("unused")
          Future<?> possiblyIgnoredError =
              cleanup.schedule(new PeriodicCachePruner(cache), 30, TimeUnit.SECONDS);
        }

        @SuppressWarnings("unused")
        Future<?> possiblyIgnoredError =
            cleanup.scheduleAtFixedRate(
                new PeriodicCachePruner(cache),
                schedule.initialDelay(),
                schedule.interval(),
                TimeUnit.MILLISECONDS);
      }
    }
  }

  @Override
  public void stop() {
    if (cleanup != null) {
      cleanup.shutdownNow();
    }
    cacheManager.stop(); // Stops all caches
    caches.clear();
  }

  @Override
  public void onStop(String plugin) {
    synchronized (caches) {
      for (Map.Entry<String, Provider<Cache<?, ?>>> entry : cacheMap.byPlugin(plugin).entrySet()) {
        Cache<?, ?> cache = entry.getValue().get();
        if (cache instanceof InfinispanCacheImpl<?, ?> infinispanCache) {
          caches.remove(infinispanCache);
          infinispanCache.stop();
        }
      }
    }
  }

  @Override
  protected <K, V> Cache<K, V> buildImpl(PersistentCacheDef<K, V> def, long diskLimit) {
    return buildImpl(def, null, diskLimit);
  }

  @Override
  protected <K, V> LoadingCache<K, V> buildImpl(
      PersistentCacheDef<K, V> def, CacheLoader<K, V> loader, long diskLimit) {
    org.infinispan.Cache<byte[], byte[]> cache =
        cacheManager
            .administration()
            .withFlags(CacheContainerAdmin.AdminFlag.VOLATILE)
            .getOrCreateCache(def.name(), getEmbeddedCacheConfiguration(def, diskLimit));

    int maxVictimsPerRun = 50_000;

    InfinispanCacheImpl<K, V> infinispanCacheImpl =
        new InfinispanCacheImpl<>(cache, loader, def, diskLimit, maxVictimsPerRun);

    synchronized (caches) {
      caches.add(infinispanCacheImpl);
    }
    return infinispanCacheImpl;
  }

  @Nullable
  private DefaultCacheManager initCacheManager() {
    if (cacheDir == null) {
      return null;
    }
    GlobalConfigurationBuilder gcb = new GlobalConfigurationBuilder().nonClusteredDefault();
    gcb.cacheContainer().statistics(true);
    gcb.serialization().marshaller(IdentityMarshaller.INSTANCE);
    gcb.globalState().enable().persistentLocation(cacheDir.toAbsolutePath().toString());
    return new DefaultCacheManager(gcb.build());
  }

  private <K, V> Configuration getEmbeddedCacheConfiguration(
      PersistentCacheDef<K, V> def, long diskLimit) {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.clustering().cacheMode(CacheMode.LOCAL).statistics().enable();
    cb.encoding().mediaType(MediaType.APPLICATION_OCTET_STREAM);
    cb.expiration().lifespan(getExpiration(def));

    long memoryLimit = getMemoryLimit(def);
    if (Long.MAX_VALUE == memoryLimit) {
      cb.memory().maxCount(-1);
    } else if (memoryLimit >= 1 << 20 /* 1MiB */) {
      cb.memory().maxSize(String.valueOf(memoryLimit)).whenFull(EvictionStrategy.REMOVE);
    } else if (memoryLimit > 0) {
      cb.memory().maxCount(memoryLimit).whenFull(EvictionStrategy.REMOVE);
    } else {
      // Infinispan doesn't support disabling the memory store, so set to 1 entry
      cb.memory().maxCount(1).whenFull(EvictionStrategy.REMOVE);
    }

    if (diskLimit > 0) {
      cb.persistence()
          .addSoftIndexFileStore()
          .openFilesLimit(perCacheOpenFilesLimit)
          .async()
          .enable();
    }
    return cb.build();
  }

  private <K, V> long getMemoryLimit(CacheDef<K, V> def) {
    return config.getLong("cache", def.configKey(), "memoryLimit", def.maximumWeight());
  }

  private <K, V> long getExpiration(PersistentCacheDef<K, V> def) {
    long expireAfterWriteInSec =
        ConfigUtil.getTimeUnit(config, "cache", def.configKey(), "maxAge", -1, SECONDS);
    if (expireAfterWriteInSec > 0) {
      return Duration.ofSeconds(expireAfterWriteInSec).toMillis();
    }
    if (expireAfterWriteInSec == 0 || def.expireAfterWrite() == null) {
      return -1; // 0 in gerrit.config means never expire
    }
    return def.expireAfterWrite().toMillis();
  }
}
