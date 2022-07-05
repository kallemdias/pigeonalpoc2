package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.CheckLine;
import de.mellak.pigeonal.repository.CheckLineRepository;
import de.mellak.pigeonal.service.CheckLineService;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.CheckLine}.
 */
@RestController
@RequestMapping("/api")
public class CheckLineResource {

    private final Logger log = LoggerFactory.getLogger(CheckLineResource.class);

    private static final String ENTITY_NAME = "checkLine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckLineService checkLineService;

    private final CheckLineRepository checkLineRepository;

    public CheckLineResource(CheckLineService checkLineService, CheckLineRepository checkLineRepository) {
        this.checkLineService = checkLineService;
        this.checkLineRepository = checkLineRepository;
    }

    /**
     * {@code POST  /check-lines} : Create a new checkLine.
     *
     * @param checkLine the checkLine to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checkLine, or with status {@code 400 (Bad Request)} if the checkLine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/check-lines")
    public ResponseEntity<CheckLine> createCheckLine(@Valid @RequestBody CheckLine checkLine) throws URISyntaxException {
        log.debug("REST request to save CheckLine : {}", checkLine);
        if (checkLine.getId() != null) {
            throw new BadRequestAlertException("A new checkLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CheckLine result = checkLineService.save(checkLine);
        return ResponseEntity
            .created(new URI("/api/check-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /check-lines/:id} : Updates an existing checkLine.
     *
     * @param id the id of the checkLine to save.
     * @param checkLine the checkLine to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkLine,
     * or with status {@code 400 (Bad Request)} if the checkLine is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checkLine couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/check-lines/{id}")
    public ResponseEntity<CheckLine> updateCheckLine(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CheckLine checkLine
    ) throws URISyntaxException {
        log.debug("REST request to update CheckLine : {}, {}", id, checkLine);
        if (checkLine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkLine.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkLineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CheckLine result = checkLineService.update(checkLine);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, checkLine.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /check-lines/:id} : Partial updates given fields of an existing checkLine, field will ignore if it is null
     *
     * @param id the id of the checkLine to save.
     * @param checkLine the checkLine to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkLine,
     * or with status {@code 400 (Bad Request)} if the checkLine is not valid,
     * or with status {@code 404 (Not Found)} if the checkLine is not found,
     * or with status {@code 500 (Internal Server Error)} if the checkLine couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/check-lines/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CheckLine> partialUpdateCheckLine(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CheckLine checkLine
    ) throws URISyntaxException {
        log.debug("REST request to partial update CheckLine partially : {}, {}", id, checkLine);
        if (checkLine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkLine.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkLineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CheckLine> result = checkLineService.partialUpdate(checkLine);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, checkLine.getId().toString())
        );
    }

    /**
     * {@code GET  /check-lines} : get all the checkLines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkLines in body.
     */
    @GetMapping("/check-lines")
    public ResponseEntity<List<CheckLine>> getAllCheckLines(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of CheckLines");
        Page<CheckLine> page = checkLineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /check-lines/:id} : get the "id" checkLine.
     *
     * @param id the id of the checkLine to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checkLine, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/check-lines/{id}")
    public ResponseEntity<CheckLine> getCheckLine(@PathVariable Long id) {
        log.debug("REST request to get CheckLine : {}", id);
        Optional<CheckLine> checkLine = checkLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checkLine);
    }

    /**
     * {@code DELETE  /check-lines/:id} : delete the "id" checkLine.
     *
     * @param id the id of the checkLine to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/check-lines/{id}")
    public ResponseEntity<Void> deleteCheckLine(@PathVariable Long id) {
        log.debug("REST request to delete CheckLine : {}", id);
        checkLineService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
