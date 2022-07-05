package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.WeatherDataPoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WeatherDataPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WeatherDataPointRepository extends JpaRepository<WeatherDataPoint, Long> {}
