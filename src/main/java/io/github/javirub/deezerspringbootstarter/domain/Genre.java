package io.github.javirub.deezerspringbootstarter.domain;

/**
 * Represents a Deezer music genre with associated imagery.
 * This record contains genre information including its name and representative images
 * in various sizes for display purposes.
 *
 * @param id The unique identifier of the genre
 * @param name The name of the genre (e.g., "Rock", "Pop", "Hip-Hop")
 * @param picture The URL of the genre picture (default size)
 * @param pictureSmall The URL of the small genre picture (56x56)
 * @param pictureMedium The URL of the medium genre picture (250x250)
 * @param pictureBig The URL of the big genre picture (500x500)
 * @param pictureXl The URL of the extra large genre picture (1000x1000)
 */
public record Genre(
    Long id,
    String name,
    String picture,
    String pictureSmall,
    String pictureMedium,
    String pictureBig,
    String pictureXl
) {
}
