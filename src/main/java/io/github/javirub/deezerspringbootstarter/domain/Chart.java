package io.github.javirub.deezerspringbootstarter.domain;


import java.util.List;

public record Chart(
        Long id,
        List<Track> tracks,
        List<Album> albums,
        List<Artist> artists,
        List<Playlist> playlists,
        List<Podcast> podcasts
) {
}
