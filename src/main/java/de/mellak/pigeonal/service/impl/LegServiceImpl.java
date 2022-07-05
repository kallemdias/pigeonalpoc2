package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.Leg;
import de.mellak.pigeonal.repository.LegRepository;
import de.mellak.pigeonal.service.LegService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Leg}.
 */
@Service
@Transactional
public class LegServiceImpl implements LegService {

    private final Logger log = LoggerFactory.getLogger(LegServiceImpl.class);

    private final LegRepository legRepository;

    public LegServiceImpl(LegRepository legRepository) {
        this.legRepository = legRepository;
    }

    @Override
    public Leg save(Leg leg) {
        log.debug("Request to save Leg : {}", leg);
        return legRepository.save(leg);
    }

    @Override
    public Leg update(Leg leg) {
        log.debug("Request to save Leg : {}", leg);
        return legRepository.save(leg);
    }

    @Override
    public Optional<Leg> partialUpdate(Leg leg) {
        log.debug("Request to partially update Leg : {}", leg);

        return legRepository
            .findById(leg.getId())
            .map(existingLeg -> {
                if (leg.getOrder() != null) {
                    existingLeg.setOrder(leg.getOrder());
                }
                if (leg.getDepLatDeg() != null) {
                    existingLeg.setDepLatDeg(leg.getDepLatDeg());
                }
                if (leg.getDepLatMin() != null) {
                    existingLeg.setDepLatMin(leg.getDepLatMin());
                }
                if (leg.getDepLatSec() != null) {
                    existingLeg.setDepLatSec(leg.getDepLatSec());
                }
                if (leg.getDepLatDirection() != null) {
                    existingLeg.setDepLatDirection(leg.getDepLatDirection());
                }
                if (leg.getDepLngDeg() != null) {
                    existingLeg.setDepLngDeg(leg.getDepLngDeg());
                }
                if (leg.getDepLngMin() != null) {
                    existingLeg.setDepLngMin(leg.getDepLngMin());
                }
                if (leg.getDepLngSec() != null) {
                    existingLeg.setDepLngSec(leg.getDepLngSec());
                }
                if (leg.getDepLngDirection() != null) {
                    existingLeg.setDepLngDirection(leg.getDepLngDirection());
                }
                if (leg.getDepLatDisplayedValue() != null) {
                    existingLeg.setDepLatDisplayedValue(leg.getDepLatDisplayedValue());
                }
                if (leg.getDepLngDisplayedValue() != null) {
                    existingLeg.setDepLngDisplayedValue(leg.getDepLngDisplayedValue());
                }
                if (leg.getDepLatDecimal() != null) {
                    existingLeg.setDepLatDecimal(leg.getDepLatDecimal());
                }
                if (leg.getDepLngDecimal() != null) {
                    existingLeg.setDepLngDecimal(leg.getDepLngDecimal());
                }
                if (leg.getArrLatDeg() != null) {
                    existingLeg.setArrLatDeg(leg.getArrLatDeg());
                }
                if (leg.getArrLatMin() != null) {
                    existingLeg.setArrLatMin(leg.getArrLatMin());
                }
                if (leg.getArrLatSec() != null) {
                    existingLeg.setArrLatSec(leg.getArrLatSec());
                }
                if (leg.getArrLatDirection() != null) {
                    existingLeg.setArrLatDirection(leg.getArrLatDirection());
                }
                if (leg.getArrLngDeg() != null) {
                    existingLeg.setArrLngDeg(leg.getArrLngDeg());
                }
                if (leg.getArrLngMin() != null) {
                    existingLeg.setArrLngMin(leg.getArrLngMin());
                }
                if (leg.getArrLngSec() != null) {
                    existingLeg.setArrLngSec(leg.getArrLngSec());
                }
                if (leg.getArrLngDirection() != null) {
                    existingLeg.setArrLngDirection(leg.getArrLngDirection());
                }
                if (leg.getArrLatDisplayedValue() != null) {
                    existingLeg.setArrLatDisplayedValue(leg.getArrLatDisplayedValue());
                }
                if (leg.getArrLngDisplayedValue() != null) {
                    existingLeg.setArrLngDisplayedValue(leg.getArrLngDisplayedValue());
                }
                if (leg.getArrLatDecimal() != null) {
                    existingLeg.setArrLatDecimal(leg.getArrLatDecimal());
                }
                if (leg.getArrLngDecimal() != null) {
                    existingLeg.setArrLngDecimal(leg.getArrLngDecimal());
                }
                if (leg.getCheckPointDistance() != null) {
                    existingLeg.setCheckPointDistance(leg.getCheckPointDistance());
                }
                if (leg.getCheckLinePointDistance() != null) {
                    existingLeg.setCheckLinePointDistance(leg.getCheckLinePointDistance());
                }
                if (leg.getTimeWindow() != null) {
                    existingLeg.setTimeWindow(leg.getTimeWindow());
                }

                return existingLeg;
            })
            .map(legRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Leg> findAll(Pageable pageable) {
        log.debug("Request to get all Legs");
        return legRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Leg> findOne(Long id) {
        log.debug("Request to get Leg : {}", id);
        return legRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Leg : {}", id);
        legRepository.deleteById(id);
    }
}
