package de.mellak.pigeonal.repository;

import de.mellak.pigeonal.domain.WeatherReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WeatherReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WeatherReportRepository extends JpaRepository<WeatherReport, Long> {}
