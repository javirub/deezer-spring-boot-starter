package io.github.javirub.deezerspringbootstarter.domain;

/**
 * Represents Deezer subscription options and feature availability.
 * This record contains information about what features are available
 * for a specific subscription plan or user account type.
 *
 * @param id The unique identifier of the subscription option
 * @param streaming Whether streaming is available for this option
 * @param streamingDuration The maximum streaming duration (in seconds) for this option
 * @param offline Whether offline listening is available
 * @param hq Whether high-quality audio is available
 * @param adsDisplay Whether display ads are shown
 * @param adsAudio Whether audio ads are played
 * @param tooManyDevices Whether the user has reached the device limit
 * @param canSubscribe Whether the user can subscribe to premium features
 * @param radioSkips The number of radio skips allowed
 * @param lossless Whether lossless audio quality is available
 * @param preview Whether track previews are available
 * @param radio Whether radio functionality is available
 */
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
