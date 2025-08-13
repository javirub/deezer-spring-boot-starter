package io.github.javirub.deezerspringbootstarter.domain;

import java.util.List;

public record Infos(
        String countryIso,
        String country,
        Boolean open,
        List<Options> offers
) {
}
