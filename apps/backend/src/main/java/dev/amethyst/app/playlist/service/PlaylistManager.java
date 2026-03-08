package dev.amethyst.app.playlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.amethyst.app.media.model.Media;
import dev.amethyst.app.metadata.model.KeyframeData;
import dev.amethyst.app.metadata.service.KeyframeExtractor;
import dev.amethyst.app.transcode.service.TranscodingService;
import dev.amethyst.app.video.model.QualityProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PlaylistManager {
  @Autowired
  private TranscodingService transcodingService;
  @Autowired
  private KeyframeExtractor keyframeExtractor;

  public String generateMultiVariantPlaylist(Media media) {
    StringBuilder multiVariantPlaylist = new StringBuilder();
    multiVariantPlaylist.append("#EXTM3U\n");
    for (QualityProfiles.QualityProfile profile : QualityProfiles.AllProfiles) {
      multiVariantPlaylist.append("#EXT-X-STREAM-INF:BANDWIDTH=")
          .append(profile.getBandwidthUpperBound())
          .append(",RESOLUTION=")
          .append(profile)
          .append("\n");
      multiVariantPlaylist.append("/api/playlist/")
          .append(media.getId())
          .append("/")
          .append(profile.getName())
          .append("\n");
    }
    return multiVariantPlaylist.toString();
  }

  public String generateVodPlaylist(Media media, QualityProfiles.QualityProfile qualityProfile) {
    KeyframeData keyframeData = keyframeExtractor.getKeyframeData(media.getPath());
    return this.generateVodPlaylist(
        String.format("/api/video/%s/%s", media.getId(), qualityProfile.getName()),
        keyframeData);
  }

  public String generateVodPlaylist(String uriPrefix, KeyframeData keyframeData) {
    List<Double> segments = this.getSegments(keyframeData);
    int targetDuration = Math.max(
        (int) Collections.max(segments).doubleValue(),
        (int) this.transcodingService.getSegmentLength());

    StringBuilder vodPlaylist = new StringBuilder();
    vodPlaylist.append("#EXTM3U\n");
    vodPlaylist.append("#EXT-X-VERSION:3\n");
    vodPlaylist.append("#EXT-X-TARGETDURATION:").append(targetDuration).append("\n");
    vodPlaylist.append("#EXT-X-MEDIA-SEQUENCE:0\n");
    vodPlaylist.append("#EXT-X-PLAYLIST-TYPE:VOD\n");
    for (int i = 0; i < segments.size(); i++) {
      double segment = segments.get(i);
      double rounded = Math.round(segment * 1_000_000.0) / 1_000_000.0;
      vodPlaylist.append("#EXTINF:").append(rounded).append("\n");

      String uriPrefixWithoutLeadingSlash = uriPrefix.endsWith("/") ? uriPrefix.replaceAll("\\/$", "") : uriPrefix;
      String fullUri = String.format("%s/segment%05d.ts", uriPrefixWithoutLeadingSlash, i);

      vodPlaylist.append(fullUri).append("\n");
    }
    vodPlaylist.append("#EXT-X-ENDLIST");
    return vodPlaylist.toString();
  }

  public List<Double> getSegmentsExact(KeyframeData keyframeData) {
    double duration = keyframeData.getDuration();
    List<Double> segments = new ArrayList<>();
    double current = 0.0;
    double segmentLength = this.transcodingService.getSegmentLength();

    while (current + segmentLength < duration) {
      segments.add(segmentLength);
      current += segmentLength;
    }

    // Last segment: remaining duration
    if (current < duration) {
      segments.add(duration - current);
    }

    return segments;
  }

  public List<Double> getSegments(KeyframeData keyframeData) {
    List<Double> keyframes = keyframeData.getPositions();

    if (keyframes.isEmpty()) {
      throw new IllegalArgumentException("Invalid KeyframeData passed to getSegments()");
    }

    List<Double> segments = new ArrayList<>();
    double segmentLength = this.transcodingService.getSegmentLength();
    double lastKeyframe = keyframes.get(0);
    double desiredCutTime = segmentLength;
    for (int i = 1; i < keyframes.size(); i++) {
      double keyframe = keyframes.get(i);

      if (keyframe >= desiredCutTime) {
        double currentSegmentLength = keyframe - lastKeyframe;
        segments.add(currentSegmentLength);
        lastKeyframe = keyframe;
        desiredCutTime += segmentLength;
      }
    }

    return segments;
  }
}
