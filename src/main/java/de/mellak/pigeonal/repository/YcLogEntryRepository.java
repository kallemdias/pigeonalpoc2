package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.YcLogEntry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the YcLogEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface YcLogEntryRepository extends JpaRepository<YcLogEntry, Long> {}
