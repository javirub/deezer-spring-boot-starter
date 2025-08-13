package io.github.javirub.deezerspringbootstarter.domain;


import java.time.LocalDate;
import java.util.List;

/**
 * Represents a Deezer album with comprehensive metadata.
 * This record contains all the information returned by the Deezer API for an album,
 * including basic details, cover images, tracks, and associated metadata.
 *
 * @param id The unique identifier of the album
 * @param title The title of the album
 * @param upc The Universal Product Code of the album
 * @param link The URL link to the album on Deezer
 * @param share The URL for sharing the album
 * @param cover The URL of the album cover image (default size)
 * @param coverSmall The URL of the small album cover image (56x56)
 * @param coverMedium The URL of the medium album cover image (250x250)
 * @param coverBig The URL of the big album cover image (500x500)
 * @param coverXl The URL of the extra large album cover image (1000x1000)
 * @param md5Image The MD5 hash of the album cover image
 * @param genreId The ID of the main genre associated with the album
 * @param label The record label of the album
 * @param nbTracks The total number of tracks in the album
 * @param duration The total duration of the album in seconds
 * @param fans The number of fans/followers of the album
 * @param releaseDate The release date of the album
 * @param recordType The type of record (e.g., "album", "single", "ep")
 * @param available Whether the album is available for streaming
 * @param tracklist The URL to get the list of tracks in the album
 * @param explicitLyrics Whether the album contains explicit lyrics
 * @param explicitContentLyrics The explicit content level for lyrics (0-3)
 * @param explicitContentCover The explicit content level for cover art (0-3)
 * @param genres The list of genres associated with the album
 * @param contributors The list of artists who contributed to the album
 * @param alternative Alternative album information (dynamic content)
 * @param fallback Fallback album information (dynamic content)
 * @param artist The main artist of the album (dynamic content)
 * @param tracks The list of tracks in the album
 */
public record Album(
        Long id,
        String title,
        String upc,
        String link,
        String share,
        String cover,
        String coverSmall,
        String coverMedium,
        String coverBig,
        String coverXl,
        String md5Image,
        Integer genreId,
        String label,
        Integer nbTracks,
        Integer duration,
        Integer fans,
        LocalDate releaseDate,
        String recordType,
        Boolean available,
        String tracklist,
        Boolean explicitLyrics,
        Integer explicitContentLyrics,
        Integer explicitContentCover,
        List<Genre> genres,
        List<Artist> contributors,
        Object alternative,
        Object fallback,
        Object artist,
        List<Track> tracks
) {
}
