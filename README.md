# Deezer Spring Boot Starter

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Version](https://img.shields.io/badge/version-0.0.1--SNAPSHOT-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-green)
![Java](https://img.shields.io/badge/Java-21-orange)

> ⚠️ **Work in Progress**: This project is currently under development and is not yet finalized. Features and APIs may change without notice.

A Spring Boot Starter that provides seamless integration with the Deezer API. This starter offers both reactive and blocking HTTP clients with intelligent caching, comprehensive configuration options, and automatic Spring Boot integration.

## Features

### 🚀 **Dual Client Support**
- **Reactive Client**: Non-blocking WebClient implementation for high-concurrency applications
- **Blocking Client**: Traditional RestTemplate implementation for simpler use cases

### 🎵 **Complete Deezer API Coverage**
- Albums, Artists, Tracks, Playlists
- Genres, Radios, Users, Editorials
- Advanced search functionality with multiple criteria
- Comprehensive domain models for all API responses

### ⚡ **Performance & Resilience**
- Intelligent caching with configurable TTL
- Automatic retry mechanisms with exponential backoff
- Connection pooling and timeout management
- Error handling and graceful degradation

### 🔧 **Spring Boot Integration**
- Auto-configuration out of the box
- Comprehensive configuration properties
- IDE autocomplete support for all properties
- Conditional bean creation based on configuration

## Installation

Add the following dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.javirub:deezer-spring-boot-starter:0.0.1-SNAPSHOT")
}
```

Or for Maven users, add to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.javirub</groupId>
    <artifactId>deezer-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Quick Start

### 1. Basic Configuration

Add to your `application.properties`:

```properties
# Basic configuration (optional - these are the defaults)
deezer.enabled=true
deezer.client-type=REACTIVE
deezer.base-url=https://api.deezer.com

# Timeouts
deezer.connection-timeout=5000
deezer.read-timeout=5000

# Retry configuration
deezer.max-retries=3
deezer.backoff-delay=300

# Cache configuration
deezer.cache.enabled=true
deezer.cache.ttl=60
deezer.cache.max-size=1000
deezer.cache.cleanup-interval=60000
```

### 2. Using the Reactive Client

```java
@Service
public class MusicService {
    
    @Autowired
    private ReactiveDeezerClient deezerClient;
    
    public Mono<Album> getAlbum(Long albumId) {
        return deezerClient.getAlbumById(albumId);
    }
    
    public Mono<Search> searchTracks(String query) {
        return deezerClient.search(query);
    }
    
    public Mono<Search> advancedSearch() {
        SearchOptions options = SearchOptions.builder()
            .artist("Daft Punk")
            .album("Discovery")
            .durationMin(180)
            .order("RATING_DESC")
            .build();
            
        return deezerClient.search(options);
    }
}
```

### 3. Using the Blocking Client

Configure for blocking client:

```properties
deezer.client-type=BLOCKING
```

```java
@Service
public class MusicService {
    
    @Autowired
    private DeezerClient deezerClient;
    
    public Album getAlbum(Long albumId) {
        return deezerClient.getAlbumByIdBlocking(albumId);
    }
    
    public Search searchTracks(String query) {
        return deezerClient.searchBlocking(query);
    }
}
```

## Configuration Reference

### Client Configuration

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `deezer.enabled` | Boolean | `true` | Enable/disable the Deezer starter |
| `deezer.client-type` | Enum | `REACTIVE` | Client type: `REACTIVE` or `BLOCKING` |
| `deezer.base-url` | String | `https://api.deezer.com` | Deezer API base URL |
| `deezer.connection-timeout` | Integer | `5000` | Connection timeout in milliseconds |
| `deezer.read-timeout` | Integer | `5000` | Read timeout in milliseconds |
| `deezer.max-retries` | Integer | `3` | Maximum retry attempts |
| `deezer.backoff-delay` | Long | `300` | Initial backoff delay in milliseconds |

### Cache Configuration

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `deezer.cache.enabled` | Boolean | `true` | Enable response caching |
| `deezer.cache.ttl` | Integer | `60` | Cache TTL in seconds |
| `deezer.cache.max-size` | Integer | `1000` | Maximum cache entries |
| `deezer.cache.cleanup-interval` | Long | `60000` | Cache cleanup interval in milliseconds |

## API Coverage

### Resource Retrieval
- `getAlbumById(Long id)` - Get album details
- `getArtistById(Long id)` - Get artist information  
- `getTrackById(Long id)` - Get track details
- `getPlaylistById(Long id)` - Get playlist information
- `getUserById(Long id)` - Get user profile
- `getGenreById(Long id)` - Get genre details
- `getRadioById(Long id)` - Get radio station info
- `getEditorialById(Long id)` - Get editorial content

### Search Functionality

#### Basic Search
```java
deezerClient.search("eminem");
```

#### Advanced Search with Options
```java
SearchOptions options = SearchOptions.builder()
    .query("hip hop")           // Basic query
    .artist("Eminem")           // Filter by artist
    .album("The Marshall Mathers LP") // Filter by album
    .track("The Real Slim Shady")     // Filter by track
    .label("Aftermath")         // Filter by label
    .durationMin(180)          // Minimum duration (seconds)
    .durationMax(300)          // Maximum duration (seconds)
    .bpmMin(120)               // Minimum BPM
    .bpmMax(140)               // Maximum BPM
    .strict(true)              // Disable fuzzy search
    .order("RATING_DESC")      // Sort order
    .build();

deezerClient.search(options);
```

#### Available Sort Orders
- `RANKING` - Deezer ranking
- `TRACK_ASC` / `TRACK_DESC` - By track name
- `ARTIST_ASC` / `ARTIST_DESC` - By artist name
- `ALBUM_ASC` / `ALBUM_DESC` - By album name
- `RATING_ASC` / `RATING_DESC` - By rating
- `DURATION_ASC` / `DURATION_DESC` - By duration

## Architecture

### Client Types

#### Reactive Client (`REACTIVE`)
- Built on Spring WebFlux WebClient
- Non-blocking I/O operations
- Recommended for high-concurrency applications
- Perfect for microservices and reactive applications
- Returns `Mono<T>` and `Flux<T>` types

#### Blocking Client (`BLOCKING`)
- Built on Spring RestTemplate  
- Traditional blocking HTTP calls
- Simpler programming model
- Suitable for traditional Spring MVC applications
- Returns plain Java objects

### Caching Strategy
- Intelligent in-memory caching with configurable TTL
- Cache keys based on endpoint and parameters
- Automatic cache cleanup of expired entries
- Configurable cache size limits
- Per-request cache bypass capabilities

### Error Handling
- Automatic retry with exponential backoff
- Graceful handling of 4xx and 5xx HTTP errors
- Timeout management with configurable limits
- Circuit breaker pattern for resilience

## Examples

### Complete Spring Boot Application

```java
@SpringBootApplication
public class MusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }
}

@RestController
@RequestMapping("/api/music")
public class MusicController {
    
    @Autowired
    private ReactiveDeezerClient deezerClient;
    
    @GetMapping("/album/{id}")
    public Mono<Album> getAlbum(@PathVariable Long id) {
        return deezerClient.getAlbumById(id);
    }
    
    @GetMapping("/search")
    public Mono<Search> search(@RequestParam String q) {
        return deezerClient.search(q);
    }
    
    @GetMapping("/artist/{id}/albums")
    public Mono<Artist> getArtistWithAlbums(@PathVariable Long id) {
        return deezerClient.getArtistById(id)
            .doOnNext(artist -> {
                // Artist data is automatically cached
                log.info("Retrieved artist: {}", artist.getName());
            });
    }
}
```

### Custom Configuration

```java
@Configuration
public class DeezerConfig {
    
    @Bean
    @ConditionalOnProperty("deezer.custom-cache")
    public ReactiveCache<String, Object> customDeezerCache() {
        // Provide custom cache implementation if needed
        return new CustomReactiveCacheImpl();
    }
}
```

## Development Status

This project is currently in active development. The following features are planned or in progress:

### ✅ Completed
- Basic API client implementation
- Reactive and blocking client support
- Comprehensive configuration system
- Caching mechanism
- Search functionality with advanced options
- Auto-configuration for Spring Boot
- Complete domain model coverage

### 🚧 In Progress
- Performance optimizations
- Additional error handling scenarios
- Comprehensive test coverage
- Documentation improvements

### 📋 Planned
- Rate limiting support
- Metrics and monitoring integration
- OAuth authentication support
- Additional caching backends (Redis, Hazelcast)
- Kotlin DSL support

## Contributing

We welcome contributions! Please note that this project is still in early development.

### Getting Started
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Setup
```bash
git clone https://github.com/javirub/deezer-spring-boot-starter.git
cd deezer-spring-boot-starter
./gradlew build
```

## Requirements

- Java 21 or higher
- Spring Boot 3.5.4 or compatible
- Gradle 7.0+ or Maven 3.6+

## License

This project is licensed under the MIT License

## Acknowledgments

- [Deezer API](https://developers.deezer.com/) for providing the excellent music API
- [Spring Boot](https://spring.io/projects/spring-boot) team for the fantastic framework
- [Project Reactor](https://projectreactor.io/) for reactive programming support

---

⚠️ **Disclaimer**: This is an unofficial library and is not affiliated with Deezer. Please comply with Deezer's API terms of service when using this library.