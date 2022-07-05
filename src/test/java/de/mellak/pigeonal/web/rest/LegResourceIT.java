package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.Leg;
import de.mellak.pigeonal.domain.enumeration.CheckLinePointDistance;
import de.mellak.pigeonal.domain.enumeration.CheckPointDistance;
import de.mellak.pigeonal.domain.enumeration.LatDirection;
import de.mellak.pigeonal.domain.enumeration.LatDirection;
import de.mellak.pigeonal.domain.enumeration.LngDirection;
import de.mellak.pigeonal.domain.enumeration.LngDirection;
import de.mellak.pigeonal.domain.enumeration.TimeWindow;
import de.mellak.pigeonal.repository.LegRepository;
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
 * Integration tests for the {@link LegResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LegResourceIT {

    private static final Integer DEFAULT_ORDER = 1;
    private static final Integer UPDATED_ORDER = 2;

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

    private static final CheckPointDistance DEFAULT_CHECK_POINT_DISTANCE = CheckPointDistance.ONEKM;
    private static final CheckPointDistance UPDATED_CHECK_POINT_DISTANCE = CheckPointDistance.TWOKM;

    private static final CheckLinePointDistance DEFAULT_CHECK_LINE_POINT_DISTANCE = CheckLinePointDistance.FIVEKM;
    private static final CheckLinePointDistance UPDATED_CHECK_LINE_POINT_DISTANCE = CheckLinePointDistance.TENKM;

    private static final TimeWindow DEFAULT_TIME_WINDOW = TimeWindow.THREEH;
    private static final TimeWindow UPDATED_TIME_WINDOW = TimeWindow.FOURH;

    private static final String ENTITY_API_URL = "/api/legs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LegRepository legRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLegMockMvc;

    private Leg leg;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Leg createEntity(EntityManager em) {
        Leg leg = new Leg()
            .order(DEFAULT_ORDER)
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
            .checkPointDistance(DEFAULT_CHECK_POINT_DISTANCE)
            .checkLinePointDistance(DEFAULT_CHECK_LINE_POINT_DISTANCE)
            .timeWindow(DEFAULT_TIME_WINDOW);
        return leg;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Leg createUpdatedEntity(EntityManager em) {
        Leg leg = new Leg()
            .order(UPDATED_ORDER)
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
            .checkPointDistance(UPDATED_CHECK_POINT_DISTANCE)
            .checkLinePointDistance(UPDATED_CHECK_LINE_POINT_DISTANCE)
            .timeWindow(UPDATED_TIME_WINDOW);
        return leg;
    }

    @BeforeEach
    public void initTest() {
        leg = createEntity(em);
    }

    @Test
    @Transactional
    void createLeg() throws Exception {
        int databaseSizeBeforeCreate = legRepository.findAll().size();
        // Create the Leg
        restLegMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leg)))
            .andExpect(status().isCreated());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeCreate + 1);
        Leg testLeg = legList.get(legList.size() - 1);
        assertThat(testLeg.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testLeg.getDepLatDeg()).isEqualTo(DEFAULT_DEP_LAT_DEG);
        assertThat(testLeg.getDepLatMin()).isEqualTo(DEFAULT_DEP_LAT_MIN);
        assertThat(testLeg.getDepLatSec()).isEqualTo(DEFAULT_DEP_LAT_SEC);
        assertThat(testLeg.getDepLatDirection()).isEqualTo(DEFAULT_DEP_LAT_DIRECTION);
        assertThat(testLeg.getDepLngDeg()).isEqualTo(DEFAULT_DEP_LNG_DEG);
        assertThat(testLeg.getDepLngMin()).isEqualTo(DEFAULT_DEP_LNG_MIN);
        assertThat(testLeg.getDepLngSec()).isEqualTo(DEFAULT_DEP_LNG_SEC);
        assertThat(testLeg.getDepLngDirection()).isEqualTo(DEFAULT_DEP_LNG_DIRECTION);
        assertThat(testLeg.getDepLatDisplayedValue()).isEqualTo(DEFAULT_DEP_LAT_DISPLAYED_VALUE);
        assertThat(testLeg.getDepLngDisplayedValue()).isEqualTo(DEFAULT_DEP_LNG_DISPLAYED_VALUE);
        assertThat(testLeg.getDepLatDecimal()).isEqualTo(DEFAULT_DEP_LAT_DECIMAL);
        assertThat(testLeg.getDepLngDecimal()).isEqualTo(DEFAULT_DEP_LNG_DECIMAL);
        assertThat(testLeg.getArrLatDeg()).isEqualTo(DEFAULT_ARR_LAT_DEG);
        assertThat(testLeg.getArrLatMin()).isEqualTo(DEFAULT_ARR_LAT_MIN);
        assertThat(testLeg.getArrLatSec()).isEqualTo(DEFAULT_ARR_LAT_SEC);
        assertThat(testLeg.getArrLatDirection()).isEqualTo(DEFAULT_ARR_LAT_DIRECTION);
        assertThat(testLeg.getArrLngDeg()).isEqualTo(DEFAULT_ARR_LNG_DEG);
        assertThat(testLeg.getArrLngMin()).isEqualTo(DEFAULT_ARR_LNG_MIN);
        assertThat(testLeg.getArrLngSec()).isEqualTo(DEFAULT_ARR_LNG_SEC);
        assertThat(testLeg.getArrLngDirection()).isEqualTo(DEFAULT_ARR_LNG_DIRECTION);
        assertThat(testLeg.getArrLatDisplayedValue()).isEqualTo(DEFAULT_ARR_LAT_DISPLAYED_VALUE);
        assertThat(testLeg.getArrLngDisplayedValue()).isEqualTo(DEFAULT_ARR_LNG_DISPLAYED_VALUE);
        assertThat(testLeg.getArrLatDecimal()).isEqualTo(DEFAULT_ARR_LAT_DECIMAL);
        assertThat(testLeg.getArrLngDecimal()).isEqualTo(DEFAULT_ARR_LNG_DECIMAL);
        assertThat(testLeg.getCheckPointDistance()).isEqualTo(DEFAULT_CHECK_POINT_DISTANCE);
        assertThat(testLeg.getCheckLinePointDistance()).isEqualTo(DEFAULT_CHECK_LINE_POINT_DISTANCE);
        assertThat(testLeg.getTimeWindow()).isEqualTo(DEFAULT_TIME_WINDOW);
    }

    @Test
    @Transactional
    void createLegWithExistingId() throws Exception {
        // Create the Leg with an existing ID
        leg.setId(1L);

        int databaseSizeBeforeCreate = legRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLegMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leg)))
            .andExpect(status().isBadRequest());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLegs() throws Exception {
        // Initialize the database
        legRepository.saveAndFlush(leg);

        // Get all the legList
        restLegMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leg.getId().intValue())))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER)))
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
            .andExpect(jsonPath("$.[*].checkPointDistance").value(hasItem(DEFAULT_CHECK_POINT_DISTANCE.toString())))
            .andExpect(jsonPath("$.[*].checkLinePointDistance").value(hasItem(DEFAULT_CHECK_LINE_POINT_DISTANCE.toString())))
            .andExpect(jsonPath("$.[*].timeWindow").value(hasItem(DEFAULT_TIME_WINDOW.toString())));
    }

    @Test
    @Transactional
    void getLeg() throws Exception {
        // Initialize the database
        legRepository.saveAndFlush(leg);

        // Get the leg
        restLegMockMvc
            .perform(get(ENTITY_API_URL_ID, leg.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(leg.getId().intValue()))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER))
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
            .andExpect(jsonPath("$.checkPointDistance").value(DEFAULT_CHECK_POINT_DISTANCE.toString()))
            .andExpect(jsonPath("$.checkLinePointDistance").value(DEFAULT_CHECK_LINE_POINT_DISTANCE.toString()))
            .andExpect(jsonPath("$.timeWindow").value(DEFAULT_TIME_WINDOW.toString()));
    }

    @Test
    @Transactional
    void getNonExistingLeg() throws Exception {
        // Get the leg
        restLegMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLeg() throws Exception {
        // Initialize the database
        legRepository.saveAndFlush(leg);

        int databaseSizeBeforeUpdate = legRepository.findAll().size();

        // Update the leg
        Leg updatedLeg = legRepository.findById(leg.getId()).get();
        // Disconnect from session so that the updates on updatedLeg are not directly saved in db
        em.detach(updatedLeg);
        updatedLeg
            .order(UPDATED_ORDER)
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
            .checkPointDistance(UPDATED_CHECK_POINT_DISTANCE)
            .checkLinePointDistance(UPDATED_CHECK_LINE_POINT_DISTANCE)
            .timeWindow(UPDATED_TIME_WINDOW);

        restLegMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLeg.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLeg))
            )
            .andExpect(status().isOk());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeUpdate);
        Leg testLeg = legList.get(legList.size() - 1);
        assertThat(testLeg.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testLeg.getDepLatDeg()).isEqualTo(UPDATED_DEP_LAT_DEG);
        assertThat(testLeg.getDepLatMin()).isEqualTo(UPDATED_DEP_LAT_MIN);
        assertThat(testLeg.getDepLatSec()).isEqualTo(UPDATED_DEP_LAT_SEC);
        assertThat(testLeg.getDepLatDirection()).isEqualTo(UPDATED_DEP_LAT_DIRECTION);
        assertThat(testLeg.getDepLngDeg()).isEqualTo(UPDATED_DEP_LNG_DEG);
        assertThat(testLeg.getDepLngMin()).isEqualTo(UPDATED_DEP_LNG_MIN);
        assertThat(testLeg.getDepLngSec()).isEqualTo(UPDATED_DEP_LNG_SEC);
        assertThat(testLeg.getDepLngDirection()).isEqualTo(UPDATED_DEP_LNG_DIRECTION);
        assertThat(testLeg.getDepLatDisplayedValue()).isEqualTo(UPDATED_DEP_LAT_DISPLAYED_VALUE);
        assertThat(testLeg.getDepLngDisplayedValue()).isEqualTo(UPDATED_DEP_LNG_DISPLAYED_VALUE);
        assertThat(testLeg.getDepLatDecimal()).isEqualTo(UPDATED_DEP_LAT_DECIMAL);
        assertThat(testLeg.getDepLngDecimal()).isEqualTo(UPDATED_DEP_LNG_DECIMAL);
        assertThat(testLeg.getArrLatDeg()).isEqualTo(UPDATED_ARR_LAT_DEG);
        assertThat(testLeg.getArrLatMin()).isEqualTo(UPDATED_ARR_LAT_MIN);
        assertThat(testLeg.getArrLatSec()).isEqualTo(UPDATED_ARR_LAT_SEC);
        assertThat(testLeg.getArrLatDirection()).isEqualTo(UPDATED_ARR_LAT_DIRECTION);
        assertThat(testLeg.getArrLngDeg()).isEqualTo(UPDATED_ARR_LNG_DEG);
        assertThat(testLeg.getArrLngMin()).isEqualTo(UPDATED_ARR_LNG_MIN);
        assertThat(testLeg.getArrLngSec()).isEqualTo(UPDATED_ARR_LNG_SEC);
        assertThat(testLeg.getArrLngDirection()).isEqualTo(UPDATED_ARR_LNG_DIRECTION);
        assertThat(testLeg.getArrLatDisplayedValue()).isEqualTo(UPDATED_ARR_LAT_DISPLAYED_VALUE);
        assertThat(testLeg.getArrLngDisplayedValue()).isEqualTo(UPDATED_ARR_LNG_DISPLAYED_VALUE);
        assertThat(testLeg.getArrLatDecimal()).isEqualTo(UPDATED_ARR_LAT_DECIMAL);
        assertThat(testLeg.getArrLngDecimal()).isEqualTo(UPDATED_ARR_LNG_DECIMAL);
        assertThat(testLeg.getCheckPointDistance()).isEqualTo(UPDATED_CHECK_POINT_DISTANCE);
        assertThat(testLeg.getCheckLinePointDistance()).isEqualTo(UPDATED_CHECK_LINE_POINT_DISTANCE);
        assertThat(testLeg.getTimeWindow()).isEqualTo(UPDATED_TIME_WINDOW);
    }

    @Test
    @Transactional
    void putNonExistingLeg() throws Exception {
        int databaseSizeBeforeUpdate = legRepository.findAll().size();
        leg.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLegMockMvc
            .perform(
                put(ENTITY_API_URL_ID, leg.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leg))
            )
            .andExpect(status().isBadRequest());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLeg() throws Exception {
        int databaseSizeBeforeUpdate = legRepository.findAll().size();
        leg.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLegMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(leg))
            )
            .andExpect(status().isBadRequest());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLeg() throws Exception {
        int databaseSizeBeforeUpdate = legRepository.findAll().size();
        leg.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLegMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leg)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLegWithPatch() throws Exception {
        // Initialize the database
        legRepository.saveAndFlush(leg);

        int databaseSizeBeforeUpdate = legRepository.findAll().size();

        // Update the leg using partial update
        Leg partialUpdatedLeg = new Leg();
        partialUpdatedLeg.setId(leg.getId());

        partialUpdatedLeg
            .order(UPDATED_ORDER)
            .depLatDeg(UPDATED_DEP_LAT_DEG)
            .depLatMin(UPDATED_DEP_LAT_MIN)
            .depLatSec(UPDATED_DEP_LAT_SEC)
            .depLatDisplayedValue(UPDATED_DEP_LAT_DISPLAYED_VALUE)
            .depLngDisplayedValue(UPDATED_DEP_LNG_DISPLAYED_VALUE)
            .arrLatDeg(UPDATED_ARR_LAT_DEG)
            .arrLatMin(UPDATED_ARR_LAT_MIN)
            .arrLatSec(UPDATED_ARR_LAT_SEC)
            .arrLngMin(UPDATED_ARR_LNG_MIN)
            .arrLatDisplayedValue(UPDATED_ARR_LAT_DISPLAYED_VALUE)
            .arrLngDisplayedValue(UPDATED_ARR_LNG_DISPLAYED_VALUE)
            .arrLngDecimal(UPDATED_ARR_LNG_DECIMAL);

        restLegMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeg.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLeg))
            )
            .andExpect(status().isOk());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeUpdate);
        Leg testLeg = legList.get(legList.size() - 1);
        assertThat(testLeg.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testLeg.getDepLatDeg()).isEqualTo(UPDATED_DEP_LAT_DEG);
        assertThat(testLeg.getDepLatMin()).isEqualTo(UPDATED_DEP_LAT_MIN);
        assertThat(testLeg.getDepLatSec()).isEqualTo(UPDATED_DEP_LAT_SEC);
        assertThat(testLeg.getDepLatDirection()).isEqualTo(DEFAULT_DEP_LAT_DIRECTION);
        assertThat(testLeg.getDepLngDeg()).isEqualTo(DEFAULT_DEP_LNG_DEG);
        assertThat(testLeg.getDepLngMin()).isEqualTo(DEFAULT_DEP_LNG_MIN);
        assertThat(testLeg.getDepLngSec()).isEqualTo(DEFAULT_DEP_LNG_SEC);
        assertThat(testLeg.getDepLngDirection()).isEqualTo(DEFAULT_DEP_LNG_DIRECTION);
        assertThat(testLeg.getDepLatDisplayedValue()).isEqualTo(UPDATED_DEP_LAT_DISPLAYED_VALUE);
        assertThat(testLeg.getDepLngDisplayedValue()).isEqualTo(UPDATED_DEP_LNG_DISPLAYED_VALUE);
        assertThat(testLeg.getDepLatDecimal()).isEqualTo(DEFAULT_DEP_LAT_DECIMAL);
        assertThat(testLeg.getDepLngDecimal()).isEqualTo(DEFAULT_DEP_LNG_DECIMAL);
        assertThat(testLeg.getArrLatDeg()).isEqualTo(UPDATED_ARR_LAT_DEG);
        assertThat(testLeg.getArrLatMin()).isEqualTo(UPDATED_ARR_LAT_MIN);
        assertThat(testLeg.getArrLatSec()).isEqualTo(UPDATED_ARR_LAT_SEC);
        assertThat(testLeg.getArrLatDirection()).isEqualTo(DEFAULT_ARR_LAT_DIRECTION);
        assertThat(testLeg.getArrLngDeg()).isEqualTo(DEFAULT_ARR_LNG_DEG);
        assertThat(testLeg.getArrLngMin()).isEqualTo(UPDATED_ARR_LNG_MIN);
        assertThat(testLeg.getArrLngSec()).isEqualTo(DEFAULT_ARR_LNG_SEC);
        assertThat(testLeg.getArrLngDirection()).isEqualTo(DEFAULT_ARR_LNG_DIRECTION);
        assertThat(testLeg.getArrLatDisplayedValue()).isEqualTo(UPDATED_ARR_LAT_DISPLAYED_VALUE);
        assertThat(testLeg.getArrLngDisplayedValue()).isEqualTo(UPDATED_ARR_LNG_DISPLAYED_VALUE);
        assertThat(testLeg.getArrLatDecimal()).isEqualTo(DEFAULT_ARR_LAT_DECIMAL);
        assertThat(testLeg.getArrLngDecimal()).isEqualTo(UPDATED_ARR_LNG_DECIMAL);
        assertThat(testLeg.getCheckPointDistance()).isEqualTo(DEFAULT_CHECK_POINT_DISTANCE);
        assertThat(testLeg.getCheckLinePointDistance()).isEqualTo(DEFAULT_CHECK_LINE_POINT_DISTANCE);
        assertThat(testLeg.getTimeWindow()).isEqualTo(DEFAULT_TIME_WINDOW);
    }

    @Test
    @Transactional
    void fullUpdateLegWithPatch() throws Exception {
        // Initialize the database
        legRepository.saveAndFlush(leg);

        int databaseSizeBeforeUpdate = legRepository.findAll().size();

        // Update the leg using partial update
        Leg partialUpdatedLeg = new Leg();
        partialUpdatedLeg.setId(leg.getId());

        partialUpdatedLeg
            .order(UPDATED_ORDER)
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
            .checkPointDistance(UPDATED_CHECK_POINT_DISTANCE)
            .checkLinePointDistance(UPDATED_CHECK_LINE_POINT_DISTANCE)
            .timeWindow(UPDATED_TIME_WINDOW);

        restLegMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeg.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLeg))
            )
            .andExpect(status().isOk());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeUpdate);
        Leg testLeg = legList.get(legList.size() - 1);
        assertThat(testLeg.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testLeg.getDepLatDeg()).isEqualTo(UPDATED_DEP_LAT_DEG);
        assertThat(testLeg.getDepLatMin()).isEqualTo(UPDATED_DEP_LAT_MIN);
        assertThat(testLeg.getDepLatSec()).isEqualTo(UPDATED_DEP_LAT_SEC);
        assertThat(testLeg.getDepLatDirection()).isEqualTo(UPDATED_DEP_LAT_DIRECTION);
        assertThat(testLeg.getDepLngDeg()).isEqualTo(UPDATED_DEP_LNG_DEG);
        assertThat(testLeg.getDepLngMin()).isEqualTo(UPDATED_DEP_LNG_MIN);
        assertThat(testLeg.getDepLngSec()).isEqualTo(UPDATED_DEP_LNG_SEC);
        assertThat(testLeg.getDepLngDirection()).isEqualTo(UPDATED_DEP_LNG_DIRECTION);
        assertThat(testLeg.getDepLatDisplayedValue()).isEqualTo(UPDATED_DEP_LAT_DISPLAYED_VALUE);
        assertThat(testLeg.getDepLngDisplayedValue()).isEqualTo(UPDATED_DEP_LNG_DISPLAYED_VALUE);
        assertThat(testLeg.getDepLatDecimal()).isEqualTo(UPDATED_DEP_LAT_DECIMAL);
        assertThat(testLeg.getDepLngDecimal()).isEqualTo(UPDATED_DEP_LNG_DECIMAL);
        assertThat(testLeg.getArrLatDeg()).isEqualTo(UPDATED_ARR_LAT_DEG);
        assertThat(testLeg.getArrLatMin()).isEqualTo(UPDATED_ARR_LAT_MIN);
        assertThat(testLeg.getArrLatSec()).isEqualTo(UPDATED_ARR_LAT_SEC);
        assertThat(testLeg.getArrLatDirection()).isEqualTo(UPDATED_ARR_LAT_DIRECTION);
        assertThat(testLeg.getArrLngDeg()).isEqualTo(UPDATED_ARR_LNG_DEG);
        assertThat(testLeg.getArrLngMin()).isEqualTo(UPDATED_ARR_LNG_MIN);
        assertThat(testLeg.getArrLngSec()).isEqualTo(UPDATED_ARR_LNG_SEC);
        assertThat(testLeg.getArrLngDirection()).isEqualTo(UPDATED_ARR_LNG_DIRECTION);
        assertThat(testLeg.getArrLatDisplayedValue()).isEqualTo(UPDATED_ARR_LAT_DISPLAYED_VALUE);
        assertThat(testLeg.getArrLngDisplayedValue()).isEqualTo(UPDATED_ARR_LNG_DISPLAYED_VALUE);
        assertThat(testLeg.getArrLatDecimal()).isEqualTo(UPDATED_ARR_LAT_DECIMAL);
        assertThat(testLeg.getArrLngDecimal()).isEqualTo(UPDATED_ARR_LNG_DECIMAL);
        assertThat(testLeg.getCheckPointDistance()).isEqualTo(UPDATED_CHECK_POINT_DISTANCE);
        assertThat(testLeg.getCheckLinePointDistance()).isEqualTo(UPDATED_CHECK_LINE_POINT_DISTANCE);
        assertThat(testLeg.getTimeWindow()).isEqualTo(UPDATED_TIME_WINDOW);
    }

    @Test
    @Transactional
    void patchNonExistingLeg() throws Exception {
        int databaseSizeBeforeUpdate = legRepository.findAll().size();
        leg.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLegMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, leg.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(leg))
            )
            .andExpect(status().isBadRequest());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLeg() throws Exception {
        int databaseSizeBeforeUpdate = legRepository.findAll().size();
        leg.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLegMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(leg))
            )
            .andExpect(status().isBadRequest());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLeg() throws Exception {
        int databaseSizeBeforeUpdate = legRepository.findAll().size();
        leg.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLegMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(leg)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Leg in the database
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLeg() throws Exception {
        // Initialize the database
        legRepository.saveAndFlush(leg);

        int databaseSizeBeforeDelete = legRepository.findAll().size();

        // Delete the leg
        restLegMockMvc.perform(delete(ENTITY_API_URL_ID, leg.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Leg> legList = legRepository.findAll();
        assertThat(legList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
