package io.github.javirub.deezerspringbootstarter.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a Deezer user with comprehensive profile information.
 * This record contains all the information returned by the Deezer API for a user,
 * including personal details, preferences, profile images, and content settings.
 *
 * @param id The unique identifier of the user
 * @param name The username or display name of the user
 * @param lastname The last name of the user
 * @param firstname The first name of the user
 * @param email The email address of the user
 * @param status The status of the user account (e.g., active, premium)
 * @param birthday The birthday of the user
 * @param inscriptionDate The date when the user registered on Deezer
 * @param gender The gender of the user
 * @param link The URL link to the user's profile on Deezer
 * @param picture The URL of the user's profile picture (default size)
 * @param pictureSmall The URL of the small user profile picture (56x56)
 * @param pictureMedium The URL of the medium user profile picture (250x250)
 * @param pictureBig The URL of the big user profile picture (500x500)
 * @param pictureXl The URL of the extra large user profile picture (1000x1000)
 * @param country The country code of the user's location
 * @param lang The language preference of the user
 * @param isKid Whether the user account is a kid account with parental controls
 * @param explicitContentLevel The current explicit content level setting
 * @param explicitContentLevelsAvailable The list of available explicit content levels
 * @param tracklist The URL to get the user's favorite tracks
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