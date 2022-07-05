package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.VelocityCalculator;
import de.mellak.pigeonal.repository.VelocityCalculatorRepository;
import de.mellak.pigeonal.service.VelocityCalculatorService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VelocityCalculator}.
 */
@Service
@Transactional
public class VelocityCalculatorServiceImpl implements VelocityCalculatorService {

    private final Logger log = LoggerFactory.getLogger(VelocityCalculatorServiceImpl.class);

    private final VelocityCalculatorRepository velocityCalculatorRepository;

    public VelocityCalculatorServiceImpl(VelocityCalculatorRepository velocityCalculatorRepository) {
        this.velocityCalculatorRepository = velocityCalculatorRepository;
    }

    @Override
    public VelocityCalculator save(VelocityCalculator velocityCalculator) {
        log.debug("Request to save VelocityCalculator : {}", velocityCalculator);
        return velocityCalculatorRepository.save(velocityCalculator);
    }

    @Override
    public VelocityCalculator update(VelocityCalculator velocityCalculator) {
        log.debug("Request to save VelocityCalculator : {}", velocityCalculator);
        return velocityCalculatorRepository.save(velocityCalculator);
    }

    @Override
    public Optional<VelocityCalculator> partialUpdate(VelocityCalculator velocityCalculator) {
        log.debug("Request to partially update VelocityCalculator : {}", velocityCalculator);

        return velocityCalculatorRepository
            .findById(velocityCalculator.getId())
            .map(existingVelocityCalculator -> {
                if (velocityCalculator.getReleaseDateTime() != null) {
                    existingVelocityCalculator.setReleaseDateTime(velocityCalculator.getReleaseDateTime());
                }
                if (velocityCalculator.getArrivalDateTime() != null) {
                    existingVelocityCalculator.setArrivalDateTime(velocityCalculator.getArrivalDateTime());
                }
                if (velocityCalculator.getDistanceKM() != null) {
                    existingVelocityCalculator.setDistanceKM(velocityCalculator.getDistanceKM());
                }
                if (velocityCalculator.getDistanceM() != null) {
                    existingVelocityCalculator.setDistanceM(velocityCalculator.getDistanceM());
                }
                if (velocityCalculator.getVelocity() != null) {
                    existingVelocityCalculator.setVelocity(velocityCalculator.getVelocity());
                }
                if (velocityCalculator.getVelocityDispVal() != null) {
                    existingVelocityCalculator.setVelocityDispVal(velocityCalculator.getVelocityDispVal());
                }

                return existingVelocityCalculator;
            })
            .map(velocityCalculatorRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VelocityCalculator> findAll(Pageable pageable) {
        log.debug("Request to get all VelocityCalculators");
        return velocityCalculatorRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VelocityCalculator> findOne(Long id) {
        log.debug("Request to get VelocityCalculator : {}", id);
        return velocityCalculatorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VelocityCalculator : {}", id);
        velocityCalculatorRepository.deleteById(id);
    }
}
