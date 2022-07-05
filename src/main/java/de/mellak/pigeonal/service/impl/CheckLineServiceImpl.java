package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.CheckLine;
import de.mellak.pigeonal.repository.CheckLineRepository;
import de.mellak.pigeonal.service.CheckLineService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CheckLine}.
 */
@Service
@Transactional
public class CheckLineServiceImpl implements CheckLineService {

    private final Logger log = LoggerFactory.getLogger(CheckLineServiceImpl.class);

    private final CheckLineRepository checkLineRepository;

    public CheckLineServiceImpl(CheckLineRepository checkLineRepository) {
        this.checkLineRepository = checkLineRepository;
    }

    @Override
    public CheckLine save(CheckLine checkLine) {
        log.debug("Request to save CheckLine : {}", checkLine);
        return checkLineRepository.save(checkLine);
    }

    @Override
    public CheckLine update(CheckLine checkLine) {
        log.debug("Request to save CheckLine : {}", checkLine);
        return checkLineRepository.save(checkLine);
    }

    @Override
    public Optional<CheckLine> partialUpdate(CheckLine checkLine) {
        log.debug("Request to partially update CheckLine : {}", checkLine);

        return checkLineRepository
            .findById(checkLine.getId())
            .map(existingCheckLine -> {
                if (checkLine.getOrder() != null) {
                    existingCheckLine.setOrder(checkLine.getOrder());
                }
                if (checkLine.getDateTime() != null) {
                    existingCheckLine.setDateTime(checkLine.getDateTime());
                }
                if (checkLine.getLocationName() != null) {
                    existingCheckLine.setLocationName(checkLine.getLocationName());
                }
                if (checkLine.getLatDecimal() != null) {
                    existingCheckLine.setLatDecimal(checkLine.getLatDecimal());
                }
                if (checkLine.getLngDecimal() != null) {
                    existingCheckLine.setLngDecimal(checkLine.getLngDecimal());
                }
                if (checkLine.getDistance() != null) {
                    existingCheckLine.setDistance(checkLine.getDistance());
                }
                if (checkLine.getDistanceDisplayedValue() != null) {
                    existingCheckLine.setDistanceDisplayedValue(checkLine.getDistanceDisplayedValue());
                }
                if (checkLine.getLink() != null) {
                    existingCheckLine.setLink(checkLine.getLink());
                }

                return existingCheckLine;
            })
            .map(checkLineRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CheckLine> findAll(Pageable pageable) {
        log.debug("Request to get all CheckLines");
        return checkLineRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CheckLine> findOne(Long id) {
        log.debug("Request to get CheckLine : {}", id);
        return checkLineRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CheckLine : {}", id);
        checkLineRepository.deleteById(id);
    }
}
