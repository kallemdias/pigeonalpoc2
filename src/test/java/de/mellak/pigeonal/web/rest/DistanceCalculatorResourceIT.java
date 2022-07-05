package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.DistanceCalculator;
import de.mellak.pigeonal.domain.enumeration.LatDirection;
import de.mellak.pigeonal.domain.enumeration.LatDirection;
import de.mellak.pigeonal.domain.enumeration.LngDirection;
import de.mellak.pigeonal.domain.enumeration.LngDirection;
import de.mellak.pigeonal.repository.DistanceCalculatorRepository;
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
 * Integration tests for the {@link DistanceCalculatorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DistanceCalculatorResourceIT {

    private static final Integer DEFAULT_DEP_LAT_DEG = 0;
    private static final Integer UPDATED_DEP_LAT_DEG = 1;

    private static final Integer DEFAULT_DEP_LAT_MIN = 0;
    private static final Integer UPDATED_DEP_LAT_MIN = 1;

    private static final Float DEFAULT_DEP_LAT_SEC = 1F;
    private static final Float UPDATED_DEP_LAT_SEC = 2F;

    private static final LatDirection DEFAULT_DEP_LAT_DIRECTION = LatDirection.NORTH;
    private static final LatDirection UPDATED_DEP_LAT_DIRECTION = LatDirection.SOUTH;

    private static final Integer DEFAULT_DEP_LNG_DEG = 0;
    private static final Integer UPDATED_DEP_LNG_DEG = 1;

    private static final Integer DEFAULT_DEP_LNG_MIN = 0;
    private static final Integer UPDATED_DEP_LNG_MIN = 1;

    private static final Float DEFAULT_DEP_LNG_SEC = 1F;
    private static final Float UPDATED_DEP_LNG_SEC = 2F;

    private static final LngDirection DEFAULT_DEP_LNG_DIRECTION = LngDirection.EAST;
    private static final LngDirection UPDATED_DEP_LNG_DIRECTION = LngDirection.WEST;

    private static final String DEFAULT_DEP_LAT_DISPLAYED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DEP_LAT_DISPLAYED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_LNG_DISPLAYED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DEP_LNG_DISPLAYED_VALUE = "BBBBBBBBBB";

    private static final Double DEFAULT_DEP_LAT_DECIMAL = 1D;
    private static final Double UPDATED_DEP_LAT_DECIMAL = 2D;

    private static final Double DEFAULT_DEP_LNG_DECIMAL = 1D;
    private static final Double UPDATED_DEP_LNG_DECIMAL = 2D;

    private static final Integer DEFAULT_ARR_LAT_DEG = 0;
    private static final Integer UPDATED_ARR_LAT_DEG = 1;

    private static final Integer DEFAULT_ARR_LAT_MIN = 0;
    private static final Integer UPDATED_ARR_LAT_MIN = 1;

    private static final Float DEFAULT_ARR_LAT_SEC = 1F;
    private static final Float UPDATED_ARR_LAT_SEC = 2F;

    private static final LatDirection DEFAULT_ARR_LAT_DIRECTION = LatDirection.NORTH;
    private static final LatDirection UPDATED_ARR_LAT_DIRECTION = LatDirection.SOUTH;

    private static final Integer DEFAULT_ARR_LNG_DEG = 0;
    private static final Integer UPDATED_ARR_LNG_DEG = 1;

    private static final Integer DEFAULT_ARR_LNG_MIN = 0;
    private static final Integer UPDATED_ARR_LNG_MIN = 1;

    private static final Float DEFAULT_ARR_LNG_SEC = 1F;
    private static final Float UPDATED_ARR_LNG_SEC = 2F;

    private static final LngDirection DEFAULT_ARR_LNG_DIRECTION = LngDirection.EAST;
    private static final LngDirection UPDATED_ARR_LNG_DIRECTION = LngDirection.WEST;

    private static final String DEFAULT_ARR_LAT_DISPLAYED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_ARR_LAT_DISPLAYED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_ARR_LNG_DISPLAYED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_ARR_LNG_DISPLAYED_VALUE = "BBBBBBBBBB";

    private static final Double DEFAULT_ARR_LAT_DECIMAL = 1D;
    private static final Double UPDATED_ARR_LAT_DECIMAL = 2D;

    private static final Double DEFAULT_ARR_LNG_DECIMAL = 1D;
    private static final Double UPDATED_ARR_LNG_DECIMAL = 2D;

    private static final Double DEFAULT_DISTANCE_IN_METERS = 0D;
    private static final Double UPDATED_DISTANCE_IN_METERS = 1D;

    private static final String DEFAULT_DISTANCE_IN_METERS_DISP_VAL = "AAAAAAAAAA";
    private static final String UPDATED_DISTANCE_IN_METERS_DISP_VAL = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_LINK = "AAAAAAAAAA";
    private static final String UPDATED_DEP_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_ARR_LINK = "AAAAAAAAAA";
    private static final String UPDATED_ARR_LINK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/distance-calculators";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DistanceCalculatorRepository distanceCalculatorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDistanceCalculatorMockMvc;

    private DistanceCalculator distanceCalculator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DistanceCalculator createEntity(EntityManager em) {
        DistanceCalculator distanceCalculator = new DistanceCalculator()
            .depLatDeg(DEFAULT_DEP_LAT_DEG)
            .depLatMin(DEFAULT_DEP_LAT_MIN)
            .depLatSec(DEFAULT_DEP_LAT_SEC)
            .depLatDirection(DEFAULT_DEP_LAT_DIRECTION)
            .depLngDeg(DEFAULT_DEP_LNG_DEG)
            .depLngMin(DEFAULT_DEP_LNG_MIN)
            .depLngSec(DEFAULT_DEP_LNG_SEC)
            .depLngDirection(DEFAULT_DEP_LNG_DIRECTION)
            .depLatDisplayedValue(DEFAULT_DEP_LAT_DISPLAYED_VALUE)
            .depLngDisplayedValue(DEFAULT_DEP_LNG_DISPLAYED_VALUE)
            .depLatDecimal(DEFAULT_DEP_LAT_DECIMAL)
            .depLngDecimal(DEFAULT_DEP_LNG_DECIMAL)
            .arrLatDeg(DEFAULT_ARR_LAT_DEG)
            .arrLatMin(DEFAULT_ARR_LAT_MIN)
            .arrLatSec(DEFAULT_ARR_LAT_SEC)
            .arrLatDirection(DEFAULT_ARR_LAT_DIRECTION)
            .arrLngDeg(DEFAULT_ARR_LNG_DEG)
            .arrLngMin(DEFAULT_ARR_LNG_MIN)
            .arrLngSec(DEFAULT_ARR_LNG_SEC)
            .arrLngDirection(DEFAULT_ARR_LNG_DIRECTION)
            .arrLatDisplayedValue(DEFAULT_ARR_LAT_DISPLAYED_VALUE)
            .arrLngDisplayedValue(DEFAULT_ARR_LNG_DISPLAYED_VALUE)
            .arrLatDecimal(DEFAULT_ARR_LAT_DECIMAL)
            .arrLngDecimal(DEFAULT_ARR_LNG_DECIMAL)
            .distanceInMeters(DEFAULT_DISTANCE_IN_METERS)
            .distanceInMetersDispVal(DEFAULT_DISTANCE_IN_METERS_DISP_VAL)
            .depLink(DEFAULT_DEP_LINK)
            .arrLink(DEFAULT_ARR_LINK);
        return distanceCalculator;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DistanceCalculator createUpdatedEntity(EntityManager em) {
        DistanceCalculator distanceCalculator = new DistanceCalculator()
            .depLatDeg(UPDATED_DEP_LAT_DEG)
            .depLatMin(UPDATED_DEP_LAT_MIN)
            .depLatSec(UPDATED_DEP_LAT_SEC)
            .depLatDirection(UPDATED_DEP_LAT_DIRECTION)
            .depLngDeg(UPDATED_DEP_LNG_DEG)
            .depLngMin(UPDATED_DEP_LNG_MIN)
            .depLngSec(UPDATED_DEP_LNG_SEC)
            .depLngDirection(UPDATED_DEP_LNG_DIRECTION)
            .depLatDisplayedValue(UPDATED_DEP_LAT_DISPLAYED_VALUE)
            .depLngDisplayedValue(UPDATED_DEP_LNG_DISPLAYED_VALUE)
            .depLatDecimal(UPDATED_DEP_LAT_DECIMAL)
            .depLngDecimal(UPDATED_DEP_LNG_DECIMAL)
            .arrLatDeg(UPDATED_ARR_LAT_DEG)
            .arrLatMin(UPDATED_ARR_LAT_MIN)
            .arrLatSec(UPDATED_ARR_LAT_SEC)
            .arrLatDirection(UPDATED_ARR_LAT_DIRECTION)
            .arrLngDeg(UPDATED_ARR_LNG_DEG)
            .arrLngMin(UPDATED_ARR_LNG_MIN)
            .arrLngSec(UPDATED_ARR_LNG_SEC)
            .arrLngDirection(UPDATED_ARR_LNG_DIRECTION)
            .arrLatDisplayedValue(UPDATED_ARR_LAT_DISPLAYED_VALUE)
            .arrLngDisplayedValue(UPDATED_ARR_LNG_DISPLAYED_VALUE)
            .arrLatDecimal(UPDATED_ARR_LAT_DECIMAL)
            .arrLngDecimal(UPDATED_ARR_LNG_DECIMAL)
            .distanceInMeters(UPDATED_DISTANCE_IN_METERS)
            .distanceInMetersDispVal(UPDATED_DISTANCE_IN_METERS_DISP_VAL)
            .depLink(UPDATED_DEP_LINK)
            .arrLink(UPDATED_ARR_LINK);
        return distanceCalculator;
    }

    @BeforeEach
    public void initTest() {
        distanceCalculator = createEntity(em);
    }

    @Test
    @Transactional
    void createDistanceCalculator() throws Exception {
        int databaseSizeBeforeCreate = distanceCalculatorRepository.findAll().size();
        // Create the DistanceCalculator
        restDistanceCalculatorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(distanceCalculator))
            )
            .andExpect(status().isCreated());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeCreate + 1);
        DistanceCalculator testDistanceCalculator = distanceCalculatorList.get(distanceCalculatorList.size() - 1);
        assertThat(testDistanceCalculator.getDepLatDeg()).isEqualTo(DEFAULT_DEP_LAT_DEG);
        assertThat(testDistanceCalculator.getDepLatMin()).isEqualTo(DEFAULT_DEP_LAT_MIN);
        assertThat(testDistanceCalculator.getDepLatSec()).isEqualTo(DEFAULT_DEP_LAT_SEC);
        assertThat(testDistanceCalculator.getDepLatDirection()).isEqualTo(DEFAULT_DEP_LAT_DIRECTION);
        assertThat(testDistanceCalculator.getDepLngDeg()).isEqualTo(DEFAULT_DEP_LNG_DEG);
        assertThat(testDistanceCalculator.getDepLngMin()).isEqualTo(DEFAULT_DEP_LNG_MIN);
        assertThat(testDistanceCalculator.getDepLngSec()).isEqualTo(DEFAULT_DEP_LNG_SEC);
        assertThat(testDistanceCalculator.getDepLngDirection()).isEqualTo(DEFAULT_DEP_LNG_DIRECTION);
        assertThat(testDistanceCalculator.getDepLatDisplayedValue()).isEqualTo(DEFAULT_DEP_LAT_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getDepLngDisplayedValue()).isEqualTo(DEFAULT_DEP_LNG_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getDepLatDecimal()).isEqualTo(DEFAULT_DEP_LAT_DECIMAL);
        assertThat(testDistanceCalculator.getDepLngDecimal()).isEqualTo(DEFAULT_DEP_LNG_DECIMAL);
        assertThat(testDistanceCalculator.getArrLatDeg()).isEqualTo(DEFAULT_ARR_LAT_DEG);
        assertThat(testDistanceCalculator.getArrLatMin()).isEqualTo(DEFAULT_ARR_LAT_MIN);
        assertThat(testDistanceCalculator.getArrLatSec()).isEqualTo(DEFAULT_ARR_LAT_SEC);
        assertThat(testDistanceCalculator.getArrLatDirection()).isEqualTo(DEFAULT_ARR_LAT_DIRECTION);
        assertThat(testDistanceCalculator.getArrLngDeg()).isEqualTo(DEFAULT_ARR_LNG_DEG);
        assertThat(testDistanceCalculator.getArrLngMin()).isEqualTo(DEFAULT_ARR_LNG_MIN);
        assertThat(testDistanceCalculator.getArrLngSec()).isEqualTo(DEFAULT_ARR_LNG_SEC);
        assertThat(testDistanceCalculator.getArrLngDirection()).isEqualTo(DEFAULT_ARR_LNG_DIRECTION);
        assertThat(testDistanceCalculator.getArrLatDisplayedValue()).isEqualTo(DEFAULT_ARR_LAT_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getArrLngDisplayedValue()).isEqualTo(DEFAULT_ARR_LNG_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getArrLatDecimal()).isEqualTo(DEFAULT_ARR_LAT_DECIMAL);
        assertThat(testDistanceCalculator.getArrLngDecimal()).isEqualTo(DEFAULT_ARR_LNG_DECIMAL);
        assertThat(testDistanceCalculator.getDistanceInMeters()).isEqualTo(DEFAULT_DISTANCE_IN_METERS);
        assertThat(testDistanceCalculator.getDistanceInMetersDispVal()).isEqualTo(DEFAULT_DISTANCE_IN_METERS_DISP_VAL);
        assertThat(testDistanceCalculator.getDepLink()).isEqualTo(DEFAULT_DEP_LINK);
        assertThat(testDistanceCalculator.getArrLink()).isEqualTo(DEFAULT_ARR_LINK);
    }

    @Test
    @Transactional
    void createDistanceCalculatorWithExistingId() throws Exception {
        // Create the DistanceCalculator with an existing ID
        distanceCalculator.setId(1L);

        int databaseSizeBeforeCreate = distanceCalculatorRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDistanceCalculatorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(distanceCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDistanceCalculators() throws Exception {
        // Initialize the database
        distanceCalculatorRepository.saveAndFlush(distanceCalculator);

        // Get all the distanceCalculatorList
        restDistanceCalculatorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(distanceCalculator.getId().intValue())))
            .andExpect(jsonPath("$.[*].depLatDeg").value(hasItem(DEFAULT_DEP_LAT_DEG)))
            .andExpect(jsonPath("$.[*].depLatMin").value(hasItem(DEFAULT_DEP_LAT_MIN)))
            .andExpect(jsonPath("$.[*].depLatSec").value(hasItem(DEFAULT_DEP_LAT_SEC.doubleValue())))
            .andExpect(jsonPath("$.[*].depLatDirection").value(hasItem(DEFAULT_DEP_LAT_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].depLngDeg").value(hasItem(DEFAULT_DEP_LNG_DEG)))
            .andExpect(jsonPath("$.[*].depLngMin").value(hasItem(DEFAULT_DEP_LNG_MIN)))
            .andExpect(jsonPath("$.[*].depLngSec").value(hasItem(DEFAULT_DEP_LNG_SEC.doubleValue())))
            .andExpect(jsonPath("$.[*].depLngDirection").value(hasItem(DEFAULT_DEP_LNG_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].depLatDisplayedValue").value(hasItem(DEFAULT_DEP_LAT_DISPLAYED_VALUE)))
            .andExpect(jsonPath("$.[*].depLngDisplayedValue").value(hasItem(DEFAULT_DEP_LNG_DISPLAYED_VALUE)))
            .andExpect(jsonPath("$.[*].depLatDecimal").value(hasItem(DEFAULT_DEP_LAT_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].depLngDecimal").value(hasItem(DEFAULT_DEP_LNG_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].arrLatDeg").value(hasItem(DEFAULT_ARR_LAT_DEG)))
            .andExpect(jsonPath("$.[*].arrLatMin").value(hasItem(DEFAULT_ARR_LAT_MIN)))
            .andExpect(jsonPath("$.[*].arrLatSec").value(hasItem(DEFAULT_ARR_LAT_SEC.doubleValue())))
            .andExpect(jsonPath("$.[*].arrLatDirection").value(hasItem(DEFAULT_ARR_LAT_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].arrLngDeg").value(hasItem(DEFAULT_ARR_LNG_DEG)))
            .andExpect(jsonPath("$.[*].arrLngMin").value(hasItem(DEFAULT_ARR_LNG_MIN)))
            .andExpect(jsonPath("$.[*].arrLngSec").value(hasItem(DEFAULT_ARR_LNG_SEC.doubleValue())))
            .andExpect(jsonPath("$.[*].arrLngDirection").value(hasItem(DEFAULT_ARR_LNG_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].arrLatDisplayedValue").value(hasItem(DEFAULT_ARR_LAT_DISPLAYED_VALUE)))
            .andExpect(jsonPath("$.[*].arrLngDisplayedValue").value(hasItem(DEFAULT_ARR_LNG_DISPLAYED_VALUE)))
            .andExpect(jsonPath("$.[*].arrLatDecimal").value(hasItem(DEFAULT_ARR_LAT_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].arrLngDecimal").value(hasItem(DEFAULT_ARR_LNG_DECIMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].distanceInMeters").value(hasItem(DEFAULT_DISTANCE_IN_METERS.doubleValue())))
            .andExpect(jsonPath("$.[*].distanceInMetersDispVal").value(hasItem(DEFAULT_DISTANCE_IN_METERS_DISP_VAL)))
            .andExpect(jsonPath("$.[*].depLink").value(hasItem(DEFAULT_DEP_LINK)))
            .andExpect(jsonPath("$.[*].arrLink").value(hasItem(DEFAULT_ARR_LINK)));
    }

    @Test
    @Transactional
    void getDistanceCalculator() throws Exception {
        // Initialize the database
        distanceCalculatorRepository.saveAndFlush(distanceCalculator);

        // Get the distanceCalculator
        restDistanceCalculatorMockMvc
            .perform(get(ENTITY_API_URL_ID, distanceCalculator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(distanceCalculator.getId().intValue()))
            .andExpect(jsonPath("$.depLatDeg").value(DEFAULT_DEP_LAT_DEG))
            .andExpect(jsonPath("$.depLatMin").value(DEFAULT_DEP_LAT_MIN))
            .andExpect(jsonPath("$.depLatSec").value(DEFAULT_DEP_LAT_SEC.doubleValue()))
            .andExpect(jsonPath("$.depLatDirection").value(DEFAULT_DEP_LAT_DIRECTION.toString()))
            .andExpect(jsonPath("$.depLngDeg").value(DEFAULT_DEP_LNG_DEG))
            .andExpect(jsonPath("$.depLngMin").value(DEFAULT_DEP_LNG_MIN))
            .andExpect(jsonPath("$.depLngSec").value(DEFAULT_DEP_LNG_SEC.doubleValue()))
            .andExpect(jsonPath("$.depLngDirection").value(DEFAULT_DEP_LNG_DIRECTION.toString()))
            .andExpect(jsonPath("$.depLatDisplayedValue").value(DEFAULT_DEP_LAT_DISPLAYED_VALUE))
            .andExpect(jsonPath("$.depLngDisplayedValue").value(DEFAULT_DEP_LNG_DISPLAYED_VALUE))
            .andExpect(jsonPath("$.depLatDecimal").value(DEFAULT_DEP_LAT_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.depLngDecimal").value(DEFAULT_DEP_LNG_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.arrLatDeg").value(DEFAULT_ARR_LAT_DEG))
            .andExpect(jsonPath("$.arrLatMin").value(DEFAULT_ARR_LAT_MIN))
            .andExpect(jsonPath("$.arrLatSec").value(DEFAULT_ARR_LAT_SEC.doubleValue()))
            .andExpect(jsonPath("$.arrLatDirection").value(DEFAULT_ARR_LAT_DIRECTION.toString()))
            .andExpect(jsonPath("$.arrLngDeg").value(DEFAULT_ARR_LNG_DEG))
            .andExpect(jsonPath("$.arrLngMin").value(DEFAULT_ARR_LNG_MIN))
            .andExpect(jsonPath("$.arrLngSec").value(DEFAULT_ARR_LNG_SEC.doubleValue()))
            .andExpect(jsonPath("$.arrLngDirection").value(DEFAULT_ARR_LNG_DIRECTION.toString()))
            .andExpect(jsonPath("$.arrLatDisplayedValue").value(DEFAULT_ARR_LAT_DISPLAYED_VALUE))
            .andExpect(jsonPath("$.arrLngDisplayedValue").value(DEFAULT_ARR_LNG_DISPLAYED_VALUE))
            .andExpect(jsonPath("$.arrLatDecimal").value(DEFAULT_ARR_LAT_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.arrLngDecimal").value(DEFAULT_ARR_LNG_DECIMAL.doubleValue()))
            .andExpect(jsonPath("$.distanceInMeters").value(DEFAULT_DISTANCE_IN_METERS.doubleValue()))
            .andExpect(jsonPath("$.distanceInMetersDispVal").value(DEFAULT_DISTANCE_IN_METERS_DISP_VAL))
            .andExpect(jsonPath("$.depLink").value(DEFAULT_DEP_LINK))
            .andExpect(jsonPath("$.arrLink").value(DEFAULT_ARR_LINK));
    }

    @Test
    @Transactional
    void getNonExistingDistanceCalculator() throws Exception {
        // Get the distanceCalculator
        restDistanceCalculatorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDistanceCalculator() throws Exception {
        // Initialize the database
        distanceCalculatorRepository.saveAndFlush(distanceCalculator);

        int databaseSizeBeforeUpdate = distanceCalculatorRepository.findAll().size();

        // Update the distanceCalculator
        DistanceCalculator updatedDistanceCalculator = distanceCalculatorRepository.findById(distanceCalculator.getId()).get();
        // Disconnect from session so that the updates on updatedDistanceCalculator are not directly saved in db
        em.detach(updatedDistanceCalculator);
        updatedDistanceCalculator
            .depLatDeg(UPDATED_DEP_LAT_DEG)
            .depLatMin(UPDATED_DEP_LAT_MIN)
            .depLatSec(UPDATED_DEP_LAT_SEC)
            .depLatDirection(UPDATED_DEP_LAT_DIRECTION)
            .depLngDeg(UPDATED_DEP_LNG_DEG)
            .depLngMin(UPDATED_DEP_LNG_MIN)
            .depLngSec(UPDATED_DEP_LNG_SEC)
            .depLngDirection(UPDATED_DEP_LNG_DIRECTION)
            .depLatDisplayedValue(UPDATED_DEP_LAT_DISPLAYED_VALUE)
            .depLngDisplayedValue(UPDATED_DEP_LNG_DISPLAYED_VALUE)
            .depLatDecimal(UPDATED_DEP_LAT_DECIMAL)
            .depLngDecimal(UPDATED_DEP_LNG_DECIMAL)
            .arrLatDeg(UPDATED_ARR_LAT_DEG)
            .arrLatMin(UPDATED_ARR_LAT_MIN)
            .arrLatSec(UPDATED_ARR_LAT_SEC)
            .arrLatDirection(UPDATED_ARR_LAT_DIRECTION)
            .arrLngDeg(UPDATED_ARR_LNG_DEG)
            .arrLngMin(UPDATED_ARR_LNG_MIN)
            .arrLngSec(UPDATED_ARR_LNG_SEC)
            .arrLngDirection(UPDATED_ARR_LNG_DIRECTION)
            .arrLatDisplayedValue(UPDATED_ARR_LAT_DISPLAYED_VALUE)
            .arrLngDisplayedValue(UPDATED_ARR_LNG_DISPLAYED_VALUE)
            .arrLatDecimal(UPDATED_ARR_LAT_DECIMAL)
            .arrLngDecimal(UPDATED_ARR_LNG_DECIMAL)
            .distanceInMeters(UPDATED_DISTANCE_IN_METERS)
            .distanceInMetersDispVal(UPDATED_DISTANCE_IN_METERS_DISP_VAL)
            .depLink(UPDATED_DEP_LINK)
            .arrLink(UPDATED_ARR_LINK);

        restDistanceCalculatorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDistanceCalculator.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDistanceCalculator))
            )
            .andExpect(status().isOk());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeUpdate);
        DistanceCalculator testDistanceCalculator = distanceCalculatorList.get(distanceCalculatorList.size() - 1);
        assertThat(testDistanceCalculator.getDepLatDeg()).isEqualTo(UPDATED_DEP_LAT_DEG);
        assertThat(testDistanceCalculator.getDepLatMin()).isEqualTo(UPDATED_DEP_LAT_MIN);
        assertThat(testDistanceCalculator.getDepLatSec()).isEqualTo(UPDATED_DEP_LAT_SEC);
        assertThat(testDistanceCalculator.getDepLatDirection()).isEqualTo(UPDATED_DEP_LAT_DIRECTION);
        assertThat(testDistanceCalculator.getDepLngDeg()).isEqualTo(UPDATED_DEP_LNG_DEG);
        assertThat(testDistanceCalculator.getDepLngMin()).isEqualTo(UPDATED_DEP_LNG_MIN);
        assertThat(testDistanceCalculator.getDepLngSec()).isEqualTo(UPDATED_DEP_LNG_SEC);
        assertThat(testDistanceCalculator.getDepLngDirection()).isEqualTo(UPDATED_DEP_LNG_DIRECTION);
        assertThat(testDistanceCalculator.getDepLatDisplayedValue()).isEqualTo(UPDATED_DEP_LAT_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getDepLngDisplayedValue()).isEqualTo(UPDATED_DEP_LNG_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getDepLatDecimal()).isEqualTo(UPDATED_DEP_LAT_DECIMAL);
        assertThat(testDistanceCalculator.getDepLngDecimal()).isEqualTo(UPDATED_DEP_LNG_DECIMAL);
        assertThat(testDistanceCalculator.getArrLatDeg()).isEqualTo(UPDATED_ARR_LAT_DEG);
        assertThat(testDistanceCalculator.getArrLatMin()).isEqualTo(UPDATED_ARR_LAT_MIN);
        assertThat(testDistanceCalculator.getArrLatSec()).isEqualTo(UPDATED_ARR_LAT_SEC);
        assertThat(testDistanceCalculator.getArrLatDirection()).isEqualTo(UPDATED_ARR_LAT_DIRECTION);
        assertThat(testDistanceCalculator.getArrLngDeg()).isEqualTo(UPDATED_ARR_LNG_DEG);
        assertThat(testDistanceCalculator.getArrLngMin()).isEqualTo(UPDATED_ARR_LNG_MIN);
        assertThat(testDistanceCalculator.getArrLngSec()).isEqualTo(UPDATED_ARR_LNG_SEC);
        assertThat(testDistanceCalculator.getArrLngDirection()).isEqualTo(UPDATED_ARR_LNG_DIRECTION);
        assertThat(testDistanceCalculator.getArrLatDisplayedValue()).isEqualTo(UPDATED_ARR_LAT_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getArrLngDisplayedValue()).isEqualTo(UPDATED_ARR_LNG_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getArrLatDecimal()).isEqualTo(UPDATED_ARR_LAT_DECIMAL);
        assertThat(testDistanceCalculator.getArrLngDecimal()).isEqualTo(UPDATED_ARR_LNG_DECIMAL);
        assertThat(testDistanceCalculator.getDistanceInMeters()).isEqualTo(UPDATED_DISTANCE_IN_METERS);
        assertThat(testDistanceCalculator.getDistanceInMetersDispVal()).isEqualTo(UPDATED_DISTANCE_IN_METERS_DISP_VAL);
        assertThat(testDistanceCalculator.getDepLink()).isEqualTo(UPDATED_DEP_LINK);
        assertThat(testDistanceCalculator.getArrLink()).isEqualTo(UPDATED_ARR_LINK);
    }

    @Test
    @Transactional
    void putNonExistingDistanceCalculator() throws Exception {
        int databaseSizeBeforeUpdate = distanceCalculatorRepository.findAll().size();
        distanceCalculator.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDistanceCalculatorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, distanceCalculator.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDistanceCalculator() throws Exception {
        int databaseSizeBeforeUpdate = distanceCalculatorRepository.findAll().size();
        distanceCalculator.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDistanceCalculatorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDistanceCalculator() throws Exception {
        int databaseSizeBeforeUpdate = distanceCalculatorRepository.findAll().size();
        distanceCalculator.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDistanceCalculatorMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(distanceCalculator))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDistanceCalculatorWithPatch() throws Exception {
        // Initialize the database
        distanceCalculatorRepository.saveAndFlush(distanceCalculator);

        int databaseSizeBeforeUpdate = distanceCalculatorRepository.findAll().size();

        // Update the distanceCalculator using partial update
        DistanceCalculator partialUpdatedDistanceCalculator = new DistanceCalculator();
        partialUpdatedDistanceCalculator.setId(distanceCalculator.getId());

        partialUpdatedDistanceCalculator
            .depLatSec(UPDATED_DEP_LAT_SEC)
            .depLngMin(UPDATED_DEP_LNG_MIN)
            .depLngDirection(UPDATED_DEP_LNG_DIRECTION)
            .depLngDecimal(UPDATED_DEP_LNG_DECIMAL)
            .arrLatDirection(UPDATED_ARR_LAT_DIRECTION)
            .arrLngSec(UPDATED_ARR_LNG_SEC)
            .arrLngDirection(UPDATED_ARR_LNG_DIRECTION)
            .arrLngDisplayedValue(UPDATED_ARR_LNG_DISPLAYED_VALUE)
            .arrLatDecimal(UPDATED_ARR_LAT_DECIMAL)
            .arrLngDecimal(UPDATED_ARR_LNG_DECIMAL)
            .distanceInMeters(UPDATED_DISTANCE_IN_METERS)
            .distanceInMetersDispVal(UPDATED_DISTANCE_IN_METERS_DISP_VAL)
            .depLink(UPDATED_DEP_LINK);

        restDistanceCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDistanceCalculator.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDistanceCalculator))
            )
            .andExpect(status().isOk());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeUpdate);
        DistanceCalculator testDistanceCalculator = distanceCalculatorList.get(distanceCalculatorList.size() - 1);
        assertThat(testDistanceCalculator.getDepLatDeg()).isEqualTo(DEFAULT_DEP_LAT_DEG);
        assertThat(testDistanceCalculator.getDepLatMin()).isEqualTo(DEFAULT_DEP_LAT_MIN);
        assertThat(testDistanceCalculator.getDepLatSec()).isEqualTo(UPDATED_DEP_LAT_SEC);
        assertThat(testDistanceCalculator.getDepLatDirection()).isEqualTo(DEFAULT_DEP_LAT_DIRECTION);
        assertThat(testDistanceCalculator.getDepLngDeg()).isEqualTo(DEFAULT_DEP_LNG_DEG);
        assertThat(testDistanceCalculator.getDepLngMin()).isEqualTo(UPDATED_DEP_LNG_MIN);
        assertThat(testDistanceCalculator.getDepLngSec()).isEqualTo(DEFAULT_DEP_LNG_SEC);
        assertThat(testDistanceCalculator.getDepLngDirection()).isEqualTo(UPDATED_DEP_LNG_DIRECTION);
        assertThat(testDistanceCalculator.getDepLatDisplayedValue()).isEqualTo(DEFAULT_DEP_LAT_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getDepLngDisplayedValue()).isEqualTo(DEFAULT_DEP_LNG_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getDepLatDecimal()).isEqualTo(DEFAULT_DEP_LAT_DECIMAL);
        assertThat(testDistanceCalculator.getDepLngDecimal()).isEqualTo(UPDATED_DEP_LNG_DECIMAL);
        assertThat(testDistanceCalculator.getArrLatDeg()).isEqualTo(DEFAULT_ARR_LAT_DEG);
        assertThat(testDistanceCalculator.getArrLatMin()).isEqualTo(DEFAULT_ARR_LAT_MIN);
        assertThat(testDistanceCalculator.getArrLatSec()).isEqualTo(DEFAULT_ARR_LAT_SEC);
        assertThat(testDistanceCalculator.getArrLatDirection()).isEqualTo(UPDATED_ARR_LAT_DIRECTION);
        assertThat(testDistanceCalculator.getArrLngDeg()).isEqualTo(DEFAULT_ARR_LNG_DEG);
        assertThat(testDistanceCalculator.getArrLngMin()).isEqualTo(DEFAULT_ARR_LNG_MIN);
        assertThat(testDistanceCalculator.getArrLngSec()).isEqualTo(UPDATED_ARR_LNG_SEC);
        assertThat(testDistanceCalculator.getArrLngDirection()).isEqualTo(UPDATED_ARR_LNG_DIRECTION);
        assertThat(testDistanceCalculator.getArrLatDisplayedValue()).isEqualTo(DEFAULT_ARR_LAT_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getArrLngDisplayedValue()).isEqualTo(UPDATED_ARR_LNG_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getArrLatDecimal()).isEqualTo(UPDATED_ARR_LAT_DECIMAL);
        assertThat(testDistanceCalculator.getArrLngDecimal()).isEqualTo(UPDATED_ARR_LNG_DECIMAL);
        assertThat(testDistanceCalculator.getDistanceInMeters()).isEqualTo(UPDATED_DISTANCE_IN_METERS);
        assertThat(testDistanceCalculator.getDistanceInMetersDispVal()).isEqualTo(UPDATED_DISTANCE_IN_METERS_DISP_VAL);
        assertThat(testDistanceCalculator.getDepLink()).isEqualTo(UPDATED_DEP_LINK);
        assertThat(testDistanceCalculator.getArrLink()).isEqualTo(DEFAULT_ARR_LINK);
    }

    @Test
    @Transactional
    void fullUpdateDistanceCalculatorWithPatch() throws Exception {
        // Initialize the database
        distanceCalculatorRepository.saveAndFlush(distanceCalculator);

        int databaseSizeBeforeUpdate = distanceCalculatorRepository.findAll().size();

        // Update the distanceCalculator using partial update
        DistanceCalculator partialUpdatedDistanceCalculator = new DistanceCalculator();
        partialUpdatedDistanceCalculator.setId(distanceCalculator.getId());

        partialUpdatedDistanceCalculator
            .depLatDeg(UPDATED_DEP_LAT_DEG)
            .depLatMin(UPDATED_DEP_LAT_MIN)
            .depLatSec(UPDATED_DEP_LAT_SEC)
            .depLatDirection(UPDATED_DEP_LAT_DIRECTION)
            .depLngDeg(UPDATED_DEP_LNG_DEG)
            .depLngMin(UPDATED_DEP_LNG_MIN)
            .depLngSec(UPDATED_DEP_LNG_SEC)
            .depLngDirection(UPDATED_DEP_LNG_DIRECTION)
            .depLatDisplayedValue(UPDATED_DEP_LAT_DISPLAYED_VALUE)
            .depLngDisplayedValue(UPDATED_DEP_LNG_DISPLAYED_VALUE)
            .depLatDecimal(UPDATED_DEP_LAT_DECIMAL)
            .depLngDecimal(UPDATED_DEP_LNG_DECIMAL)
            .arrLatDeg(UPDATED_ARR_LAT_DEG)
            .arrLatMin(UPDATED_ARR_LAT_MIN)
            .arrLatSec(UPDATED_ARR_LAT_SEC)
            .arrLatDirection(UPDATED_ARR_LAT_DIRECTION)
            .arrLngDeg(UPDATED_ARR_LNG_DEG)
            .arrLngMin(UPDATED_ARR_LNG_MIN)
            .arrLngSec(UPDATED_ARR_LNG_SEC)
            .arrLngDirection(UPDATED_ARR_LNG_DIRECTION)
            .arrLatDisplayedValue(UPDATED_ARR_LAT_DISPLAYED_VALUE)
            .arrLngDisplayedValue(UPDATED_ARR_LNG_DISPLAYED_VALUE)
            .arrLatDecimal(UPDATED_ARR_LAT_DECIMAL)
            .arrLngDecimal(UPDATED_ARR_LNG_DECIMAL)
            .distanceInMeters(UPDATED_DISTANCE_IN_METERS)
            .distanceInMetersDispVal(UPDATED_DISTANCE_IN_METERS_DISP_VAL)
            .depLink(UPDATED_DEP_LINK)
            .arrLink(UPDATED_ARR_LINK);

        restDistanceCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDistanceCalculator.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDistanceCalculator))
            )
            .andExpect(status().isOk());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeUpdate);
        DistanceCalculator testDistanceCalculator = distanceCalculatorList.get(distanceCalculatorList.size() - 1);
        assertThat(testDistanceCalculator.getDepLatDeg()).isEqualTo(UPDATED_DEP_LAT_DEG);
        assertThat(testDistanceCalculator.getDepLatMin()).isEqualTo(UPDATED_DEP_LAT_MIN);
        assertThat(testDistanceCalculator.getDepLatSec()).isEqualTo(UPDATED_DEP_LAT_SEC);
        assertThat(testDistanceCalculator.getDepLatDirection()).isEqualTo(UPDATED_DEP_LAT_DIRECTION);
        assertThat(testDistanceCalculator.getDepLngDeg()).isEqualTo(UPDATED_DEP_LNG_DEG);
        assertThat(testDistanceCalculator.getDepLngMin()).isEqualTo(UPDATED_DEP_LNG_MIN);
        assertThat(testDistanceCalculator.getDepLngSec()).isEqualTo(UPDATED_DEP_LNG_SEC);
        assertThat(testDistanceCalculator.getDepLngDirection()).isEqualTo(UPDATED_DEP_LNG_DIRECTION);
        assertThat(testDistanceCalculator.getDepLatDisplayedValue()).isEqualTo(UPDATED_DEP_LAT_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getDepLngDisplayedValue()).isEqualTo(UPDATED_DEP_LNG_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getDepLatDecimal()).isEqualTo(UPDATED_DEP_LAT_DECIMAL);
        assertThat(testDistanceCalculator.getDepLngDecimal()).isEqualTo(UPDATED_DEP_LNG_DECIMAL);
        assertThat(testDistanceCalculator.getArrLatDeg()).isEqualTo(UPDATED_ARR_LAT_DEG);
        assertThat(testDistanceCalculator.getArrLatMin()).isEqualTo(UPDATED_ARR_LAT_MIN);
        assertThat(testDistanceCalculator.getArrLatSec()).isEqualTo(UPDATED_ARR_LAT_SEC);
        assertThat(testDistanceCalculator.getArrLatDirection()).isEqualTo(UPDATED_ARR_LAT_DIRECTION);
        assertThat(testDistanceCalculator.getArrLngDeg()).isEqualTo(UPDATED_ARR_LNG_DEG);
        assertThat(testDistanceCalculator.getArrLngMin()).isEqualTo(UPDATED_ARR_LNG_MIN);
        assertThat(testDistanceCalculator.getArrLngSec()).isEqualTo(UPDATED_ARR_LNG_SEC);
        assertThat(testDistanceCalculator.getArrLngDirection()).isEqualTo(UPDATED_ARR_LNG_DIRECTION);
        assertThat(testDistanceCalculator.getArrLatDisplayedValue()).isEqualTo(UPDATED_ARR_LAT_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getArrLngDisplayedValue()).isEqualTo(UPDATED_ARR_LNG_DISPLAYED_VALUE);
        assertThat(testDistanceCalculator.getArrLatDecimal()).isEqualTo(UPDATED_ARR_LAT_DECIMAL);
        assertThat(testDistanceCalculator.getArrLngDecimal()).isEqualTo(UPDATED_ARR_LNG_DECIMAL);
        assertThat(testDistanceCalculator.getDistanceInMeters()).isEqualTo(UPDATED_DISTANCE_IN_METERS);
        assertThat(testDistanceCalculator.getDistanceInMetersDispVal()).isEqualTo(UPDATED_DISTANCE_IN_METERS_DISP_VAL);
        assertThat(testDistanceCalculator.getDepLink()).isEqualTo(UPDATED_DEP_LINK);
        assertThat(testDistanceCalculator.getArrLink()).isEqualTo(UPDATED_ARR_LINK);
    }

    @Test
    @Transactional
    void patchNonExistingDistanceCalculator() throws Exception {
        int databaseSizeBeforeUpdate = distanceCalculatorRepository.findAll().size();
        distanceCalculator.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDistanceCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, distanceCalculator.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(distanceCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDistanceCalculator() throws Exception {
        int databaseSizeBeforeUpdate = distanceCalculatorRepository.findAll().size();
        distanceCalculator.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDistanceCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(distanceCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDistanceCalculator() throws Exception {
        int databaseSizeBeforeUpdate = distanceCalculatorRepository.findAll().size();
        distanceCalculator.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDistanceCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(distanceCalculator))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DistanceCalculator in the database
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDistanceCalculator() throws Exception {
        // Initialize the database
        distanceCalculatorRepository.saveAndFlush(distanceCalculator);

        int databaseSizeBeforeDelete = distanceCalculatorRepository.findAll().size();

        // Delete the distanceCalculator
        restDistanceCalculatorMockMvc
            .perform(delete(ENTITY_API_URL_ID, distanceCalculator.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DistanceCalculator> distanceCalculatorList = distanceCalculatorRepository.findAll();
        assertThat(distanceCalculatorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
