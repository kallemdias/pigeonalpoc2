package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.WeatherReport;
import de.mellak.pigeonal.repository.WeatherReportRepository;
import de.mellak.pigeonal.service.WeatherReportService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WeatherReport}.
 */
@Service
@Transactional
public class WeatherReportServiceImpl implements WeatherReportService {

    private final Logger log = LoggerFactory.getLogger(WeatherReportServiceImpl.class);

    private final WeatherReportRepository weatherReportRepository;

    public WeatherReportServiceImpl(WeatherReportRepository weatherReportRepository) {
        this.weatherReportRepository = weatherReportRepository;
    }

    @Override
    public WeatherReport save(WeatherReport weatherReport) {
        log.debug("Request to save WeatherReport : {}", weatherReport);
        return weatherReportRepository.save(weatherReport);
    }

    @Override
    public WeatherReport update(WeatherReport weatherReport) {
        log.debug("Request to save WeatherReport : {}", weatherReport);
        return weatherReportRepository.save(weatherReport);
    }

    @Override
    public Optional<WeatherReport> partialUpdate(WeatherReport weatherReport) {
        log.debug("Request to partially update WeatherReport : {}", weatherReport);

        return weatherReportRepository
            .findById(weatherReport.getId())
            .map(existingWeatherReport -> {
                if (weatherReport.getInitiated() != null) {
                    existingWeatherReport.setInitiated(weatherReport.getInitiated());
                }
                if (weatherReport.getReleaseDateTime() != null) {
                    existingWeatherReport.setReleaseDateTime(weatherReport.getReleaseDateTime());
                }
                if (weatherReport.getCheckpointsDistance() != null) {
                    existingWeatherReport.setCheckpointsDistance(weatherReport.getCheckpointsDistance());
                }
                if (weatherReport.getAlerts() != null) {
                    existingWeatherReport.setAlerts(weatherReport.getAlerts());
                }

                return existingWeatherReport;
            })
            .map(weatherReportRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WeatherReport> findAll(Pageable pageable) {
        log.debug("Request to get all WeatherReports");
        return weatherReportRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WeatherReport> findOne(Long id) {
        log.debug("Request to get WeatherReport : {}", id);
        return weatherReportRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WeatherReport : {}", id);
        weatherReportRepository.deleteById(id);
    }
}
