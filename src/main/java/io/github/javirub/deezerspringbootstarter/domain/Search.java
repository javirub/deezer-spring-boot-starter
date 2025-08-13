package io.github.javirub.deezerspringbootstarter.domain;

import java.util.List;

/**
 * Represents a Deezer search operation with query parameters and results.
 * This record contains both the search criteria used to query the Deezer API
 * and the resulting tracks that match the search parameters.
 *
 * @param id The unique identifier of the search operation
 * @param query The main search query string
 * @param strict Whether to use strict search mode (exact matches)
 * @param order The order to sort results (e.g., "RANKING", "TRACK_ASC", "ARTIST_ASC")
 * @param artistFilter Filter results by specific artist name
 * @param albumFilter Filter results by specific album name
 * @param trackFilter Filter results by specific track name
 * @param labelFilter Filter results by specific record label
 * @param durationMin Minimum track duration in seconds for filtering
 * @param durationMax Maximum track duration in seconds for filtering
 * @param bpmMin Minimum beats per minute (BPM) for filtering
 * @param bpmMax Maximum beats per minute (BPM) for filtering
 * @param results The list of tracks that match the search criteria
 */
public record Search(
    Long id,
    String query,
    Boolean strict,
    String order,
    String artistFilter,
    String albumFilter,
    String trackFilter,
    String labelFilter,
    Integer durationMin,
    Integer durationMax,
    Integer bpmMin,
    Integer bpmMax,
    List<Track> results
) {
}
