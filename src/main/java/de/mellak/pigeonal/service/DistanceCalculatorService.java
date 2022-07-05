package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.DistanceCalculator;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link DistanceCalculator}.
 */
public interface DistanceCalculatorService {
    /**
     * Save a distanceCalculator.
     *
     * @param distanceCalculator the entity to save.
     * @return the persisted entity.
     */
    DistanceCalculator save(DistanceCalculator distanceCalculator);

    /**
     * Updates a distanceCalculator.
     *
     * @param distanceCalculator the entity to update.
     * @return the persisted entity.
     */
    DistanceCalculator update(DistanceCalculator distanceCalculator);

    /**
     * Partially updates a distanceCalculator.
     *
     * @param distanceCalculator the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DistanceCalculator> partialUpdate(DistanceCalculator distanceCalculator);

    /**
     * Get all the distanceCalculators.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DistanceCalculator> findAll(Pageable pageable);

    /**
     * Get the "id" distanceCalculator.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DistanceCalculator> findOne(Long id);

    /**
     * Delete the "id" distanceCalculator.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
