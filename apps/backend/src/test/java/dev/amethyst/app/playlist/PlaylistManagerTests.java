package dev.amethyst.app.playlist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.amethyst.app.metadata.model.KeyframeData;
import dev.amethyst.app.metadata.service.KeyframeExtractor;
import dev.amethyst.app.playlist.service.PlaylistManager;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PlaylistManagerTests {
  @Autowired
  private KeyframeExtractor keyframeExtractor;
  @Autowired
  private PlaylistManager playlistManager;

  @Test
  public void testGetSegments() {
    // String mkv = "E:/Stuff/Media/TV/Game of Thrones/Season 1/Game of Thrones
    // S01E01 - Winter is Coming.mkv";
    // KeyframeData mkvResult = this.extractAndWriteKeyFrameData(mkv);
    // assertTrue(mkvResult.getDuration() > 0);
    // assertFalse(mkvResult.getPositions().isEmpty());

    // KeyframeData keyframeDataMkv = this.loadKeyframeData("Game of Thrones S01E01
    // - Winter is Coming.mkv");
    // this.playlistManager.generateVodPlaylist("", keyframeDataMkv);

    // String mp4 = "E:/Stuff/Media/Movies/Avatar 2009.mp4";
    // KeyframeData mp4Result = this.extractAndWriteKeyFrameData(mp4);
    KeyframeData keyframeDataMp4 = this.loadKeyframeData("Avatar 2009.mp4");
    // List<Double> segments = this.playlistManager.getSegments(keyframeDataMp4);
    String playlist = this.playlistManager.generateVodPlaylist("", keyframeDataMp4);
    System.out.println(playlist.substring(0, 500));

    // assertTrue(mp4Result.getDuration() > 0);
    // assertFalse(mp4Result.getPositions().isEmpty());

    // String hevc =
    // "E:/Stuff/Media/Movies/Sunset.Blvd.1950.720p.BluRay.999MB.HQ.x265.10bit-GalaxyRG.mkv";
    // KeyframeData hevcResult = this.extractAndWriteKeyFrameData(hevc);
    // KeyframeData keyframeDataHevc = this
    // .loadKeyframeData("Sunset.Blvd.1950.720p.BluRay.999MB.HQ.x265.10bit-GalaxyRG.mkv");

    // assertTrue(hevcResult.getDuration() > 0);
    // assertFalse(hevcResult.getPositions().isEmpty());
  }

  private KeyframeData loadKeyframeData(String inputFile) {
    try {
      String inputFilePath = "src/test/resources/data/".concat(inputFile);
      if (!inputFilePath.endsWith("_keyframes.json")) {
        inputFilePath = inputFilePath.concat("_keyframes.json");
      }

      File file = new File(inputFilePath);
      if (!file.exists()) {
        throw new IOException("The input file does not exist: " + inputFilePath);
      }

      FileReader reader = new FileReader(file);
      return new ObjectMapper().readValue(reader, KeyframeData.class);
    } catch (JsonProcessingException e) {
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }

    return null;
  }

  private KeyframeData extractAndWriteKeyFrameData(String inputFile) {
    KeyframeData result = keyframeExtractor.getKeyframeData(inputFile);

    try {
      String json = (new ObjectMapper().writeValueAsString(result));
      String outputFilePath = "src/test/resources/data/".concat(inputFile.replaceAll(".*[/\\\\]", ""))
          .concat("_keyframes.json");
      File outputFile = new File(outputFilePath);
      outputFile.getParentFile().mkdirs();

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getPath()))) {
        writer.write(json);
      }
    } catch (JsonProcessingException e) {
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }

    return result;
  }
}
