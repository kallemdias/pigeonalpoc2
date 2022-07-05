package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.DistanceCalculator;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DistanceCalculator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistanceCalculatorRepository extends JpaRepository<DistanceCalculator, Long> {}
