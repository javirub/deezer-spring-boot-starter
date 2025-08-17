package io.github.javirub.deezerspringbootstarter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Deezer API client.
 *
 * <p>This class defines all configurable properties for the Deezer Spring Boot Starter.
 * Properties can be configured in application.properties or application.yml using the 'deezer' prefix.
 *
 * <p>Example configuration:
 * <pre>
 * # Basic configuration
 * deezer.enabled=true
 * deezer.base-url=<a href="https://api.deezer.com">https://api.deezer.com</a>
 * deezer.client-type=REACTIVE
 * deezer.connection-timeout=5000
 * deezer.read-timeout=5000
 * deezer.max-retries=3
 * deezer.backoff-delay=300
 *
 * # Cache configuration
 * deezer.cache.enabled=true
 * deezer.cache.ttl=60
 * deezer.cache.max-size=1000
 * deezer.cache.cleanup-interval=60000
 * </pre>
 */
@Data
@ConfigurationProperties(prefix = "deezer")
public class DeezerProperties {

    /**
     * Default constructor for DeezerProperties.
     * Creates a new instance with default configuration values.
     */
    public DeezerProperties() {
        // Default constructor
    }

    /**
     * Whether to enable the Deezer starter.
     * Set to false to completely disable the Deezer client configuration.
     */
    private boolean enabled = true;

    /**
     * Base URL for the Deezer API.
     * Should not be changed unless using a proxy or different API endpoint.
     */
    private String baseUrl = "https://api.deezer.com";

    /**
     * The type of client to use for API calls.
     * REACTIVE uses WebClient (non-blocking, recommended for high concurrency).
     * BLOCKING uses RestTemplate (simpler, blocking calls).
     * @see ClientType
     */
    private ClientType clientType = ClientType.REACTIVE;

    /**
     * Connection timeout in milliseconds.
     * Maximum time to wait when establishing a connection to the Deezer API.
     */
    private int connectionTimeout = 5000;

    /**
     * Read timeout in milliseconds.
     * Maximum time to wait for a response from the Deezer API.
     */
    private int readTimeout = 5000;

    /**
     * Maximum number of retry attempts for failed requests.
     * Only applies to server errors (5xx) and network issues.
     */
    private int maxRetries = 3;

    /**
     * Initial backoff delay in milliseconds for retry attempts.
     * The delay increases exponentially with each retry.
     */
    private long backoffDelay = 300;

    /**
     * Cache configuration properties.
     * Controls caching behavior for API responses to improve performance.
     */
    private Cache cache = new Cache();

    /**
     * Cache configuration properties.
     * Helps reduce API calls and improve application performance.
     */
    @Data
    public static class Cache {
        
        /**
         * Default constructor for Cache configuration.
         */
        public Cache() {
            // Default constructor
        }
        
        /**
         * Whether to enable response caching.
         * When enabled, API responses are cached to reduce redundant requests.
         */
        private boolean enabled = true;

        /**
         * Cache time-to-live in seconds.
         * How long cached entries remain valid before being refreshed.
         */
        private int ttl = 60;

        /**
         * Maximum number of entries in the cache.
         * When exceeded, the least recently used entries are evicted.
         */
        private int maxSize = 1000;

        /**
         * Cache cleanup interval in milliseconds.
         * How often expired entries are removed from the cache.
         */
        private long cleanupInterval = 60000;
    }

    /**
     * Enumeration of available client types.
     */
    public enum ClientType {
        /**
         * Reactive client using WebClient (non-blocking).
         */
        REACTIVE,
        
        /**
         * Blocking client using RestTemplate.
         */
        BLOCKING
    }
}