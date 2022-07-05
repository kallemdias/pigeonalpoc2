package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.RelevanceHeader;
import de.mellak.pigeonal.repository.RelevanceHeaderRepository;
import de.mellak.pigeonal.service.RelevanceHeaderService;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.RelevanceHeader}.
 */
@RestController
@RequestMapping("/api")
public class RelevanceHeaderResource {

    private final Logger log = LoggerFactory.getLogger(RelevanceHeaderResource.class);

    private static final String ENTITY_NAME = "relevanceHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelevanceHeaderService relevanceHeaderService;

    private final RelevanceHeaderRepository relevanceHeaderRepository;

    public RelevanceHeaderResource(RelevanceHeaderService relevanceHeaderService, RelevanceHeaderRepository relevanceHeaderRepository) {
        this.relevanceHeaderService = relevanceHeaderService;
        this.relevanceHeaderRepository = relevanceHeaderRepository;
    }

    /**
     * {@code POST  /relevance-headers} : Create a new relevanceHeader.
     *
     * @param relevanceHeader the relevanceHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relevanceHeader, or with status {@code 400 (Bad Request)} if the relevanceHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relevance-headers")
    public ResponseEntity<RelevanceHeader> createRelevanceHeader(@RequestBody RelevanceHeader relevanceHeader) throws URISyntaxException {
        log.debug("REST request to save RelevanceHeader : {}", relevanceHeader);
        if (relevanceHeader.getId() != null) {
            throw new BadRequestAlertException("A new relevanceHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelevanceHeader result = relevanceHeaderService.save(relevanceHeader);
        return ResponseEntity
            .created(new URI("/api/relevance-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relevance-headers/:id} : Updates an existing relevanceHeader.
     *
     * @param id the id of the relevanceHeader to save.
     * @param relevanceHeader the relevanceHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relevanceHeader,
     * or with status {@code 400 (Bad Request)} if the relevanceHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relevanceHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relevance-headers/{id}")
    public ResponseEntity<RelevanceHeader> updateRelevanceHeader(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RelevanceHeader relevanceHeader
    ) throws URISyntaxException {
        log.debug("REST request to update RelevanceHeader : {}, {}", id, relevanceHeader);
        if (relevanceHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relevanceHeader.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relevanceHeaderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RelevanceHeader result = relevanceHeaderService.update(relevanceHeader);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relevanceHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /relevance-headers/:id} : Partial updates given fields of an existing relevanceHeader, field will ignore if it is null
     *
     * @param id the id of the relevanceHeader to save.
     * @param relevanceHeader the relevanceHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relevanceHeader,
     * or with status {@code 400 (Bad Request)} if the relevanceHeader is not valid,
     * or with status {@code 404 (Not Found)} if the relevanceHeader is not found,
     * or with status {@code 500 (Internal Server Error)} if the relevanceHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/relevance-headers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RelevanceHeader> partialUpdateRelevanceHeader(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RelevanceHeader relevanceHeader
    ) throws URISyntaxException {
        log.debug("REST request to partial update RelevanceHeader partially : {}, {}", id, relevanceHeader);
        if (relevanceHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relevanceHeader.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relevanceHeaderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RelevanceHeader> result = relevanceHeaderService.partialUpdate(relevanceHeader);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relevanceHeader.getId().toString())
        );
    }

    /**
     * {@code GET  /relevance-headers} : get all the relevanceHeaders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relevanceHeaders in body.
     */
    @GetMapping("/relevance-headers")
    public ResponseEntity<List<RelevanceHeader>> getAllRelevanceHeaders(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of RelevanceHeaders");
        Page<RelevanceHeader> page = relevanceHeaderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relevance-headers/:id} : get the "id" relevanceHeader.
     *
     * @param id the id of the relevanceHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relevanceHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relevance-headers/{id}")
    public ResponseEntity<RelevanceHeader> getRelevanceHeader(@PathVariable Long id) {
        log.debug("REST request to get RelevanceHeader : {}", id);
        Optional<RelevanceHeader> relevanceHeader = relevanceHeaderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relevanceHeader);
    }

    /**
     * {@code DELETE  /relevance-headers/:id} : delete the "id" relevanceHeader.
     *
     * @param id the id of the relevanceHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relevance-headers/{id}")
    public ResponseEntity<Void> deleteRelevanceHeader(@PathVariable Long id) {
        log.debug("REST request to delete RelevanceHeader : {}", id);
        relevanceHeaderService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
