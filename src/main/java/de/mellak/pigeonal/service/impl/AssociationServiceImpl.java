package de.mellak.pigeonal.service.impl;

import de.mellak.pigeonal.domain.Association;
import de.mellak.pigeonal.repository.AssociationRepository;
import de.mellak.pigeonal.service.AssociationService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Association}.
 */
@Service
@Transactional
public class AssociationServiceImpl implements AssociationService {

    private final Logger log = LoggerFactory.getLogger(AssociationServiceImpl.class);

    private final AssociationRepository associationRepository;

    public AssociationServiceImpl(AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

    @Override
    public Association save(Association association) {
        log.debug("Request to save Association : {}", association);
        return associationRepository.save(association);
    }

    @Override
    public Association update(Association association) {
        log.debug("Request to save Association : {}", association);
        return associationRepository.save(association);
    }

    @Override
    public Optional<Association> partialUpdate(Association association) {
        log.debug("Request to partially update Association : {}", association);

        return associationRepository
            .findById(association.getId())
            .map(existingAssociation -> {
                if (association.getName() != null) {
                    existingAssociation.setName(association.getName());
                }
                if (association.getShortName() != null) {
                    existingAssociation.setShortName(association.getShortName());
                }
                if (association.getEmail() != null) {
                    existingAssociation.setEmail(association.getEmail());
                }
                if (association.getPhone() != null) {
                    existingAssociation.setPhone(association.getPhone());
                }
                if (association.getAddressLine1() != null) {
                    existingAssociation.setAddressLine1(association.getAddressLine1());
                }
                if (association.getAddressLine2() != null) {
                    existingAssociation.setAddressLine2(association.getAddressLine2());
                }
                if (association.getCity() != null) {
                    existingAssociation.setCity(association.getCity());
                }
                if (association.getCountry() != null) {
                    existingAssociation.setCountry(association.getCountry());
                }
                if (association.getFbLink() != null) {
                    existingAssociation.setFbLink(association.getFbLink());
                }

                return existingAssociation;
            })
            .map(associationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Association> findAll(Pageable pageable) {
        log.debug("Request to get all Associations");
        return associationRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Association> findOne(Long id) {
        log.debug("Request to get Association : {}", id);
        return associationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Association : {}", id);
        associationRepository.deleteById(id);
    }
}
