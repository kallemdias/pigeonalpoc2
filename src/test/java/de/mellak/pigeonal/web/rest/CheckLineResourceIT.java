package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.CheckLine;
import de.mellak.pigeonal.repository.CheckLineRepository;
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
 * Integration tests for the {@link CheckLineResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CheckLineResourceIT {

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

    private static final String ENTITY_API_URL = "/api/check-lines";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CheckLineRepository checkLineRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCheckLineMockMvc;

    private CheckLine checkLine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckLine createEntity(EntityManager em) {
        CheckLine checkLine = new CheckLine()
            .order(DEFAULT_ORDER)
            .dateTime(DEFAULT_DATE_TIME)
            .locationName(DEFAULT_LOCATION_NAME)
            .latDecimal(DEFAULT_LAT_DECIMAL)
            .lngDecimal(DEFAULT_LNG_DECIMAL)
            .distance(DEFAULT_DISTANCE)
            .distanceDisplayedValue(DEFAULT_DISTANCE_DISPLAYED_VALUE)
            .link(DEFAULT_LINK);
        return checkLine;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckLine createUpdatedEntity(EntityManager em) {
        CheckLine checkLine = new CheckLine()
            .order(UPDATED_ORDER)
            .dateTime(UPDATED_DATE_TIME)
            .locationName(UPDATED_LOCATION_NAME)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .distance(UPDATED_DISTANCE)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .link(UPDATED_LINK);
        return checkLine;
    }

    @BeforeEach
    public void initTest() {
        checkLine = createEntity(em);
    }

    @Test
    @Transactional
    void createCheckLine() throws Exception {
        int databaseSizeBeforeCreate = checkLineRepository.findAll().size();
        // Create the CheckLine
        restCheckLineMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkLine)))
            .andExpect(status().isCreated());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeCreate + 1);
        CheckLine testCheckLine = checkLineList.get(checkLineList.size() - 1);
        assertThat(testCheckLine.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testCheckLine.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testCheckLine.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testCheckLine.getLatDecimal()).isEqualTo(DEFAULT_LAT_DECIMAL);
        assertThat(testCheckLine.getLngDecimal()).isEqualTo(DEFAULT_LNG_DECIMAL);
        assertThat(testCheckLine.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testCheckLine.getDistanceDisplayedValue()).isEqualTo(DEFAULT_DISTANCE_DISPLAYED_VALUE);
        assertThat(testCheckLine.getLink()).isEqualTo(DEFAULT_LINK);
    }

    @Test
    @Transactional
    void createCheckLineWithExistingId() throws Exception {
        // Create the CheckLine with an existing ID
        checkLine.setId(1L);

        int databaseSizeBeforeCreate = checkLineRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCheckLineMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkLine)))
            .andExpect(status().isBadRequest());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCheckLines() throws Exception {
        // Initialize the database
        checkLineRepository.saveAndFlush(checkLine);

        // Get all the checkLineList
        restCheckLineMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checkLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER)))
            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(DEFAULT_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].latDecimal").value(hasItem(DEFAULT_LAT_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].lngDecimal").value(hasItem(DEFAULT_LNG_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].distance").value(hasItem(DEFAULT_DISTANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].distanceDisplayedValue").value(hasItem(DEFAULT_DISTANCE_DISPLAYED_VALUE)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)));
    }

    @Test
    @Transactional
    void getCheckLine() throws Exception {
        // Initialize the database
        checkLineRepository.saveAndFlush(checkLine);

        // Get the checkLine
        restCheckLineMockMvc
            .perform(get(ENTITY_API_URL_ID, checkLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(checkLine.getId().intValue()))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER))
            .andExpect(jsonPath("$.dateTime").value(DEFAULT_DATE_TIME.toString()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.latDecimal").value(DEFAULT_LAT_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.lngDecimal").value(DEFAULT_LNG_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.distance").value(DEFAULT_DISTANCE.doubleValue()))
            .andExpect(jsonPath("$.distanceDisplayedValue").value(DEFAULT_DISTANCE_DISPLAYED_VALUE))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK));
    }

    @Test
    @Transactional
    void getNonExistingCheckLine() throws Exception {
        // Get the checkLine
        restCheckLineMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCheckLine() throws Exception {
        // Initialize the database
        checkLineRepository.saveAndFlush(checkLine);

        int databaseSizeBeforeUpdate = checkLineRepository.findAll().size();

        // Update the checkLine
        CheckLine updatedCheckLine = checkLineRepository.findById(checkLine.getId()).get();
        // Disconnect from session so that the updates on updatedCheckLine are not directly saved in db
        em.detach(updatedCheckLine);
        updatedCheckLine
            .order(UPDATED_ORDER)
            .dateTime(UPDATED_DATE_TIME)
            .locationName(UPDATED_LOCATION_NAME)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .distance(UPDATED_DISTANCE)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .link(UPDATED_LINK);

        restCheckLineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCheckLine.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCheckLine))
            )
            .andExpect(status().isOk());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeUpdate);
        CheckLine testCheckLine = checkLineList.get(checkLineList.size() - 1);
        assertThat(testCheckLine.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testCheckLine.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testCheckLine.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testCheckLine.getLatDecimal()).isEqualTo(UPDATED_LAT_DECIMAL);
        assertThat(testCheckLine.getLngDecimal()).isEqualTo(UPDATED_LNG_DECIMAL);
        assertThat(testCheckLine.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testCheckLine.getDistanceDisplayedValue()).isEqualTo(UPDATED_DISTANCE_DISPLAYED_VALUE);
        assertThat(testCheckLine.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    @Transactional
    void putNonExistingCheckLine() throws Exception {
        int databaseSizeBeforeUpdate = checkLineRepository.findAll().size();
        checkLine.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckLineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, checkLine.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(checkLine))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCheckLine() throws Exception {
        int databaseSizeBeforeUpdate = checkLineRepository.findAll().size();
        checkLine.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckLineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(checkLine))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCheckLine() throws Exception {
        int databaseSizeBeforeUpdate = checkLineRepository.findAll().size();
        checkLine.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckLineMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(checkLine)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCheckLineWithPatch() throws Exception {
        // Initialize the database
        checkLineRepository.saveAndFlush(checkLine);

        int databaseSizeBeforeUpdate = checkLineRepository.findAll().size();

        // Update the checkLine using partial update
        CheckLine partialUpdatedCheckLine = new CheckLine();
        partialUpdatedCheckLine.setId(checkLine.getId());

        partialUpdatedCheckLine
            .order(UPDATED_ORDER)
            .dateTime(UPDATED_DATE_TIME)
            .locationName(UPDATED_LOCATION_NAME)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .distance(UPDATED_DISTANCE)
            .link(UPDATED_LINK);

        restCheckLineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckLine.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckLine))
            )
            .andExpect(status().isOk());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeUpdate);
        CheckLine testCheckLine = checkLineList.get(checkLineList.size() - 1);
        assertThat(testCheckLine.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testCheckLine.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testCheckLine.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testCheckLine.getLatDecimal()).isEqualTo(DEFAULT_LAT_DECIMAL);
        assertThat(testCheckLine.getLngDecimal()).isEqualTo(UPDATED_LNG_DECIMAL);
        assertThat(testCheckLine.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testCheckLine.getDistanceDisplayedValue()).isEqualTo(DEFAULT_DISTANCE_DISPLAYED_VALUE);
        assertThat(testCheckLine.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    @Transactional
    void fullUpdateCheckLineWithPatch() throws Exception {
        // Initialize the database
        checkLineRepository.saveAndFlush(checkLine);

        int databaseSizeBeforeUpdate = checkLineRepository.findAll().size();

        // Update the checkLine using partial update
        CheckLine partialUpdatedCheckLine = new CheckLine();
        partialUpdatedCheckLine.setId(checkLine.getId());

        partialUpdatedCheckLine
            .order(UPDATED_ORDER)
            .dateTime(UPDATED_DATE_TIME)
            .locationName(UPDATED_LOCATION_NAME)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .distance(UPDATED_DISTANCE)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .link(UPDATED_LINK);

        restCheckLineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCheckLine.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCheckLine))
            )
            .andExpect(status().isOk());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeUpdate);
        CheckLine testCheckLine = checkLineList.get(checkLineList.size() - 1);
        assertThat(testCheckLine.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testCheckLine.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testCheckLine.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testCheckLine.getLatDecimal()).isEqualTo(UPDATED_LAT_DECIMAL);
        assertThat(testCheckLine.getLngDecimal()).isEqualTo(UPDATED_LNG_DECIMAL);
        assertThat(testCheckLine.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testCheckLine.getDistanceDisplayedValue()).isEqualTo(UPDATED_DISTANCE_DISPLAYED_VALUE);
        assertThat(testCheckLine.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    @Transactional
    void patchNonExistingCheckLine() throws Exception {
        int databaseSizeBeforeUpdate = checkLineRepository.findAll().size();
        checkLine.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckLineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, checkLine.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(checkLine))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCheckLine() throws Exception {
        int databaseSizeBeforeUpdate = checkLineRepository.findAll().size();
        checkLine.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckLineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(checkLine))
            )
            .andExpect(status().isBadRequest());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCheckLine() throws Exception {
        int databaseSizeBeforeUpdate = checkLineRepository.findAll().size();
        checkLine.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCheckLineMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(checkLine))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CheckLine in the database
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCheckLine() throws Exception {
        // Initialize the database
        checkLineRepository.saveAndFlush(checkLine);

        int databaseSizeBeforeDelete = checkLineRepository.findAll().size();

        // Delete the checkLine
        restCheckLineMockMvc
            .perform(delete(ENTITY_API_URL_ID, checkLine.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CheckLine> checkLineList = checkLineRepository.findAll();
        assertThat(checkLineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
