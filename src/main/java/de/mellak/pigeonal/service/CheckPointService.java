package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.CheckPoint;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link CheckPoint}.
 */
public interface CheckPointService {
    /**
     * Save a checkPoint.
     *
     * @param checkPoint the entity to save.
     * @return the persisted entity.
     */
    CheckPoint save(CheckPoint checkPoint);

    /**
     * Updates a checkPoint.
     *
     * @param checkPoint the entity to update.
     * @return the persisted entity.
     */
    CheckPoint update(CheckPoint checkPoint);

    /**
     * Partially updates a checkPoint.
     *
     * @param checkPoint the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CheckPoint> partialUpdate(CheckPoint checkPoint);

    /**
     * Get all the checkPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CheckPoint> findAll(Pageable pageable);

    /**
     * Get the "id" checkPoint.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CheckPoint> findOne(Long id);

    /**
     * Delete the "id" checkPoint.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
