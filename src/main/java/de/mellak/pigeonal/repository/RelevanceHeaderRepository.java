package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.RelevanceHeader;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RelevanceHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelevanceHeaderRepository extends JpaRepository<RelevanceHeader, Long> {}
