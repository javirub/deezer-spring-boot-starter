package io.github.javirub.deezerspringbootstarter;

import io.github.javirub.deezerspringbootstarter.domain.*;

/**
 * Common interface for Deezer API clients.
 * This interface defines methods that can be implemented by both reactive and non-reactive clients.
 */
public interface DeezerClient {

    // ===============================
    // Resource retrieval methods (blocking/non-reactive)
    // ===============================

    /**
     * Get an album by its ID (blocking).
     * Endpoint: GET https://api.deezer.com/album/{id}
     *
     * @param albumId The album ID
     * @return The album
     */
    Album getAlbumById(Long albumId);

    /**
     * Get an artist by its ID (blocking).
     * Endpoint: GET https://api.deezer.com/artist/{id}
     *
     * @param artistId The artist ID
     * @return The artist
     */
    Artist getArtistById(Long artistId);

    /**
     * Get an editorial by its ID (blocking).
     * Endpoint: GET https://api.deezer.com/editorial/{id}
     *
     * @param editorialId The editorial ID
     * @return The editorial
     */
    Editorial getEditorialById(Long editorialId);

    /**
     * Get a genre by its ID (blocking).
     * Endpoint: GET https://api.deezer.com/genre/{id}
     *
     * @param genreId The genre ID
     * @return The genre
     */
    Genre getGenreById(Long genreId);

    /**
     * Get a playlist by its ID (blocking).
     * Endpoint: GET https://api.deezer.com/playlist/{id}
     *
     * @param playlistId The playlist ID
     * @return The playlist
     */
    Playlist getPlaylistById(Long playlistId);

    /**
     * Get a radio by its ID (blocking).
     * Endpoint: GET https://api.deezer.com/radio/{id}
     *
     * @param radioId The radio ID
     * @return The radio
     */
    Radio getRadioById(Long radioId);

    /**
     * Get a track by its ID (blocking).
     * Endpoint: GET https://api.deezer.com/track/{id}
     *
     * @param trackId The track ID
     * @return The track
     */
    Track getTrackById(Long trackId);

    /**
     * Get a user by its ID (blocking).
     * Endpoint: GET https://api.deezer.com/user/{id}
     *
     * @param userId The user ID
     * @return The user
     */
    User getUserById(Long userId);

    /**
     * Search for tracks on Deezer using the provided search options (blocking).
     * Endpoint: GET https://api.deezer.com/search?q={query}&amp;strict={strict}&amp;order={order}
     *
     * @param options The search options built using SearchOptions.builder()
     * @return The search results
     * @see SearchOptions
     */
    Search search(SearchOptions options);

    /**
     * Convenience method for simple searches (blocking).
     * Equivalent to searchBlocking(SearchOptions.builder().query(query).build())
     *
     * @param query The search query
     * @return The search results
     */
    Search search(String query);
}
