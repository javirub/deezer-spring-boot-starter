package io.github.javirub.deezerspringbootstarter.domain;

/**
 * Represents a Deezer artist with comprehensive metadata.
 * This record contains all the information returned by the Deezer API for an artist,
 * including basic details, picture images, statistics, and associated metadata.
 *
 * @param id The unique identifier of the artist
 * @param track The track associated with this artist (if applicable)
 * @param name The name of the artist
 * @param link The URL link to the artist on Deezer
 * @param share The URL for sharing the artist
 * @param picture The URL of the artist's picture (default size)
 * @param pictureSmall The URL of the small artist picture (56x56)
 * @param pictureMedium The URL of the medium artist picture (250x250)
 * @param pictureBig The URL of the big artist picture (500x500)
 * @param pictureXl The URL of the extra large artist picture (1000x1000)
 * @param nbAlbum The number of albums by this artist
 * @param nbFan The number of fans/followers of this artist
 * @param radio Whether the artist has a radio station available
 * @param tracklist The URL to get the list of tracks by this artist
 */
public record Artist(
        Long id,
        Track track,
        String name,
        String link,
        String share,
        String picture,
        String pictureSmall,
        String pictureMedium,
        String pictureBig,
        String pictureXl,
        Integer nbAlbum,
        Integer nbFan,
        Boolean radio,
        String tracklist
) {
}
