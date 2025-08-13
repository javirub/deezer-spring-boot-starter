package io.github.javirub.deezerspringbootstarter.domain;

/**
 * Record representing a Deezer podcast.
 * Contains all fields as specified in the Deezer API documentation.
 */
public record Podcast(
    Integer id,           // The podcast's Deezer id
    String title,         // The podcast's title
    String description,   // The podcast's description
    Boolean available,    // If the podcast is available or not
    Integer fans,         // The number of podcast's fans
    String link,          // The url of the podcast on Deezer
    String share,         // The share link of the podcast on Deezer
    String picture,       // The url of the podcast's cover
    String pictureSmall,  // The url of the podcast's cover in size small
    String pictureMedium, // The url of the podcast's cover in size medium
    String pictureBig,    // The url of the podcast's cover in size big
    String pictureXl,     // The url of the podcast's cover in size xl
    Integer position      // The position of the podcasts in the charts
) {
}
