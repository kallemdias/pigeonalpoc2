package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.GpsCoordinateCheck;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link GpsCoordinateCheck}.
 */
public interface GpsCoordinateCheckService {
    /**
     * Save a gpsCoordinateCheck.
     *
     * @param gpsCoordinateCheck the entity to save.
     * @return the persisted entity.
     */
    GpsCoordinateCheck save(GpsCoordinateCheck gpsCoordinateCheck);

    /**
     * Updates a gpsCoordinateCheck.
     *
     * @param gpsCoordinateCheck the entity to update.
     * @return the persisted entity.
     */
    GpsCoordinateCheck update(GpsCoordinateCheck gpsCoordinateCheck);

    /**
     * Partially updates a gpsCoordinateCheck.
     *
     * @param gpsCoordinateCheck the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GpsCoordinateCheck> partialUpdate(GpsCoordinateCheck gpsCoordinateCheck);

    /**
     * Get all the gpsCoordinateChecks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GpsCoordinateCheck> findAll(Pageable pageable);

    /**
     * Get the "id" gpsCoordinateCheck.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GpsCoordinateCheck> findOne(Long id);

    /**
     * Delete the "id" gpsCoordinateCheck.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
