package dev.amethyst.app.tv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.amethyst.app.config.base.AbstractCrudController;
import dev.amethyst.app.tv.model.TvSeason;
import dev.amethyst.app.tv.repository.TvSeasonRepository;
import dev.amethyst.app.tv.service.TvSeasonService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/seasons")
@Tag(name = "TV Seasons", description = "Retrieve TV Seasons")
public class TvSeasonController extends
    AbstractCrudController<TvSeason, String, TvSeasonRepository, TvSeasonService> {
  protected TvSeasonController(TvSeasonService service) {
    super(service);
  }

  @GetMapping("/{tvSeriesId}/seasons")
  public ResponseEntity<List<TvSeason>> findByTvSeries(@PathVariable("tvSeriesId") String tvSeriesId) {
    return ResponseEntity.ok(this.service.findByTvSeries(tvSeriesId));
  }
}
