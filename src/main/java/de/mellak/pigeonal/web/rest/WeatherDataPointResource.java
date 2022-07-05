package de.mellak.pigeonal.web.rest;

import de.mellak.pigeonal.domain.WeatherDataPoint;
import de.mellak.pigeonal.repository.WeatherDataPointRepository;
import de.mellak.pigeonal.service.WeatherDataPointService;
import de.mellak.pigeonal.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
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
 * REST controller for managing {@link de.mellak.pigeonal.domain.WeatherDataPoint}.
 */
@RestController
@RequestMapping("/api")
public class WeatherDataPointResource {

    private final Logger log = LoggerFactory.getLogger(WeatherDataPointResource.class);

    private static final String ENTITY_NAME = "weatherDataPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WeatherDataPointService weatherDataPointService;

    private final WeatherDataPointRepository weatherDataPointRepository;

    public WeatherDataPointResource(
        WeatherDataPointService weatherDataPointService,
        WeatherDataPointRepository weatherDataPointRepository
    ) {
        this.weatherDataPointService = weatherDataPointService;
        this.weatherDataPointRepository = weatherDataPointRepository;
    }

    /**
     * {@code POST  /weather-data-points} : Create a new weatherDataPoint.
     *
     * @param weatherDataPoint the weatherDataPoint to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new weatherDataPoint, or with status {@code 400 (Bad Request)} if the weatherDataPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/weather-data-points")
    public ResponseEntity<WeatherDataPoint> createWeatherDataPoint(@RequestBody WeatherDataPoint weatherDataPoint)
        throws URISyntaxException {
        log.debug("REST request to save WeatherDataPoint : {}", weatherDataPoint);
        if (weatherDataPoint.getId() != null) {
            throw new BadRequestAlertException("A new weatherDataPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WeatherDataPoint result = weatherDataPointService.save(weatherDataPoint);
        return ResponseEntity
            .created(new URI("/api/weather-data-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /weather-data-points/:id} : Updates an existing weatherDataPoint.
     *
     * @param id the id of the weatherDataPoint to save.
     * @param weatherDataPoint the weatherDataPoint to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated weatherDataPoint,
     * or with status {@code 400 (Bad Request)} if the weatherDataPoint is not valid,
     * or with status {@code 500 (Internal Server Error)} if the weatherDataPoint couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/weather-data-points/{id}")
    public ResponseEntity<WeatherDataPoint> updateWeatherDataPoint(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WeatherDataPoint weatherDataPoint
    ) throws URISyntaxException {
        log.debug("REST request to update WeatherDataPoint : {}, {}", id, weatherDataPoint);
        if (weatherDataPoint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, weatherDataPoint.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!weatherDataPointRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WeatherDataPoint result = weatherDataPointService.update(weatherDataPoint);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, weatherDataPoint.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /weather-data-points/:id} : Partial updates given fields of an existing weatherDataPoint, field will ignore if it is null
     *
     * @param id the id of the weatherDataPoint to save.
     * @param weatherDataPoint the weatherDataPoint to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated weatherDataPoint,
     * or with status {@code 400 (Bad Request)} if the weatherDataPoint is not valid,
     * or with status {@code 404 (Not Found)} if the weatherDataPoint is not found,
     * or with status {@code 500 (Internal Server Error)} if the weatherDataPoint couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/weather-data-points/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WeatherDataPoint> partialUpdateWeatherDataPoint(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WeatherDataPoint weatherDataPoint
    ) throws URISyntaxException {
        log.debug("REST request to partial update WeatherDataPoint partially : {}, {}", id, weatherDataPoint);
        if (weatherDataPoint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, weatherDataPoint.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!weatherDataPointRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WeatherDataPoint> result = weatherDataPointService.partialUpdate(weatherDataPoint);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, weatherDataPoint.getId().toString())
        );
    }

    /**
     * {@code GET  /weather-data-points} : get all the weatherDataPoints.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of weatherDataPoints in body.
     */
    @GetMapping("/weather-data-points")
    public ResponseEntity<List<WeatherDataPoint>> getAllWeatherDataPoints(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String filter
    ) {
        if ("releavanceheader-is-null".equals(filter)) {
            log.debug("REST request to get all WeatherDataPoints where releavanceHeader is null");
            return new ResponseEntity<>(weatherDataPointService.findAllWhereReleavanceHeaderIsNull(), HttpStatus.OK);
        }
        log.debug("REST request to get a page of WeatherDataPoints");
        Page<WeatherDataPoint> page = weatherDataPointService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /weather-data-points/:id} : get the "id" weatherDataPoint.
     *
     * @param id the id of the weatherDataPoint to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the weatherDataPoint, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/weather-data-points/{id}")
    public ResponseEntity<WeatherDataPoint> getWeatherDataPoint(@PathVariable Long id) {
        log.debug("REST request to get WeatherDataPoint : {}", id);
        Optional<WeatherDataPoint> weatherDataPoint = weatherDataPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(weatherDataPoint);
    }

    /**
     * {@code DELETE  /weather-data-points/:id} : delete the "id" weatherDataPoint.
     *
     * @param id the id of the weatherDataPoint to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/weather-data-points/{id}")
    public ResponseEntity<Void> deleteWeatherDataPoint(@PathVariable Long id) {
        log.debug("REST request to delete WeatherDataPoint : {}", id);
        weatherDataPointService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
