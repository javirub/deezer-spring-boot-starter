package io.github.javirub.deezerspringbootstarter.domain;

public record Options(
    Long id,
    Boolean streaming,
    Integer streamingDuration,
    Boolean offline,
    Boolean hq,
    Boolean adsDisplay,
    Boolean adsAudio,
    Boolean tooManyDevices,
    Boolean canSubscribe,
    Integer radioSkips,
    Boolean lossless,
    Boolean preview,
    Boolean radio
) {
}
