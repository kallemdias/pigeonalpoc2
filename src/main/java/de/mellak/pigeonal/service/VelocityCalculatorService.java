package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.VelocityCalculator;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link VelocityCalculator}.
 */
public interface VelocityCalculatorService {
    /**
     * Save a velocityCalculator.
     *
     * @param velocityCalculator the entity to save.
     * @return the persisted entity.
     */
    VelocityCalculator save(VelocityCalculator velocityCalculator);

    /**
     * Updates a velocityCalculator.
     *
     * @param velocityCalculator the entity to update.
     * @return the persisted entity.
     */
    VelocityCalculator update(VelocityCalculator velocityCalculator);

    /**
     * Partially updates a velocityCalculator.
     *
     * @param velocityCalculator the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VelocityCalculator> partialUpdate(VelocityCalculator velocityCalculator);

    /**
     * Get all the velocityCalculators.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VelocityCalculator> findAll(Pageable pageable);

    /**
     * Get the "id" velocityCalculator.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VelocityCalculator> findOne(Long id);

    /**
     * Delete the "id" velocityCalculator.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
