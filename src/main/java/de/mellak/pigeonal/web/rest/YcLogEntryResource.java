package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.YcLogEntry;
import de.mellak.pigeonal.repository.YcLogEntryRepository;
import de.mellak.pigeonal.service.YcLogEntryService;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.YcLogEntry}.
 */
@RestController
@RequestMapping("/api")
public class YcLogEntryResource {

    private final Logger log = LoggerFactory.getLogger(YcLogEntryResource.class);

    private static final String ENTITY_NAME = "ycLogEntry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final YcLogEntryService ycLogEntryService;

    private final YcLogEntryRepository ycLogEntryRepository;

    public YcLogEntryResource(YcLogEntryService ycLogEntryService, YcLogEntryRepository ycLogEntryRepository) {
        this.ycLogEntryService = ycLogEntryService;
        this.ycLogEntryRepository = ycLogEntryRepository;
    }

    /**
     * {@code POST  /yc-log-entries} : Create a new ycLogEntry.
     *
     * @param ycLogEntry the ycLogEntry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ycLogEntry, or with status {@code 400 (Bad Request)} if the ycLogEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/yc-log-entries")
    public ResponseEntity<YcLogEntry> createYcLogEntry(@RequestBody YcLogEntry ycLogEntry) throws URISyntaxException {
        log.debug("REST request to save YcLogEntry : {}", ycLogEntry);
        if (ycLogEntry.getId() != null) {
            throw new BadRequestAlertException("A new ycLogEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        YcLogEntry result = ycLogEntryService.save(ycLogEntry);
        return ResponseEntity
            .created(new URI("/api/yc-log-entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /yc-log-entries/:id} : Updates an existing ycLogEntry.
     *
     * @param id the id of the ycLogEntry to save.
     * @param ycLogEntry the ycLogEntry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ycLogEntry,
     * or with status {@code 400 (Bad Request)} if the ycLogEntry is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ycLogEntry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/yc-log-entries/{id}")
    public ResponseEntity<YcLogEntry> updateYcLogEntry(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody YcLogEntry ycLogEntry
    ) throws URISyntaxException {
        log.debug("REST request to update YcLogEntry : {}, {}", id, ycLogEntry);
        if (ycLogEntry.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ycLogEntry.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ycLogEntryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        YcLogEntry result = ycLogEntryService.update(ycLogEntry);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ycLogEntry.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /yc-log-entries/:id} : Partial updates given fields of an existing ycLogEntry, field will ignore if it is null
     *
     * @param id the id of the ycLogEntry to save.
     * @param ycLogEntry the ycLogEntry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ycLogEntry,
     * or with status {@code 400 (Bad Request)} if the ycLogEntry is not valid,
     * or with status {@code 404 (Not Found)} if the ycLogEntry is not found,
     * or with status {@code 500 (Internal Server Error)} if the ycLogEntry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/yc-log-entries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<YcLogEntry> partialUpdateYcLogEntry(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody YcLogEntry ycLogEntry
    ) throws URISyntaxException {
        log.debug("REST request to partial update YcLogEntry partially : {}, {}", id, ycLogEntry);
        if (ycLogEntry.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ycLogEntry.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ycLogEntryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<YcLogEntry> result = ycLogEntryService.partialUpdate(ycLogEntry);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ycLogEntry.getId().toString())
        );
    }

    /**
     * {@code GET  /yc-log-entries} : get all the ycLogEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ycLogEntries in body.
     */
    @GetMapping("/yc-log-entries")
    public ResponseEntity<List<YcLogEntry>> getAllYcLogEntries(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of YcLogEntries");
        Page<YcLogEntry> page = ycLogEntryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /yc-log-entries/:id} : get the "id" ycLogEntry.
     *
     * @param id the id of the ycLogEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ycLogEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/yc-log-entries/{id}")
    public ResponseEntity<YcLogEntry> getYcLogEntry(@PathVariable Long id) {
        log.debug("REST request to get YcLogEntry : {}", id);
        Optional<YcLogEntry> ycLogEntry = ycLogEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ycLogEntry);
    }

    /**
     * {@code DELETE  /yc-log-entries/:id} : delete the "id" ycLogEntry.
     *
     * @param id the id of the ycLogEntry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/yc-log-entries/{id}")
    public ResponseEntity<Void> deleteYcLogEntry(@PathVariable Long id) {
        log.debug("REST request to delete YcLogEntry : {}", id);
        ycLogEntryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
