Infinispan provides a more suitable alternative to H2 due to the following reasons:

- **Granular cache statistics:** Infinispan offers built-in JMX and Micrometer
  integration, providing per-cache stats such as hits, misses, entry count,
  memory usage, and eviction count. This facilitates easy integration with
  Prometheus and Grafana for comprehensive observability.

- **Per-cache policies:** Each cache can have its own TTL and max idle
  settings, as well as entry-count and memory-based eviction. Advanced
  algorithms like LRU and LIRS improve performance under heavy churn.

- **Flexible data structures and APIs:** Infinispan supports rich data models,
  query capabilities, and atomic cache operations, making it easier to
  implement features such as cache resizing, space management, and targeted
  deletions.

- **Persistence and durability:** Infinispan is designed for both in-memory
  and persistent caching scenarios, supporting seamless integration with disk
  storage, clustering, and transactional guarantees. Multiple store options are
  available, including file-based, RocksDB, and JDBC. Passivation allows
  evicted entries to be written to disk, and preload warms caches on restart,
  enabling true durability beyond simple recovery snapshots.

## Limitations

- **Configuration updates:** Changes to cache configuration in the Gerrit
  config (such as maxAge, diskLimit) will not be automatically reflected on
  the Infinispan server. Manual intervention is required: either drop and re-create
  the cache programmatically, or use the Infinispan CLI/REST API to update the
  configuration on the server side.
