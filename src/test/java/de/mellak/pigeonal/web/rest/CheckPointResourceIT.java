package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.CheckPoint;
import de.mellak.pigeonal.repository.CheckPointRepository;
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
 * Integration tests for the {@link CheckPointResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CheckPointResourceIT {

    private static final Integer DEFAULT_ORDER = 0;
    private static final Integer UPDATED_ORDER = 1;

    private static final Instant DEFAULT_DATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_LAT_DECIMAL = 1D;
    private static final Double UPDATED_LAT_DECIMAL = 2D;

    private static final Double DEFAULT_LNG_DECIMAL = 1D;
    private static final Double UPDATED_LNG_DECIMAL = 2D;

    private static final Double DEFAULT_DISTANCE = 0D;
    private static final Double UPDATED_DISTANCE = 1D;

    private static final String DEFAULT_DISTANCE_DISPLAYED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DISTANCE_DISPLAYED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_ALERTS = "AAAAAAAAAA";
    private static final String UPDATED_ALERTS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/check-points";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CheckPointRepository checkPointRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCheckPointMockMvc;

    private CheckPoint checkPoint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckPoint createEntity(EntityManager em) {
        CheckPoint checkPoint = new CheckPoint()
            .order(DEFAULT_ORDER)
            .dateTime(DEFAULT_DATE_TIME)
            .locationName(DEFAULT_LOCATION_NAME)
            .latDecimal(DEFAULT_LAT_DECIMAL)
            .lngDecimal(DEFAULT_LNG_DECIMAL)
            .distance(DEFAULT_DISTANCE)
            .distanceDisplayedValue(DEFAULT_DISTANCE_DISPLAYED_VALUE)
            .link(DEFAULT_LINK)
            .alerts(DEFAULT_ALERTS);
        return checkPoint;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckPoint createUpdatedEntity(EntityManager em) {
        CheckPoint checkPoint = new CheckPoint()
            .order(UPDATED_ORDER)
            .dateTime(UPDATED_DATE_TIME)
            .locationName(UPDATED_LOCATION_NAME)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .distance(UPDATED_DISTANCE)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .link(UPDATED_LINK)
            .alerts(UPDATED_ALERTS);
        return checkPoint;
    }

    @BeforeEach
    public void initTest() {
        checkPoint = createEntity(em);
    }

    @Test
    @Transactional
    void createCheckPoint() throws Exception {
        int databaseSizeBeforeCreate = checkPointRepository.findAll().size();
        // Create the CheckPoint
        restCheckPointMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkPoint)))
            .andExpect(status().isCreated());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeCreate + 1);
        CheckPoint testCheckPoint = checkPointList.get(checkPointList.size() - 1);
        assertThat(testCheckPoint.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testCheckPoint.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testCheckPoint.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testCheckPoint.getLatDecimal()).isEqualTo(DEFAULT_LAT_DECIMAL);
        assertThat(testCheckPoint.getLngDecimal()).isEqualTo(DEFAULT_LNG_DECIMAL);
        assertThat(testCheckPoint.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testCheckPoint.getDistanceDisplayedValue()).isEqualTo(DEFAULT_DISTANCE_DISPLAYED_VALUE);
        assertThat(testCheckPoint.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testCheckPoint.getAlerts()).isEqualTo(DEFAULT_ALERTS);
    }

    @Test
    @Transactional
    void createCheckPointWithExistingId() throws Exception {
        // Create the CheckPoint with an existing ID
        checkPoint.setId(1L);

        int databaseSizeBeforeCreate = checkPointRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCheckPointMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkPoint)))
            .andExpect(status().isBadRequest());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCheckPoints() throws Exception {
        // Initialize the database
        checkPointRepository.saveAndFlush(checkPoint);

        // Get all the checkPointList
        restCheckPointMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checkPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER)))
            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(DEFAULT_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].latDecimal").value(hasItem(DEFAULT_LAT_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].lngDecimal").value(hasItem(DEFAULT_LNG_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].distance").value(hasItem(DEFAULT_DISTANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].distanceDisplayedValue").value(hasItem(DEFAULT_DISTANCE_DISPLAYED_VALUE)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].alerts").value(hasItem(DEFAULT_ALERTS)));
    }

    @Test
    @Transactional
    void getCheckPoint() throws Exception {
        // Initialize the database
        checkPointRepository.saveAndFlush(checkPoint);

        // Get the checkPoint
        restCheckPointMockMvc
            .perform(get(ENTITY_API_URL_ID, checkPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(checkPoint.getId().intValue()))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER))
            .andExpect(jsonPath("$.dateTime").value(DEFAULT_DATE_TIME.toString()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.latDecimal").value(DEFAULT_LAT_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.lngDecimal").value(DEFAULT_LNG_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.distance").value(DEFAULT_DISTANCE.doubleValue()))
            .andExpect(jsonPath("$.distanceDisplayedValue").value(DEFAULT_DISTANCE_DISPLAYED_VALUE))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.alerts").value(DEFAULT_ALERTS));
    }

    @Test
    @Transactional
    void getNonExistingCheckPoint() throws Exception {
        // Get the checkPoint
        restCheckPointMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCheckPoint() throws Exception {
        // Initialize the database
        checkPointRepository.saveAndFlush(checkPoint);

        int databaseSizeBeforeUpdate = checkPointRepository.findAll().size();

        // Update the checkPoint
        CheckPoint updatedCheckPoint = checkPointRepository.findById(checkPoint.getId()).get();
        // Disconnect from session so that the updates on updatedCheckPoint are not directly saved in db
        em.detach(updatedCheckPoint);
        updatedCheckPoint
            .order(UPDATED_ORDER)
            .dateTime(UPDATED_DATE_TIME)
            .locationName(UPDATED_LOCATION_NAME)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .distance(UPDATED_DISTANCE)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .link(UPDATED_LINK)
            .alerts(UPDATED_ALERTS);

        restCheckPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCheckPoint.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCheckPoint))
            )
            .andExpect(status().isOk());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeUpdate);
        CheckPoint testCheckPoint = checkPointList.get(checkPointList.size() - 1);
        assertThat(testCheckPoint.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testCheckPoint.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testCheckPoint.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testCheckPoint.getLatDecimal()).isEqualTo(UPDATED_LAT_DECIMAL);
        assertThat(testCheckPoint.getLngDecimal()).isEqualTo(UPDATED_LNG_DECIMAL);
        assertThat(testCheckPoint.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testCheckPoint.getDistanceDisplayedValue()).isEqualTo(UPDATED_DISTANCE_DISPLAYED_VALUE);
        assertThat(testCheckPoint.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testCheckPoint.getAlerts()).isEqualTo(UPDATED_ALERTS);
    }

    @Test
    @Transactional
    void putNonExistingCheckPoint() throws Exception {
        int databaseSizeBeforeUpdate = checkPointRepository.findAll().size();
        checkPoint.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, checkPoint.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(checkPoint))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCheckPoint() throws Exception {
        int databaseSizeBeforeUpdate = checkPointRepository.findAll().size();
        checkPoint.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(checkPoint))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCheckPoint() throws Exception {
        int databaseSizeBeforeUpdate = checkPointRepository.findAll().size();
        checkPoint.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckPointMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkPoint)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCheckPointWithPatch() throws Exception {
        // Initialize the database
        checkPointRepository.saveAndFlush(checkPoint);

        int databaseSizeBeforeUpdate = checkPointRepository.findAll().size();

        // Update the checkPoint using partial update
        CheckPoint partialUpdatedCheckPoint = new CheckPoint();
        partialUpdatedCheckPoint.setId(checkPoint.getId());

        partialUpdatedCheckPoint
            .dateTime(UPDATED_DATE_TIME)
            .locationName(UPDATED_LOCATION_NAME)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .alerts(UPDATED_ALERTS);

        restCheckPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckPoint))
            )
            .andExpect(status().isOk());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeUpdate);
        CheckPoint testCheckPoint = checkPointList.get(checkPointList.size() - 1);
        assertThat(testCheckPoint.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testCheckPoint.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testCheckPoint.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testCheckPoint.getLatDecimal()).isEqualTo(DEFAULT_LAT_DECIMAL);
        assertThat(testCheckPoint.getLngDecimal()).isEqualTo(DEFAULT_LNG_DECIMAL);
        assertThat(testCheckPoint.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testCheckPoint.getDistanceDisplayedValue()).isEqualTo(UPDATED_DISTANCE_DISPLAYED_VALUE);
        assertThat(testCheckPoint.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testCheckPoint.getAlerts()).isEqualTo(UPDATED_ALERTS);
    }

    @Test
    @Transactional
    void fullUpdateCheckPointWithPatch() throws Exception {
        // Initialize the database
        checkPointRepository.saveAndFlush(checkPoint);

        int databaseSizeBeforeUpdate = checkPointRepository.findAll().size();

        // Update the checkPoint using partial update
        CheckPoint partialUpdatedCheckPoint = new CheckPoint();
        partialUpdatedCheckPoint.setId(checkPoint.getId());

        partialUpdatedCheckPoint
            .order(UPDATED_ORDER)
            .dateTime(UPDATED_DATE_TIME)
            .locationName(UPDATED_LOCATION_NAME)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .distance(UPDATED_DISTANCE)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .link(UPDATED_LINK)
            .alerts(UPDATED_ALERTS);

        restCheckPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckPoint))
            )
            .andExpect(status().isOk());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeUpdate);
        CheckPoint testCheckPoint = checkPointList.get(checkPointList.size() - 1);
        assertThat(testCheckPoint.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testCheckPoint.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testCheckPoint.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testCheckPoint.getLatDecimal()).isEqualTo(UPDATED_LAT_DECIMAL);
        assertThat(testCheckPoint.getLngDecimal()).isEqualTo(UPDATED_LNG_DECIMAL);
        assertThat(testCheckPoint.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testCheckPoint.getDistanceDisplayedValue()).isEqualTo(UPDATED_DISTANCE_DISPLAYED_VALUE);
        assertThat(testCheckPoint.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testCheckPoint.getAlerts()).isEqualTo(UPDATED_ALERTS);
    }

    @Test
    @Transactional
    void patchNonExistingCheckPoint() throws Exception {
        int databaseSizeBeforeUpdate = checkPointRepository.findAll().size();
        checkPoint.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, checkPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(checkPoint))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCheckPoint() throws Exception {
        int databaseSizeBeforeUpdate = checkPointRepository.findAll().size();
        checkPoint.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(checkPoint))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCheckPoint() throws Exception {
        int databaseSizeBeforeUpdate = checkPointRepository.findAll().size();
        checkPoint.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckPointMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(checkPoint))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckPoint in the database
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCheckPoint() throws Exception {
        // Initialize the database
        checkPointRepository.saveAndFlush(checkPoint);

        int databaseSizeBeforeDelete = checkPointRepository.findAll().size();

        // Delete the checkPoint
        restCheckPointMockMvc
            .perform(delete(ENTITY_API_URL_ID, checkPoint.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CheckPoint> checkPointList = checkPointRepository.findAll();
        assertThat(checkPointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
