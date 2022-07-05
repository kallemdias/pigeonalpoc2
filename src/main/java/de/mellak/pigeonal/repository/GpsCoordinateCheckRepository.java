package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.GpsCoordinateCheck;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GpsCoordinateCheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GpsCoordinateCheckRepository extends JpaRepository<GpsCoordinateCheck, Long> {}
