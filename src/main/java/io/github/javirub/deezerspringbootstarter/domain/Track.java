package io.github.javirub.deezerspringbootstarter.domain;


import java.time.LocalDate;
import java.util.List;

/**
 * Record representing a Deezer music track.
 * Contains all fields as specified in the Deezer API documentation.
 */
public record Track(
    Long id,
    Playlist playlist,
    Boolean readable,
    String title,
    String titleShort,
    String titleVersion,
    Boolean unseen,
    String isrc,
    String link,
    String share,
    Integer duration,
    Integer trackPosition,
    Integer diskNumber,
    Integer rank,
    LocalDate releaseDate,
    Boolean explicitLyrics,
    Integer explicitContentLyrics,
    Integer explicitContentCover,
    String preview,
    Float bpm,
    Float gain,
    String md5Image,
    String trackToken,
    String artist,
    String fileName,
    List<String> availableCountries,
    Track alternative,
    List<Artist> contributors,
    Artist artistObject,
    Album album
) {
}
