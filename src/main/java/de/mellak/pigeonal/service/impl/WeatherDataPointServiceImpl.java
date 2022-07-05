package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.WeatherDataPoint;
import de.mellak.pigeonal.repository.WeatherDataPointRepository;
import de.mellak.pigeonal.service.WeatherDataPointService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WeatherDataPoint}.
 */
@Service
@Transactional
public class WeatherDataPointServiceImpl implements WeatherDataPointService {

    private final Logger log = LoggerFactory.getLogger(WeatherDataPointServiceImpl.class);

    private final WeatherDataPointRepository weatherDataPointRepository;

    public WeatherDataPointServiceImpl(WeatherDataPointRepository weatherDataPointRepository) {
        this.weatherDataPointRepository = weatherDataPointRepository;
    }

    @Override
    public WeatherDataPoint save(WeatherDataPoint weatherDataPoint) {
        log.debug("Request to save WeatherDataPoint : {}", weatherDataPoint);
        return weatherDataPointRepository.save(weatherDataPoint);
    }

    @Override
    public WeatherDataPoint update(WeatherDataPoint weatherDataPoint) {
        log.debug("Request to save WeatherDataPoint : {}", weatherDataPoint);
        return weatherDataPointRepository.save(weatherDataPoint);
    }

    @Override
    public Optional<WeatherDataPoint> partialUpdate(WeatherDataPoint weatherDataPoint) {
        log.debug("Request to partially update WeatherDataPoint : {}", weatherDataPoint);

        return weatherDataPointRepository
            .findById(weatherDataPoint.getId())
            .map(existingWeatherDataPoint -> {
                if (weatherDataPoint.getProperty() != null) {
                    existingWeatherDataPoint.setProperty(weatherDataPoint.getProperty());
                }
                if (weatherDataPoint.getMidNight() != null) {
                    existingWeatherDataPoint.setMidNight(weatherDataPoint.getMidNight());
                }
                if (weatherDataPoint.getMigNightRelevance() != null) {
                    existingWeatherDataPoint.setMigNightRelevance(weatherDataPoint.getMigNightRelevance());
                }
                if (weatherDataPoint.getOne() != null) {
                    existingWeatherDataPoint.setOne(weatherDataPoint.getOne());
                }
                if (weatherDataPoint.getOneRelevance() != null) {
                    existingWeatherDataPoint.setOneRelevance(weatherDataPoint.getOneRelevance());
                }
                if (weatherDataPoint.getTwo() != null) {
                    existingWeatherDataPoint.setTwo(weatherDataPoint.getTwo());
                }
                if (weatherDataPoint.getTwoRelevance() != null) {
                    existingWeatherDataPoint.setTwoRelevance(weatherDataPoint.getTwoRelevance());
                }
                if (weatherDataPoint.getThree() != null) {
                    existingWeatherDataPoint.setThree(weatherDataPoint.getThree());
                }
                if (weatherDataPoint.getThreeRelevance() != null) {
                    existingWeatherDataPoint.setThreeRelevance(weatherDataPoint.getThreeRelevance());
                }
                if (weatherDataPoint.getFour() != null) {
                    existingWeatherDataPoint.setFour(weatherDataPoint.getFour());
                }
                if (weatherDataPoint.getFourRelevance() != null) {
                    existingWeatherDataPoint.setFourRelevance(weatherDataPoint.getFourRelevance());
                }
                if (weatherDataPoint.getFive() != null) {
                    existingWeatherDataPoint.setFive(weatherDataPoint.getFive());
                }
                if (weatherDataPoint.getFiveRelevance() != null) {
                    existingWeatherDataPoint.setFiveRelevance(weatherDataPoint.getFiveRelevance());
                }
                if (weatherDataPoint.getSix() != null) {
                    existingWeatherDataPoint.setSix(weatherDataPoint.getSix());
                }
                if (weatherDataPoint.getSixRelevance() != null) {
                    existingWeatherDataPoint.setSixRelevance(weatherDataPoint.getSixRelevance());
                }
                if (weatherDataPoint.getSeven() != null) {
                    existingWeatherDataPoint.setSeven(weatherDataPoint.getSeven());
                }
                if (weatherDataPoint.getSevenRelevance() != null) {
                    existingWeatherDataPoint.setSevenRelevance(weatherDataPoint.getSevenRelevance());
                }
                if (weatherDataPoint.getEight() != null) {
                    existingWeatherDataPoint.setEight(weatherDataPoint.getEight());
                }
                if (weatherDataPoint.getEightRelevance() != null) {
                    existingWeatherDataPoint.setEightRelevance(weatherDataPoint.getEightRelevance());
                }
                if (weatherDataPoint.getNine() != null) {
                    existingWeatherDataPoint.setNine(weatherDataPoint.getNine());
                }
                if (weatherDataPoint.getNineRelevance() != null) {
                    existingWeatherDataPoint.setNineRelevance(weatherDataPoint.getNineRelevance());
                }
                if (weatherDataPoint.getTen() != null) {
                    existingWeatherDataPoint.setTen(weatherDataPoint.getTen());
                }
                if (weatherDataPoint.getTenRelevance() != null) {
                    existingWeatherDataPoint.setTenRelevance(weatherDataPoint.getTenRelevance());
                }
                if (weatherDataPoint.getEleven() != null) {
                    existingWeatherDataPoint.setEleven(weatherDataPoint.getEleven());
                }
                if (weatherDataPoint.getElevenRelevance() != null) {
                    existingWeatherDataPoint.setElevenRelevance(weatherDataPoint.getElevenRelevance());
                }
                if (weatherDataPoint.getTwelve() != null) {
                    existingWeatherDataPoint.setTwelve(weatherDataPoint.getTwelve());
                }
                if (weatherDataPoint.getTwelveRelevance() != null) {
                    existingWeatherDataPoint.setTwelveRelevance(weatherDataPoint.getTwelveRelevance());
                }
                if (weatherDataPoint.getThirteen() != null) {
                    existingWeatherDataPoint.setThirteen(weatherDataPoint.getThirteen());
                }
                if (weatherDataPoint.getThirteenRelevance() != null) {
                    existingWeatherDataPoint.setThirteenRelevance(weatherDataPoint.getThirteenRelevance());
                }
                if (weatherDataPoint.getFourteen() != null) {
                    existingWeatherDataPoint.setFourteen(weatherDataPoint.getFourteen());
                }
                if (weatherDataPoint.getFourteenRelevance() != null) {
                    existingWeatherDataPoint.setFourteenRelevance(weatherDataPoint.getFourteenRelevance());
                }
                if (weatherDataPoint.getFifteen() != null) {
                    existingWeatherDataPoint.setFifteen(weatherDataPoint.getFifteen());
                }
                if (weatherDataPoint.getFifteenRelevance() != null) {
                    existingWeatherDataPoint.setFifteenRelevance(weatherDataPoint.getFifteenRelevance());
                }
                if (weatherDataPoint.getSixteen() != null) {
                    existingWeatherDataPoint.setSixteen(weatherDataPoint.getSixteen());
                }
                if (weatherDataPoint.getSixteenRelevance() != null) {
                    existingWeatherDataPoint.setSixteenRelevance(weatherDataPoint.getSixteenRelevance());
                }
                if (weatherDataPoint.getSeventeen() != null) {
                    existingWeatherDataPoint.setSeventeen(weatherDataPoint.getSeventeen());
                }
                if (weatherDataPoint.getSeventeenRelevance() != null) {
                    existingWeatherDataPoint.setSeventeenRelevance(weatherDataPoint.getSeventeenRelevance());
                }
                if (weatherDataPoint.getEighteen() != null) {
                    existingWeatherDataPoint.setEighteen(weatherDataPoint.getEighteen());
                }
                if (weatherDataPoint.getEighteenRelevance() != null) {
                    existingWeatherDataPoint.setEighteenRelevance(weatherDataPoint.getEighteenRelevance());
                }
                if (weatherDataPoint.getNineteen() != null) {
                    existingWeatherDataPoint.setNineteen(weatherDataPoint.getNineteen());
                }
                if (weatherDataPoint.getNineteenRelevance() != null) {
                    existingWeatherDataPoint.setNineteenRelevance(weatherDataPoint.getNineteenRelevance());
                }
                if (weatherDataPoint.getTwenty() != null) {
                    existingWeatherDataPoint.setTwenty(weatherDataPoint.getTwenty());
                }
                if (weatherDataPoint.getTwentyRelevance() != null) {
                    existingWeatherDataPoint.setTwentyRelevance(weatherDataPoint.getTwentyRelevance());
                }
                if (weatherDataPoint.getTwentyOne() != null) {
                    existingWeatherDataPoint.setTwentyOne(weatherDataPoint.getTwentyOne());
                }
                if (weatherDataPoint.getTwentyOneRelevance() != null) {
                    existingWeatherDataPoint.setTwentyOneRelevance(weatherDataPoint.getTwentyOneRelevance());
                }
                if (weatherDataPoint.getTwentyTwo() != null) {
                    existingWeatherDataPoint.setTwentyTwo(weatherDataPoint.getTwentyTwo());
                }
                if (weatherDataPoint.getTwentyTwoRelevance() != null) {
                    existingWeatherDataPoint.setTwentyTwoRelevance(weatherDataPoint.getTwentyTwoRelevance());
                }
                if (weatherDataPoint.getTwentyThree() != null) {
                    existingWeatherDataPoint.setTwentyThree(weatherDataPoint.getTwentyThree());
                }

                return existingWeatherDataPoint;
            })
            .map(weatherDataPointRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WeatherDataPoint> findAll(Pageable pageable) {
        log.debug("Request to get all WeatherDataPoints");
        return weatherDataPointRepository.findAll(pageable);
    }

    /**
     *  Get all the weatherDataPoints where ReleavanceHeader is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WeatherDataPoint> findAllWhereReleavanceHeaderIsNull() {
        log.debug("Request to get all weatherDataPoints where ReleavanceHeader is null");
        return StreamSupport
            .stream(weatherDataPointRepository.findAll().spliterator(), false)
            .filter(weatherDataPoint -> weatherDataPoint.getReleavanceHeader() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WeatherDataPoint> findOne(Long id) {
        log.debug("Request to get WeatherDataPoint : {}", id);
        return weatherDataPointRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WeatherDataPoint : {}", id);
        weatherDataPointRepository.deleteById(id);
    }
}
