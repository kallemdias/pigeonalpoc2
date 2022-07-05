package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.GpsCoordinateCheck;
import de.mellak.pigeonal.repository.GpsCoordinateCheckRepository;
import de.mellak.pigeonal.service.GpsCoordinateCheckService;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.GpsCoordinateCheck}.
 */
@RestController
@RequestMapping("/api")
public class GpsCoordinateCheckResource {

    private final Logger log = LoggerFactory.getLogger(GpsCoordinateCheckResource.class);

    private static final String ENTITY_NAME = "gpsCoordinateCheck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GpsCoordinateCheckService gpsCoordinateCheckService;

    private final GpsCoordinateCheckRepository gpsCoordinateCheckRepository;

    public GpsCoordinateCheckResource(
        GpsCoordinateCheckService gpsCoordinateCheckService,
        GpsCoordinateCheckRepository gpsCoordinateCheckRepository
    ) {
        this.gpsCoordinateCheckService = gpsCoordinateCheckService;
        this.gpsCoordinateCheckRepository = gpsCoordinateCheckRepository;
    }

    /**
     * {@code POST  /gps-coordinate-checks} : Create a new gpsCoordinateCheck.
     *
     * @param gpsCoordinateCheck the gpsCoordinateCheck to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gpsCoordinateCheck, or with status {@code 400 (Bad Request)} if the gpsCoordinateCheck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gps-coordinate-checks")
    public ResponseEntity<GpsCoordinateCheck> createGpsCoordinateCheck(@Valid @RequestBody GpsCoordinateCheck gpsCoordinateCheck)
        throws URISyntaxException {
        log.debug("REST request to save GpsCoordinateCheck : {}", gpsCoordinateCheck);
        if (gpsCoordinateCheck.getId() != null) {
            throw new BadRequestAlertException("A new gpsCoordinateCheck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GpsCoordinateCheck result = gpsCoordinateCheckService.save(gpsCoordinateCheck);
        return ResponseEntity
            .created(new URI("/api/gps-coordinate-checks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gps-coordinate-checks/:id} : Updates an existing gpsCoordinateCheck.
     *
     * @param id the id of the gpsCoordinateCheck to save.
     * @param gpsCoordinateCheck the gpsCoordinateCheck to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gpsCoordinateCheck,
     * or with status {@code 400 (Bad Request)} if the gpsCoordinateCheck is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gpsCoordinateCheck couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gps-coordinate-checks/{id}")
    public ResponseEntity<GpsCoordinateCheck> updateGpsCoordinateCheck(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GpsCoordinateCheck gpsCoordinateCheck
    ) throws URISyntaxException {
        log.debug("REST request to update GpsCoordinateCheck : {}, {}", id, gpsCoordinateCheck);
        if (gpsCoordinateCheck.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gpsCoordinateCheck.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gpsCoordinateCheckRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GpsCoordinateCheck result = gpsCoordinateCheckService.update(gpsCoordinateCheck);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, gpsCoordinateCheck.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /gps-coordinate-checks/:id} : Partial updates given fields of an existing gpsCoordinateCheck, field will ignore if it is null
     *
     * @param id the id of the gpsCoordinateCheck to save.
     * @param gpsCoordinateCheck the gpsCoordinateCheck to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gpsCoordinateCheck,
     * or with status {@code 400 (Bad Request)} if the gpsCoordinateCheck is not valid,
     * or with status {@code 404 (Not Found)} if the gpsCoordinateCheck is not found,
     * or with status {@code 500 (Internal Server Error)} if the gpsCoordinateCheck couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/gps-coordinate-checks/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GpsCoordinateCheck> partialUpdateGpsCoordinateCheck(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GpsCoordinateCheck gpsCoordinateCheck
    ) throws URISyntaxException {
        log.debug("REST request to partial update GpsCoordinateCheck partially : {}, {}", id, gpsCoordinateCheck);
        if (gpsCoordinateCheck.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gpsCoordinateCheck.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gpsCoordinateCheckRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GpsCoordinateCheck> result = gpsCoordinateCheckService.partialUpdate(gpsCoordinateCheck);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, gpsCoordinateCheck.getId().toString())
        );
    }

    /**
     * {@code GET  /gps-coordinate-checks} : get all the gpsCoordinateChecks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gpsCoordinateChecks in body.
     */
    @GetMapping("/gps-coordinate-checks")
    public ResponseEntity<List<GpsCoordinateCheck>> getAllGpsCoordinateChecks(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of GpsCoordinateChecks");
        Page<GpsCoordinateCheck> page = gpsCoordinateCheckService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gps-coordinate-checks/:id} : get the "id" gpsCoordinateCheck.
     *
     * @param id the id of the gpsCoordinateCheck to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gpsCoordinateCheck, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gps-coordinate-checks/{id}")
    public ResponseEntity<GpsCoordinateCheck> getGpsCoordinateCheck(@PathVariable Long id) {
        log.debug("REST request to get GpsCoordinateCheck : {}", id);
        Optional<GpsCoordinateCheck> gpsCoordinateCheck = gpsCoordinateCheckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gpsCoordinateCheck);
    }

    /**
     * {@code DELETE  /gps-coordinate-checks/:id} : delete the "id" gpsCoordinateCheck.
     *
     * @param id the id of the gpsCoordinateCheck to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gps-coordinate-checks/{id}")
    public ResponseEntity<Void> deleteGpsCoordinateCheck(@PathVariable Long id) {
        log.debug("REST request to delete GpsCoordinateCheck : {}", id);
        gpsCoordinateCheckService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
