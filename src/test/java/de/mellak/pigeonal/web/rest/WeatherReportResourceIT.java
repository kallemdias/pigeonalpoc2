package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.WeatherReport;
import de.mellak.pigeonal.repository.WeatherReportRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link WeatherReportResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WeatherReportResourceIT {

    private static final Instant DEFAULT_INITIATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INITIATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_RELEASE_DATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RELEASE_DATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CHECKPOINTS_DISTANCE = 5;
    private static final Integer UPDATED_CHECKPOINTS_DISTANCE = 6;

    private static final String DEFAULT_ALERTS = "AAAAAAAAAA";
    private static final String UPDATED_ALERTS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/weather-reports";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WeatherReportRepository weatherReportRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWeatherReportMockMvc;

    private WeatherReport weatherReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeatherReport createEntity(EntityManager em) {
        WeatherReport weatherReport = new WeatherReport()
            .initiated(DEFAULT_INITIATED)
            .releaseDateTime(DEFAULT_RELEASE_DATE_TIME)
            .checkpointsDistance(DEFAULT_CHECKPOINTS_DISTANCE)
            .alerts(DEFAULT_ALERTS);
        return weatherReport;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeatherReport createUpdatedEntity(EntityManager em) {
        WeatherReport weatherReport = new WeatherReport()
            .initiated(UPDATED_INITIATED)
            .releaseDateTime(UPDATED_RELEASE_DATE_TIME)
            .checkpointsDistance(UPDATED_CHECKPOINTS_DISTANCE)
            .alerts(UPDATED_ALERTS);
        return weatherReport;
    }

    @BeforeEach
    public void initTest() {
        weatherReport = createEntity(em);
    }

    @Test
    @Transactional
    void createWeatherReport() throws Exception {
        int databaseSizeBeforeCreate = weatherReportRepository.findAll().size();
        // Create the WeatherReport
        restWeatherReportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(weatherReport)))
            .andExpect(status().isCreated());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeCreate + 1);
        WeatherReport testWeatherReport = weatherReportList.get(weatherReportList.size() - 1);
        assertThat(testWeatherReport.getInitiated()).isEqualTo(DEFAULT_INITIATED);
        assertThat(testWeatherReport.getReleaseDateTime()).isEqualTo(DEFAULT_RELEASE_DATE_TIME);
        assertThat(testWeatherReport.getCheckpointsDistance()).isEqualTo(DEFAULT_CHECKPOINTS_DISTANCE);
        assertThat(testWeatherReport.getAlerts()).isEqualTo(DEFAULT_ALERTS);
    }

    @Test
    @Transactional
    void createWeatherReportWithExistingId() throws Exception {
        // Create the WeatherReport with an existing ID
        weatherReport.setId(1L);

        int databaseSizeBeforeCreate = weatherReportRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWeatherReportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(weatherReport)))
            .andExpect(status().isBadRequest());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWeatherReports() throws Exception {
        // Initialize the database
        weatherReportRepository.saveAndFlush(weatherReport);

        // Get all the weatherReportList
        restWeatherReportMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(weatherReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].initiated").value(hasItem(DEFAULT_INITIATED.toString())))
            .andExpect(jsonPath("$.[*].releaseDateTime").value(hasItem(DEFAULT_RELEASE_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].checkpointsDistance").value(hasItem(DEFAULT_CHECKPOINTS_DISTANCE)))
            .andExpect(jsonPath("$.[*].alerts").value(hasItem(DEFAULT_ALERTS)));
    }

    @Test
    @Transactional
    void getWeatherReport() throws Exception {
        // Initialize the database
        weatherReportRepository.saveAndFlush(weatherReport);

        // Get the weatherReport
        restWeatherReportMockMvc
            .perform(get(ENTITY_API_URL_ID, weatherReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(weatherReport.getId().intValue()))
            .andExpect(jsonPath("$.initiated").value(DEFAULT_INITIATED.toString()))
            .andExpect(jsonPath("$.releaseDateTime").value(DEFAULT_RELEASE_DATE_TIME.toString()))
            .andExpect(jsonPath("$.checkpointsDistance").value(DEFAULT_CHECKPOINTS_DISTANCE))
            .andExpect(jsonPath("$.alerts").value(DEFAULT_ALERTS));
    }

    @Test
    @Transactional
    void getNonExistingWeatherReport() throws Exception {
        // Get the weatherReport
        restWeatherReportMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWeatherReport() throws Exception {
        // Initialize the database
        weatherReportRepository.saveAndFlush(weatherReport);

        int databaseSizeBeforeUpdate = weatherReportRepository.findAll().size();

        // Update the weatherReport
        WeatherReport updatedWeatherReport = weatherReportRepository.findById(weatherReport.getId()).get();
        // Disconnect from session so that the updates on updatedWeatherReport are not directly saved in db
        em.detach(updatedWeatherReport);
        updatedWeatherReport
            .initiated(UPDATED_INITIATED)
            .releaseDateTime(UPDATED_RELEASE_DATE_TIME)
            .checkpointsDistance(UPDATED_CHECKPOINTS_DISTANCE)
            .alerts(UPDATED_ALERTS);

        restWeatherReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedWeatherReport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedWeatherReport))
            )
            .andExpect(status().isOk());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeUpdate);
        WeatherReport testWeatherReport = weatherReportList.get(weatherReportList.size() - 1);
        assertThat(testWeatherReport.getInitiated()).isEqualTo(UPDATED_INITIATED);
        assertThat(testWeatherReport.getReleaseDateTime()).isEqualTo(UPDATED_RELEASE_DATE_TIME);
        assertThat(testWeatherReport.getCheckpointsDistance()).isEqualTo(UPDATED_CHECKPOINTS_DISTANCE);
        assertThat(testWeatherReport.getAlerts()).isEqualTo(UPDATED_ALERTS);
    }

    @Test
    @Transactional
    void putNonExistingWeatherReport() throws Exception {
        int databaseSizeBeforeUpdate = weatherReportRepository.findAll().size();
        weatherReport.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWeatherReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, weatherReport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(weatherReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWeatherReport() throws Exception {
        int databaseSizeBeforeUpdate = weatherReportRepository.findAll().size();
        weatherReport.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWeatherReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(weatherReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWeatherReport() throws Exception {
        int databaseSizeBeforeUpdate = weatherReportRepository.findAll().size();
        weatherReport.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWeatherReportMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(weatherReport)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWeatherReportWithPatch() throws Exception {
        // Initialize the database
        weatherReportRepository.saveAndFlush(weatherReport);

        int databaseSizeBeforeUpdate = weatherReportRepository.findAll().size();

        // Update the weatherReport using partial update
        WeatherReport partialUpdatedWeatherReport = new WeatherReport();
        partialUpdatedWeatherReport.setId(weatherReport.getId());

        partialUpdatedWeatherReport.alerts(UPDATED_ALERTS);

        restWeatherReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWeatherReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWeatherReport))
            )
            .andExpect(status().isOk());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeUpdate);
        WeatherReport testWeatherReport = weatherReportList.get(weatherReportList.size() - 1);
        assertThat(testWeatherReport.getInitiated()).isEqualTo(DEFAULT_INITIATED);
        assertThat(testWeatherReport.getReleaseDateTime()).isEqualTo(DEFAULT_RELEASE_DATE_TIME);
        assertThat(testWeatherReport.getCheckpointsDistance()).isEqualTo(DEFAULT_CHECKPOINTS_DISTANCE);
        assertThat(testWeatherReport.getAlerts()).isEqualTo(UPDATED_ALERTS);
    }

    @Test
    @Transactional
    void fullUpdateWeatherReportWithPatch() throws Exception {
        // Initialize the database
        weatherReportRepository.saveAndFlush(weatherReport);

        int databaseSizeBeforeUpdate = weatherReportRepository.findAll().size();

        // Update the weatherReport using partial update
        WeatherReport partialUpdatedWeatherReport = new WeatherReport();
        partialUpdatedWeatherReport.setId(weatherReport.getId());

        partialUpdatedWeatherReport
            .initiated(UPDATED_INITIATED)
            .releaseDateTime(UPDATED_RELEASE_DATE_TIME)
            .checkpointsDistance(UPDATED_CHECKPOINTS_DISTANCE)
            .alerts(UPDATED_ALERTS);

        restWeatherReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWeatherReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWeatherReport))
            )
            .andExpect(status().isOk());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeUpdate);
        WeatherReport testWeatherReport = weatherReportList.get(weatherReportList.size() - 1);
        assertThat(testWeatherReport.getInitiated()).isEqualTo(UPDATED_INITIATED);
        assertThat(testWeatherReport.getReleaseDateTime()).isEqualTo(UPDATED_RELEASE_DATE_TIME);
        assertThat(testWeatherReport.getCheckpointsDistance()).isEqualTo(UPDATED_CHECKPOINTS_DISTANCE);
        assertThat(testWeatherReport.getAlerts()).isEqualTo(UPDATED_ALERTS);
    }

    @Test
    @Transactional
    void patchNonExistingWeatherReport() throws Exception {
        int databaseSizeBeforeUpdate = weatherReportRepository.findAll().size();
        weatherReport.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWeatherReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, weatherReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(weatherReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWeatherReport() throws Exception {
        int databaseSizeBeforeUpdate = weatherReportRepository.findAll().size();
        weatherReport.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWeatherReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(weatherReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWeatherReport() throws Exception {
        int databaseSizeBeforeUpdate = weatherReportRepository.findAll().size();
        weatherReport.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWeatherReportMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(weatherReport))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WeatherReport in the database
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWeatherReport() throws Exception {
        // Initialize the database
        weatherReportRepository.saveAndFlush(weatherReport);

        int databaseSizeBeforeDelete = weatherReportRepository.findAll().size();

        // Delete the weatherReport
        restWeatherReportMockMvc
            .perform(delete(ENTITY_API_URL_ID, weatherReport.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WeatherReport> weatherReportList = weatherReportRepository.findAll();
        assertThat(weatherReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
