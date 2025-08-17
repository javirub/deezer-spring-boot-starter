package io.github.javirub.deezerspringbootstarter;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Options for searching tracks on Deezer API.
 * This class uses the Builder pattern for easier configuration of search parameters.
 * 
 * <p>Use {@code SearchOptions.builder()} to create instances of this class.
 */
@Builder
@Getter
public class SearchOptions {
    
    /**
     * Constructor for SearchOptions with all parameters.
     * 
     * @param query Basic search query string
     * @param strict If true, disables the fuzzy mode in search results
     * @param order Sort order for search results
     * @param artist Filter results by artist name
     * @param album Filter results by album title
     * @param track Filter results by track title
     * @param label Filter results by label name
     * @param durationMin Filter results by minimum track duration in seconds
     * @param durationMax Filter results by maximum track duration in seconds
     * @param bpmMin Filter results by minimum BPM (beats per minute)
     * @param bpmMax Filter results by maximum BPM (beats per minute)
     */
    public SearchOptions(String query, Boolean strict, String order, String artist, String album, 
                        String track, String label, Integer durationMin, Integer durationMax, 
                        Integer bpmMin, Integer bpmMax) {
        this.query = query;
        this.strict = strict;
        this.order = order;
        this.artist = artist;
        this.album = album;
        this.track = track;
        this.label = label;
        this.durationMin = durationMin;
        this.durationMax = durationMax;
        this.bpmMin = bpmMin;
        this.bpmMax = bpmMax;
    }
    
    /**
     * Basic search query string.
     */
    private final String query;
    
    /**
     * If true, disables the fuzzy mode in search results.
     */
    private final Boolean strict;
    
    /**
     * Sort order for search results.
     * Possible values: RANKING, TRACK_ASC, TRACK_DESC, ARTIST_ASC, ARTIST_DESC,
     * ALBUM_ASC, ALBUM_DESC, RATING_ASC, RATING_DESC, DURATION_ASC, DURATION_DESC
     */
    private final String order;
    
    /**
     * Filter results by artist name.
     */
    private final String artist;
    
    /**
     * Filter results by album title.
     */
    private final String album;
    
    /**
     * Filter results by track title.
     */
    private final String track;
    
    /**
     * Filter results by label name.
     */
    private final String label;
    
    /**
     * Filter results by minimum track duration in seconds.
     */
    private final Integer durationMin;
    
    /**
     * Filter results by maximum track duration in seconds.
     */
    private final Integer durationMax;
    
    /**
     * Filter results by minimum BPM (beats per minute).
     */
    private final Integer bpmMin;
    
    /**
     * Filter results by maximum BPM (beats per minute).
     */
    private final Integer bpmMax;
    
    /**
     * Builds the query string for the Deezer API based on the provided options.
     * 
     * @return A properly formatted query string for the Deezer API
     */
    public String buildQueryString() {
        // If there's already a basic query with no advanced options, return it as is
        if (query != null && !hasAdvancedOptions()) {
            return query;
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        
        // Add a basic query if provided (will be combined with advanced options)
        if (query != null && !query.isEmpty()) {
            queryBuilder.append(query).append(" ");
        }
        
        // Add advanced search parameters if provided
        if (artist != null && !artist.isEmpty()) {
            queryBuilder.append("artist:\"").append(artist).append("\" ");
        }
        
        if (album != null && !album.isEmpty()) {
            queryBuilder.append("album:\"").append(album).append("\" ");
        }
        
        if (track != null && !track.isEmpty()) {
            queryBuilder.append("track:\"").append(track).append("\" ");
        }
        
        if (label != null && !label.isEmpty()) {
            queryBuilder.append("label:\"").append(label).append("\" ");
        }
        
        if (durationMin != null) {
            queryBuilder.append("dur_min:").append(durationMin).append(" ");
        }
        
        if (durationMax != null) {
            queryBuilder.append("dur_max:").append(durationMax).append(" ");
        }
        
        if (bpmMin != null) {
            queryBuilder.append("bpm_min:").append(bpmMin).append(" ");
        }
        
        if (bpmMax != null) {
            queryBuilder.append("bpm_max:").append(bpmMax).append(" ");
        }
        
        // Trim trailing space and return the built query
        return queryBuilder.toString().trim();
    }
    
    /**
     * Checks if any advanced search options are set.
     * 
     * @return true if any advanced search option is set, false otherwise
     */
    private boolean hasAdvancedOptions() {
        return (artist != null && !artist.isEmpty())
                || (album != null && !album.isEmpty())
                || (track != null && !track.isEmpty())
                || (label != null && !label.isEmpty())
                || durationMin != null
                || durationMax != null
                || bpmMin != null
                || bpmMax != null;
    }

    /**
     * Builds query parameters map for Deezer API requests.
     * Includes the main query string and additional parameters like strict mode and order.
     *
     * @return A map containing all query parameters for the API request
     */
    public Map<String, Object> buildQueryParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("q", buildQueryString());

        if (strict != null) {
            queryParams.put("strict", strict ? "on" : "off");
        }

        if (order != null && !order.isEmpty()) {
            queryParams.put("order", order);
        }

        return queryParams;
    }
}