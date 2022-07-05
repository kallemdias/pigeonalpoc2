package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.GpsCoordinateCheck;
import de.mellak.pigeonal.domain.enumeration.LatDirection;
import de.mellak.pigeonal.domain.enumeration.LngDirection;
import de.mellak.pigeonal.repository.GpsCoordinateCheckRepository;
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
 * Integration tests for the {@link GpsCoordinateCheckResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GpsCoordinateCheckResourceIT {

    private static final Integer DEFAULT_LAT_DEG = 0;
    private static final Integer UPDATED_LAT_DEG = 1;

    private static final Integer DEFAULT_LAT_MIN = 0;
    private static final Integer UPDATED_LAT_MIN = 1;

    private static final Float DEFAULT_LAT_SEC = 1F;
    private static final Float UPDATED_LAT_SEC = 2F;

    private static final LatDirection DEFAULT_LAT_DIRECTION = LatDirection.NORTH;
    private static final LatDirection UPDATED_LAT_DIRECTION = LatDirection.SOUTH;

    private static final Integer DEFAULT_LNG_DEG = 0;
    private static final Integer UPDATED_LNG_DEG = 1;

    private static final Integer DEFAULT_LNG_MIN = 0;
    private static final Integer UPDATED_LNG_MIN = 1;

    private static final Float DEFAULT_LNG_SEC = 1F;
    private static final Float UPDATED_LNG_SEC = 2F;

    private static final LngDirection DEFAULT_LNG_DIRECTION = LngDirection.EAST;
    private static final LngDirection UPDATED_LNG_DIRECTION = LngDirection.WEST;

    private static final String DEFAULT_LAT_DISPLAYED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_LAT_DISPLAYED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_LNG_DISPLAYED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_LNG_DISPLAYED_VALUE = "BBBBBBBBBB";

    private static final Double DEFAULT_LAT_DECIMAL = 1D;
    private static final Double UPDATED_LAT_DECIMAL = 2D;

    private static final Double DEFAULT_LNG_DECIMAL = 1D;
    private static final Double UPDATED_LNG_DECIMAL = 2D;

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/gps-coordinate-checks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GpsCoordinateCheckRepository gpsCoordinateCheckRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGpsCoordinateCheckMockMvc;

    private GpsCoordinateCheck gpsCoordinateCheck;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GpsCoordinateCheck createEntity(EntityManager em) {
        GpsCoordinateCheck gpsCoordinateCheck = new GpsCoordinateCheck()
            .latDeg(DEFAULT_LAT_DEG)
            .latMin(DEFAULT_LAT_MIN)
            .latSec(DEFAULT_LAT_SEC)
            .latDirection(DEFAULT_LAT_DIRECTION)
            .lngDeg(DEFAULT_LNG_DEG)
            .lngMin(DEFAULT_LNG_MIN)
            .lngSec(DEFAULT_LNG_SEC)
            .lngDirection(DEFAULT_LNG_DIRECTION)
            .latDisplayedValue(DEFAULT_LAT_DISPLAYED_VALUE)
            .lngDisplayedValue(DEFAULT_LNG_DISPLAYED_VALUE)
            .latDecimal(DEFAULT_LAT_DECIMAL)
            .lngDecimal(DEFAULT_LNG_DECIMAL)
            .link(DEFAULT_LINK);
        return gpsCoordinateCheck;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GpsCoordinateCheck createUpdatedEntity(EntityManager em) {
        GpsCoordinateCheck gpsCoordinateCheck = new GpsCoordinateCheck()
            .latDeg(UPDATED_LAT_DEG)
            .latMin(UPDATED_LAT_MIN)
            .latSec(UPDATED_LAT_SEC)
            .latDirection(UPDATED_LAT_DIRECTION)
            .lngDeg(UPDATED_LNG_DEG)
            .lngMin(UPDATED_LNG_MIN)
            .lngSec(UPDATED_LNG_SEC)
            .lngDirection(UPDATED_LNG_DIRECTION)
            .latDisplayedValue(UPDATED_LAT_DISPLAYED_VALUE)
            .lngDisplayedValue(UPDATED_LNG_DISPLAYED_VALUE)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .link(UPDATED_LINK);
        return gpsCoordinateCheck;
    }

    @BeforeEach
    public void initTest() {
        gpsCoordinateCheck = createEntity(em);
    }

    @Test
    @Transactional
    void createGpsCoordinateCheck() throws Exception {
        int databaseSizeBeforeCreate = gpsCoordinateCheckRepository.findAll().size();
        // Create the GpsCoordinateCheck
        restGpsCoordinateCheckMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gpsCoordinateCheck))
            )
            .andExpect(status().isCreated());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeCreate + 1);
        GpsCoordinateCheck testGpsCoordinateCheck = gpsCoordinateCheckList.get(gpsCoordinateCheckList.size() - 1);
        assertThat(testGpsCoordinateCheck.getLatDeg()).isEqualTo(DEFAULT_LAT_DEG);
        assertThat(testGpsCoordinateCheck.getLatMin()).isEqualTo(DEFAULT_LAT_MIN);
        assertThat(testGpsCoordinateCheck.getLatSec()).isEqualTo(DEFAULT_LAT_SEC);
        assertThat(testGpsCoordinateCheck.getLatDirection()).isEqualTo(DEFAULT_LAT_DIRECTION);
        assertThat(testGpsCoordinateCheck.getLngDeg()).isEqualTo(DEFAULT_LNG_DEG);
        assertThat(testGpsCoordinateCheck.getLngMin()).isEqualTo(DEFAULT_LNG_MIN);
        assertThat(testGpsCoordinateCheck.getLngSec()).isEqualTo(DEFAULT_LNG_SEC);
        assertThat(testGpsCoordinateCheck.getLngDirection()).isEqualTo(DEFAULT_LNG_DIRECTION);
        assertThat(testGpsCoordinateCheck.getLatDisplayedValue()).isEqualTo(DEFAULT_LAT_DISPLAYED_VALUE);
        assertThat(testGpsCoordinateCheck.getLngDisplayedValue()).isEqualTo(DEFAULT_LNG_DISPLAYED_VALUE);
        assertThat(testGpsCoordinateCheck.getLatDecimal()).isEqualTo(DEFAULT_LAT_DECIMAL);
        assertThat(testGpsCoordinateCheck.getLngDecimal()).isEqualTo(DEFAULT_LNG_DECIMAL);
        assertThat(testGpsCoordinateCheck.getLink()).isEqualTo(DEFAULT_LINK);
    }

    @Test
    @Transactional
    void createGpsCoordinateCheckWithExistingId() throws Exception {
        // Create the GpsCoordinateCheck with an existing ID
        gpsCoordinateCheck.setId(1L);

        int databaseSizeBeforeCreate = gpsCoordinateCheckRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGpsCoordinateCheckMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gpsCoordinateCheck))
            )
            .andExpect(status().isBadRequest());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGpsCoordinateChecks() throws Exception {
        // Initialize the database
        gpsCoordinateCheckRepository.saveAndFlush(gpsCoordinateCheck);

        // Get all the gpsCoordinateCheckList
        restGpsCoordinateCheckMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gpsCoordinateCheck.getId().intValue())))
            .andExpect(jsonPath("$.[*].latDeg").value(hasItem(DEFAULT_LAT_DEG)))
            .andExpect(jsonPath("$.[*].latMin").value(hasItem(DEFAULT_LAT_MIN)))
            .andExpect(jsonPath("$.[*].latSec").value(hasItem(DEFAULT_LAT_SEC.doubleValue())))
            .andExpect(jsonPath("$.[*].latDirection").value(hasItem(DEFAULT_LAT_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].lngDeg").value(hasItem(DEFAULT_LNG_DEG)))
            .andExpect(jsonPath("$.[*].lngMin").value(hasItem(DEFAULT_LNG_MIN)))
            .andExpect(jsonPath("$.[*].lngSec").value(hasItem(DEFAULT_LNG_SEC.doubleValue())))
            .andExpect(jsonPath("$.[*].lngDirection").value(hasItem(DEFAULT_LNG_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].latDisplayedValue").value(hasItem(DEFAULT_LAT_DISPLAYED_VALUE)))
            .andExpect(jsonPath("$.[*].lngDisplayedValue").value(hasItem(DEFAULT_LNG_DISPLAYED_VALUE)))
            .andExpect(jsonPath("$.[*].latDecimal").value(hasItem(DEFAULT_LAT_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].lngDecimal").value(hasItem(DEFAULT_LNG_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)));
    }

    @Test
    @Transactional
    void getGpsCoordinateCheck() throws Exception {
        // Initialize the database
        gpsCoordinateCheckRepository.saveAndFlush(gpsCoordinateCheck);

        // Get the gpsCoordinateCheck
        restGpsCoordinateCheckMockMvc
            .perform(get(ENTITY_API_URL_ID, gpsCoordinateCheck.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gpsCoordinateCheck.getId().intValue()))
            .andExpect(jsonPath("$.latDeg").value(DEFAULT_LAT_DEG))
            .andExpect(jsonPath("$.latMin").value(DEFAULT_LAT_MIN))
            .andExpect(jsonPath("$.latSec").value(DEFAULT_LAT_SEC.doubleValue()))
            .andExpect(jsonPath("$.latDirection").value(DEFAULT_LAT_DIRECTION.toString()))
            .andExpect(jsonPath("$.lngDeg").value(DEFAULT_LNG_DEG))
            .andExpect(jsonPath("$.lngMin").value(DEFAULT_LNG_MIN))
            .andExpect(jsonPath("$.lngSec").value(DEFAULT_LNG_SEC.doubleValue()))
            .andExpect(jsonPath("$.lngDirection").value(DEFAULT_LNG_DIRECTION.toString()))
            .andExpect(jsonPath("$.latDisplayedValue").value(DEFAULT_LAT_DISPLAYED_VALUE))
            .andExpect(jsonPath("$.lngDisplayedValue").value(DEFAULT_LNG_DISPLAYED_VALUE))
            .andExpect(jsonPath("$.latDecimal").value(DEFAULT_LAT_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.lngDecimal").value(DEFAULT_LNG_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK));
    }

    @Test
    @Transactional
    void getNonExistingGpsCoordinateCheck() throws Exception {
        // Get the gpsCoordinateCheck
        restGpsCoordinateCheckMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGpsCoordinateCheck() throws Exception {
        // Initialize the database
        gpsCoordinateCheckRepository.saveAndFlush(gpsCoordinateCheck);

        int databaseSizeBeforeUpdate = gpsCoordinateCheckRepository.findAll().size();

        // Update the gpsCoordinateCheck
        GpsCoordinateCheck updatedGpsCoordinateCheck = gpsCoordinateCheckRepository.findById(gpsCoordinateCheck.getId()).get();
        // Disconnect from session so that the updates on updatedGpsCoordinateCheck are not directly saved in db
        em.detach(updatedGpsCoordinateCheck);
        updatedGpsCoordinateCheck
            .latDeg(UPDATED_LAT_DEG)
            .latMin(UPDATED_LAT_MIN)
            .latSec(UPDATED_LAT_SEC)
            .latDirection(UPDATED_LAT_DIRECTION)
            .lngDeg(UPDATED_LNG_DEG)
            .lngMin(UPDATED_LNG_MIN)
            .lngSec(UPDATED_LNG_SEC)
            .lngDirection(UPDATED_LNG_DIRECTION)
            .latDisplayedValue(UPDATED_LAT_DISPLAYED_VALUE)
            .lngDisplayedValue(UPDATED_LNG_DISPLAYED_VALUE)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .link(UPDATED_LINK);

        restGpsCoordinateCheckMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedGpsCoordinateCheck.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedGpsCoordinateCheck))
            )
            .andExpect(status().isOk());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeUpdate);
        GpsCoordinateCheck testGpsCoordinateCheck = gpsCoordinateCheckList.get(gpsCoordinateCheckList.size() - 1);
        assertThat(testGpsCoordinateCheck.getLatDeg()).isEqualTo(UPDATED_LAT_DEG);
        assertThat(testGpsCoordinateCheck.getLatMin()).isEqualTo(UPDATED_LAT_MIN);
        assertThat(testGpsCoordinateCheck.getLatSec()).isEqualTo(UPDATED_LAT_SEC);
        assertThat(testGpsCoordinateCheck.getLatDirection()).isEqualTo(UPDATED_LAT_DIRECTION);
        assertThat(testGpsCoordinateCheck.getLngDeg()).isEqualTo(UPDATED_LNG_DEG);
        assertThat(testGpsCoordinateCheck.getLngMin()).isEqualTo(UPDATED_LNG_MIN);
        assertThat(testGpsCoordinateCheck.getLngSec()).isEqualTo(UPDATED_LNG_SEC);
        assertThat(testGpsCoordinateCheck.getLngDirection()).isEqualTo(UPDATED_LNG_DIRECTION);
        assertThat(testGpsCoordinateCheck.getLatDisplayedValue()).isEqualTo(UPDATED_LAT_DISPLAYED_VALUE);
        assertThat(testGpsCoordinateCheck.getLngDisplayedValue()).isEqualTo(UPDATED_LNG_DISPLAYED_VALUE);
        assertThat(testGpsCoordinateCheck.getLatDecimal()).isEqualTo(UPDATED_LAT_DECIMAL);
        assertThat(testGpsCoordinateCheck.getLngDecimal()).isEqualTo(UPDATED_LNG_DECIMAL);
        assertThat(testGpsCoordinateCheck.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    @Transactional
    void putNonExistingGpsCoordinateCheck() throws Exception {
        int databaseSizeBeforeUpdate = gpsCoordinateCheckRepository.findAll().size();
        gpsCoordinateCheck.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGpsCoordinateCheckMockMvc
            .perform(
                put(ENTITY_API_URL_ID, gpsCoordinateCheck.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gpsCoordinateCheck))
            )
            .andExpect(status().isBadRequest());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGpsCoordinateCheck() throws Exception {
        int databaseSizeBeforeUpdate = gpsCoordinateCheckRepository.findAll().size();
        gpsCoordinateCheck.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGpsCoordinateCheckMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gpsCoordinateCheck))
            )
            .andExpect(status().isBadRequest());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGpsCoordinateCheck() throws Exception {
        int databaseSizeBeforeUpdate = gpsCoordinateCheckRepository.findAll().size();
        gpsCoordinateCheck.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGpsCoordinateCheckMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gpsCoordinateCheck))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGpsCoordinateCheckWithPatch() throws Exception {
        // Initialize the database
        gpsCoordinateCheckRepository.saveAndFlush(gpsCoordinateCheck);

        int databaseSizeBeforeUpdate = gpsCoordinateCheckRepository.findAll().size();

        // Update the gpsCoordinateCheck using partial update
        GpsCoordinateCheck partialUpdatedGpsCoordinateCheck = new GpsCoordinateCheck();
        partialUpdatedGpsCoordinateCheck.setId(gpsCoordinateCheck.getId());

        partialUpdatedGpsCoordinateCheck
            .latDeg(UPDATED_LAT_DEG)
            .latSec(UPDATED_LAT_SEC)
            .latDirection(UPDATED_LAT_DIRECTION)
            .lngMin(UPDATED_LNG_MIN)
            .lngDirection(UPDATED_LNG_DIRECTION)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL);

        restGpsCoordinateCheckMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGpsCoordinateCheck.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGpsCoordinateCheck))
            )
            .andExpect(status().isOk());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeUpdate);
        GpsCoordinateCheck testGpsCoordinateCheck = gpsCoordinateCheckList.get(gpsCoordinateCheckList.size() - 1);
        assertThat(testGpsCoordinateCheck.getLatDeg()).isEqualTo(UPDATED_LAT_DEG);
        assertThat(testGpsCoordinateCheck.getLatMin()).isEqualTo(DEFAULT_LAT_MIN);
        assertThat(testGpsCoordinateCheck.getLatSec()).isEqualTo(UPDATED_LAT_SEC);
        assertThat(testGpsCoordinateCheck.getLatDirection()).isEqualTo(UPDATED_LAT_DIRECTION);
        assertThat(testGpsCoordinateCheck.getLngDeg()).isEqualTo(DEFAULT_LNG_DEG);
        assertThat(testGpsCoordinateCheck.getLngMin()).isEqualTo(UPDATED_LNG_MIN);
        assertThat(testGpsCoordinateCheck.getLngSec()).isEqualTo(DEFAULT_LNG_SEC);
        assertThat(testGpsCoordinateCheck.getLngDirection()).isEqualTo(UPDATED_LNG_DIRECTION);
        assertThat(testGpsCoordinateCheck.getLatDisplayedValue()).isEqualTo(DEFAULT_LAT_DISPLAYED_VALUE);
        assertThat(testGpsCoordinateCheck.getLngDisplayedValue()).isEqualTo(DEFAULT_LNG_DISPLAYED_VALUE);
        assertThat(testGpsCoordinateCheck.getLatDecimal()).isEqualTo(UPDATED_LAT_DECIMAL);
        assertThat(testGpsCoordinateCheck.getLngDecimal()).isEqualTo(UPDATED_LNG_DECIMAL);
        assertThat(testGpsCoordinateCheck.getLink()).isEqualTo(DEFAULT_LINK);
    }

    @Test
    @Transactional
    void fullUpdateGpsCoordinateCheckWithPatch() throws Exception {
        // Initialize the database
        gpsCoordinateCheckRepository.saveAndFlush(gpsCoordinateCheck);

        int databaseSizeBeforeUpdate = gpsCoordinateCheckRepository.findAll().size();

        // Update the gpsCoordinateCheck using partial update
        GpsCoordinateCheck partialUpdatedGpsCoordinateCheck = new GpsCoordinateCheck();
        partialUpdatedGpsCoordinateCheck.setId(gpsCoordinateCheck.getId());

        partialUpdatedGpsCoordinateCheck
            .latDeg(UPDATED_LAT_DEG)
            .latMin(UPDATED_LAT_MIN)
            .latSec(UPDATED_LAT_SEC)
            .latDirection(UPDATED_LAT_DIRECTION)
            .lngDeg(UPDATED_LNG_DEG)
            .lngMin(UPDATED_LNG_MIN)
            .lngSec(UPDATED_LNG_SEC)
            .lngDirection(UPDATED_LNG_DIRECTION)
            .latDisplayedValue(UPDATED_LAT_DISPLAYED_VALUE)
            .lngDisplayedValue(UPDATED_LNG_DISPLAYED_VALUE)
            .latDecimal(UPDATED_LAT_DECIMAL)
            .lngDecimal(UPDATED_LNG_DECIMAL)
            .link(UPDATED_LINK);

        restGpsCoordinateCheckMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGpsCoordinateCheck.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGpsCoordinateCheck))
            )
            .andExpect(status().isOk());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeUpdate);
        GpsCoordinateCheck testGpsCoordinateCheck = gpsCoordinateCheckList.get(gpsCoordinateCheckList.size() - 1);
        assertThat(testGpsCoordinateCheck.getLatDeg()).isEqualTo(UPDATED_LAT_DEG);
        assertThat(testGpsCoordinateCheck.getLatMin()).isEqualTo(UPDATED_LAT_MIN);
        assertThat(testGpsCoordinateCheck.getLatSec()).isEqualTo(UPDATED_LAT_SEC);
        assertThat(testGpsCoordinateCheck.getLatDirection()).isEqualTo(UPDATED_LAT_DIRECTION);
        assertThat(testGpsCoordinateCheck.getLngDeg()).isEqualTo(UPDATED_LNG_DEG);
        assertThat(testGpsCoordinateCheck.getLngMin()).isEqualTo(UPDATED_LNG_MIN);
        assertThat(testGpsCoordinateCheck.getLngSec()).isEqualTo(UPDATED_LNG_SEC);
        assertThat(testGpsCoordinateCheck.getLngDirection()).isEqualTo(UPDATED_LNG_DIRECTION);
        assertThat(testGpsCoordinateCheck.getLatDisplayedValue()).isEqualTo(UPDATED_LAT_DISPLAYED_VALUE);
        assertThat(testGpsCoordinateCheck.getLngDisplayedValue()).isEqualTo(UPDATED_LNG_DISPLAYED_VALUE);
        assertThat(testGpsCoordinateCheck.getLatDecimal()).isEqualTo(UPDATED_LAT_DECIMAL);
        assertThat(testGpsCoordinateCheck.getLngDecimal()).isEqualTo(UPDATED_LNG_DECIMAL);
        assertThat(testGpsCoordinateCheck.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    @Transactional
    void patchNonExistingGpsCoordinateCheck() throws Exception {
        int databaseSizeBeforeUpdate = gpsCoordinateCheckRepository.findAll().size();
        gpsCoordinateCheck.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGpsCoordinateCheckMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, gpsCoordinateCheck.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gpsCoordinateCheck))
            )
            .andExpect(status().isBadRequest());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGpsCoordinateCheck() throws Exception {
        int databaseSizeBeforeUpdate = gpsCoordinateCheckRepository.findAll().size();
        gpsCoordinateCheck.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGpsCoordinateCheckMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gpsCoordinateCheck))
            )
            .andExpect(status().isBadRequest());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGpsCoordinateCheck() throws Exception {
        int databaseSizeBeforeUpdate = gpsCoordinateCheckRepository.findAll().size();
        gpsCoordinateCheck.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGpsCoordinateCheckMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gpsCoordinateCheck))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GpsCoordinateCheck in the database
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGpsCoordinateCheck() throws Exception {
        // Initialize the database
        gpsCoordinateCheckRepository.saveAndFlush(gpsCoordinateCheck);

        int databaseSizeBeforeDelete = gpsCoordinateCheckRepository.findAll().size();

        // Delete the gpsCoordinateCheck
        restGpsCoordinateCheckMockMvc
            .perform(delete(ENTITY_API_URL_ID, gpsCoordinateCheck.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GpsCoordinateCheck> gpsCoordinateCheckList = gpsCoordinateCheckRepository.findAll();
        assertThat(gpsCoordinateCheckList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
