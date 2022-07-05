package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.CheckLine;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link CheckLine}.
 */
public interface CheckLineService {
    /**
     * Save a checkLine.
     *
     * @param checkLine the entity to save.
     * @return the persisted entity.
     */
    CheckLine save(CheckLine checkLine);

    /**
     * Updates a checkLine.
     *
     * @param checkLine the entity to update.
     * @return the persisted entity.
     */
    CheckLine update(CheckLine checkLine);

    /**
     * Partially updates a checkLine.
     *
     * @param checkLine the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CheckLine> partialUpdate(CheckLine checkLine);

    /**
     * Get all the checkLines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CheckLine> findAll(Pageable pageable);

    /**
     * Get the "id" checkLine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CheckLine> findOne(Long id);

    /**
     * Delete the "id" checkLine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
