package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.RacingPlan;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RacingPlan}.
 */
public interface RacingPlanService {
    /**
     * Save a racingPlan.
     *
     * @param racingPlan the entity to save.
     * @return the persisted entity.
     */
    RacingPlan save(RacingPlan racingPlan);

    /**
     * Updates a racingPlan.
     *
     * @param racingPlan the entity to update.
     * @return the persisted entity.
     */
    RacingPlan update(RacingPlan racingPlan);

    /**
     * Partially updates a racingPlan.
     *
     * @param racingPlan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RacingPlan> partialUpdate(RacingPlan racingPlan);

    /**
     * Get all the racingPlans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RacingPlan> findAll(Pageable pageable);

    /**
     * Get all the racingPlans with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RacingPlan> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" racingPlan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RacingPlan> findOne(Long id);

    /**
     * Delete the "id" racingPlan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
