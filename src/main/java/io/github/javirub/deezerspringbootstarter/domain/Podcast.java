package io.github.javirub.deezerspringbootstarter.domain;

/**
 * Represents a Deezer podcast with comprehensive metadata.
 * This record contains all the information returned by the Deezer API for a podcast,
 * including basic details, availability status, cover images, and chart position.
 *
 * @param id The unique identifier of the podcast on Deezer
 * @param title The title of the podcast
 * @param description The description of the podcast
 * @param available Whether the podcast is available for listening
 * @param fans The number of fans/followers of the podcast
 * @param link The URL link to the podcast on Deezer
 * @param share The URL for sharing the podcast
 * @param picture The URL of the podcast cover image (default size)
 * @param pictureSmall The URL of the small podcast cover image (56x56)
 * @param pictureMedium The URL of the medium podcast cover image (250x250)
 * @param pictureBig The URL of the big podcast cover image (500x500)
 * @param pictureXl The URL of the extra large podcast cover image (1000x1000)
 * @param position The position of the podcast in the charts
 */
public record Podcast(
    Integer id,
    String title,
    String description,
    Boolean available,
    Integer fans,
    String link,
    String share,
    String picture,
    String pictureSmall,
    String pictureMedium,
    String pictureBig,
    String pictureXl,
    Integer position
) {
}
