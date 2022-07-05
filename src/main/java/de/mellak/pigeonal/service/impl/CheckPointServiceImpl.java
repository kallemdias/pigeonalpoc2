package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.CheckPoint;
import de.mellak.pigeonal.repository.CheckPointRepository;
import de.mellak.pigeonal.service.CheckPointService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CheckPoint}.
 */
@Service
@Transactional
public class CheckPointServiceImpl implements CheckPointService {

    private final Logger log = LoggerFactory.getLogger(CheckPointServiceImpl.class);

    private final CheckPointRepository checkPointRepository;

    public CheckPointServiceImpl(CheckPointRepository checkPointRepository) {
        this.checkPointRepository = checkPointRepository;
    }

    @Override
    public CheckPoint save(CheckPoint checkPoint) {
        log.debug("Request to save CheckPoint : {}", checkPoint);
        return checkPointRepository.save(checkPoint);
    }

    @Override
    public CheckPoint update(CheckPoint checkPoint) {
        log.debug("Request to save CheckPoint : {}", checkPoint);
        return checkPointRepository.save(checkPoint);
    }

    @Override
    public Optional<CheckPoint> partialUpdate(CheckPoint checkPoint) {
        log.debug("Request to partially update CheckPoint : {}", checkPoint);

        return checkPointRepository
            .findById(checkPoint.getId())
            .map(existingCheckPoint -> {
                if (checkPoint.getOrder() != null) {
                    existingCheckPoint.setOrder(checkPoint.getOrder());
                }
                if (checkPoint.getDateTime() != null) {
                    existingCheckPoint.setDateTime(checkPoint.getDateTime());
                }
                if (checkPoint.getLocationName() != null) {
                    existingCheckPoint.setLocationName(checkPoint.getLocationName());
                }
                if (checkPoint.getLatDecimal() != null) {
                    existingCheckPoint.setLatDecimal(checkPoint.getLatDecimal());
                }
                if (checkPoint.getLngDecimal() != null) {
                    existingCheckPoint.setLngDecimal(checkPoint.getLngDecimal());
                }
                if (checkPoint.getDistance() != null) {
                    existingCheckPoint.setDistance(checkPoint.getDistance());
                }
                if (checkPoint.getDistanceDisplayedValue() != null) {
                    existingCheckPoint.setDistanceDisplayedValue(checkPoint.getDistanceDisplayedValue());
                }
                if (checkPoint.getLink() != null) {
                    existingCheckPoint.setLink(checkPoint.getLink());
                }
                if (checkPoint.getAlerts() != null) {
                    existingCheckPoint.setAlerts(checkPoint.getAlerts());
                }

                return existingCheckPoint;
            })
            .map(checkPointRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CheckPoint> findAll(Pageable pageable) {
        log.debug("Request to get all CheckPoints");
        return checkPointRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CheckPoint> findOne(Long id) {
        log.debug("Request to get CheckPoint : {}", id);
        return checkPointRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CheckPoint : {}", id);
        checkPointRepository.deleteById(id);
    }
}
