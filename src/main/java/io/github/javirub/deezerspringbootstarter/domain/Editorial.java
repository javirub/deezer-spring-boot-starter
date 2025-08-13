package io.github.javirub.deezerspringbootstarter.domain;

/**
 * Represents a Deezer editorial content with associated imagery.
 * This record contains editorial information such as curated playlists, featured content,
 * or promotional materials provided by Deezer's editorial team.
 *
 * @param id The unique identifier of the editorial content
 * @param name The name or title of the editorial content
 * @param picture The URL of the editorial picture (default size)
 * @param pictureSmall The URL of the small editorial picture (56x56)
 * @param pictureMedium The URL of the medium editorial picture (250x250)
 * @param pictureBig The URL of the big editorial picture (500x500)
 * @param pictureXl The URL of the extra large editorial picture (1000x1000)
 */
public record Editorial(
    Long id,
    String name,
    String picture,
    String pictureSmall,
    String pictureMedium,
    String pictureBig,
    String pictureXl
) {
}
