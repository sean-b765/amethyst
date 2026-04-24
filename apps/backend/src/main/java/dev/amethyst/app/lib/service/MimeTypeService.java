package dev.amethyst.app.lib.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MimeTypeService {
  public final Map<String, String> videoTypes = Map.of(
      "mkv", "video/x-matroska",
      "mp4", "video/mp4");

  public final Map<String, String> musicTypes = Map.of(
      "mp3", "audio/mp3");

  public boolean isVideoType(String fileName) {
    for (String type : videoTypes.keySet()) {
      if (StringUtils.endsWith("." + type, fileName)) {
        return true;
      }
    }
    return false;
  }

  public boolean isMusicType(String fileName) {
    for (String type : musicTypes.keySet()) {
      if (StringUtils.endsWith("." + type, fileName)) {
        return true;
      }
    }
    return false;
  }
}
