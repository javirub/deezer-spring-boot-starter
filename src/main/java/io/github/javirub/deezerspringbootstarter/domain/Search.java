package io.github.javirub.deezerspringbootstarter.domain;

import java.util.List;

/**
 * Record representing a Deezer search query and its results.
 * Supports both basic and advanced search functionality.
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
