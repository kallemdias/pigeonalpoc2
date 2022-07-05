package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.RelevanceHeader;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RelevanceHeader}.
 */
public interface RelevanceHeaderService {
    /**
     * Save a relevanceHeader.
     *
     * @param relevanceHeader the entity to save.
     * @return the persisted entity.
     */
    RelevanceHeader save(RelevanceHeader relevanceHeader);

    /**
     * Updates a relevanceHeader.
     *
     * @param relevanceHeader the entity to update.
     * @return the persisted entity.
     */
    RelevanceHeader update(RelevanceHeader relevanceHeader);

    /**
     * Partially updates a relevanceHeader.
     *
     * @param relevanceHeader the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RelevanceHeader> partialUpdate(RelevanceHeader relevanceHeader);

    /**
     * Get all the relevanceHeaders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelevanceHeader> findAll(Pageable pageable);

    /**
     * Get the "id" relevanceHeader.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelevanceHeader> findOne(Long id);

    /**
     * Delete the "id" relevanceHeader.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
