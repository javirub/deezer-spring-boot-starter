package io.github.javirub.deezerspringbootstarter.config;

import io.github.javirub.deezerspringbootstarter.properties.DeezerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * Configuration class for creating and configuring RestTemplate instances for the Deezer API.
 * 
 * <p>This configuration provides a properly configured RestTemplate bean that includes:
 * <ul>
 *   <li>Base URL configuration pointing to the Deezer API</li>
 *   <li>Connection and read timeout settings from DeezerProperties</li>
 *   <li>Request logging interceptor for debugging purposes</li>
 * </ul>
 * 
 * <p>The RestTemplate is used by the blocking Deezer client implementation
 * to make synchronous HTTP requests to the Deezer API when the client type
 * is set to BLOCKING in the configuration.
 */
@Configuration
@EnableConfigurationProperties(DeezerProperties.class)
public class DeezerRestTemplateConfig {

    /**
     * Default constructor for DeezerRestTemplateConfig.
     */
    public DeezerRestTemplateConfig() {
    }

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