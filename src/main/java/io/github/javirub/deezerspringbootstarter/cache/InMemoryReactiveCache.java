package io.github.javirub.deezerspringbootstarter.cache;

import io.github.javirub.deezerspringbootstarter.properties.DeezerProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * In-memory implementation of ReactiveCache using ConcurrentHashMap.
 *
 * @param <K> The type of keys
 * @param <V> The type of values
 */
public class InMemoryReactiveCache<K, V> implements ReactiveCache<K, V> {

    private final Map<K, CacheEntry<V>> cache;
    private final Duration ttl;
    private final int maxSize;
    private final long cleanupInterval;
    private ScheduledExecutorService executor;

    /**
     * Creates a new InMemoryReactiveCache with configuration from properties.
     *
     * @param properties Deezer configuration properties
     */
    public InMemoryReactiveCache(DeezerProperties properties) {
        this.maxSize = properties.getCache().getMaxSize();
        this.cache = new ConcurrentHashMap<>(this.maxSize);
        this.ttl = Duration.ofSeconds(properties.getCache().getTtl());
        this.cleanupInterval = properties.getCache().getCleanupInterval();
    }

    @PostConstruct
    private void scheduleCleanup() {
        this.executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "deezer-cache-cleanup");
            thread.setDaemon(true);
            return thread;
        });

        executor.scheduleWithFixedDelay(
                this::cleanExpiredEntries,
                cleanupInterval,
                cleanupInterval,
                TimeUnit.MILLISECONDS
        );
    }

    /**
     * Shuts down the cleanup executor when the bean is destroyed.
     */
    @PreDestroy
    private void shutdownCleanup() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
            try {
                // Wait up to 5 seconds for existing tasks to terminate
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public Mono<V> get(K key, Function<K, Mono<V>> valueLoader) {
        return Mono.justOrEmpty(getCacheEntry(key))
                .filter(entry -> !entry.isExpired())
                .map(CacheEntry::value)
                .switchIfEmpty(valueLoader.apply(key)
                        .doOnNext(value -> {
                            // Check if cache is full and evict if necessary
                            if (cache.size() >= maxSize && !cache.containsKey(key)) {
                                evictLeastRecentlyUsed();
                            }
                            cache.put(key, CacheEntry.of(value, ttl));
                        })
                        .doOnError(error -> cache.remove(key)));
    }

    /**
     * Evicts the least recently used entries to make space for new ones.
     * Removes expired entries first, then oldest entries if needed.
     */
    private void evictLeastRecentlyUsed() {
        // Remove expired entries first
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired());

        // If needed, remove oldest entries
        while (cache.size() >= maxSize) {
            cache.entrySet().stream()
                    .min(Comparator.comparingLong(e -> e.getValue().expirationTime))
                    .ifPresent(oldestEntry -> cache.remove(oldestEntry.getKey()));
        }
    }

    @Override
    public Mono<Void> invalidate(K key) {
        return Mono.fromRunnable(() -> cache.remove(key));
    }

    @Override
    public Mono<Void> invalidateAll() {
        return Mono.fromRunnable(cache::clear);
    }

    /**
     * Scheduled task to clean expired entries.
     * Runs every minute by default.
     */
    @Scheduled(fixedDelayString = "${deezer.cache.cleanup-interval:60000}")
    public void cleanExpiredEntries() {
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    /**
     * Gets a cache entry if it exists.
     *
     * @param key The cache key
     * @return The cache entry, or null if not found
     */
    private CacheEntry<V> getCacheEntry(K key) {
        return cache.get(key);
    }

    /**
     * Cache entry with expiration time.
     *
     * @param <T> The type of the cached value
     * @param value The cached value
     * @param expirationTime The expiration timestamp in milliseconds
     */
    private record CacheEntry<T>(T value, long expirationTime) {

        /**
         * Creates a new cache entry with TTL.
         *
         * @param value The value to cache
         * @param ttl The time-to-live duration
         * @return A new cache entry
         */
        public static <T> CacheEntry<T> of(T value, Duration ttl) {
            return new CacheEntry<>(value, System.currentTimeMillis() + ttl.toMillis());
        }

        /**
         * Checks if the entry is expired.
         *
         * @return true if expired, false otherwise
         */
        public boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }
}