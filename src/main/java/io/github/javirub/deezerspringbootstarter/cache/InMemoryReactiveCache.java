package io.github.javirub.deezerspringbootstarter.cache;

import io.github.javirub.deezerspringbootstarter.config.DeezerProperties;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    /**
     * Creates a new InMemoryReactiveCache with configuration from properties.
     *
     * @param properties Deezer configuration properties
     */
    public InMemoryReactiveCache(DeezerProperties properties) {
        this.cache = new ConcurrentHashMap<>(properties.getCache().getMaxSize());
        this.ttl = Duration.ofSeconds(properties.getCache().getTtl());
        this.maxSize = properties.getCache().getMaxSize();
    }

    @Override
    public Mono<V> get(K key, Function<K, Mono<V>> valueLoader) {
        return Mono.justOrEmpty(getCacheEntry(key))
                .filter(entry -> !entry.isExpired())
                .map(CacheEntry::getValue)
                .switchIfEmpty(valueLoader.apply(key)
                        .doOnNext(value -> cache.put(key, new CacheEntry<>(value, ttl)))
                        .doOnError(error -> cache.remove(key)));
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
     * Cache entry with expiration.
     *
     * @param <T> The type of the cached value
     */
    private static class CacheEntry<T> {
        private final T value;
        private final long expirationTime;

        /**
         * Creates a new cache entry.
         *
         * @param value The value to cache
         * @param ttl The time-to-live duration
         */
        public CacheEntry(T value, Duration ttl) {
            this.value = value;
            this.expirationTime = System.currentTimeMillis() + ttl.toMillis();
        }

        /**
         * Gets the cached value.
         *
         * @return The cached value
         */
        public T getValue() {
            return value;
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