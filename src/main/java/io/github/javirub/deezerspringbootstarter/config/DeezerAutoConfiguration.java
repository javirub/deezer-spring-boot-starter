package io.github.javirub.deezerspringbootstarter.config;

import io.github.javirub.deezerspringbootstarter.DeezerClient;
import io.github.javirub.deezerspringbootstarter.ReactiveDeezerClient;
import io.github.javirub.deezerspringbootstarter.cache.InMemoryReactiveCache;
import io.github.javirub.deezerspringbootstarter.cache.ReactiveCache;
import io.github.javirub.deezerspringbootstarter.client.DeezerClientImpl;
import io.github.javirub.deezerspringbootstarter.client.ReactiveDeezerClientImpl;
import io.github.javirub.deezerspringbootstarter.properties.DeezerProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Autoconfiguration for the Deezer API client.
 * This class automatically configures all the necessary beans for the Deezer client
 * when the starter is included in a project.
 */
@AutoConfiguration
@EnableConfigurationProperties(DeezerProperties.class)
@EnableScheduling
@Import({DeezerWebClientConfig.class, DeezerRestTemplateConfig.class})
@ConditionalOnProperty(prefix = "deezer", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DeezerAutoConfiguration {

    /**
     * Default constructor for DeezerAutoConfiguration.
     */
    public DeezerAutoConfiguration() {
        // Default constructor
    }

    /**
     * Creates a reactive cache for the Deezer client.
     *
     * @param properties The Deezer configuration properties
     * @return A reactive cache
     */
    @Bean
    @ConditionalOnMissingBean
    public ReactiveCache<String, Object> deezerCache(DeezerProperties properties) {
        return new InMemoryReactiveCache<>(properties);
    }

    /**
     * Creates the reactive Deezer client when clientType is REACTIVE.
     *
     * @param deezerWebClient The WebClient for making API requests
     * @param deezerCache The reactive cache for caching API responses
     * @param properties The Deezer configuration properties
     * @return A configured reactive Deezer client
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "deezer", name = "client-type", havingValue = "REACTIVE", matchIfMissing = true)
    public ReactiveDeezerClient reactiveDeezerClient(WebClient deezerWebClient, ReactiveCache<String, Object> deezerCache, DeezerProperties properties) {
        return new ReactiveDeezerClientImpl(deezerWebClient, deezerCache, properties);
    }

    /**
     * Creates the blocking Deezer client when clientType is BLOCKING.
     *
     * @param deezerRestTemplate The RestTemplate for making API requests
     * @param properties The Deezer configuration properties
     * @return A configured blocking Deezer client
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "deezer", name = "client-type", havingValue = "BLOCKING")
    public DeezerClient blockingDeezerClient(RestTemplate deezerRestTemplate, DeezerProperties properties) {
        return new DeezerClientImpl(deezerRestTemplate, properties.getBaseUrl());
    }
}