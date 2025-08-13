package io.github.javirub.deezerspringbootstarter.domain;

import java.util.List;

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
