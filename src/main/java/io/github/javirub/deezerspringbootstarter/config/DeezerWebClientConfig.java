package io.github.javirub.deezerspringbootstarter.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Configuration class for creating and configuring WebClient instances for the Deezer API.
 * 
 * <p>This configuration provides a properly configured WebClient bean that includes:
 * <ul>
 *   <li>Connection and read timeout settings from DeezerProperties</li>
 *   <li>Optimized HTTP client with Netty for reactive operations</li>
 *   <li>Increased buffer size to handle large API responses</li>
 *   <li>Request logging for debugging purposes</li>
 * </ul>
 * 
 * <p>The WebClient is primarily used by the reactive Deezer client implementation
 * to make non-blocking HTTP requests to the Deezer API.
 */
@Configuration
@EnableConfigurationProperties(DeezerProperties.class)
public class DeezerWebClientConfig {

    /**
     * Default constructor for DeezerWebClientConfig.
     */
    public DeezerWebClientConfig() {
        // Default constructor
    }

    /**
     * Creates and configures the WebClient for Deezer API.
     *
     * @param properties The Deezer configuration properties.
     * @return A configured WebClient.Builder.
     */
    @Bean
    public WebClient deezerWebClient(DeezerProperties properties) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectionTimeout())
                .responseTimeout(Duration.ofMillis(properties.getReadTimeout()))
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout(), TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(properties.getReadTimeout(), TimeUnit.MILLISECONDS)));

        // Increase the default buffer size to handle larger responses
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)) // 2MB
                .build();

        return WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .filter(logRequest())
                .build();
    }

    /**
     * Creates a filter function to log requests (useful for debugging).
     *
     * @return An ExchangeFilterFunction that logs requests.
     */
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
            return Mono.just(clientRequest);
        });
    }
}