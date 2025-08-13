package io.github.javirub.deezerspringbootstarter.domain;

/**
 * Represents a Deezer radio station with comprehensive metadata.
 * This record contains all the information returned by the Deezer API for a radio station,
 * including basic details, description, cover images, and tracklist information.
 *
 * @param id The unique identifier of the radio station
 * @param title The title of the radio station
 * @param description The description of the radio station
 * @param share The URL for sharing the radio station
 * @param picture The URL of the radio station picture (default size)
 * @param pictureSmall The URL of the small radio station picture (56x56)
 * @param pictureMedium The URL of the medium radio station picture (250x250)
 * @param pictureBig The URL of the big radio station picture (500x500)
 * @param pictureXl The URL of the extra large radio station picture (1000x1000)
 * @param tracklist The URL to get the list of tracks played on this radio station
 * @param md5Image The MD5 hash of the radio station image
 */
public record Radio(
    Long id,
    String title,
    String description,
    String share,
    String picture,
    String pictureSmall,
    String pictureMedium,
    String pictureBig,
    String pictureXl,
    String tracklist,
    String md5Image
) {
}
