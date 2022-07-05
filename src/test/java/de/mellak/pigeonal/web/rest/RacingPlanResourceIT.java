package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.RacingPlan;
import de.mellak.pigeonal.domain.enumeration.CheckLinePointDistance;
import de.mellak.pigeonal.domain.enumeration.CheckPointDistance;
import de.mellak.pigeonal.domain.enumeration.TimeWindow;
import de.mellak.pigeonal.repository.RacingPlanRepository;
import de.mellak.pigeonal.service.RacingPlanService;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RacingPlanResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class RacingPlanResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ASSOCATION = "AAAAAAAAAA";
    private static final String UPDATED_ASSOCATION = "BBBBBBBBBB";

    private static final Instant DEFAULT_RELEASE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RELEASE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_RELEASE_POINT = "AAAAAAAAAA";
    private static final String UPDATED_RELEASE_POINT = "BBBBBBBBBB";

    private static final String DEFAULT_ARRIVAL_POINT = "AAAAAAAAAA";
    private static final String UPDATED_ARRIVAL_POINT = "BBBBBBBBBB";

    private static final String DEFAULT_RELEASE_POINT_DMS = "AAAAAAAAAA";
    private static final String UPDATED_RELEASE_POINT_DMS = "BBBBBBBBBB";

    private static final String DEFAULT_ARRIVAL_POINT_DMS = "AAAAAAAAAA";
    private static final String UPDATED_ARRIVAL_POINT_DMS = "BBBBBBBBBB";

    private static final String DEFAULT_RELEASE_MAP_LINK = "AAAAAAAAAA";
    private static final String UPDATED_RELEASE_MAP_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_ARRIVAL_MAP_LINK = "AAAAAAAAAA";
    private static final String UPDATED_ARRIVAL_MAP_LINK = "BBBBBBBBBB";

    private static final Double DEFAULT_DISTANCE = 0D;
    private static final Double UPDATED_DISTANCE = 1D;

    private static final String DEFAULT_DISTANCE_DISPLAYED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DISTANCE_DISPLAYED_VALUE = "BBBBBBBBBB";

    private static final CheckPointDistance DEFAULT_CHECK_POINT_DISTANCE = CheckPointDistance.ONEKM;
    private static final CheckPointDistance UPDATED_CHECK_POINT_DISTANCE = CheckPointDistance.TWOKM;

    private static final Boolean DEFAULT_CHECK_LINED_REPORTING = false;
    private static final Boolean UPDATED_CHECK_LINED_REPORTING = true;

    private static final CheckLinePointDistance DEFAULT_CHECK_LINE_POINT_DISTANCE = CheckLinePointDistance.FIVEKM;
    private static final CheckLinePointDistance UPDATED_CHECK_LINE_POINT_DISTANCE = CheckLinePointDistance.TENKM;

    private static final TimeWindow DEFAULT_TIME_WINDOW = TimeWindow.THREEH;
    private static final TimeWindow UPDATED_TIME_WINDOW = TimeWindow.FOURH;

    private static final Boolean DEFAULT_GENERATION_IN_PROGRESS = false;
    private static final Boolean UPDATED_GENERATION_IN_PROGRESS = true;

    private static final Boolean DEFAULT_GENERATED = false;
    private static final Boolean UPDATED_GENERATED = true;

    private static final Boolean DEFAULT_RESET = false;
    private static final Boolean UPDATED_RESET = true;

    private static final Integer DEFAULT_PROGRESS = 1;
    private static final Integer UPDATED_PROGRESS = 2;

    private static final String ENTITY_API_URL = "/api/racing-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RacingPlanRepository racingPlanRepository;

    @Mock
    private RacingPlanRepository racingPlanRepositoryMock;

    @Mock
    private RacingPlanService racingPlanServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRacingPlanMockMvc;

    private RacingPlan racingPlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RacingPlan createEntity(EntityManager em) {
        RacingPlan racingPlan = new RacingPlan()
            .name(DEFAULT_NAME)
            .assocation(DEFAULT_ASSOCATION)
            .releaseDate(DEFAULT_RELEASE_DATE)
            .releasePoint(DEFAULT_RELEASE_POINT)
            .arrivalPoint(DEFAULT_ARRIVAL_POINT)
            .releasePointDMS(DEFAULT_RELEASE_POINT_DMS)
            .arrivalPointDMS(DEFAULT_ARRIVAL_POINT_DMS)
            .releaseMapLink(DEFAULT_RELEASE_MAP_LINK)
            .arrivalMapLink(DEFAULT_ARRIVAL_MAP_LINK)
            .distance(DEFAULT_DISTANCE)
            .distanceDisplayedValue(DEFAULT_DISTANCE_DISPLAYED_VALUE)
            .checkPointDistance(DEFAULT_CHECK_POINT_DISTANCE)
            .checkLinedReporting(DEFAULT_CHECK_LINED_REPORTING)
            .checkLinePointDistance(DEFAULT_CHECK_LINE_POINT_DISTANCE)
            .timeWindow(DEFAULT_TIME_WINDOW)
            .generationInProgress(DEFAULT_GENERATION_IN_PROGRESS)
            .generated(DEFAULT_GENERATED)
            .reset(DEFAULT_RESET)
            .progress(DEFAULT_PROGRESS);
        return racingPlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RacingPlan createUpdatedEntity(EntityManager em) {
        RacingPlan racingPlan = new RacingPlan()
            .name(UPDATED_NAME)
            .assocation(UPDATED_ASSOCATION)
            .releaseDate(UPDATED_RELEASE_DATE)
            .releasePoint(UPDATED_RELEASE_POINT)
            .arrivalPoint(UPDATED_ARRIVAL_POINT)
            .releasePointDMS(UPDATED_RELEASE_POINT_DMS)
            .arrivalPointDMS(UPDATED_ARRIVAL_POINT_DMS)
            .releaseMapLink(UPDATED_RELEASE_MAP_LINK)
            .arrivalMapLink(UPDATED_ARRIVAL_MAP_LINK)
            .distance(UPDATED_DISTANCE)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .checkPointDistance(UPDATED_CHECK_POINT_DISTANCE)
            .checkLinedReporting(UPDATED_CHECK_LINED_REPORTING)
            .checkLinePointDistance(UPDATED_CHECK_LINE_POINT_DISTANCE)
            .timeWindow(UPDATED_TIME_WINDOW)
            .generationInProgress(UPDATED_GENERATION_IN_PROGRESS)
            .generated(UPDATED_GENERATED)
            .reset(UPDATED_RESET)
            .progress(UPDATED_PROGRESS);
        return racingPlan;
    }

    @BeforeEach
    public void initTest() {
        racingPlan = createEntity(em);
    }

    @Test
    @Transactional
    void createRacingPlan() throws Exception {
        int databaseSizeBeforeCreate = racingPlanRepository.findAll().size();
        // Create the RacingPlan
        restRacingPlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(racingPlan)))
            .andExpect(status().isCreated());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeCreate + 1);
        RacingPlan testRacingPlan = racingPlanList.get(racingPlanList.size() - 1);
        assertThat(testRacingPlan.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRacingPlan.getAssocation()).isEqualTo(DEFAULT_ASSOCATION);
        assertThat(testRacingPlan.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
        assertThat(testRacingPlan.getReleasePoint()).isEqualTo(DEFAULT_RELEASE_POINT);
        assertThat(testRacingPlan.getArrivalPoint()).isEqualTo(DEFAULT_ARRIVAL_POINT);
        assertThat(testRacingPlan.getReleasePointDMS()).isEqualTo(DEFAULT_RELEASE_POINT_DMS);
        assertThat(testRacingPlan.getArrivalPointDMS()).isEqualTo(DEFAULT_ARRIVAL_POINT_DMS);
        assertThat(testRacingPlan.getReleaseMapLink()).isEqualTo(DEFAULT_RELEASE_MAP_LINK);
        assertThat(testRacingPlan.getArrivalMapLink()).isEqualTo(DEFAULT_ARRIVAL_MAP_LINK);
        assertThat(testRacingPlan.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testRacingPlan.getDistanceDisplayedValue()).isEqualTo(DEFAULT_DISTANCE_DISPLAYED_VALUE);
        assertThat(testRacingPlan.getCheckPointDistance()).isEqualTo(DEFAULT_CHECK_POINT_DISTANCE);
        assertThat(testRacingPlan.getCheckLinedReporting()).isEqualTo(DEFAULT_CHECK_LINED_REPORTING);
        assertThat(testRacingPlan.getCheckLinePointDistance()).isEqualTo(DEFAULT_CHECK_LINE_POINT_DISTANCE);
        assertThat(testRacingPlan.getTimeWindow()).isEqualTo(DEFAULT_TIME_WINDOW);
        assertThat(testRacingPlan.getGenerationInProgress()).isEqualTo(DEFAULT_GENERATION_IN_PROGRESS);
        assertThat(testRacingPlan.getGenerated()).isEqualTo(DEFAULT_GENERATED);
        assertThat(testRacingPlan.getReset()).isEqualTo(DEFAULT_RESET);
        assertThat(testRacingPlan.getProgress()).isEqualTo(DEFAULT_PROGRESS);
    }

    @Test
    @Transactional
    void createRacingPlanWithExistingId() throws Exception {
        // Create the RacingPlan with an existing ID
        racingPlan.setId(1L);

        int databaseSizeBeforeCreate = racingPlanRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRacingPlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(racingPlan)))
            .andExpect(status().isBadRequest());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = racingPlanRepository.findAll().size();
        // set the field null
        racingPlan.setName(null);

        // Create the RacingPlan, which fails.

        restRacingPlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(racingPlan)))
            .andExpect(status().isBadRequest());

        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAssocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = racingPlanRepository.findAll().size();
        // set the field null
        racingPlan.setAssocation(null);

        // Create the RacingPlan, which fails.

        restRacingPlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(racingPlan)))
            .andExpect(status().isBadRequest());

        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllRacingPlans() throws Exception {
        // Initialize the database
        racingPlanRepository.saveAndFlush(racingPlan);

        // Get all the racingPlanList
        restRacingPlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(racingPlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].assocation").value(hasItem(DEFAULT_ASSOCATION)))
            .andExpect(jsonPath("$.[*].releaseDate").value(hasItem(DEFAULT_RELEASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].releasePoint").value(hasItem(DEFAULT_RELEASE_POINT)))
            .andExpect(jsonPath("$.[*].arrivalPoint").value(hasItem(DEFAULT_ARRIVAL_POINT)))
            .andExpect(jsonPath("$.[*].releasePointDMS").value(hasItem(DEFAULT_RELEASE_POINT_DMS)))
            .andExpect(jsonPath("$.[*].arrivalPointDMS").value(hasItem(DEFAULT_ARRIVAL_POINT_DMS)))
            .andExpect(jsonPath("$.[*].releaseMapLink").value(hasItem(DEFAULT_RELEASE_MAP_LINK)))
            .andExpect(jsonPath("$.[*].arrivalMapLink").value(hasItem(DEFAULT_ARRIVAL_MAP_LINK)))
            .andExpect(jsonPath("$.[*].distance").value(hasItem(DEFAULT_DISTANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].distanceDisplayedValue").value(hasItem(DEFAULT_DISTANCE_DISPLAYED_VALUE)))
            .andExpect(jsonPath("$.[*].checkPointDistance").value(hasItem(DEFAULT_CHECK_POINT_DISTANCE.toString())))
            .andExpect(jsonPath("$.[*].checkLinedReporting").value(hasItem(DEFAULT_CHECK_LINED_REPORTING.booleanValue())))
            .andExpect(jsonPath("$.[*].checkLinePointDistance").value(hasItem(DEFAULT_CHECK_LINE_POINT_DISTANCE.toString())))
            .andExpect(jsonPath("$.[*].timeWindow").value(hasItem(DEFAULT_TIME_WINDOW.toString())))
            .andExpect(jsonPath("$.[*].generationInProgress").value(hasItem(DEFAULT_GENERATION_IN_PROGRESS.booleanValue())))
            .andExpect(jsonPath("$.[*].generated").value(hasItem(DEFAULT_GENERATED.booleanValue())))
            .andExpect(jsonPath("$.[*].reset").value(hasItem(DEFAULT_RESET.booleanValue())))
            .andExpect(jsonPath("$.[*].progress").value(hasItem(DEFAULT_PROGRESS)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRacingPlansWithEagerRelationshipsIsEnabled() throws Exception {
        when(racingPlanServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRacingPlanMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(racingPlanServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRacingPlansWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(racingPlanServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRacingPlanMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(racingPlanServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getRacingPlan() throws Exception {
        // Initialize the database
        racingPlanRepository.saveAndFlush(racingPlan);

        // Get the racingPlan
        restRacingPlanMockMvc
            .perform(get(ENTITY_API_URL_ID, racingPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(racingPlan.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.assocation").value(DEFAULT_ASSOCATION))
            .andExpect(jsonPath("$.releaseDate").value(DEFAULT_RELEASE_DATE.toString()))
            .andExpect(jsonPath("$.releasePoint").value(DEFAULT_RELEASE_POINT))
            .andExpect(jsonPath("$.arrivalPoint").value(DEFAULT_ARRIVAL_POINT))
            .andExpect(jsonPath("$.releasePointDMS").value(DEFAULT_RELEASE_POINT_DMS))
            .andExpect(jsonPath("$.arrivalPointDMS").value(DEFAULT_ARRIVAL_POINT_DMS))
            .andExpect(jsonPath("$.releaseMapLink").value(DEFAULT_RELEASE_MAP_LINK))
            .andExpect(jsonPath("$.arrivalMapLink").value(DEFAULT_ARRIVAL_MAP_LINK))
            .andExpect(jsonPath("$.distance").value(DEFAULT_DISTANCE.doubleValue()))
            .andExpect(jsonPath("$.distanceDisplayedValue").value(DEFAULT_DISTANCE_DISPLAYED_VALUE))
            .andExpect(jsonPath("$.checkPointDistance").value(DEFAULT_CHECK_POINT_DISTANCE.toString()))
            .andExpect(jsonPath("$.checkLinedReporting").value(DEFAULT_CHECK_LINED_REPORTING.booleanValue()))
            .andExpect(jsonPath("$.checkLinePointDistance").value(DEFAULT_CHECK_LINE_POINT_DISTANCE.toString()))
            .andExpect(jsonPath("$.timeWindow").value(DEFAULT_TIME_WINDOW.toString()))
            .andExpect(jsonPath("$.generationInProgress").value(DEFAULT_GENERATION_IN_PROGRESS.booleanValue()))
            .andExpect(jsonPath("$.generated").value(DEFAULT_GENERATED.booleanValue()))
            .andExpect(jsonPath("$.reset").value(DEFAULT_RESET.booleanValue()))
            .andExpect(jsonPath("$.progress").value(DEFAULT_PROGRESS));
    }

    @Test
    @Transactional
    void getNonExistingRacingPlan() throws Exception {
        // Get the racingPlan
        restRacingPlanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRacingPlan() throws Exception {
        // Initialize the database
        racingPlanRepository.saveAndFlush(racingPlan);

        int databaseSizeBeforeUpdate = racingPlanRepository.findAll().size();

        // Update the racingPlan
        RacingPlan updatedRacingPlan = racingPlanRepository.findById(racingPlan.getId()).get();
        // Disconnect from session so that the updates on updatedRacingPlan are not directly saved in db
        em.detach(updatedRacingPlan);
        updatedRacingPlan
            .name(UPDATED_NAME)
            .assocation(UPDATED_ASSOCATION)
            .releaseDate(UPDATED_RELEASE_DATE)
            .releasePoint(UPDATED_RELEASE_POINT)
            .arrivalPoint(UPDATED_ARRIVAL_POINT)
            .releasePointDMS(UPDATED_RELEASE_POINT_DMS)
            .arrivalPointDMS(UPDATED_ARRIVAL_POINT_DMS)
            .releaseMapLink(UPDATED_RELEASE_MAP_LINK)
            .arrivalMapLink(UPDATED_ARRIVAL_MAP_LINK)
            .distance(UPDATED_DISTANCE)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .checkPointDistance(UPDATED_CHECK_POINT_DISTANCE)
            .checkLinedReporting(UPDATED_CHECK_LINED_REPORTING)
            .checkLinePointDistance(UPDATED_CHECK_LINE_POINT_DISTANCE)
            .timeWindow(UPDATED_TIME_WINDOW)
            .generationInProgress(UPDATED_GENERATION_IN_PROGRESS)
            .generated(UPDATED_GENERATED)
            .reset(UPDATED_RESET)
            .progress(UPDATED_PROGRESS);

        restRacingPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRacingPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRacingPlan))
            )
            .andExpect(status().isOk());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeUpdate);
        RacingPlan testRacingPlan = racingPlanList.get(racingPlanList.size() - 1);
        assertThat(testRacingPlan.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRacingPlan.getAssocation()).isEqualTo(UPDATED_ASSOCATION);
        assertThat(testRacingPlan.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testRacingPlan.getReleasePoint()).isEqualTo(UPDATED_RELEASE_POINT);
        assertThat(testRacingPlan.getArrivalPoint()).isEqualTo(UPDATED_ARRIVAL_POINT);
        assertThat(testRacingPlan.getReleasePointDMS()).isEqualTo(UPDATED_RELEASE_POINT_DMS);
        assertThat(testRacingPlan.getArrivalPointDMS()).isEqualTo(UPDATED_ARRIVAL_POINT_DMS);
        assertThat(testRacingPlan.getReleaseMapLink()).isEqualTo(UPDATED_RELEASE_MAP_LINK);
        assertThat(testRacingPlan.getArrivalMapLink()).isEqualTo(UPDATED_ARRIVAL_MAP_LINK);
        assertThat(testRacingPlan.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testRacingPlan.getDistanceDisplayedValue()).isEqualTo(UPDATED_DISTANCE_DISPLAYED_VALUE);
        assertThat(testRacingPlan.getCheckPointDistance()).isEqualTo(UPDATED_CHECK_POINT_DISTANCE);
        assertThat(testRacingPlan.getCheckLinedReporting()).isEqualTo(UPDATED_CHECK_LINED_REPORTING);
        assertThat(testRacingPlan.getCheckLinePointDistance()).isEqualTo(UPDATED_CHECK_LINE_POINT_DISTANCE);
        assertThat(testRacingPlan.getTimeWindow()).isEqualTo(UPDATED_TIME_WINDOW);
        assertThat(testRacingPlan.getGenerationInProgress()).isEqualTo(UPDATED_GENERATION_IN_PROGRESS);
        assertThat(testRacingPlan.getGenerated()).isEqualTo(UPDATED_GENERATED);
        assertThat(testRacingPlan.getReset()).isEqualTo(UPDATED_RESET);
        assertThat(testRacingPlan.getProgress()).isEqualTo(UPDATED_PROGRESS);
    }

    @Test
    @Transactional
    void putNonExistingRacingPlan() throws Exception {
        int databaseSizeBeforeUpdate = racingPlanRepository.findAll().size();
        racingPlan.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRacingPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, racingPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(racingPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRacingPlan() throws Exception {
        int databaseSizeBeforeUpdate = racingPlanRepository.findAll().size();
        racingPlan.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRacingPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(racingPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRacingPlan() throws Exception {
        int databaseSizeBeforeUpdate = racingPlanRepository.findAll().size();
        racingPlan.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRacingPlanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(racingPlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRacingPlanWithPatch() throws Exception {
        // Initialize the database
        racingPlanRepository.saveAndFlush(racingPlan);

        int databaseSizeBeforeUpdate = racingPlanRepository.findAll().size();

        // Update the racingPlan using partial update
        RacingPlan partialUpdatedRacingPlan = new RacingPlan();
        partialUpdatedRacingPlan.setId(racingPlan.getId());

        partialUpdatedRacingPlan
            .assocation(UPDATED_ASSOCATION)
            .releasePointDMS(UPDATED_RELEASE_POINT_DMS)
            .arrivalPointDMS(UPDATED_ARRIVAL_POINT_DMS)
            .arrivalMapLink(UPDATED_ARRIVAL_MAP_LINK)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .checkPointDistance(UPDATED_CHECK_POINT_DISTANCE)
            .checkLinedReporting(UPDATED_CHECK_LINED_REPORTING)
            .generationInProgress(UPDATED_GENERATION_IN_PROGRESS)
            .progress(UPDATED_PROGRESS);

        restRacingPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRacingPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRacingPlan))
            )
            .andExpect(status().isOk());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeUpdate);
        RacingPlan testRacingPlan = racingPlanList.get(racingPlanList.size() - 1);
        assertThat(testRacingPlan.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRacingPlan.getAssocation()).isEqualTo(UPDATED_ASSOCATION);
        assertThat(testRacingPlan.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
        assertThat(testRacingPlan.getReleasePoint()).isEqualTo(DEFAULT_RELEASE_POINT);
        assertThat(testRacingPlan.getArrivalPoint()).isEqualTo(DEFAULT_ARRIVAL_POINT);
        assertThat(testRacingPlan.getReleasePointDMS()).isEqualTo(UPDATED_RELEASE_POINT_DMS);
        assertThat(testRacingPlan.getArrivalPointDMS()).isEqualTo(UPDATED_ARRIVAL_POINT_DMS);
        assertThat(testRacingPlan.getReleaseMapLink()).isEqualTo(DEFAULT_RELEASE_MAP_LINK);
        assertThat(testRacingPlan.getArrivalMapLink()).isEqualTo(UPDATED_ARRIVAL_MAP_LINK);
        assertThat(testRacingPlan.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testRacingPlan.getDistanceDisplayedValue()).isEqualTo(UPDATED_DISTANCE_DISPLAYED_VALUE);
        assertThat(testRacingPlan.getCheckPointDistance()).isEqualTo(UPDATED_CHECK_POINT_DISTANCE);
        assertThat(testRacingPlan.getCheckLinedReporting()).isEqualTo(UPDATED_CHECK_LINED_REPORTING);
        assertThat(testRacingPlan.getCheckLinePointDistance()).isEqualTo(DEFAULT_CHECK_LINE_POINT_DISTANCE);
        assertThat(testRacingPlan.getTimeWindow()).isEqualTo(DEFAULT_TIME_WINDOW);
        assertThat(testRacingPlan.getGenerationInProgress()).isEqualTo(UPDATED_GENERATION_IN_PROGRESS);
        assertThat(testRacingPlan.getGenerated()).isEqualTo(DEFAULT_GENERATED);
        assertThat(testRacingPlan.getReset()).isEqualTo(DEFAULT_RESET);
        assertThat(testRacingPlan.getProgress()).isEqualTo(UPDATED_PROGRESS);
    }

    @Test
    @Transactional
    void fullUpdateRacingPlanWithPatch() throws Exception {
        // Initialize the database
        racingPlanRepository.saveAndFlush(racingPlan);

        int databaseSizeBeforeUpdate = racingPlanRepository.findAll().size();

        // Update the racingPlan using partial update
        RacingPlan partialUpdatedRacingPlan = new RacingPlan();
        partialUpdatedRacingPlan.setId(racingPlan.getId());

        partialUpdatedRacingPlan
            .name(UPDATED_NAME)
            .assocation(UPDATED_ASSOCATION)
            .releaseDate(UPDATED_RELEASE_DATE)
            .releasePoint(UPDATED_RELEASE_POINT)
            .arrivalPoint(UPDATED_ARRIVAL_POINT)
            .releasePointDMS(UPDATED_RELEASE_POINT_DMS)
            .arrivalPointDMS(UPDATED_ARRIVAL_POINT_DMS)
            .releaseMapLink(UPDATED_RELEASE_MAP_LINK)
            .arrivalMapLink(UPDATED_ARRIVAL_MAP_LINK)
            .distance(UPDATED_DISTANCE)
            .distanceDisplayedValue(UPDATED_DISTANCE_DISPLAYED_VALUE)
            .checkPointDistance(UPDATED_CHECK_POINT_DISTANCE)
            .checkLinedReporting(UPDATED_CHECK_LINED_REPORTING)
            .checkLinePointDistance(UPDATED_CHECK_LINE_POINT_DISTANCE)
            .timeWindow(UPDATED_TIME_WINDOW)
            .generationInProgress(UPDATED_GENERATION_IN_PROGRESS)
            .generated(UPDATED_GENERATED)
            .reset(UPDATED_RESET)
            .progress(UPDATED_PROGRESS);

        restRacingPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRacingPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRacingPlan))
            )
            .andExpect(status().isOk());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeUpdate);
        RacingPlan testRacingPlan = racingPlanList.get(racingPlanList.size() - 1);
        assertThat(testRacingPlan.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRacingPlan.getAssocation()).isEqualTo(UPDATED_ASSOCATION);
        assertThat(testRacingPlan.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testRacingPlan.getReleasePoint()).isEqualTo(UPDATED_RELEASE_POINT);
        assertThat(testRacingPlan.getArrivalPoint()).isEqualTo(UPDATED_ARRIVAL_POINT);
        assertThat(testRacingPlan.getReleasePointDMS()).isEqualTo(UPDATED_RELEASE_POINT_DMS);
        assertThat(testRacingPlan.getArrivalPointDMS()).isEqualTo(UPDATED_ARRIVAL_POINT_DMS);
        assertThat(testRacingPlan.getReleaseMapLink()).isEqualTo(UPDATED_RELEASE_MAP_LINK);
        assertThat(testRacingPlan.getArrivalMapLink()).isEqualTo(UPDATED_ARRIVAL_MAP_LINK);
        assertThat(testRacingPlan.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testRacingPlan.getDistanceDisplayedValue()).isEqualTo(UPDATED_DISTANCE_DISPLAYED_VALUE);
        assertThat(testRacingPlan.getCheckPointDistance()).isEqualTo(UPDATED_CHECK_POINT_DISTANCE);
        assertThat(testRacingPlan.getCheckLinedReporting()).isEqualTo(UPDATED_CHECK_LINED_REPORTING);
        assertThat(testRacingPlan.getCheckLinePointDistance()).isEqualTo(UPDATED_CHECK_LINE_POINT_DISTANCE);
        assertThat(testRacingPlan.getTimeWindow()).isEqualTo(UPDATED_TIME_WINDOW);
        assertThat(testRacingPlan.getGenerationInProgress()).isEqualTo(UPDATED_GENERATION_IN_PROGRESS);
        assertThat(testRacingPlan.getGenerated()).isEqualTo(UPDATED_GENERATED);
        assertThat(testRacingPlan.getReset()).isEqualTo(UPDATED_RESET);
        assertThat(testRacingPlan.getProgress()).isEqualTo(UPDATED_PROGRESS);
    }

    @Test
    @Transactional
    void patchNonExistingRacingPlan() throws Exception {
        int databaseSizeBeforeUpdate = racingPlanRepository.findAll().size();
        racingPlan.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRacingPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, racingPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(racingPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRacingPlan() throws Exception {
        int databaseSizeBeforeUpdate = racingPlanRepository.findAll().size();
        racingPlan.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRacingPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(racingPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRacingPlan() throws Exception {
        int databaseSizeBeforeUpdate = racingPlanRepository.findAll().size();
        racingPlan.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRacingPlanMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(racingPlan))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RacingPlan in the database
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRacingPlan() throws Exception {
        // Initialize the database
        racingPlanRepository.saveAndFlush(racingPlan);

        int databaseSizeBeforeDelete = racingPlanRepository.findAll().size();

        // Delete the racingPlan
        restRacingPlanMockMvc
            .perform(delete(ENTITY_API_URL_ID, racingPlan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RacingPlan> racingPlanList = racingPlanRepository.findAll();
        assertThat(racingPlanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
