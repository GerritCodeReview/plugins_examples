package com.googlesource.gerrit.modules.cache.infinispan;

import com.google.common.cache.AbstractLoadingCache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.flogger.FluentLogger;
import com.google.gerrit.server.cache.PersistentCache;
import com.google.gerrit.server.cache.PersistentCacheDef;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.LongAdder;
import org.infinispan.AdvancedCache;
import org.infinispan.Cache;
import org.infinispan.context.Flag;
import org.infinispan.stats.Stats;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

public class InfinispanCacheImpl<K, V> extends AbstractLoadingCache<K, V>
    implements PersistentCache {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  private final Cache<byte[], byte[]> cache;
  private final CacheLoader<K, V> loader;
  private final PersistentCacheDef<K, V> def;

  private final long diskLimit;
  private final int maxVictimsPerRun;

  @Inject
  public InfinispanCacheImpl(
      Cache<byte[], byte[]> cache,
      @Nullable CacheLoader<K, V> loader,
      PersistentCacheDef<K, V> def,
      long diskLimit,
      int maxVictimsPerRun) {
    this.cache = cache;
    this.loader = loader;
    this.def = def;
    this.diskLimit = diskLimit;
    this.maxVictimsPerRun = Math.max(1, maxVictimsPerRun);
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
    Stats stats = cache.getAdvancedCache().getStats();
    return new DiskStats(
        stats.getApproximateEntries(),
        stats.getDataMemoryUsed(),
        stats.getHits(),
        stats.getMisses(),
        stats.getEvictions());
  }

  public void stop() {
    cache.stop();
  }

  public int prune() {
    AdvancedCache<byte[], byte[]> cacheView =
        cache
            .getAdvancedCache()
            .withFlags(
                List.of(
                    Flag.CACHE_MODE_LOCAL,
                    Flag.IGNORE_RETURN_VALUES,
                    Flag.SKIP_LISTENER_NOTIFICATION));

    final LongAdder totalBytes = new LongAdder();
    final PriorityQueue<Candidate> pruneCandidates =
        new PriorityQueue<>(
            maxVictimsPerRun + 1, Comparator.comparingLong((Candidate c) -> c.created).reversed());

    cacheView
        .cacheEntrySet()
        .forEach(
            entry -> {
              byte[] k = entry.getKey();
              byte[] v = entry.getValue();
              if (v == null) {
                return;
              }
              int sizeBytes = k.length + v.length;
              totalBytes.add(sizeBytes);

              long created = entry.getCreated();
              if (created <= 0) {
                created = Long.MIN_VALUE;
              }

              pruneCandidates.offer(new Candidate(k, sizeBytes, created));
              if (pruneCandidates.size() > maxVictimsPerRun) {
                pruneCandidates.poll();
              }
            });

    long total = totalBytes.sum();
    if (total <= diskLimit || pruneCandidates.isEmpty()) {
      return 0;
    }

    List<Candidate> sorted = new ArrayList<>();
    while (!pruneCandidates.isEmpty()) {
      sorted.add(pruneCandidates.poll());
    }

    long bytesToShed = total - diskLimit;
    int removed = 0;

    // Heap polls in newest-first order; iterate in reverse to remove oldest entries first
    for (int i = sorted.size() - 1; i >= 0; i--) {
      Candidate c = sorted.get(i);
      if (bytesToShed <= 0) {
        break;
      }
      cacheView.remove(c.key);
      bytesToShed -= c.sizeBytes;
      removed++;
    }
    logger.atInfo().log(
        "Pruned %d entries, freed %d bytes from cache %s",
        removed, (total - diskLimit) - bytesToShed, cache.getName());
    return removed;
  }

  private record Candidate(byte[] key, int sizeBytes, long created) {}

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
      throw new RuntimeException("Failed to load value for key: " + key, e);
    }
    cache.put(serializedKey, def.valueSerializer().serialize(value));
    return value;
  }
}
