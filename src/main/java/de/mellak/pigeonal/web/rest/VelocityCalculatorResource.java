package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.VelocityCalculator;
import de.mellak.pigeonal.repository.VelocityCalculatorRepository;
import de.mellak.pigeonal.service.VelocityCalculatorService;
import de.mellak.pigeonal.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link de.mellak.pigeonal.domain.VelocityCalculator}.
 */
@RestController
@RequestMapping("/api")
public class VelocityCalculatorResource {

    private final Logger log = LoggerFactory.getLogger(VelocityCalculatorResource.class);

    private static final String ENTITY_NAME = "velocityCalculator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VelocityCalculatorService velocityCalculatorService;

    private final VelocityCalculatorRepository velocityCalculatorRepository;

    public VelocityCalculatorResource(
        VelocityCalculatorService velocityCalculatorService,
        VelocityCalculatorRepository velocityCalculatorRepository
    ) {
        this.velocityCalculatorService = velocityCalculatorService;
        this.velocityCalculatorRepository = velocityCalculatorRepository;
    }

    /**
     * {@code POST  /velocity-calculators} : Create a new velocityCalculator.
     *
     * @param velocityCalculator the velocityCalculator to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new velocityCalculator, or with status {@code 400 (Bad Request)} if the velocityCalculator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/velocity-calculators")
    public ResponseEntity<VelocityCalculator> createVelocityCalculator(@RequestBody VelocityCalculator velocityCalculator)
        throws URISyntaxException {
        log.debug("REST request to save VelocityCalculator : {}", velocityCalculator);
        if (velocityCalculator.getId() != null) {
            throw new BadRequestAlertException("A new velocityCalculator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VelocityCalculator result = velocityCalculatorService.save(velocityCalculator);
        return ResponseEntity
            .created(new URI("/api/velocity-calculators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /velocity-calculators/:id} : Updates an existing velocityCalculator.
     *
     * @param id the id of the velocityCalculator to save.
     * @param velocityCalculator the velocityCalculator to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated velocityCalculator,
     * or with status {@code 400 (Bad Request)} if the velocityCalculator is not valid,
     * or with status {@code 500 (Internal Server Error)} if the velocityCalculator couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/velocity-calculators/{id}")
    public ResponseEntity<VelocityCalculator> updateVelocityCalculator(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VelocityCalculator velocityCalculator
    ) throws URISyntaxException {
        log.debug("REST request to update VelocityCalculator : {}, {}", id, velocityCalculator);
        if (velocityCalculator.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, velocityCalculator.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!velocityCalculatorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VelocityCalculator result = velocityCalculatorService.update(velocityCalculator);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, velocityCalculator.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /velocity-calculators/:id} : Partial updates given fields of an existing velocityCalculator, field will ignore if it is null
     *
     * @param id the id of the velocityCalculator to save.
     * @param velocityCalculator the velocityCalculator to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated velocityCalculator,
     * or with status {@code 400 (Bad Request)} if the velocityCalculator is not valid,
     * or with status {@code 404 (Not Found)} if the velocityCalculator is not found,
     * or with status {@code 500 (Internal Server Error)} if the velocityCalculator couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/velocity-calculators/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VelocityCalculator> partialUpdateVelocityCalculator(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VelocityCalculator velocityCalculator
    ) throws URISyntaxException {
        log.debug("REST request to partial update VelocityCalculator partially : {}, {}", id, velocityCalculator);
        if (velocityCalculator.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, velocityCalculator.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!velocityCalculatorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VelocityCalculator> result = velocityCalculatorService.partialUpdate(velocityCalculator);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, velocityCalculator.getId().toString())
        );
    }

    /**
     * {@code GET  /velocity-calculators} : get all the velocityCalculators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of velocityCalculators in body.
     */
    @GetMapping("/velocity-calculators")
    public ResponseEntity<List<VelocityCalculator>> getAllVelocityCalculators(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of VelocityCalculators");
        Page<VelocityCalculator> page = velocityCalculatorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /velocity-calculators/:id} : get the "id" velocityCalculator.
     *
     * @param id the id of the velocityCalculator to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the velocityCalculator, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/velocity-calculators/{id}")
    public ResponseEntity<VelocityCalculator> getVelocityCalculator(@PathVariable Long id) {
        log.debug("REST request to get VelocityCalculator : {}", id);
        Optional<VelocityCalculator> velocityCalculator = velocityCalculatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(velocityCalculator);
    }

    /**
     * {@code DELETE  /velocity-calculators/:id} : delete the "id" velocityCalculator.
     *
     * @param id the id of the velocityCalculator to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/velocity-calculators/{id}")
    public ResponseEntity<Void> deleteVelocityCalculator(@PathVariable Long id) {
        log.debug("REST request to delete VelocityCalculator : {}", id);
        velocityCalculatorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
