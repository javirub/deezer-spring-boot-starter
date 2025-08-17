# Deezer Spring Boot Starter - Project Guidelines

## Project Overview

This is a **Spring Boot Starter** that provides easy integration with the Deezer API for Java/Spring applications. It offers both **reactive** (non-blocking) and **blocking** client implementations to interact with various Deezer resources including tracks, albums, artists, playlists, users, and more.

### Key Features
- **Dual client support**: Reactive (WebClient) and Blocking (RestTemplate) implementations
- **Advanced search capabilities**: Complex query building with filters for artist, album, duration, BPM, etc.
- **Intelligent caching**: In-memory cache with TTL, LRU eviction, and configurable cleanup
- **Auto-configuration**: Spring Boot starter with comprehensive configuration properties
- **Comprehensive domain models**: Complete entity mapping for Deezer API responses
- **Retry mechanism**: Built-in retry logic with exponential backoff for resilient API calls

## Project Structure

```
src/
├── main/java/io/github/javirub/deezerspringbootstarter/
│   ├── DeezerClient.java                     # Blocking client interface
│   ├── ReactiveDeezerClient.java             # Reactive client interface
│   ├── SearchOptions.java                    # Advanced search query builder
│   ├── cache/
│   │   ├── ReactiveCache.java                # Cache interface
│   │   └── InMemoryReactiveCache.java        # In-memory cache implementation
│   ├── client/
│   │   ├── DeezerClientImpl.java             # Blocking client implementation
│   │   └── ReactiveDeezerClientImpl.java     # Reactive client implementation
│   ├── config/
│   │   ├── DeezerAutoConfiguration.java      # Spring Boot auto-configuration
│   │   ├── DeezerProperties.java             # Configuration properties
│   │   ├── DeezerRestTemplateConfig.java     # RestTemplate configuration
│   │   └── DeezerWebClientConfig.java        # WebClient configuration
│   └── domain/                               # Deezer API entity models
│       ├── Album.java, Artist.java, Track.java, etc.
└── test/java/
    └── config/
        └── DeezerAutoConfigurationTest.java  # Auto-configuration tests
```

## Testing Guidelines

### Running Tests
- **Standard test execution**: `.\gradlew.bat test`
- **Integration tests**: `.\gradlew.bat integrationTest` (if available)
- **All verification**: `.\gradlew.bat check` (includes tests, linting, etc.)

### Test Requirements
- **Always run tests** after making changes to verify correctness
- **Write unit tests** for new functionality, especially business logic in SearchOptions and cache implementations
- **Mock external dependencies** using appropriate testing frameworks
- **Test both reactive and blocking implementations** when modifying client code

## Build Process

### Build Commands
- **Full build**: `.\gradlew.bat build`
- **Clean build**: `.\gradlew.bat clean build`
- **Generate JavaDoc**: `.\gradlew.bat javadoc`
- **Check build without tests**: `.\gradlew.bat assemble`

### Build Verification
- **Always build the project** before submitting changes to ensure compilation success
- **Verify JavaDoc generation** with no warnings: `.\gradlew.bat javadoc`
- **Check for dependency issues**: `.\gradlew.bat dependencies`

## Code Style and Documentation Requirements

### JavaDoc and Comments
- **Language**: All JavaDoc and code comments MUST be in **English**
- **Coverage**: Every public class, method, and field must have comprehensive JavaDoc
- **Format**: Follow standard JavaDoc conventions with proper `@param`, `@return`, `@throws` tags
- **Links**: Use `{@link}`, `{@code}`, and `<a href="">` tags appropriately for references
- **Examples**: Include usage examples in class-level JavaDoc where helpful

### Code Style
- **Lombok usage**: Continue using `@Builder`, `@Getter`, `@Data` for reducing boilerplate
- **Immutability**: Prefer immutable objects where possible (final fields, builder pattern)
- **Null safety**: Handle null values appropriately, document nullable parameters
- **Reactive patterns**: Use proper Mono/Flux patterns in reactive implementations
- **Error handling**: Implement proper exception handling and error propagation

### Architecture Patterns
- **Builder pattern**: Use for complex objects like SearchOptions
- **Cache pattern**: Implement proper cache eviction and TTL handling
- **Configuration**: Use Spring Boot configuration properties pattern
- **Separation of concerns**: Keep interfaces, implementations, and configurations separate

## Development Workflow

### Before Making Changes
1. **Understand the impact**: Review existing code and tests
2. **Check current state**: Run tests to ensure starting from a clean state
3. **Plan changes**: Consider both reactive and blocking implementations

### During Development
1. **Incremental changes**: Make small, focused commits
2. **Test frequently**: Run relevant tests after each change
3. **Documentation**: Update JavaDoc as you modify code

### Before Submitting
1. **Full test suite**: `.\gradlew.bat test`
2. **Clean build**: `.\gradlew.bat clean build`
3. **JavaDoc verification**: `.\gradlew.bat javadoc` (ensure no warnings)
4. **Code review**: Check that all changes maintain English documentation

## Configuration Management

The project uses Spring Boot's configuration property system:
- **Properties file**: `src/main/resources/application.properties`
- **Configuration class**: `DeezerProperties.java`
- **Metadata**: `META-INF/additional-spring-configuration-metadata.json`

All configuration changes should be reflected in all three locations for proper IDE support and documentation.
