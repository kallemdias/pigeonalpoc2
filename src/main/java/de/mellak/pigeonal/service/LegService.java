package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.Leg;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Leg}.
 */
public interface LegService {
    /**
     * Save a leg.
     *
     * @param leg the entity to save.
     * @return the persisted entity.
     */
    Leg save(Leg leg);

    /**
     * Updates a leg.
     *
     * @param leg the entity to update.
     * @return the persisted entity.
     */
    Leg update(Leg leg);

    /**
     * Partially updates a leg.
     *
     * @param leg the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Leg> partialUpdate(Leg leg);

    /**
     * Get all the legs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Leg> findAll(Pageable pageable);

    /**
     * Get the "id" leg.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Leg> findOne(Long id);

    /**
     * Delete the "id" leg.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
