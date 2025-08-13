package io.github.javirub.deezerspringbootstarter.domain;


import java.time.LocalDate;
import java.util.List;

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
