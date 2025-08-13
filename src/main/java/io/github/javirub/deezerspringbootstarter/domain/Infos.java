package io.github.javirub.deezerspringbootstarter.domain;

import java.util.List;

/**
 * Represents Deezer service information for a specific country or region.
 * This record contains information about the availability of Deezer services
 * and the various subscription offers available in different countries.
 *
 * @param countryIso The ISO code of the country (e.g., "US", "FR", "DE")
 * @param country The full name of the country
 * @param open Whether Deezer services are available in this country
 * @param offers The list of subscription options available in this country
 */
public record Infos(
        String countryIso,
        String country,
        Boolean open,
        List<Options> offers
) {
}
