package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.WeatherDataPoint;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link WeatherDataPoint}.
 */
public interface WeatherDataPointService {
    /**
     * Save a weatherDataPoint.
     *
     * @param weatherDataPoint the entity to save.
     * @return the persisted entity.
     */
    WeatherDataPoint save(WeatherDataPoint weatherDataPoint);

    /**
     * Updates a weatherDataPoint.
     *
     * @param weatherDataPoint the entity to update.
     * @return the persisted entity.
     */
    WeatherDataPoint update(WeatherDataPoint weatherDataPoint);

    /**
     * Partially updates a weatherDataPoint.
     *
     * @param weatherDataPoint the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WeatherDataPoint> partialUpdate(WeatherDataPoint weatherDataPoint);

    /**
     * Get all the weatherDataPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WeatherDataPoint> findAll(Pageable pageable);
    /**
     * Get all the WeatherDataPoint where ReleavanceHeader is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<WeatherDataPoint> findAllWhereReleavanceHeaderIsNull();

    /**
     * Get the "id" weatherDataPoint.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WeatherDataPoint> findOne(Long id);

    /**
     * Delete the "id" weatherDataPoint.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
