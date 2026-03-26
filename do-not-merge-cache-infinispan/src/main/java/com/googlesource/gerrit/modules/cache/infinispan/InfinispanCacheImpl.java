package com.googlesource.gerrit.modules.cache.infinispan;

import com.google.common.cache.AbstractLoadingCache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.flogger.FluentLogger;
import com.google.gerrit.server.cache.PersistentCache;
import com.google.gerrit.server.cache.PersistentCacheDef;
import com.google.inject.Inject;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import org.infinispan.Cache;
import org.infinispan.stats.Stats;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

public class InfinispanCacheImpl<K, V> extends AbstractLoadingCache<K, V>
    implements PersistentCache {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  private final Cache<byte[], byte[]> cache;
  private final CacheLoader<K, V> loader;
  private final PersistentCacheDef<K, V> def;
  private final RemoteStore remoteStore;

  @Inject
  public InfinispanCacheImpl(
      Cache<byte[], byte[]> cache,
      @Nullable CacheLoader<K, V> loader,
      PersistentCacheDef<K, V> def,
      RemoteStore remoteStore) {
    this.cache = cache;
    this.loader = loader;
    this.def = def;
    this.remoteStore = remoteStore;
  }

  public String getCacheName() {
    return cache.getName();
  }

  @Override
  public V get(K key) throws ExecutionException {
    return getWithLoader(key, null);
  }

  @SuppressWarnings("unchecked")
  @Override
  public @Nullable V getIfPresent(@Nullable Object objKey) {
    if (objKey == null) {
      return null;
    }
    try {
      return get((K) objKey);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public V get(K key, @Nullable Callable<? extends V> valueLoader) throws ExecutionException {
    return getWithLoader(key, valueLoader);
  }

  @Override
  public void put(K key, V val) {
    byte[] serializedKey = def.keySerializer().serialize(key);
    byte[] serializedValue = def.valueSerializer().serialize(val);
    cache.put(serializedKey, serializedValue);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void invalidate(@Nullable Object objKey) {
    if (objKey == null) {
      return;
    }
    byte[] serializedKey = def.keySerializer().serialize((K) objKey);
    cache.remove(serializedKey);
  }

  @Override
  public void invalidateAll() {
    cache.clear();
  }

  @Override
  public long size() {
    return cache.size();
  }

  @NullMarked
  @Override
  public CacheStats stats() {
    Stats stats = cache.getAdvancedCache().getStats();
    return new CacheStats(stats.getHits(), stats.getMisses(), 0, 0, 0, stats.getEvictions());
  }

  @Override
  public DiskStats diskStats() {
    return remoteStore.getStats(cache);
  }

  public void stop() {
    cache.stop();
  }

  private V getWithLoader(K key, @Nullable Callable<? extends V> valueLoader) {
    byte[] serializedKey = def.keySerializer().serialize(key);
    byte[] serializedValue = cache.get(serializedKey);

    if (serializedValue != null) {
      return def.valueSerializer().deserialize(serializedValue);
    }
    V value;
    try {
      if (valueLoader != null) {
        value = valueLoader.call();
      } else if (loader != null) {
        value = loader.load(key);
      } else {
        throw new UnsupportedOperationException(
            String.format("Could not load value for %s without any loader", key));
      }
    } catch (Exception e) {
      logger.atSevere().withCause(e).log(
          "Cache: %s, Failed to load value for key: %s", getCacheName(), key);
      throw new RuntimeException("Failed to load value for key: " + key, e);
    }
    cache.put(serializedKey, def.valueSerializer().serialize(value));
    return value;
  }
}
