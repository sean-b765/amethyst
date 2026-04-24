package dev.amethyst.app.metadata.model;

public enum PlaybackCompatibility {
  /**
   * <p>
   * When the client can play the video and audio file
   * </p>
   */
  DIRECT_PLAY,
  /**
   * <p>
   * Audio codec needs to be transcoded
   * </p>
   */
  TRANSCODE_AUDIO,
  /**
   * <p>
   * Video codec needs to be transcoded
   * </p>
   */
  TRANSCODE_VIDEO,
  /**
   * <p>
   * Both video and audio codecs need to be transcoded
   * </p>
   */
  TRANSCODE_BOTH,
}
