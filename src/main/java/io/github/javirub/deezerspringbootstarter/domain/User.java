package io.github.javirub.deezerspringbootstarter.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Record representing a Deezer user.
 * Contains all fields as specified in the Deezer API documentation.
 */
public record User(
    Long id,
    String name,
    String lastname,
    String firstname,
    String email,
    Integer status,
    LocalDate birthday,
    LocalDate inscriptionDate,
    String gender,
    String link,
    String picture,
    String pictureSmall,
    String pictureMedium,
    String pictureBig,
    String pictureXl,
    String country,
    String lang,
    Boolean isKid,
    String explicitContentLevel,
    List<String> explicitContentLevelsAvailable,
    String tracklist
) {
}