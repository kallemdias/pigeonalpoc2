package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.CheckPoint;
import de.mellak.pigeonal.repository.CheckPointRepository;
import de.mellak.pigeonal.service.CheckPointService;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.CheckPoint}.
 */
@RestController
@RequestMapping("/api")
public class CheckPointResource {

    private final Logger log = LoggerFactory.getLogger(CheckPointResource.class);

    private static final String ENTITY_NAME = "checkPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckPointService checkPointService;

    private final CheckPointRepository checkPointRepository;

    public CheckPointResource(CheckPointService checkPointService, CheckPointRepository checkPointRepository) {
        this.checkPointService = checkPointService;
        this.checkPointRepository = checkPointRepository;
    }

    /**
     * {@code POST  /check-points} : Create a new checkPoint.
     *
     * @param checkPoint the checkPoint to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checkPoint, or with status {@code 400 (Bad Request)} if the checkPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/check-points")
    public ResponseEntity<CheckPoint> createCheckPoint(@Valid @RequestBody CheckPoint checkPoint) throws URISyntaxException {
        log.debug("REST request to save CheckPoint : {}", checkPoint);
        if (checkPoint.getId() != null) {
            throw new BadRequestAlertException("A new checkPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CheckPoint result = checkPointService.save(checkPoint);
        return ResponseEntity
            .created(new URI("/api/check-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /check-points/:id} : Updates an existing checkPoint.
     *
     * @param id the id of the checkPoint to save.
     * @param checkPoint the checkPoint to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkPoint,
     * or with status {@code 400 (Bad Request)} if the checkPoint is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checkPoint couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/check-points/{id}")
    public ResponseEntity<CheckPoint> updateCheckPoint(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CheckPoint checkPoint
    ) throws URISyntaxException {
        log.debug("REST request to update CheckPoint : {}, {}", id, checkPoint);
        if (checkPoint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkPoint.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkPointRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CheckPoint result = checkPointService.update(checkPoint);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, checkPoint.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /check-points/:id} : Partial updates given fields of an existing checkPoint, field will ignore if it is null
     *
     * @param id the id of the checkPoint to save.
     * @param checkPoint the checkPoint to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkPoint,
     * or with status {@code 400 (Bad Request)} if the checkPoint is not valid,
     * or with status {@code 404 (Not Found)} if the checkPoint is not found,
     * or with status {@code 500 (Internal Server Error)} if the checkPoint couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/check-points/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CheckPoint> partialUpdateCheckPoint(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CheckPoint checkPoint
    ) throws URISyntaxException {
        log.debug("REST request to partial update CheckPoint partially : {}, {}", id, checkPoint);
        if (checkPoint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkPoint.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkPointRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CheckPoint> result = checkPointService.partialUpdate(checkPoint);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, checkPoint.getId().toString())
        );
    }

    /**
     * {@code GET  /check-points} : get all the checkPoints.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkPoints in body.
     */
    @GetMapping("/check-points")
    public ResponseEntity<List<CheckPoint>> getAllCheckPoints(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of CheckPoints");
        Page<CheckPoint> page = checkPointService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /check-points/:id} : get the "id" checkPoint.
     *
     * @param id the id of the checkPoint to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checkPoint, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/check-points/{id}")
    public ResponseEntity<CheckPoint> getCheckPoint(@PathVariable Long id) {
        log.debug("REST request to get CheckPoint : {}", id);
        Optional<CheckPoint> checkPoint = checkPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checkPoint);
    }

    /**
     * {@code DELETE  /check-points/:id} : delete the "id" checkPoint.
     *
     * @param id the id of the checkPoint to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/check-points/{id}")
    public ResponseEntity<Void> deleteCheckPoint(@PathVariable Long id) {
        log.debug("REST request to delete CheckPoint : {}", id);
        checkPointService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
