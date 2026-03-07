package dev.amethyst.app.tv.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.amethyst.app.config.base.AbstractCrudService;
import dev.amethyst.app.tv.model.TvSeason;
import dev.amethyst.app.tv.model.TvSeries;
import dev.amethyst.app.tv.repository.TvSeriesRepository;
import jakarta.transaction.Transactional;

@Service
public class TvSeriesService
    extends AbstractCrudService<TvSeries, String, TvSeriesRepository> {
  @Autowired
  private TvSeasonService tvSeasonService;

  protected TvSeriesService(TvSeriesRepository repository) {
    super(repository);
  }

  public Optional<TvSeries> findByName(String name) {
    return this.repository.findByName(name);
  }

  public Optional<TvSeries> findByExternalId(String externalId) {
    return this.repository.findByExternalId(externalId);
  }

  @Transactional
  public void addTvSeasonToTvSeries(String tvSeasonId, String tvSeriesId) {
    if (tvSeriesId == null || tvSeasonId == null)
      return;

    TvSeries tvSeries = this.repository
        .findById(tvSeriesId)
        .orElseThrow();

    TvSeason tvSeason = this.tvSeasonService.findById(tvSeasonId)
        .orElseThrow();

    if (tvSeries.getTvSeasons().contains(tvSeason)
        && tvSeason.getTvSeries() != null
        && tvSeason.getTvSeries().equals(tvSeries)) {
      return;
    }

    // Estalish links:
    // TvSeries -> TvSeasons
    tvSeries.getTvSeasons().add(tvSeason);
    // TvSeason -> TvSeries
    tvSeason.setTvSeries(tvSeries);
    this.tvSeasonService.save(tvSeason);
  }
}
