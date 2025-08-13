package io.github.javirub.deezerspringbootstarter.cache;

import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Interface for a reactive cache.
 * 
 * @param <K> The type of keys
 * @param <V> The type of values
 */
public interface ReactiveCache<K, V> {
    
    /**
     * Gets a value from the cache, or computes it using the provided function if it's not in the cache.
     * 
     * @param key The cache key
     * @param valueLoader Function to compute the value if not found in the cache
     * @return A Mono that emits the cached or computed value
     */
    Mono<V> get(K key, Function<K, Mono<V>> valueLoader);
    
    /**
     * Invalidates a cache entry.
     * 
     * @param key The key to invalidate
     * @return A Mono that completes when the entry is invalidated
     */
    Mono<Void> invalidate(K key);
    
    /**
     * Invalidates all cache entries.
     * 
     * @return A Mono that completes when all entries are invalidated
     */
    Mono<Void> invalidateAll();
}