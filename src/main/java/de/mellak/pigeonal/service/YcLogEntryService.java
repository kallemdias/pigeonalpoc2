package de.mellak.pigeonal.service;

import de.mellak.pigeonal.domain.YcLogEntry;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link YcLogEntry}.
 */
public interface YcLogEntryService {
    /**
     * Save a ycLogEntry.
     *
     * @param ycLogEntry the entity to save.
     * @return the persisted entity.
     */
    YcLogEntry save(YcLogEntry ycLogEntry);

    /**
     * Updates a ycLogEntry.
     *
     * @param ycLogEntry the entity to update.
     * @return the persisted entity.
     */
    YcLogEntry update(YcLogEntry ycLogEntry);

    /**
     * Partially updates a ycLogEntry.
     *
     * @param ycLogEntry the entity to update partially.
     * @return the persisted entity.
     */
    Optional<YcLogEntry> partialUpdate(YcLogEntry ycLogEntry);

    /**
     * Get all the ycLogEntries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<YcLogEntry> findAll(Pageable pageable);

    /**
     * Get the "id" ycLogEntry.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<YcLogEntry> findOne(Long id);

    /**
     * Delete the "id" ycLogEntry.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
