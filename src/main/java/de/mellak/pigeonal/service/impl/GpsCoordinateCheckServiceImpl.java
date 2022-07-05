package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.GpsCoordinateCheck;
import de.mellak.pigeonal.repository.GpsCoordinateCheckRepository;
import de.mellak.pigeonal.service.GpsCoordinateCheckService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GpsCoordinateCheck}.
 */
@Service
@Transactional
public class GpsCoordinateCheckServiceImpl implements GpsCoordinateCheckService {

    private final Logger log = LoggerFactory.getLogger(GpsCoordinateCheckServiceImpl.class);

    private final GpsCoordinateCheckRepository gpsCoordinateCheckRepository;

    public GpsCoordinateCheckServiceImpl(GpsCoordinateCheckRepository gpsCoordinateCheckRepository) {
        this.gpsCoordinateCheckRepository = gpsCoordinateCheckRepository;
    }

    @Override
    public GpsCoordinateCheck save(GpsCoordinateCheck gpsCoordinateCheck) {
        log.debug("Request to save GpsCoordinateCheck : {}", gpsCoordinateCheck);
        return gpsCoordinateCheckRepository.save(gpsCoordinateCheck);
    }

    @Override
    public GpsCoordinateCheck update(GpsCoordinateCheck gpsCoordinateCheck) {
        log.debug("Request to save GpsCoordinateCheck : {}", gpsCoordinateCheck);
        return gpsCoordinateCheckRepository.save(gpsCoordinateCheck);
    }

    @Override
    public Optional<GpsCoordinateCheck> partialUpdate(GpsCoordinateCheck gpsCoordinateCheck) {
        log.debug("Request to partially update GpsCoordinateCheck : {}", gpsCoordinateCheck);

        return gpsCoordinateCheckRepository
            .findById(gpsCoordinateCheck.getId())
            .map(existingGpsCoordinateCheck -> {
                if (gpsCoordinateCheck.getLatDeg() != null) {
                    existingGpsCoordinateCheck.setLatDeg(gpsCoordinateCheck.getLatDeg());
                }
                if (gpsCoordinateCheck.getLatMin() != null) {
                    existingGpsCoordinateCheck.setLatMin(gpsCoordinateCheck.getLatMin());
                }
                if (gpsCoordinateCheck.getLatSec() != null) {
                    existingGpsCoordinateCheck.setLatSec(gpsCoordinateCheck.getLatSec());
                }
                if (gpsCoordinateCheck.getLatDirection() != null) {
                    existingGpsCoordinateCheck.setLatDirection(gpsCoordinateCheck.getLatDirection());
                }
                if (gpsCoordinateCheck.getLngDeg() != null) {
                    existingGpsCoordinateCheck.setLngDeg(gpsCoordinateCheck.getLngDeg());
                }
                if (gpsCoordinateCheck.getLngMin() != null) {
                    existingGpsCoordinateCheck.setLngMin(gpsCoordinateCheck.getLngMin());
                }
                if (gpsCoordinateCheck.getLngSec() != null) {
                    existingGpsCoordinateCheck.setLngSec(gpsCoordinateCheck.getLngSec());
                }
                if (gpsCoordinateCheck.getLngDirection() != null) {
                    existingGpsCoordinateCheck.setLngDirection(gpsCoordinateCheck.getLngDirection());
                }
                if (gpsCoordinateCheck.getLatDisplayedValue() != null) {
                    existingGpsCoordinateCheck.setLatDisplayedValue(gpsCoordinateCheck.getLatDisplayedValue());
                }
                if (gpsCoordinateCheck.getLngDisplayedValue() != null) {
                    existingGpsCoordinateCheck.setLngDisplayedValue(gpsCoordinateCheck.getLngDisplayedValue());
                }
                if (gpsCoordinateCheck.getLatDecimal() != null) {
                    existingGpsCoordinateCheck.setLatDecimal(gpsCoordinateCheck.getLatDecimal());
                }
                if (gpsCoordinateCheck.getLngDecimal() != null) {
                    existingGpsCoordinateCheck.setLngDecimal(gpsCoordinateCheck.getLngDecimal());
                }
                if (gpsCoordinateCheck.getLink() != null) {
                    existingGpsCoordinateCheck.setLink(gpsCoordinateCheck.getLink());
                }

                return existingGpsCoordinateCheck;
            })
            .map(gpsCoordinateCheckRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GpsCoordinateCheck> findAll(Pageable pageable) {
        log.debug("Request to get all GpsCoordinateChecks");
        return gpsCoordinateCheckRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GpsCoordinateCheck> findOne(Long id) {
        log.debug("Request to get GpsCoordinateCheck : {}", id);
        return gpsCoordinateCheckRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GpsCoordinateCheck : {}", id);
        gpsCoordinateCheckRepository.deleteById(id);
    }
}
