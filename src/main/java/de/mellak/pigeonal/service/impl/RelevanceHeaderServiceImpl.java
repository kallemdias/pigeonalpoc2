package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.RelevanceHeader;
import de.mellak.pigeonal.repository.RelevanceHeaderRepository;
import de.mellak.pigeonal.service.RelevanceHeaderService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RelevanceHeader}.
 */
@Service
@Transactional
public class RelevanceHeaderServiceImpl implements RelevanceHeaderService {

    private final Logger log = LoggerFactory.getLogger(RelevanceHeaderServiceImpl.class);

    private final RelevanceHeaderRepository relevanceHeaderRepository;

    public RelevanceHeaderServiceImpl(RelevanceHeaderRepository relevanceHeaderRepository) {
        this.relevanceHeaderRepository = relevanceHeaderRepository;
    }

    @Override
    public RelevanceHeader save(RelevanceHeader relevanceHeader) {
        log.debug("Request to save RelevanceHeader : {}", relevanceHeader);
        return relevanceHeaderRepository.save(relevanceHeader);
    }

    @Override
    public RelevanceHeader update(RelevanceHeader relevanceHeader) {
        log.debug("Request to save RelevanceHeader : {}", relevanceHeader);
        return relevanceHeaderRepository.save(relevanceHeader);
    }

    @Override
    public Optional<RelevanceHeader> partialUpdate(RelevanceHeader relevanceHeader) {
        log.debug("Request to partially update RelevanceHeader : {}", relevanceHeader);

        return relevanceHeaderRepository
            .findById(relevanceHeader.getId())
            .map(existingRelevanceHeader -> {
                if (relevanceHeader.getMidnight() != null) {
                    existingRelevanceHeader.setMidnight(relevanceHeader.getMidnight());
                }
                if (relevanceHeader.getOne() != null) {
                    existingRelevanceHeader.setOne(relevanceHeader.getOne());
                }
                if (relevanceHeader.getTwo() != null) {
                    existingRelevanceHeader.setTwo(relevanceHeader.getTwo());
                }
                if (relevanceHeader.getThree() != null) {
                    existingRelevanceHeader.setThree(relevanceHeader.getThree());
                }
                if (relevanceHeader.getFour() != null) {
                    existingRelevanceHeader.setFour(relevanceHeader.getFour());
                }
                if (relevanceHeader.getFive() != null) {
                    existingRelevanceHeader.setFive(relevanceHeader.getFive());
                }
                if (relevanceHeader.getSix() != null) {
                    existingRelevanceHeader.setSix(relevanceHeader.getSix());
                }
                if (relevanceHeader.getSeven() != null) {
                    existingRelevanceHeader.setSeven(relevanceHeader.getSeven());
                }
                if (relevanceHeader.getEight() != null) {
                    existingRelevanceHeader.setEight(relevanceHeader.getEight());
                }
                if (relevanceHeader.getNine() != null) {
                    existingRelevanceHeader.setNine(relevanceHeader.getNine());
                }
                if (relevanceHeader.getTen() != null) {
                    existingRelevanceHeader.setTen(relevanceHeader.getTen());
                }
                if (relevanceHeader.getEleven() != null) {
                    existingRelevanceHeader.setEleven(relevanceHeader.getEleven());
                }
                if (relevanceHeader.getTwelve() != null) {
                    existingRelevanceHeader.setTwelve(relevanceHeader.getTwelve());
                }
                if (relevanceHeader.getThirteen() != null) {
                    existingRelevanceHeader.setThirteen(relevanceHeader.getThirteen());
                }
                if (relevanceHeader.getFourteen() != null) {
                    existingRelevanceHeader.setFourteen(relevanceHeader.getFourteen());
                }
                if (relevanceHeader.getFifteen() != null) {
                    existingRelevanceHeader.setFifteen(relevanceHeader.getFifteen());
                }
                if (relevanceHeader.getSixteen() != null) {
                    existingRelevanceHeader.setSixteen(relevanceHeader.getSixteen());
                }
                if (relevanceHeader.getSeventeen() != null) {
                    existingRelevanceHeader.setSeventeen(relevanceHeader.getSeventeen());
                }
                if (relevanceHeader.getEighteen() != null) {
                    existingRelevanceHeader.setEighteen(relevanceHeader.getEighteen());
                }
                if (relevanceHeader.getNineteen() != null) {
                    existingRelevanceHeader.setNineteen(relevanceHeader.getNineteen());
                }
                if (relevanceHeader.getTwenty() != null) {
                    existingRelevanceHeader.setTwenty(relevanceHeader.getTwenty());
                }
                if (relevanceHeader.getTwentyOne() != null) {
                    existingRelevanceHeader.setTwentyOne(relevanceHeader.getTwentyOne());
                }
                if (relevanceHeader.getTwentyTwo() != null) {
                    existingRelevanceHeader.setTwentyTwo(relevanceHeader.getTwentyTwo());
                }
                if (relevanceHeader.getTwentyThree() != null) {
                    existingRelevanceHeader.setTwentyThree(relevanceHeader.getTwentyThree());
                }

                return existingRelevanceHeader;
            })
            .map(relevanceHeaderRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelevanceHeader> findAll(Pageable pageable) {
        log.debug("Request to get all RelevanceHeaders");
        return relevanceHeaderRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelevanceHeader> findOne(Long id) {
        log.debug("Request to get RelevanceHeader : {}", id);
        return relevanceHeaderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelevanceHeader : {}", id);
        relevanceHeaderRepository.deleteById(id);
    }
}
