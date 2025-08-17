package io.github.javirub.deezerspringbootstarter.client;

import io.github.javirub.deezerspringbootstarter.DeezerClient;
import io.github.javirub.deezerspringbootstarter.SearchOptions;
import io.github.javirub.deezerspringbootstarter.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Non-reactive client for the Deezer API.
 * This client uses RestTemplate to make blocking requests to Deezer endpoints.
 */
public class DeezerClientImpl implements DeezerClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    /**
     * Creates a new DeezerClientImpl with the provided RestTemplate and base URL.
     *
     * @param restTemplate The RestTemplate for making HTTP requests
     * @param baseUrl      The base URL for the Deezer API
     */
    public DeezerClientImpl(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * Generic method to get a resource by ID using RestTemplate.
     *
     * @param endpoint     The API endpoint
     * @param id           The resource ID
     * @param responseType The expected response type
     * @param <T>          The type of resource
     * @param <ID>         The type of ID
     * @return The requested resource
     */
    private <T, ID> T getById(String endpoint, ID id, Class<T> responseType) {
        String url = baseUrl + "/" + endpoint + "/" + id;
        ResponseEntity<T> response = restTemplate.getForEntity(url, responseType);
        return response.getBody();
    }

    @Override
    public Album getAlbumById(Long albumId) {
        return getById("album", albumId, Album.class);
    }

    @Override
    public Artist getArtistById(Long artistId) {
        return getById("artist", artistId, Artist.class);
    }

    @Override
    public Editorial getEditorialById(Long editorialId) {
        return getById("editorial", editorialId, Editorial.class);
    }

    @Override
    public Genre getGenreById(Long genreId) {
        return getById("genre", genreId, Genre.class);
    }

    @Override
    public Playlist getPlaylistById(Long playlistId) {
        return getById("playlist", playlistId, Playlist.class);
    }

    @Override
    public Radio getRadioById(Long radioId) {
        return getById("radio", radioId, Radio.class);
    }

    @Override
    public Track getTrackById(Long trackId) {
        return getById("track", trackId, Track.class);
    }

    @Override
    public User getUserById(Long userId) {
        return getById("user", userId, User.class);
    }

    @Override
    public Search search(SearchOptions options) {
        Map<String, Object> queryParams = options.buildQueryParams();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(baseUrl + "/search");
        queryParams.forEach(uriBuilder::queryParam);

        String url = uriBuilder.toUriString();
        ResponseEntity<Search> response = restTemplate.getForEntity(url, Search.class);
        return response.getBody();
    }

    @Override
    public Search search(String query) {
        return search(SearchOptions.builder().query(query).build());
    }
}
