package io.github.javirub.deezerspringbootstarter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * RestTemplate configuration for the Deezer API.
 */
@Configuration
@EnableConfigurationProperties(DeezerProperties.class)
public class DeezerRestTemplateConfig {

    /**
     * Creates and configures the RestTemplate for Deezer API.
     *
     * @param properties The Deezer configuration properties.
     * @return A configured RestTemplate.
     */
    @Bean
    public RestTemplate deezerRestTemplate(DeezerProperties properties) {
        return new RestTemplateBuilder()
                .rootUri(properties.getBaseUrl())
                .connectTimeout(Duration.ofMillis(properties.getConnectionTimeout()))
                .readTimeout(Duration.ofMillis(properties.getReadTimeout()))
                .interceptors(logRequestInterceptor())
                .build();
    }

    /**
     * Creates a request interceptor to log requests (useful for debugging).
     *
     * @return A ClientHttpRequestInterceptor that logs requests.
     */
    private ClientHttpRequestInterceptor logRequestInterceptor() {
        return (request, body, execution) -> {
            System.out.println("Request: " + request.getMethod() + " " + request.getURI());
            return execution.execute(request, body);
        };
    }
}