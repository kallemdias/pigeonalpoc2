package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.DistanceCalculator;
import de.mellak.pigeonal.repository.DistanceCalculatorRepository;
import de.mellak.pigeonal.service.DistanceCalculatorService;
import de.mellak.pigeonal.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.DistanceCalculator}.
 */
@RestController
@RequestMapping("/api")
public class DistanceCalculatorResource {

    private final Logger log = LoggerFactory.getLogger(DistanceCalculatorResource.class);

    private static final String ENTITY_NAME = "distanceCalculator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DistanceCalculatorService distanceCalculatorService;

    private final DistanceCalculatorRepository distanceCalculatorRepository;

    public DistanceCalculatorResource(
        DistanceCalculatorService distanceCalculatorService,
        DistanceCalculatorRepository distanceCalculatorRepository
    ) {
        this.distanceCalculatorService = distanceCalculatorService;
        this.distanceCalculatorRepository = distanceCalculatorRepository;
    }

    /**
     * {@code POST  /distance-calculators} : Create a new distanceCalculator.
     *
     * @param distanceCalculator the distanceCalculator to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new distanceCalculator, or with status {@code 400 (Bad Request)} if the distanceCalculator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/distance-calculators")
    public ResponseEntity<DistanceCalculator> createDistanceCalculator(@Valid @RequestBody DistanceCalculator distanceCalculator)
        throws URISyntaxException {
        log.debug("REST request to save DistanceCalculator : {}", distanceCalculator);
        if (distanceCalculator.getId() != null) {
            throw new BadRequestAlertException("A new distanceCalculator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DistanceCalculator result = distanceCalculatorService.save(distanceCalculator);
        return ResponseEntity
            .created(new URI("/api/distance-calculators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /distance-calculators/:id} : Updates an existing distanceCalculator.
     *
     * @param id the id of the distanceCalculator to save.
     * @param distanceCalculator the distanceCalculator to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated distanceCalculator,
     * or with status {@code 400 (Bad Request)} if the distanceCalculator is not valid,
     * or with status {@code 500 (Internal Server Error)} if the distanceCalculator couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/distance-calculators/{id}")
    public ResponseEntity<DistanceCalculator> updateDistanceCalculator(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DistanceCalculator distanceCalculator
    ) throws URISyntaxException {
        log.debug("REST request to update DistanceCalculator : {}, {}", id, distanceCalculator);
        if (distanceCalculator.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, distanceCalculator.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!distanceCalculatorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DistanceCalculator result = distanceCalculatorService.update(distanceCalculator);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, distanceCalculator.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /distance-calculators/:id} : Partial updates given fields of an existing distanceCalculator, field will ignore if it is null
     *
     * @param id the id of the distanceCalculator to save.
     * @param distanceCalculator the distanceCalculator to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated distanceCalculator,
     * or with status {@code 400 (Bad Request)} if the distanceCalculator is not valid,
     * or with status {@code 404 (Not Found)} if the distanceCalculator is not found,
     * or with status {@code 500 (Internal Server Error)} if the distanceCalculator couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/distance-calculators/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DistanceCalculator> partialUpdateDistanceCalculator(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DistanceCalculator distanceCalculator
    ) throws URISyntaxException {
        log.debug("REST request to partial update DistanceCalculator partially : {}, {}", id, distanceCalculator);
        if (distanceCalculator.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, distanceCalculator.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!distanceCalculatorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DistanceCalculator> result = distanceCalculatorService.partialUpdate(distanceCalculator);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, distanceCalculator.getId().toString())
        );
    }

    /**
     * {@code GET  /distance-calculators} : get all the distanceCalculators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of distanceCalculators in body.
     */
    @GetMapping("/distance-calculators")
    public ResponseEntity<List<DistanceCalculator>> getAllDistanceCalculators(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DistanceCalculators");
        Page<DistanceCalculator> page = distanceCalculatorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /distance-calculators/:id} : get the "id" distanceCalculator.
     *
     * @param id the id of the distanceCalculator to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the distanceCalculator, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/distance-calculators/{id}")
    public ResponseEntity<DistanceCalculator> getDistanceCalculator(@PathVariable Long id) {
        log.debug("REST request to get DistanceCalculator : {}", id);
        Optional<DistanceCalculator> distanceCalculator = distanceCalculatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(distanceCalculator);
    }

    /**
     * {@code DELETE  /distance-calculators/:id} : delete the "id" distanceCalculator.
     *
     * @param id the id of the distanceCalculator to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/distance-calculators/{id}")
    public ResponseEntity<Void> deleteDistanceCalculator(@PathVariable Long id) {
        log.debug("REST request to delete DistanceCalculator : {}", id);
        distanceCalculatorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
