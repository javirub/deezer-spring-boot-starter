package io.github.javirub.deezerspringbootstarter.domain;


import java.time.LocalDate;
import java.util.List;

/**
 * Represents a Deezer music track with comprehensive metadata.
 * This record contains all the information returned by the Deezer API for a track,
 * including basic details, technical metadata, release information, and associated objects.
 *
 * @param id The unique identifier of the track
 * @param playlist The playlist this track belongs to (if applicable)
 * @param readable Whether the track is readable/available for the user
 * @param title The full title of the track
 * @param titleShort The short version of the track title
 * @param titleVersion The version information of the track (e.g., "Radio Edit", "Extended Mix")
 * @param unseen Whether the track is new/unseen by the user
 * @param isrc The International Standard Recording Code of the track
 * @param link The URL link to the track on Deezer
 * @param share The URL for sharing the track
 * @param duration The duration of the track in seconds
 * @param trackPosition The position of the track in its album
 * @param diskNumber The disk number if the album has multiple disks
 * @param rank The popularity rank of the track (higher is more popular)
 * @param releaseDate The release date of the track
 * @param explicitLyrics Whether the track contains explicit lyrics
 * @param explicitContentLyrics The explicit content level for lyrics (0-3)
 * @param explicitContentCover The explicit content level for cover art (0-3)
 * @param preview The URL of the track's 30-second preview
 * @param bpm The beats per minute (tempo) of the track
 * @param gain The audio gain/volume adjustment of the track
 * @param md5Image The MD5 hash of the track's associated image
 * @param trackToken The unique token for this track
 * @param artist The name of the main artist (string representation)
 * @param fileName The filename of the track file
 * @param availableCountries The list of country codes where the track is available
 * @param alternative An alternative version of this track (if available)
 * @param contributors The list of all artists who contributed to this track
 * @param artistObject The main artist as a full Artist object
 * @param album The album this track belongs to
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
