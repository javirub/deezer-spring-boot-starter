package io.github.javirub.deezerspringbootstarter.domain;

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
