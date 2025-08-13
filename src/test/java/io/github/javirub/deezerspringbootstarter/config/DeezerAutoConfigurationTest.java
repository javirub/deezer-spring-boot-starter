package io.github.javirub.deezerspringbootstarter.config;

import io.github.javirub.deezerspringbootstarter.client.ReactiveDeezerClientImpl;
import io.github.javirub.deezerspringbootstarter.cache.ReactiveCache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for DeezerAutoConfiguration.
 * Verifies that all the necessary beans are created when the auto-configuration is applied.
 */
class DeezerAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(DeezerAutoConfiguration.class));

    @Test
    void shouldCreateDeezerClient_whenAutoConfigurationIsApplied() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(ReactiveDeezerClientImpl.class);
            assertThat(context).hasSingleBean(ReactiveCache.class);
            assertThat(context).hasSingleBean(WebClient.class);
            assertThat(context).hasSingleBean(DeezerProperties.class);
        });
    }

    @Test
    void shouldUseDeezerProperties_whenPropertiesAreConfigured() {
        contextRunner
                .withPropertyValues(
                        "deezer.base-url=https://test-api.deezer.com",
                        "deezer.connection-timeout=3000",
                        "deezer.read-timeout=3000",
                        "deezer.max-retries=2",
                        "deezer.cache.enabled=true",
                        "deezer.cache.ttl=120",
                        "deezer.cache.max-size=500"
                )
                .run(context -> {
                    DeezerProperties properties = context.getBean(DeezerProperties.class);
                    
                    assertThat(properties.getBaseUrl()).isEqualTo("https://test-api.deezer.com");
                    assertThat(properties.getConnectionTimeout()).isEqualTo(3000);
                    assertThat(properties.getReadTimeout()).isEqualTo(3000);
                    assertThat(properties.getMaxRetries()).isEqualTo(2);
                    assertThat(properties.getCache().isEnabled()).isTrue();
                    assertThat(properties.getCache().getTtl()).isEqualTo(120);
                    assertThat(properties.getCache().getMaxSize()).isEqualTo(500);
                });
    }

    @Test
    void shouldNotCreateDeezerClient_whenDisabled() {
        contextRunner
                .withPropertyValues("deezer.enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(ReactiveDeezerClientImpl.class);
                    assertThat(context).doesNotHaveBean(ReactiveCache.class);
                    assertThat(context).doesNotHaveBean(WebClient.class);
                });
    }
}