package io.github.javirub.deezerspringbootstarter.domain;

import java.util.List;

/**
 * Represents a Deezer playlist with comprehensive metadata and track information.
 * This record contains all the information returned by the Deezer API for a playlist,
 * including basic details, privacy settings, statistics, cover images, and associated tracks.
 *
 * @param id The unique identifier of the playlist
 * @param title The title of the playlist
 * @param description The description of the playlist
 * @param duration The total duration of the playlist in seconds
 * @param isPublic Whether the playlist is public (true) or private (false)
 * @param isLovedTrack Whether this playlist represents the user's loved tracks
 * @param collaborative Whether the playlist allows collaborative editing
 * @param nbTracks The total number of tracks in the playlist
 * @param unseenTrackCount The number of tracks that haven't been seen by the user
 * @param fans The number of fans/followers of the playlist
 * @param link The URL link to the playlist on Deezer
 * @param share The URL for sharing the playlist
 * @param picture The URL of the playlist cover image (default size)
 * @param pictureSmall The URL of the small playlist cover image (56x56)
 * @param pictureMedium The URL of the medium playlist cover image (250x250)
 * @param pictureBig The URL of the big playlist cover image (500x500)
 * @param pictureXl The URL of the extra large playlist cover image (1000x1000)
 * @param checksum The checksum/hash of the playlist for change detection
 * @param creator The user who created this playlist
 * @param tracks The list of tracks in the playlist
 */
public record Playlist(
        Long id,
        String title,
        String description,
        Integer duration,
        Boolean isPublic,
        Boolean isLovedTrack,
        Boolean collaborative,
        Integer nbTracks,
        Integer unseenTrackCount,
        Integer fans,
        String link,
        String share,
        String picture,
        String pictureSmall,
        String pictureMedium,
        String pictureBig,
        String pictureXl,
        String checksum,
        User creator,
        List<Track> tracks
) {
}
