package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.YcLogEntry;
import de.mellak.pigeonal.repository.YcLogEntryRepository;
import de.mellak.pigeonal.service.YcLogEntryService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link YcLogEntry}.
 */
@Service
@Transactional
public class YcLogEntryServiceImpl implements YcLogEntryService {

    private final Logger log = LoggerFactory.getLogger(YcLogEntryServiceImpl.class);

    private final YcLogEntryRepository ycLogEntryRepository;

    public YcLogEntryServiceImpl(YcLogEntryRepository ycLogEntryRepository) {
        this.ycLogEntryRepository = ycLogEntryRepository;
    }

    @Override
    public YcLogEntry save(YcLogEntry ycLogEntry) {
        log.debug("Request to save YcLogEntry : {}", ycLogEntry);
        return ycLogEntryRepository.save(ycLogEntry);
    }

    @Override
    public YcLogEntry update(YcLogEntry ycLogEntry) {
        log.debug("Request to save YcLogEntry : {}", ycLogEntry);
        return ycLogEntryRepository.save(ycLogEntry);
    }

    @Override
    public Optional<YcLogEntry> partialUpdate(YcLogEntry ycLogEntry) {
        log.debug("Request to partially update YcLogEntry : {}", ycLogEntry);

        return ycLogEntryRepository
            .findById(ycLogEntry.getId())
            .map(existingYcLogEntry -> {
                if (ycLogEntry.getCreated() != null) {
                    existingYcLogEntry.setCreated(ycLogEntry.getCreated());
                }
                if (ycLogEntry.getServiceName() != null) {
                    existingYcLogEntry.setServiceName(ycLogEntry.getServiceName());
                }
                if (ycLogEntry.getMethod() != null) {
                    existingYcLogEntry.setMethod(ycLogEntry.getMethod());
                }
                if (ycLogEntry.getStep() != null) {
                    existingYcLogEntry.setStep(ycLogEntry.getStep());
                }
                if (ycLogEntry.getDiscription() != null) {
                    existingYcLogEntry.setDiscription(ycLogEntry.getDiscription());
                }
                if (ycLogEntry.getLogType() != null) {
                    existingYcLogEntry.setLogType(ycLogEntry.getLogType());
                }

                return existingYcLogEntry;
            })
            .map(ycLogEntryRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<YcLogEntry> findAll(Pageable pageable) {
        log.debug("Request to get all YcLogEntries");
        return ycLogEntryRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<YcLogEntry> findOne(Long id) {
        log.debug("Request to get YcLogEntry : {}", id);
        return ycLogEntryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete YcLogEntry : {}", id);
        ycLogEntryRepository.deleteById(id);
    }
}
