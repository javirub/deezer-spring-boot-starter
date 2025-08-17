package io.github.javirub.deezerspringbootstarter;

import io.github.javirub.deezerspringbootstarter.domain.*;

/**
 * Common interface for Deezer API clients.
 * This interface defines methods that can be implemented by both reactive and non-reactive clients.
 */
public interface DeezerClient {
    /**
     * Get an album by its ID (blocking).
     * Endpoint: GET <a href="https://api.deezer.com/album/{id}">https://api.deezer.com/album/{id}</a>
     *
     * @param albumId The album ID
     * @return The album
     */
    Album getAlbumById(Long albumId);

    /**
     * Get an artist by its ID (blocking).
     * Endpoint: GET <a href="https://api.deezer.com/artist/{id}">https://api.deezer.com/artist/{id}</a>{id}
     *
     * @param artistId The artist ID
     * @return The artist
     */
    Artist getArtistById(Long artistId);

    /**
     * Get an editorial by its ID (blocking).
     * Endpoint: GET <a href="https://api.deezer.com/editorial/{id}">https://api.deezer.com/editorial/{id}</a>
     *
     * @param editorialId The editorial ID
     * @return The editorial
     */
    Editorial getEditorialById(Long editorialId);

    /**
     * Get a genre by its ID (blocking).
     * Endpoint: GET <a href="https://api.deezer.com/genre/{id}">https://api.deezer.com/genre/{id}</a>
     *
     * @param genreId The genre ID
     * @return The genre
     */
    Genre getGenreById(Long genreId);

    /**
     * Get a playlist by its ID (blocking).
     * Endpoint: GET <a href="https://api.deezer.com/playlist/{id}">https://api.deezer.com/playlist/{id}</a>
     *
     * @param playlistId The playlist ID
     * @return The playlist
     */
    Playlist getPlaylistById(Long playlistId);

    /**
     * Get a radio by its ID (blocking).
     * Endpoint: GET <a href="https://api.deezer.com/radio/{id}">https://api.deezer.com/radio/{id}</a>
     *
     * @param radioId The radio ID
     * @return The radio
     */
    Radio getRadioById(Long radioId);

    /**
     * Get a track by its ID (blocking).
     * Endpoint: GET <a href="https://api.deezer.com/track/{id}">https://api.deezer.com/track/{id}</a>{id}
     *
     * @param trackId The track ID
     * @return The track
     */
    Track getTrackById(Long trackId);

    /**
     * Get a user by its ID (blocking).
     * Endpoint: GET <a href="https://api.deezer.com/user/{id}">https://api.deezer.com/user/{id}</a>
     *
     * @param userId The user ID
     * @return The user
     */
    User getUserById(Long userId);

    /**
     * Search for tracks on Deezer using the provided search options (blocking).
     * Endpoint: GET <a href="https://api.deezer.com/search?q={query}&amp;strict={strict}&amp;order={order}">https://api.deezer.com/search?q={query}&amp;strict={strict}&amp;order={order}</a>
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
