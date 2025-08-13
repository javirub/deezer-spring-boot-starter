package io.github.javirub.deezerspringbootstarter.domain;


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
