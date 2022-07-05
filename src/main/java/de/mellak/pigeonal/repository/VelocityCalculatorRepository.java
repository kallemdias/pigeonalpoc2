package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.VelocityCalculator;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the VelocityCalculator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VelocityCalculatorRepository extends JpaRepository<VelocityCalculator, Long> {}
