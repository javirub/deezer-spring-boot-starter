package io.github.javirub.deezerspringbootstarter.domain;

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
