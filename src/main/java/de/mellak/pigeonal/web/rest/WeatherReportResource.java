package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.WeatherReport;
import de.mellak.pigeonal.repository.WeatherReportRepository;
import de.mellak.pigeonal.service.WeatherReportService;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.WeatherReport}.
 */
@RestController
@RequestMapping("/api")
public class WeatherReportResource {

    private final Logger log = LoggerFactory.getLogger(WeatherReportResource.class);

    private static final String ENTITY_NAME = "weatherReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WeatherReportService weatherReportService;

    private final WeatherReportRepository weatherReportRepository;

    public WeatherReportResource(WeatherReportService weatherReportService, WeatherReportRepository weatherReportRepository) {
        this.weatherReportService = weatherReportService;
        this.weatherReportRepository = weatherReportRepository;
    }

    /**
     * {@code POST  /weather-reports} : Create a new weatherReport.
     *
     * @param weatherReport the weatherReport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new weatherReport, or with status {@code 400 (Bad Request)} if the weatherReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/weather-reports")
    public ResponseEntity<WeatherReport> createWeatherReport(@Valid @RequestBody WeatherReport weatherReport) throws URISyntaxException {
        log.debug("REST request to save WeatherReport : {}", weatherReport);
        if (weatherReport.getId() != null) {
            throw new BadRequestAlertException("A new weatherReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WeatherReport result = weatherReportService.save(weatherReport);
        return ResponseEntity
            .created(new URI("/api/weather-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /weather-reports/:id} : Updates an existing weatherReport.
     *
     * @param id the id of the weatherReport to save.
     * @param weatherReport the weatherReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated weatherReport,
     * or with status {@code 400 (Bad Request)} if the weatherReport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the weatherReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/weather-reports/{id}")
    public ResponseEntity<WeatherReport> updateWeatherReport(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WeatherReport weatherReport
    ) throws URISyntaxException {
        log.debug("REST request to update WeatherReport : {}, {}", id, weatherReport);
        if (weatherReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, weatherReport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!weatherReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WeatherReport result = weatherReportService.update(weatherReport);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, weatherReport.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /weather-reports/:id} : Partial updates given fields of an existing weatherReport, field will ignore if it is null
     *
     * @param id the id of the weatherReport to save.
     * @param weatherReport the weatherReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated weatherReport,
     * or with status {@code 400 (Bad Request)} if the weatherReport is not valid,
     * or with status {@code 404 (Not Found)} if the weatherReport is not found,
     * or with status {@code 500 (Internal Server Error)} if the weatherReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/weather-reports/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WeatherReport> partialUpdateWeatherReport(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WeatherReport weatherReport
    ) throws URISyntaxException {
        log.debug("REST request to partial update WeatherReport partially : {}, {}", id, weatherReport);
        if (weatherReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, weatherReport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!weatherReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WeatherReport> result = weatherReportService.partialUpdate(weatherReport);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, weatherReport.getId().toString())
        );
    }

    /**
     * {@code GET  /weather-reports} : get all the weatherReports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of weatherReports in body.
     */
    @GetMapping("/weather-reports")
    public ResponseEntity<List<WeatherReport>> getAllWeatherReports(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of WeatherReports");
        Page<WeatherReport> page = weatherReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /weather-reports/:id} : get the "id" weatherReport.
     *
     * @param id the id of the weatherReport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the weatherReport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/weather-reports/{id}")
    public ResponseEntity<WeatherReport> getWeatherReport(@PathVariable Long id) {
        log.debug("REST request to get WeatherReport : {}", id);
        Optional<WeatherReport> weatherReport = weatherReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(weatherReport);
    }

    /**
     * {@code DELETE  /weather-reports/:id} : delete the "id" weatherReport.
     *
     * @param id the id of the weatherReport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/weather-reports/{id}")
    public ResponseEntity<Void> deleteWeatherReport(@PathVariable Long id) {
        log.debug("REST request to delete WeatherReport : {}", id);
        weatherReportService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
