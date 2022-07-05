package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.Leg;
import de.mellak.pigeonal.repository.LegRepository;
import de.mellak.pigeonal.service.LegService;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.Leg}.
 */
@RestController
@RequestMapping("/api")
public class LegResource {

    private final Logger log = LoggerFactory.getLogger(LegResource.class);

    private static final String ENTITY_NAME = "leg";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LegService legService;

    private final LegRepository legRepository;

    public LegResource(LegService legService, LegRepository legRepository) {
        this.legService = legService;
        this.legRepository = legRepository;
    }

    /**
     * {@code POST  /legs} : Create a new leg.
     *
     * @param leg the leg to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leg, or with status {@code 400 (Bad Request)} if the leg has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/legs")
    public ResponseEntity<Leg> createLeg(@Valid @RequestBody Leg leg) throws URISyntaxException {
        log.debug("REST request to save Leg : {}", leg);
        if (leg.getId() != null) {
            throw new BadRequestAlertException("A new leg cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Leg result = legService.save(leg);
        return ResponseEntity
            .created(new URI("/api/legs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /legs/:id} : Updates an existing leg.
     *
     * @param id the id of the leg to save.
     * @param leg the leg to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leg,
     * or with status {@code 400 (Bad Request)} if the leg is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leg couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/legs/{id}")
    public ResponseEntity<Leg> updateLeg(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Leg leg)
        throws URISyntaxException {
        log.debug("REST request to update Leg : {}, {}", id, leg);
        if (leg.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leg.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!legRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Leg result = legService.update(leg);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, leg.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /legs/:id} : Partial updates given fields of an existing leg, field will ignore if it is null
     *
     * @param id the id of the leg to save.
     * @param leg the leg to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leg,
     * or with status {@code 400 (Bad Request)} if the leg is not valid,
     * or with status {@code 404 (Not Found)} if the leg is not found,
     * or with status {@code 500 (Internal Server Error)} if the leg couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/legs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Leg> partialUpdateLeg(@PathVariable(value = "id", required = false) final Long id, @NotNull @RequestBody Leg leg)
        throws URISyntaxException {
        log.debug("REST request to partial update Leg partially : {}, {}", id, leg);
        if (leg.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leg.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!legRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Leg> result = legService.partialUpdate(leg);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, leg.getId().toString())
        );
    }

    /**
     * {@code GET  /legs} : get all the legs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of legs in body.
     */
    @GetMapping("/legs")
    public ResponseEntity<List<Leg>> getAllLegs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Legs");
        Page<Leg> page = legService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /legs/:id} : get the "id" leg.
     *
     * @param id the id of the leg to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leg, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/legs/{id}")
    public ResponseEntity<Leg> getLeg(@PathVariable Long id) {
        log.debug("REST request to get Leg : {}", id);
        Optional<Leg> leg = legService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leg);
    }

    /**
     * {@code DELETE  /legs/:id} : delete the "id" leg.
     *
     * @param id the id of the leg to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/legs/{id}")
    public ResponseEntity<Void> deleteLeg(@PathVariable Long id) {
        log.debug("REST request to delete Leg : {}", id);
        legService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
