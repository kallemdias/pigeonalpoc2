package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.CheckPoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CheckPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CheckPointRepository extends JpaRepository<CheckPoint, Long> {}
