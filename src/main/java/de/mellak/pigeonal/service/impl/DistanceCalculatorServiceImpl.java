package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.DistanceCalculator;
import de.mellak.pigeonal.repository.DistanceCalculatorRepository;
import de.mellak.pigeonal.service.DistanceCalculatorService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DistanceCalculator}.
 */
@Service
@Transactional
public class DistanceCalculatorServiceImpl implements DistanceCalculatorService {

    private final Logger log = LoggerFactory.getLogger(DistanceCalculatorServiceImpl.class);

    private final DistanceCalculatorRepository distanceCalculatorRepository;

    public DistanceCalculatorServiceImpl(DistanceCalculatorRepository distanceCalculatorRepository) {
        this.distanceCalculatorRepository = distanceCalculatorRepository;
    }

    @Override
    public DistanceCalculator save(DistanceCalculator distanceCalculator) {
        log.debug("Request to save DistanceCalculator : {}", distanceCalculator);
        return distanceCalculatorRepository.save(distanceCalculator);
    }

    @Override
    public DistanceCalculator update(DistanceCalculator distanceCalculator) {
        log.debug("Request to save DistanceCalculator : {}", distanceCalculator);
        return distanceCalculatorRepository.save(distanceCalculator);
    }

    @Override
    public Optional<DistanceCalculator> partialUpdate(DistanceCalculator distanceCalculator) {
        log.debug("Request to partially update DistanceCalculator : {}", distanceCalculator);

        return distanceCalculatorRepository
            .findById(distanceCalculator.getId())
            .map(existingDistanceCalculator -> {
                if (distanceCalculator.getDepLatDeg() != null) {
                    existingDistanceCalculator.setDepLatDeg(distanceCalculator.getDepLatDeg());
                }
                if (distanceCalculator.getDepLatMin() != null) {
                    existingDistanceCalculator.setDepLatMin(distanceCalculator.getDepLatMin());
                }
                if (distanceCalculator.getDepLatSec() != null) {
                    existingDistanceCalculator.setDepLatSec(distanceCalculator.getDepLatSec());
                }
                if (distanceCalculator.getDepLatDirection() != null) {
                    existingDistanceCalculator.setDepLatDirection(distanceCalculator.getDepLatDirection());
                }
                if (distanceCalculator.getDepLngDeg() != null) {
                    existingDistanceCalculator.setDepLngDeg(distanceCalculator.getDepLngDeg());
                }
                if (distanceCalculator.getDepLngMin() != null) {
                    existingDistanceCalculator.setDepLngMin(distanceCalculator.getDepLngMin());
                }
                if (distanceCalculator.getDepLngSec() != null) {
                    existingDistanceCalculator.setDepLngSec(distanceCalculator.getDepLngSec());
                }
                if (distanceCalculator.getDepLngDirection() != null) {
                    existingDistanceCalculator.setDepLngDirection(distanceCalculator.getDepLngDirection());
                }
                if (distanceCalculator.getDepLatDisplayedValue() != null) {
                    existingDistanceCalculator.setDepLatDisplayedValue(distanceCalculator.getDepLatDisplayedValue());
                }
                if (distanceCalculator.getDepLngDisplayedValue() != null) {
                    existingDistanceCalculator.setDepLngDisplayedValue(distanceCalculator.getDepLngDisplayedValue());
                }
                if (distanceCalculator.getDepLatDecimal() != null) {
                    existingDistanceCalculator.setDepLatDecimal(distanceCalculator.getDepLatDecimal());
                }
                if (distanceCalculator.getDepLngDecimal() != null) {
                    existingDistanceCalculator.setDepLngDecimal(distanceCalculator.getDepLngDecimal());
                }
                if (distanceCalculator.getArrLatDeg() != null) {
                    existingDistanceCalculator.setArrLatDeg(distanceCalculator.getArrLatDeg());
                }
                if (distanceCalculator.getArrLatMin() != null) {
                    existingDistanceCalculator.setArrLatMin(distanceCalculator.getArrLatMin());
                }
                if (distanceCalculator.getArrLatSec() != null) {
                    existingDistanceCalculator.setArrLatSec(distanceCalculator.getArrLatSec());
                }
                if (distanceCalculator.getArrLatDirection() != null) {
                    existingDistanceCalculator.setArrLatDirection(distanceCalculator.getArrLatDirection());
                }
                if (distanceCalculator.getArrLngDeg() != null) {
                    existingDistanceCalculator.setArrLngDeg(distanceCalculator.getArrLngDeg());
                }
                if (distanceCalculator.getArrLngMin() != null) {
                    existingDistanceCalculator.setArrLngMin(distanceCalculator.getArrLngMin());
                }
                if (distanceCalculator.getArrLngSec() != null) {
                    existingDistanceCalculator.setArrLngSec(distanceCalculator.getArrLngSec());
                }
                if (distanceCalculator.getArrLngDirection() != null) {
                    existingDistanceCalculator.setArrLngDirection(distanceCalculator.getArrLngDirection());
                }
                if (distanceCalculator.getArrLatDisplayedValue() != null) {
                    existingDistanceCalculator.setArrLatDisplayedValue(distanceCalculator.getArrLatDisplayedValue());
                }
                if (distanceCalculator.getArrLngDisplayedValue() != null) {
                    existingDistanceCalculator.setArrLngDisplayedValue(distanceCalculator.getArrLngDisplayedValue());
                }
                if (distanceCalculator.getArrLatDecimal() != null) {
                    existingDistanceCalculator.setArrLatDecimal(distanceCalculator.getArrLatDecimal());
                }
                if (distanceCalculator.getArrLngDecimal() != null) {
                    existingDistanceCalculator.setArrLngDecimal(distanceCalculator.getArrLngDecimal());
                }
                if (distanceCalculator.getDistanceInMeters() != null) {
                    existingDistanceCalculator.setDistanceInMeters(distanceCalculator.getDistanceInMeters());
                }
                if (distanceCalculator.getDistanceInMetersDispVal() != null) {
                    existingDistanceCalculator.setDistanceInMetersDispVal(distanceCalculator.getDistanceInMetersDispVal());
                }
                if (distanceCalculator.getDepLink() != null) {
                    existingDistanceCalculator.setDepLink(distanceCalculator.getDepLink());
                }
                if (distanceCalculator.getArrLink() != null) {
                    existingDistanceCalculator.setArrLink(distanceCalculator.getArrLink());
                }

                return existingDistanceCalculator;
            })
            .map(distanceCalculatorRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DistanceCalculator> findAll(Pageable pageable) {
        log.debug("Request to get all DistanceCalculators");
        return distanceCalculatorRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DistanceCalculator> findOne(Long id) {
        log.debug("Request to get DistanceCalculator : {}", id);
        return distanceCalculatorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DistanceCalculator : {}", id);
        distanceCalculatorRepository.deleteById(id);
    }
}
