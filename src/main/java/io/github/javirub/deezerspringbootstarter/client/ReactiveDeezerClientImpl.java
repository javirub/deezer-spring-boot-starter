package io.github.javirub.deezerspringbootstarter.client;

import io.github.javirub.deezerspringbootstarter.ReactiveDeezerClient;
import io.github.javirub.deezerspringbootstarter.SearchOptions;
import io.github.javirub.deezerspringbootstarter.cache.ReactiveCache;
import io.github.javirub.deezerspringbootstarter.properties.DeezerProperties;
import io.github.javirub.deezerspringbootstarter.domain.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

/**
 * Reactive client for the Deezer API.
 * This client uses WebClient to make non-blocking requests to Deezer endpoints,
 * with caching support for improved performance.
 */
public class ReactiveDeezerClientImpl implements ReactiveDeezerClient {

    private final WebClient webClient;
    private final ReactiveCache<String, Object> cache;
    private final DeezerProperties properties;
    
    /**
     * Creates a new DeezerClient with the provided WebClient, cache, and properties.
     * 
     * @param webClient The WebClient configured for Deezer API
     * @param cache The reactive cache for caching API responses
     * @param properties The Deezer configuration properties
     */
    public ReactiveDeezerClientImpl(WebClient webClient, ReactiveCache<String, Object> cache, DeezerProperties properties) {
        this.webClient = webClient;
        this.cache = cache;
        this.properties = properties;
    }

    /**
     * Generic method to get a resource by ID, with caching.
     * 
     * @param endpoint The API endpoint
     * @param id The resource ID
     * @param responseType The expected response type
     * @param <T> The type of resource
     * @param <ID> The type of ID
     * @return A Mono that emits the requested resource
     */
    private <T, ID> Mono<T> getById(String endpoint, ID id, Class<T> responseType) {
        String cacheKey = endpoint + ":" + id;
        
        return cache.get(cacheKey, key -> webClient.get()
                .uri("/{endpoint}/{id}", endpoint, id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                    response.bodyToMono(String.class)
                        .map(error -> new RuntimeException("Client error: " + error)))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                    response.bodyToMono(String.class)
                        .map(error -> new RuntimeException("Server error: " + error)))
                .bodyToMono(responseType)
                .retryWhen(Retry.backoff(properties.getMaxRetries(), Duration.ofMillis(properties.getBackoffDelay()))
                        .filter(throwable -> throwable instanceof WebClientResponseException.ServiceUnavailable))
                .cast(Object.class))
                .cast(responseType);
    }

    /**
     * Get an album by its ID.
     * Endpoint: GET <a href="https://api.deezer.com/album/{id}">https://api.deezer.com/album/{id}</a>
     *
     * @param albumId The album ID
     * @return A Mono that emits the album
     */
    public Mono<Album> getAlbumById(Long albumId) {
        return getById("album", albumId, Album.class);
    }

    /**
     * Get an artist by its ID.
     * Endpoint: GET <a href="https://api.deezer.com/artist/{id}">https://api.deezer.com/artist/{id}</a>
     *
     * @param artistId The artist ID
     * @return A Mono that emits the artist
     */
    public Mono<Artist> getArtistById(Long artistId) {
        return getById("artist", artistId, Artist.class);
    }

    /**
     * Get an editorial by its ID.
     * Endpoint: GET <a href="https://api.deezer.com/editorial/{id}">https://api.deezer.com/editorial/{id}</a>
     *
     * @param editorialId The editorial ID
     * @return A Mono that emits the editorial
     */
    public Mono<Editorial> getEditorialById(Long editorialId) {
        return getById("editorial", editorialId, Editorial.class);
    }

    /**
     * Get a genre by its ID.
     * Endpoint: GET <a href="https://api.deezer.com/genre/{id}">https://api.deezer.com/genre/{id}</a>
     *
     * @param genreId The genre ID
     * @return A Mono that emits the genre
     */
    public Mono<Genre> getGenreById(Long genreId) {
        return getById("genre", genreId, Genre.class);
    }

    /**
     * Get a playlist by its ID.
     * Endpoint: GET <a href="https://api.deezer.com/playlist/{id}">https://api.deezer.com/playlist/{id}</a>
     *
     * @param playlistId The playlist ID
     * @return A Mono that emits the playlist
     */
    public Mono<Playlist> getPlaylistById(Long playlistId) {
        return getById("playlist", playlistId, Playlist.class);
    }

    /**
     * Get a radio by its ID.
     * Endpoint: GET <a href="https://api.deezer.com/radio/{id}">https://api.deezer.com/radio/{id}</a>
     *
     * @param radioId The radio ID
     * @return A Mono that emits the radio
     */
    public Mono<Radio> getRadioById(Long radioId) {
        return getById("radio", radioId, Radio.class);
    }

    /**
     * Get a track by its ID.
     * Endpoint: GET <a href="https://api.deezer.com/track/{id}">https://api.deezer.com/track/{id}</a>
     *
     * @param trackId The track ID
     * @return A Mono that emits the track
     */
    public Mono<Track> getTrackById(Long trackId) {
        return getById("track", trackId, Track.class);
    }

    /**
     * Get a user by its ID.
     * Endpoint: GET <a href="https://api.deezer.com/user/{id}">https://api.deezer.com/user/{id}</a>
     *
     * @param userId The user ID
     * @return A Mono that emits the user
     */
    public Mono<User> getUserById(Long userId) {
        return getById("user", userId, User.class);
    }

    /**
     * Search for tracks on Deezer using the provided search options.
     * Endpoint: GET <a href="https://api.deezer.com/search?q=">https://api.deezer.com/search?q={query}&amp;strict={strict}&amp;order={order}</a>
     *
     * <p>Examples:
     * <pre>
     * // Basic search
     * deezerClient.search(SearchOptions.builder().query("eminem").build());
     *
     * // Search with strict mode and custom order
     * deezerClient.search(SearchOptions.builder()
     *     .query("eminem")
     *     .strict(true)
     *     .order("RATING_DESC")
     *     .build());
     *
     * // Advanced search with multiple criteria
     * deezerClient.search(SearchOptions.builder()
     *     .artist("aloe blacc")
     *     .track("i need a dollar")
     *     .durationMin(180)
     *     .build());
     * </pre>
     *
     * @param options The search options built using SearchOptions.builder()
     * @return A Mono that emits the search results
     * @see SearchOptions
     */
    public Mono<Search> search(SearchOptions options) {
        Map<String, Object> queryParams = options.buildQueryParams();
        
        // Generate a unique cache key based on search parameters
        StringBuilder cacheKeyBuilder = new StringBuilder("search:");
        cacheKeyBuilder.append(options.buildQueryString());
        
        if (options.getStrict() != null) {
            cacheKeyBuilder.append(":strict=").append(options.getStrict());
        }
        
        if (options.getOrder() != null) {
            cacheKeyBuilder.append(":order=").append(options.getOrder());
        }
        
        String cacheKey = cacheKeyBuilder.toString();
        
        return cache.get(cacheKey, key -> webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path("/search");
                    queryParams.forEach(uriBuilder::queryParam);
                    return uriBuilder.build();
                })
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                    response.bodyToMono(String.class)
                        .map(error -> new RuntimeException("Client error: " + error)))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                    response.bodyToMono(String.class)
                        .map(error -> new RuntimeException("Server error: " + error)))
                .bodyToMono(Search.class)
                .retryWhen(Retry.backoff(properties.getMaxRetries(), Duration.ofMillis(properties.getBackoffDelay()))
                        .filter(throwable -> throwable instanceof WebClientResponseException.ServiceUnavailable))
                .cast(Object.class))
                .cast(Search.class);
    }
    
    /**
     * Convenience method for simple searches.
     * Equivalent to search(SearchOptions.builder().query(query).build())
     * 
     * @param query The search query
     * @return A Mono that emits the search results
     */
    public Mono<Search> search(String query) {
        return search(SearchOptions.builder().query(query).build());
    }
}
