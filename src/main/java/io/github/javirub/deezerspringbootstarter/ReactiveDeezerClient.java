package io.github.javirub.deezerspringbootstarter;

import io.github.javirub.deezerspringbootstarter.domain.*;
import reactor.core.publisher.Mono;

/**
 * Common interface for Deezer API clients.
 * This interface defines methods that can be implemented by both reactive and non-reactive clients.
 */
public interface ReactiveDeezerClient {
    /**
     * Get an album by its ID (reactive).
     * Endpoint: GET https://api.deezer.com/album/{id}
     *
     * @param albumId The album ID
     * @return A Mono that emits the album
     */
    Mono<Album> getAlbumById(Long albumId);

    /**
     * Get an artist by its ID (reactive).
     * Endpoint: GET https://api.deezer.com/artist/{id}
     *
     * @param artistId The artist ID
     * @return A Mono that emits the artist
     */
    Mono<Artist> getArtistById(Long artistId);

    /**
     * Get an editorial by its ID (reactive).
     * Endpoint: GET https://api.deezer.com/editorial/{id}
     *
     * @param editorialId The editorial ID
     * @return A Mono that emits the editorial
     */
    Mono<Editorial> getEditorialById(Long editorialId);

    /**
     * Get a genre by its ID (reactive).
     * Endpoint: GET https://api.deezer.com/genre/{id}
     *
     * @param genreId The genre ID
     * @return A Mono that emits the genre
     */
    Mono<Genre> getGenreById(Long genreId);

    /**
     * Get a playlist by its ID (reactive).
     * Endpoint: GET https://api.deezer.com/playlist/{id}
     *
     * @param playlistId The playlist ID
     * @return A Mono that emits the playlist
     */
    Mono<Playlist> getPlaylistById(Long playlistId);

    /**
     * Get a radio by its ID (reactive).
     * Endpoint: GET https://api.deezer.com/radio/{id}
     *
     * @param radioId The radio ID
     * @return A Mono that emits the radio
     */
    Mono<Radio> getRadioById(Long radioId);

    /**
     * Get a track by its ID (reactive).
     * Endpoint: GET https://api.deezer.com/track/{id}
     *
     * @param trackId The track ID
     * @return A Mono that emits the track
     */
    Mono<Track> getTrackById(Long trackId);

    /**
     * Get a user by its ID (reactive).
     * Endpoint: GET https://api.deezer.com/user/{id}
     *
     * @param userId The user ID
     * @return A Mono that emits the user
     */
    Mono<User> getUserById(Long userId);

    /**
     * Search for tracks on Deezer using the provided search options (reactive).
     * Endpoint: GET https://api.deezer.com/search?q={query}&amp;strict={strict}&amp;order={order}
     * 
     * @param options The search options built using SearchOptions.builder()
     * @return A Mono that emits the search results
     * @see SearchOptions
     */
    Mono<Search> search(SearchOptions options);

    /**
     * Convenience method for simple searches (reactive).
     * Equivalent to search(SearchOptions.builder().query(query).build())
     * 
     * @param query The search query
     * @return A Mono that emits the search results
     */
    Mono<Search> search(String query);
}
