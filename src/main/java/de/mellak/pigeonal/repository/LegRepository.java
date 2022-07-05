package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.Leg;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Leg entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LegRepository extends JpaRepository<Leg, Long> {}
