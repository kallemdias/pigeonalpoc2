package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.WeatherReport;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link WeatherReport}.
 */
public interface WeatherReportService {
    /**
     * Save a weatherReport.
     *
     * @param weatherReport the entity to save.
     * @return the persisted entity.
     */
    WeatherReport save(WeatherReport weatherReport);

    /**
     * Updates a weatherReport.
     *
     * @param weatherReport the entity to update.
     * @return the persisted entity.
     */
    WeatherReport update(WeatherReport weatherReport);

    /**
     * Partially updates a weatherReport.
     *
     * @param weatherReport the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WeatherReport> partialUpdate(WeatherReport weatherReport);

    /**
     * Get all the weatherReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WeatherReport> findAll(Pageable pageable);

    /**
     * Get the "id" weatherReport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WeatherReport> findOne(Long id);

    /**
     * Delete the "id" weatherReport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
