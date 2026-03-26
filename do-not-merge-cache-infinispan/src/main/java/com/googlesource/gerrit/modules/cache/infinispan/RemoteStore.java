package com.googlesource.gerrit.modules.cache.infinispan;

import com.google.gerrit.server.cache.PersistentCache;
import com.google.gerrit.server.cache.PersistentCacheDef;
import com.google.gerrit.server.config.GerritServerConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.jgit.lib.Config;
import org.infinispan.Cache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.ServerStatistics;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.commons.configuration.BasicConfiguration;
import org.infinispan.commons.marshall.IdentityMarshaller;
import org.infinispan.stats.Stats;

@Singleton
public class RemoteStore {
  protected final Config config;
  private final RemoteCacheManager cacheManager;

  @Inject
  public RemoteStore(@GerritServerConfig Config config) {
    this.config = config;
    this.cacheManager = initRemoteCacheManager();
  }

  public <K, V> void initCacheIfNotPresent(
      PersistentCacheDef<K, V> def, BasicConfiguration configuration) {
    cacheManager
        .administration()
        .withFlags(CacheContainerAdmin.AdminFlag.VOLATILE)
        .getOrCreateCache(def.name(), configuration);
  }

  public PersistentCache.DiskStats getStats(Cache<byte[], byte[]> cache) {
    Stats memoryStats = cache.getAdvancedCache().getStats();
    long entriesInMemory = memoryStats.getApproximateEntries();
    long totalMemoryUsed = memoryStats.getDataMemoryUsed();
    long approxEntrySize = entriesInMemory > 0 ? Math.abs(totalMemoryUsed / entriesInMemory) : 0;

    ServerStatistics serverStatistics = cacheManager.getCache(cache.getName()).serverStatistics();
    long approxEntries =
        Long.parseLong(serverStatistics.getStatistic(ServerStatistics.APPROXIMATE_ENTRIES));
    long approxSize = approxEntrySize * approxEntries;
    long hits = Long.parseLong(serverStatistics.getStatistic(ServerStatistics.HITS));
    long misses = Long.parseLong(serverStatistics.getStatistic(ServerStatistics.MISSES));
    long removeHits = Long.parseLong(serverStatistics.getStatistic(ServerStatistics.REMOVE_HITS));
    return new PersistentCache.DiskStats(approxEntries, approxSize, hits, misses, removeHits);
  }

  public void stop() {
    if (cacheManager != null) {
      cacheManager.stop();
    }
  }

  private RemoteCacheManager initRemoteCacheManager() {
    RemoteCacheManager cacheManager;
    try {
      ConfigurationBuilder configBuilder = connectionConfig();
      cacheManager = new RemoteCacheManager(configBuilder.build());
    } catch (Exception ex) {
      throw new RuntimeException("Unable to connect to the infinispan server", ex);
    }
    return cacheManager;
  }

  private ConfigurationBuilder connectionConfig() {
    return new ConfigurationBuilder()
        .security()
        .authentication()
        .username(getUser())
        .password(getPassword())
        .marshaller(IdentityMarshaller.INSTANCE);
  }

  private String getUser() {
    String user = config.getString("cache", "infinispan", "user");
    return user != null ? user : "admin";
  }

  private String getPassword() {
    String password = config.getString("cache", "infinispan", "password");
    return password != null ? password : "password";
  }
}
