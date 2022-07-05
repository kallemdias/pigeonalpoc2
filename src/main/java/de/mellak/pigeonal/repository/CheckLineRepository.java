package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.CheckLine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CheckLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CheckLineRepository extends JpaRepository<CheckLine, Long> {}
