package io.github.javirub.deezerspringbootstarter.domain;


import java.util.List;

/**
 * Represents a Deezer chart containing various types of popular content.
 * This record contains lists of the most popular tracks, albums, artists, playlists, and podcasts
 * according to Deezer's charts and trending data.
 *
 * @param id The unique identifier of the chart
 * @param tracks The list of popular tracks in the chart
 * @param albums The list of popular albums in the chart
 * @param artists The list of popular artists in the chart
 * @param playlists The list of popular playlists in the chart
 * @param podcasts The list of popular podcasts in the chart
 */
public record Chart(
        Long id,
        List<Track> tracks,
        List<Album> albums,
        List<Artist> artists,
        List<Playlist> playlists,
        List<Podcast> podcasts
) {
}
