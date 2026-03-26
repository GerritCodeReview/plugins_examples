package com.googlesource.gerrit.modules.cache.infinispan;

import static java.util.concurrent.TimeUnit.SECONDS;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gerrit.common.Nullable;
import com.google.gerrit.extensions.events.LifecycleListener;
import com.google.gerrit.extensions.registration.DynamicMap;
import com.google.gerrit.server.cache.CacheDef;
import com.google.gerrit.server.cache.MemoryCacheFactory;
import com.google.gerrit.server.cache.PersistentCacheBaseFactory;
import com.google.gerrit.server.cache.PersistentCacheDef;
import com.google.gerrit.server.config.ConfigUtil;
import com.google.gerrit.server.config.GerritServerConfig;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import java.nio.file.Path;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.eclipse.jgit.lib.Config;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.commons.configuration.BasicConfiguration;
import org.infinispan.commons.dataconversion.MediaType;
import org.infinispan.commons.marshall.IdentityMarshaller;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.configuration.parsing.ConfigurationBuilderHolder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.persistence.remote.configuration.RemoteStoreConfigurationBuilder;
import org.infinispan.persistence.remote.configuration.global.RemoteContainersConfigurationBuilder;

@Singleton
public class InfinispanCacheFactory extends PersistentCacheBaseFactory
    implements LifecycleListener {
  private final EmbeddedCacheManager cacheManager;
  private final DynamicMap<Cache<?, ?>> cacheMap;
  private final List<InfinispanCacheImpl<?, ?>> caches;
  private final RemoteStore remoteStore;

  @Inject
  public InfinispanCacheFactory(
      MemoryCacheFactory memCacheFactory,
      @GerritServerConfig Config config,
      DynamicMap<Cache<?, ?>> cacheMap,
      @Nullable @InfinispanDir Path infinispanDir,
      RemoteStore remoteStore) {
    super(memCacheFactory, config, infinispanDir);
    this.cacheManager = initCacheManager();
    this.cacheMap = cacheMap;
    this.caches = new LinkedList<>();
    this.remoteStore = remoteStore;
  }

  @Override
  public void start() {}

  @Override
  public void stop() {
    cacheManager.stop(); // Stops all caches
    caches.clear();
    remoteStore.stop();
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
    if (diskLimit > 0) {
      remoteStore.initCacheIfNotPresent(def, getRemoteCacheConfiguration(diskLimit));
    }

    org.infinispan.Cache<byte[], byte[]> cache =
        cacheManager
            .administration()
            .withFlags(CacheContainerAdmin.AdminFlag.VOLATILE)
            .getOrCreateCache(def.name(), getEmbeddedCacheConfiguration(def, diskLimit));

    InfinispanCacheImpl<K, V> infinispanCacheImpl =
        new InfinispanCacheImpl<>(cache, loader, def, remoteStore);

    synchronized (caches) {
      caches.add(infinispanCacheImpl);
    }
    return infinispanCacheImpl;
  }

  @Nullable
  private EmbeddedCacheManager initCacheManager() {
    if (cacheDir == null) {
      return null;
    }
    GlobalConfigurationBuilder gcb = new GlobalConfigurationBuilder().nonClusteredDefault();
    gcb.cacheContainer().statistics(true);
    gcb.serialization().marshaller(IdentityMarshaller.INSTANCE);
    gcb.globalState().enable().persistentLocation(cacheDir.toAbsolutePath().toString());
    gcb.addModule(RemoteContainersConfigurationBuilder.class)
        .addRemoteContainer("shared-remote-container")
        .uri(
            String.format(
                "hotrod://%s:%s@%s:%s",
                getUser(), getPassword(), getRemoteHost(), getRemotePort()));
    return new DefaultCacheManager(
        new ConfigurationBuilderHolder(gcb.build().classLoader(), gcb), true);
  }

  private BasicConfiguration getRemoteCacheConfiguration(long diskLimit) {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.clustering().cacheMode(CacheMode.DIST_ASYNC).statistics().enable();
    cb.encoding().key().mediaType(MediaType.APPLICATION_OCTET_STREAM);
    cb.encoding().value().mediaType(MediaType.APPLICATION_PROTOSTREAM);
    cb.memory().maxSize(String.valueOf(diskLimit)).whenFull(EvictionStrategy.REMOVE);
    return cb.build();
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
      RemoteStoreConfigurationBuilder remoteStoreBuilder =
          cb.persistence()
              .addStore(RemoteStoreConfigurationBuilder.class)
              .remoteCacheName(def.name())
              .remoteCacheContainer("shared-remote-container");

      remoteStoreBuilder.shared(true).segmented(false);
      remoteStoreBuilder.async().enable();
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

  private String getUser() {
    String user = config.getString("cache", "infinispan", "user");
    return user != null ? user : "admin";
  }

  private String getPassword() {
    String password = config.getString("cache", "infinispan", "password");
    return password != null ? password : "password";
  }

  private String getRemoteHost() {
    String host = config.getString("cache", "infinispan", "remoteHost");
    return host != null ? host : "localhost";
  }

  private int getRemotePort() {
    return config.getInt("cache", "infinispan", "remotePort", 11222);
  }
}
