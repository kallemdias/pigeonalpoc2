package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.RacingPlan;
import de.mellak.pigeonal.repository.RacingPlanRepository;
import de.mellak.pigeonal.service.RacingPlanService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RacingPlan}.
 */
@Service
@Transactional
public class RacingPlanServiceImpl implements RacingPlanService {

    private final Logger log = LoggerFactory.getLogger(RacingPlanServiceImpl.class);

    private final RacingPlanRepository racingPlanRepository;

    public RacingPlanServiceImpl(RacingPlanRepository racingPlanRepository) {
        this.racingPlanRepository = racingPlanRepository;
    }

    @Override
    public RacingPlan save(RacingPlan racingPlan) {
        log.debug("Request to save RacingPlan : {}", racingPlan);
        return racingPlanRepository.save(racingPlan);
    }

    @Override
    public RacingPlan update(RacingPlan racingPlan) {
        log.debug("Request to save RacingPlan : {}", racingPlan);
        return racingPlanRepository.save(racingPlan);
    }

    @Override
    public Optional<RacingPlan> partialUpdate(RacingPlan racingPlan) {
        log.debug("Request to partially update RacingPlan : {}", racingPlan);

        return racingPlanRepository
            .findById(racingPlan.getId())
            .map(existingRacingPlan -> {
                if (racingPlan.getName() != null) {
                    existingRacingPlan.setName(racingPlan.getName());
                }
                if (racingPlan.getAssocation() != null) {
                    existingRacingPlan.setAssocation(racingPlan.getAssocation());
                }
                if (racingPlan.getReleaseDate() != null) {
                    existingRacingPlan.setReleaseDate(racingPlan.getReleaseDate());
                }
                if (racingPlan.getReleasePoint() != null) {
                    existingRacingPlan.setReleasePoint(racingPlan.getReleasePoint());
                }
                if (racingPlan.getArrivalPoint() != null) {
                    existingRacingPlan.setArrivalPoint(racingPlan.getArrivalPoint());
                }
                if (racingPlan.getReleasePointDMS() != null) {
                    existingRacingPlan.setReleasePointDMS(racingPlan.getReleasePointDMS());
                }
                if (racingPlan.getArrivalPointDMS() != null) {
                    existingRacingPlan.setArrivalPointDMS(racingPlan.getArrivalPointDMS());
                }
                if (racingPlan.getReleaseMapLink() != null) {
                    existingRacingPlan.setReleaseMapLink(racingPlan.getReleaseMapLink());
                }
                if (racingPlan.getArrivalMapLink() != null) {
                    existingRacingPlan.setArrivalMapLink(racingPlan.getArrivalMapLink());
                }
                if (racingPlan.getDistance() != null) {
                    existingRacingPlan.setDistance(racingPlan.getDistance());
                }
                if (racingPlan.getDistanceDisplayedValue() != null) {
                    existingRacingPlan.setDistanceDisplayedValue(racingPlan.getDistanceDisplayedValue());
                }
                if (racingPlan.getCheckPointDistance() != null) {
                    existingRacingPlan.setCheckPointDistance(racingPlan.getCheckPointDistance());
                }
                if (racingPlan.getCheckLinedReporting() != null) {
                    existingRacingPlan.setCheckLinedReporting(racingPlan.getCheckLinedReporting());
                }
                if (racingPlan.getCheckLinePointDistance() != null) {
                    existingRacingPlan.setCheckLinePointDistance(racingPlan.getCheckLinePointDistance());
                }
                if (racingPlan.getTimeWindow() != null) {
                    existingRacingPlan.setTimeWindow(racingPlan.getTimeWindow());
                }
                if (racingPlan.getGenerationInProgress() != null) {
                    existingRacingPlan.setGenerationInProgress(racingPlan.getGenerationInProgress());
                }
                if (racingPlan.getGenerated() != null) {
                    existingRacingPlan.setGenerated(racingPlan.getGenerated());
                }
                if (racingPlan.getReset() != null) {
                    existingRacingPlan.setReset(racingPlan.getReset());
                }
                if (racingPlan.getProgress() != null) {
                    existingRacingPlan.setProgress(racingPlan.getProgress());
                }

                return existingRacingPlan;
            })
            .map(racingPlanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RacingPlan> findAll(Pageable pageable) {
        log.debug("Request to get all RacingPlans");
        return racingPlanRepository.findAll(pageable);
    }

    public Page<RacingPlan> findAllWithEagerRelationships(Pageable pageable) {
        return racingPlanRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RacingPlan> findOne(Long id) {
        log.debug("Request to get RacingPlan : {}", id);
        return racingPlanRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RacingPlan : {}", id);
        racingPlanRepository.deleteById(id);
    }
}
