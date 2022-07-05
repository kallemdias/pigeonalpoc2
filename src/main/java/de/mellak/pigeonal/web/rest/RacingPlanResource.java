package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.RacingPlan;
import de.mellak.pigeonal.repository.RacingPlanRepository;
import de.mellak.pigeonal.service.RacingPlanService;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.RacingPlan}.
 */
@RestController
@RequestMapping("/api")
public class RacingPlanResource {

    private final Logger log = LoggerFactory.getLogger(RacingPlanResource.class);

    private static final String ENTITY_NAME = "racingPlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RacingPlanService racingPlanService;

    private final RacingPlanRepository racingPlanRepository;

    public RacingPlanResource(RacingPlanService racingPlanService, RacingPlanRepository racingPlanRepository) {
        this.racingPlanService = racingPlanService;
        this.racingPlanRepository = racingPlanRepository;
    }

    /**
     * {@code POST  /racing-plans} : Create a new racingPlan.
     *
     * @param racingPlan the racingPlan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new racingPlan, or with status {@code 400 (Bad Request)} if the racingPlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/racing-plans")
    public ResponseEntity<RacingPlan> createRacingPlan(@Valid @RequestBody RacingPlan racingPlan) throws URISyntaxException {
        log.debug("REST request to save RacingPlan : {}", racingPlan);
        if (racingPlan.getId() != null) {
            throw new BadRequestAlertException("A new racingPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RacingPlan result = racingPlanService.save(racingPlan);
        return ResponseEntity
            .created(new URI("/api/racing-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /racing-plans/:id} : Updates an existing racingPlan.
     *
     * @param id the id of the racingPlan to save.
     * @param racingPlan the racingPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated racingPlan,
     * or with status {@code 400 (Bad Request)} if the racingPlan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the racingPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/racing-plans/{id}")
    public ResponseEntity<RacingPlan> updateRacingPlan(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RacingPlan racingPlan
    ) throws URISyntaxException {
        log.debug("REST request to update RacingPlan : {}, {}", id, racingPlan);
        if (racingPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, racingPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!racingPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RacingPlan result = racingPlanService.update(racingPlan);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, racingPlan.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /racing-plans/:id} : Partial updates given fields of an existing racingPlan, field will ignore if it is null
     *
     * @param id the id of the racingPlan to save.
     * @param racingPlan the racingPlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated racingPlan,
     * or with status {@code 400 (Bad Request)} if the racingPlan is not valid,
     * or with status {@code 404 (Not Found)} if the racingPlan is not found,
     * or with status {@code 500 (Internal Server Error)} if the racingPlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/racing-plans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RacingPlan> partialUpdateRacingPlan(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RacingPlan racingPlan
    ) throws URISyntaxException {
        log.debug("REST request to partial update RacingPlan partially : {}, {}", id, racingPlan);
        if (racingPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, racingPlan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!racingPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RacingPlan> result = racingPlanService.partialUpdate(racingPlan);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, racingPlan.getId().toString())
        );
    }

    /**
     * {@code GET  /racing-plans} : get all the racingPlans.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of racingPlans in body.
     */
    @GetMapping("/racing-plans")
    public ResponseEntity<List<RacingPlan>> getAllRacingPlans(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of RacingPlans");
        Page<RacingPlan> page;
        if (eagerload) {
            page = racingPlanService.findAllWithEagerRelationships(pageable);
        } else {
            page = racingPlanService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /racing-plans/:id} : get the "id" racingPlan.
     *
     * @param id the id of the racingPlan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the racingPlan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/racing-plans/{id}")
    public ResponseEntity<RacingPlan> getRacingPlan(@PathVariable Long id) {
        log.debug("REST request to get RacingPlan : {}", id);
        Optional<RacingPlan> racingPlan = racingPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(racingPlan);
    }

    /**
     * {@code DELETE  /racing-plans/:id} : delete the "id" racingPlan.
     *
     * @param id the id of the racingPlan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/racing-plans/{id}")
    public ResponseEntity<Void> deleteRacingPlan(@PathVariable Long id) {
        log.debug("REST request to delete RacingPlan : {}", id);
        racingPlanService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
