package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.WeatherDataPoint;
import de.mellak.pigeonal.domain.enumeration.WeatherProperty;
import de.mellak.pigeonal.repository.WeatherDataPointRepository;
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
 * Integration tests for the {@link WeatherDataPointResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WeatherDataPointResourceIT {

    private static final WeatherProperty DEFAULT_PROPERTY = WeatherProperty.TIME;
    private static final WeatherProperty UPDATED_PROPERTY = WeatherProperty.SUMMARY;

    private static final String DEFAULT_MID_NIGHT = "AAAAAAAAAA";
    private static final String UPDATED_MID_NIGHT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MIG_NIGHT_RELEVANCE = false;
    private static final Boolean UPDATED_MIG_NIGHT_RELEVANCE = true;

    private static final String DEFAULT_ONE = "AAAAAAAAAA";
    private static final String UPDATED_ONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ONE_RELEVANCE = false;
    private static final Boolean UPDATED_ONE_RELEVANCE = true;

    private static final String DEFAULT_TWO = "AAAAAAAAAA";
    private static final String UPDATED_TWO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TWO_RELEVANCE = false;
    private static final Boolean UPDATED_TWO_RELEVANCE = true;

    private static final String DEFAULT_THREE = "AAAAAAAAAA";
    private static final String UPDATED_THREE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THREE_RELEVANCE = false;
    private static final Boolean UPDATED_THREE_RELEVANCE = true;

    private static final String DEFAULT_FOUR = "AAAAAAAAAA";
    private static final String UPDATED_FOUR = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FOUR_RELEVANCE = false;
    private static final Boolean UPDATED_FOUR_RELEVANCE = true;

    private static final String DEFAULT_FIVE = "AAAAAAAAAA";
    private static final String UPDATED_FIVE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FIVE_RELEVANCE = false;
    private static final Boolean UPDATED_FIVE_RELEVANCE = true;

    private static final String DEFAULT_SIX = "AAAAAAAAAA";
    private static final String UPDATED_SIX = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SIX_RELEVANCE = false;
    private static final Boolean UPDATED_SIX_RELEVANCE = true;

    private static final String DEFAULT_SEVEN = "AAAAAAAAAA";
    private static final String UPDATED_SEVEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SEVEN_RELEVANCE = false;
    private static final Boolean UPDATED_SEVEN_RELEVANCE = true;

    private static final String DEFAULT_EIGHT = "AAAAAAAAAA";
    private static final String UPDATED_EIGHT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EIGHT_RELEVANCE = false;
    private static final Boolean UPDATED_EIGHT_RELEVANCE = true;

    private static final String DEFAULT_NINE = "AAAAAAAAAA";
    private static final String UPDATED_NINE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NINE_RELEVANCE = false;
    private static final Boolean UPDATED_NINE_RELEVANCE = true;

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TEN_RELEVANCE = false;
    private static final Boolean UPDATED_TEN_RELEVANCE = true;

    private static final String DEFAULT_ELEVEN = "AAAAAAAAAA";
    private static final String UPDATED_ELEVEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ELEVEN_RELEVANCE = false;
    private static final Boolean UPDATED_ELEVEN_RELEVANCE = true;

    private static final String DEFAULT_TWELVE = "AAAAAAAAAA";
    private static final String UPDATED_TWELVE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TWELVE_RELEVANCE = false;
    private static final Boolean UPDATED_TWELVE_RELEVANCE = true;

    private static final String DEFAULT_THIRTEEN = "AAAAAAAAAA";
    private static final String UPDATED_THIRTEEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THIRTEEN_RELEVANCE = false;
    private static final Boolean UPDATED_THIRTEEN_RELEVANCE = true;

    private static final String DEFAULT_FOURTEEN = "AAAAAAAAAA";
    private static final String UPDATED_FOURTEEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FOURTEEN_RELEVANCE = false;
    private static final Boolean UPDATED_FOURTEEN_RELEVANCE = true;

    private static final String DEFAULT_FIFTEEN = "AAAAAAAAAA";
    private static final String UPDATED_FIFTEEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FIFTEEN_RELEVANCE = false;
    private static final Boolean UPDATED_FIFTEEN_RELEVANCE = true;

    private static final String DEFAULT_SIXTEEN = "AAAAAAAAAA";
    private static final String UPDATED_SIXTEEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SIXTEEN_RELEVANCE = false;
    private static final Boolean UPDATED_SIXTEEN_RELEVANCE = true;

    private static final String DEFAULT_SEVENTEEN = "AAAAAAAAAA";
    private static final String UPDATED_SEVENTEEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SEVENTEEN_RELEVANCE = false;
    private static final Boolean UPDATED_SEVENTEEN_RELEVANCE = true;

    private static final String DEFAULT_EIGHTEEN = "AAAAAAAAAA";
    private static final String UPDATED_EIGHTEEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EIGHTEEN_RELEVANCE = false;
    private static final Boolean UPDATED_EIGHTEEN_RELEVANCE = true;

    private static final String DEFAULT_NINETEEN = "AAAAAAAAAA";
    private static final String UPDATED_NINETEEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NINETEEN_RELEVANCE = false;
    private static final Boolean UPDATED_NINETEEN_RELEVANCE = true;

    private static final String DEFAULT_TWENTY = "AAAAAAAAAA";
    private static final String UPDATED_TWENTY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TWENTY_RELEVANCE = false;
    private static final Boolean UPDATED_TWENTY_RELEVANCE = true;

    private static final String DEFAULT_TWENTY_ONE = "AAAAAAAAAA";
    private static final String UPDATED_TWENTY_ONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TWENTY_ONE_RELEVANCE = false;
    private static final Boolean UPDATED_TWENTY_ONE_RELEVANCE = true;

    private static final String DEFAULT_TWENTY_TWO = "AAAAAAAAAA";
    private static final String UPDATED_TWENTY_TWO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TWENTY_TWO_RELEVANCE = false;
    private static final Boolean UPDATED_TWENTY_TWO_RELEVANCE = true;

    private static final String DEFAULT_TWENTY_THREE = "AAAAAAAAAA";
    private static final String UPDATED_TWENTY_THREE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/weather-data-points";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WeatherDataPointRepository weatherDataPointRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWeatherDataPointMockMvc;

    private WeatherDataPoint weatherDataPoint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeatherDataPoint createEntity(EntityManager em) {
        WeatherDataPoint weatherDataPoint = new WeatherDataPoint()
            .property(DEFAULT_PROPERTY)
            .midNight(DEFAULT_MID_NIGHT)
            .migNightRelevance(DEFAULT_MIG_NIGHT_RELEVANCE)
            .one(DEFAULT_ONE)
            .oneRelevance(DEFAULT_ONE_RELEVANCE)
            .two(DEFAULT_TWO)
            .twoRelevance(DEFAULT_TWO_RELEVANCE)
            .three(DEFAULT_THREE)
            .threeRelevance(DEFAULT_THREE_RELEVANCE)
            .four(DEFAULT_FOUR)
            .fourRelevance(DEFAULT_FOUR_RELEVANCE)
            .five(DEFAULT_FIVE)
            .fiveRelevance(DEFAULT_FIVE_RELEVANCE)
            .six(DEFAULT_SIX)
            .sixRelevance(DEFAULT_SIX_RELEVANCE)
            .seven(DEFAULT_SEVEN)
            .sevenRelevance(DEFAULT_SEVEN_RELEVANCE)
            .eight(DEFAULT_EIGHT)
            .eightRelevance(DEFAULT_EIGHT_RELEVANCE)
            .nine(DEFAULT_NINE)
            .nineRelevance(DEFAULT_NINE_RELEVANCE)
            .ten(DEFAULT_TEN)
            .tenRelevance(DEFAULT_TEN_RELEVANCE)
            .eleven(DEFAULT_ELEVEN)
            .elevenRelevance(DEFAULT_ELEVEN_RELEVANCE)
            .twelve(DEFAULT_TWELVE)
            .twelveRelevance(DEFAULT_TWELVE_RELEVANCE)
            .thirteen(DEFAULT_THIRTEEN)
            .thirteenRelevance(DEFAULT_THIRTEEN_RELEVANCE)
            .fourteen(DEFAULT_FOURTEEN)
            .fourteenRelevance(DEFAULT_FOURTEEN_RELEVANCE)
            .fifteen(DEFAULT_FIFTEEN)
            .fifteenRelevance(DEFAULT_FIFTEEN_RELEVANCE)
            .sixteen(DEFAULT_SIXTEEN)
            .sixteenRelevance(DEFAULT_SIXTEEN_RELEVANCE)
            .seventeen(DEFAULT_SEVENTEEN)
            .seventeenRelevance(DEFAULT_SEVENTEEN_RELEVANCE)
            .eighteen(DEFAULT_EIGHTEEN)
            .eighteenRelevance(DEFAULT_EIGHTEEN_RELEVANCE)
            .nineteen(DEFAULT_NINETEEN)
            .nineteenRelevance(DEFAULT_NINETEEN_RELEVANCE)
            .twenty(DEFAULT_TWENTY)
            .twentyRelevance(DEFAULT_TWENTY_RELEVANCE)
            .twentyOne(DEFAULT_TWENTY_ONE)
            .twentyOneRelevance(DEFAULT_TWENTY_ONE_RELEVANCE)
            .twentyTwo(DEFAULT_TWENTY_TWO)
            .twentyTwoRelevance(DEFAULT_TWENTY_TWO_RELEVANCE)
            .twentyThree(DEFAULT_TWENTY_THREE);
        return weatherDataPoint;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeatherDataPoint createUpdatedEntity(EntityManager em) {
        WeatherDataPoint weatherDataPoint = new WeatherDataPoint()
            .property(UPDATED_PROPERTY)
            .midNight(UPDATED_MID_NIGHT)
            .migNightRelevance(UPDATED_MIG_NIGHT_RELEVANCE)
            .one(UPDATED_ONE)
            .oneRelevance(UPDATED_ONE_RELEVANCE)
            .two(UPDATED_TWO)
            .twoRelevance(UPDATED_TWO_RELEVANCE)
            .three(UPDATED_THREE)
            .threeRelevance(UPDATED_THREE_RELEVANCE)
            .four(UPDATED_FOUR)
            .fourRelevance(UPDATED_FOUR_RELEVANCE)
            .five(UPDATED_FIVE)
            .fiveRelevance(UPDATED_FIVE_RELEVANCE)
            .six(UPDATED_SIX)
            .sixRelevance(UPDATED_SIX_RELEVANCE)
            .seven(UPDATED_SEVEN)
            .sevenRelevance(UPDATED_SEVEN_RELEVANCE)
            .eight(UPDATED_EIGHT)
            .eightRelevance(UPDATED_EIGHT_RELEVANCE)
            .nine(UPDATED_NINE)
            .nineRelevance(UPDATED_NINE_RELEVANCE)
            .ten(UPDATED_TEN)
            .tenRelevance(UPDATED_TEN_RELEVANCE)
            .eleven(UPDATED_ELEVEN)
            .elevenRelevance(UPDATED_ELEVEN_RELEVANCE)
            .twelve(UPDATED_TWELVE)
            .twelveRelevance(UPDATED_TWELVE_RELEVANCE)
            .thirteen(UPDATED_THIRTEEN)
            .thirteenRelevance(UPDATED_THIRTEEN_RELEVANCE)
            .fourteen(UPDATED_FOURTEEN)
            .fourteenRelevance(UPDATED_FOURTEEN_RELEVANCE)
            .fifteen(UPDATED_FIFTEEN)
            .fifteenRelevance(UPDATED_FIFTEEN_RELEVANCE)
            .sixteen(UPDATED_SIXTEEN)
            .sixteenRelevance(UPDATED_SIXTEEN_RELEVANCE)
            .seventeen(UPDATED_SEVENTEEN)
            .seventeenRelevance(UPDATED_SEVENTEEN_RELEVANCE)
            .eighteen(UPDATED_EIGHTEEN)
            .eighteenRelevance(UPDATED_EIGHTEEN_RELEVANCE)
            .nineteen(UPDATED_NINETEEN)
            .nineteenRelevance(UPDATED_NINETEEN_RELEVANCE)
            .twenty(UPDATED_TWENTY)
            .twentyRelevance(UPDATED_TWENTY_RELEVANCE)
            .twentyOne(UPDATED_TWENTY_ONE)
            .twentyOneRelevance(UPDATED_TWENTY_ONE_RELEVANCE)
            .twentyTwo(UPDATED_TWENTY_TWO)
            .twentyTwoRelevance(UPDATED_TWENTY_TWO_RELEVANCE)
            .twentyThree(UPDATED_TWENTY_THREE);
        return weatherDataPoint;
    }

    @BeforeEach
    public void initTest() {
        weatherDataPoint = createEntity(em);
    }

    @Test
    @Transactional
    void createWeatherDataPoint() throws Exception {
        int databaseSizeBeforeCreate = weatherDataPointRepository.findAll().size();
        // Create the WeatherDataPoint
        restWeatherDataPointMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(weatherDataPoint))
            )
            .andExpect(status().isCreated());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeCreate + 1);
        WeatherDataPoint testWeatherDataPoint = weatherDataPointList.get(weatherDataPointList.size() - 1);
        assertThat(testWeatherDataPoint.getProperty()).isEqualTo(DEFAULT_PROPERTY);
        assertThat(testWeatherDataPoint.getMidNight()).isEqualTo(DEFAULT_MID_NIGHT);
        assertThat(testWeatherDataPoint.getMigNightRelevance()).isEqualTo(DEFAULT_MIG_NIGHT_RELEVANCE);
        assertThat(testWeatherDataPoint.getOne()).isEqualTo(DEFAULT_ONE);
        assertThat(testWeatherDataPoint.getOneRelevance()).isEqualTo(DEFAULT_ONE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwo()).isEqualTo(DEFAULT_TWO);
        assertThat(testWeatherDataPoint.getTwoRelevance()).isEqualTo(DEFAULT_TWO_RELEVANCE);
        assertThat(testWeatherDataPoint.getThree()).isEqualTo(DEFAULT_THREE);
        assertThat(testWeatherDataPoint.getThreeRelevance()).isEqualTo(DEFAULT_THREE_RELEVANCE);
        assertThat(testWeatherDataPoint.getFour()).isEqualTo(DEFAULT_FOUR);
        assertThat(testWeatherDataPoint.getFourRelevance()).isEqualTo(DEFAULT_FOUR_RELEVANCE);
        assertThat(testWeatherDataPoint.getFive()).isEqualTo(DEFAULT_FIVE);
        assertThat(testWeatherDataPoint.getFiveRelevance()).isEqualTo(DEFAULT_FIVE_RELEVANCE);
        assertThat(testWeatherDataPoint.getSix()).isEqualTo(DEFAULT_SIX);
        assertThat(testWeatherDataPoint.getSixRelevance()).isEqualTo(DEFAULT_SIX_RELEVANCE);
        assertThat(testWeatherDataPoint.getSeven()).isEqualTo(DEFAULT_SEVEN);
        assertThat(testWeatherDataPoint.getSevenRelevance()).isEqualTo(DEFAULT_SEVEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEight()).isEqualTo(DEFAULT_EIGHT);
        assertThat(testWeatherDataPoint.getEightRelevance()).isEqualTo(DEFAULT_EIGHT_RELEVANCE);
        assertThat(testWeatherDataPoint.getNine()).isEqualTo(DEFAULT_NINE);
        assertThat(testWeatherDataPoint.getNineRelevance()).isEqualTo(DEFAULT_NINE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testWeatherDataPoint.getTenRelevance()).isEqualTo(DEFAULT_TEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEleven()).isEqualTo(DEFAULT_ELEVEN);
        assertThat(testWeatherDataPoint.getElevenRelevance()).isEqualTo(DEFAULT_ELEVEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwelve()).isEqualTo(DEFAULT_TWELVE);
        assertThat(testWeatherDataPoint.getTwelveRelevance()).isEqualTo(DEFAULT_TWELVE_RELEVANCE);
        assertThat(testWeatherDataPoint.getThirteen()).isEqualTo(DEFAULT_THIRTEEN);
        assertThat(testWeatherDataPoint.getThirteenRelevance()).isEqualTo(DEFAULT_THIRTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getFourteen()).isEqualTo(DEFAULT_FOURTEEN);
        assertThat(testWeatherDataPoint.getFourteenRelevance()).isEqualTo(DEFAULT_FOURTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getFifteen()).isEqualTo(DEFAULT_FIFTEEN);
        assertThat(testWeatherDataPoint.getFifteenRelevance()).isEqualTo(DEFAULT_FIFTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getSixteen()).isEqualTo(DEFAULT_SIXTEEN);
        assertThat(testWeatherDataPoint.getSixteenRelevance()).isEqualTo(DEFAULT_SIXTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getSeventeen()).isEqualTo(DEFAULT_SEVENTEEN);
        assertThat(testWeatherDataPoint.getSeventeenRelevance()).isEqualTo(DEFAULT_SEVENTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEighteen()).isEqualTo(DEFAULT_EIGHTEEN);
        assertThat(testWeatherDataPoint.getEighteenRelevance()).isEqualTo(DEFAULT_EIGHTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getNineteen()).isEqualTo(DEFAULT_NINETEEN);
        assertThat(testWeatherDataPoint.getNineteenRelevance()).isEqualTo(DEFAULT_NINETEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwenty()).isEqualTo(DEFAULT_TWENTY);
        assertThat(testWeatherDataPoint.getTwentyRelevance()).isEqualTo(DEFAULT_TWENTY_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyOne()).isEqualTo(DEFAULT_TWENTY_ONE);
        assertThat(testWeatherDataPoint.getTwentyOneRelevance()).isEqualTo(DEFAULT_TWENTY_ONE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyTwo()).isEqualTo(DEFAULT_TWENTY_TWO);
        assertThat(testWeatherDataPoint.getTwentyTwoRelevance()).isEqualTo(DEFAULT_TWENTY_TWO_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyThree()).isEqualTo(DEFAULT_TWENTY_THREE);
    }

    @Test
    @Transactional
    void createWeatherDataPointWithExistingId() throws Exception {
        // Create the WeatherDataPoint with an existing ID
        weatherDataPoint.setId(1L);

        int databaseSizeBeforeCreate = weatherDataPointRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWeatherDataPointMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(weatherDataPoint))
            )
            .andExpect(status().isBadRequest());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWeatherDataPoints() throws Exception {
        // Initialize the database
        weatherDataPointRepository.saveAndFlush(weatherDataPoint);

        // Get all the weatherDataPointList
        restWeatherDataPointMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(weatherDataPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].property").value(hasItem(DEFAULT_PROPERTY.toString())))
            .andExpect(jsonPath("$.[*].midNight").value(hasItem(DEFAULT_MID_NIGHT)))
            .andExpect(jsonPath("$.[*].migNightRelevance").value(hasItem(DEFAULT_MIG_NIGHT_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].one").value(hasItem(DEFAULT_ONE)))
            .andExpect(jsonPath("$.[*].oneRelevance").value(hasItem(DEFAULT_ONE_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].two").value(hasItem(DEFAULT_TWO)))
            .andExpect(jsonPath("$.[*].twoRelevance").value(hasItem(DEFAULT_TWO_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].three").value(hasItem(DEFAULT_THREE)))
            .andExpect(jsonPath("$.[*].threeRelevance").value(hasItem(DEFAULT_THREE_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].four").value(hasItem(DEFAULT_FOUR)))
            .andExpect(jsonPath("$.[*].fourRelevance").value(hasItem(DEFAULT_FOUR_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].five").value(hasItem(DEFAULT_FIVE)))
            .andExpect(jsonPath("$.[*].fiveRelevance").value(hasItem(DEFAULT_FIVE_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].six").value(hasItem(DEFAULT_SIX)))
            .andExpect(jsonPath("$.[*].sixRelevance").value(hasItem(DEFAULT_SIX_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].seven").value(hasItem(DEFAULT_SEVEN)))
            .andExpect(jsonPath("$.[*].sevenRelevance").value(hasItem(DEFAULT_SEVEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].eight").value(hasItem(DEFAULT_EIGHT)))
            .andExpect(jsonPath("$.[*].eightRelevance").value(hasItem(DEFAULT_EIGHT_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].nine").value(hasItem(DEFAULT_NINE)))
            .andExpect(jsonPath("$.[*].nineRelevance").value(hasItem(DEFAULT_NINE_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].tenRelevance").value(hasItem(DEFAULT_TEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].eleven").value(hasItem(DEFAULT_ELEVEN)))
            .andExpect(jsonPath("$.[*].elevenRelevance").value(hasItem(DEFAULT_ELEVEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].twelve").value(hasItem(DEFAULT_TWELVE)))
            .andExpect(jsonPath("$.[*].twelveRelevance").value(hasItem(DEFAULT_TWELVE_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].thirteen").value(hasItem(DEFAULT_THIRTEEN)))
            .andExpect(jsonPath("$.[*].thirteenRelevance").value(hasItem(DEFAULT_THIRTEEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].fourteen").value(hasItem(DEFAULT_FOURTEEN)))
            .andExpect(jsonPath("$.[*].fourteenRelevance").value(hasItem(DEFAULT_FOURTEEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].fifteen").value(hasItem(DEFAULT_FIFTEEN)))
            .andExpect(jsonPath("$.[*].fifteenRelevance").value(hasItem(DEFAULT_FIFTEEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].sixteen").value(hasItem(DEFAULT_SIXTEEN)))
            .andExpect(jsonPath("$.[*].sixteenRelevance").value(hasItem(DEFAULT_SIXTEEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].seventeen").value(hasItem(DEFAULT_SEVENTEEN)))
            .andExpect(jsonPath("$.[*].seventeenRelevance").value(hasItem(DEFAULT_SEVENTEEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].eighteen").value(hasItem(DEFAULT_EIGHTEEN)))
            .andExpect(jsonPath("$.[*].eighteenRelevance").value(hasItem(DEFAULT_EIGHTEEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].nineteen").value(hasItem(DEFAULT_NINETEEN)))
            .andExpect(jsonPath("$.[*].nineteenRelevance").value(hasItem(DEFAULT_NINETEEN_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].twenty").value(hasItem(DEFAULT_TWENTY)))
            .andExpect(jsonPath("$.[*].twentyRelevance").value(hasItem(DEFAULT_TWENTY_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].twentyOne").value(hasItem(DEFAULT_TWENTY_ONE)))
            .andExpect(jsonPath("$.[*].twentyOneRelevance").value(hasItem(DEFAULT_TWENTY_ONE_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].twentyTwo").value(hasItem(DEFAULT_TWENTY_TWO)))
            .andExpect(jsonPath("$.[*].twentyTwoRelevance").value(hasItem(DEFAULT_TWENTY_TWO_RELEVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].twentyThree").value(hasItem(DEFAULT_TWENTY_THREE)));
    }

    @Test
    @Transactional
    void getWeatherDataPoint() throws Exception {
        // Initialize the database
        weatherDataPointRepository.saveAndFlush(weatherDataPoint);

        // Get the weatherDataPoint
        restWeatherDataPointMockMvc
            .perform(get(ENTITY_API_URL_ID, weatherDataPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(weatherDataPoint.getId().intValue()))
            .andExpect(jsonPath("$.property").value(DEFAULT_PROPERTY.toString()))
            .andExpect(jsonPath("$.midNight").value(DEFAULT_MID_NIGHT))
            .andExpect(jsonPath("$.migNightRelevance").value(DEFAULT_MIG_NIGHT_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.one").value(DEFAULT_ONE))
            .andExpect(jsonPath("$.oneRelevance").value(DEFAULT_ONE_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.two").value(DEFAULT_TWO))
            .andExpect(jsonPath("$.twoRelevance").value(DEFAULT_TWO_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.three").value(DEFAULT_THREE))
            .andExpect(jsonPath("$.threeRelevance").value(DEFAULT_THREE_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.four").value(DEFAULT_FOUR))
            .andExpect(jsonPath("$.fourRelevance").value(DEFAULT_FOUR_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.five").value(DEFAULT_FIVE))
            .andExpect(jsonPath("$.fiveRelevance").value(DEFAULT_FIVE_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.six").value(DEFAULT_SIX))
            .andExpect(jsonPath("$.sixRelevance").value(DEFAULT_SIX_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.seven").value(DEFAULT_SEVEN))
            .andExpect(jsonPath("$.sevenRelevance").value(DEFAULT_SEVEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.eight").value(DEFAULT_EIGHT))
            .andExpect(jsonPath("$.eightRelevance").value(DEFAULT_EIGHT_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.nine").value(DEFAULT_NINE))
            .andExpect(jsonPath("$.nineRelevance").value(DEFAULT_NINE_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.tenRelevance").value(DEFAULT_TEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.eleven").value(DEFAULT_ELEVEN))
            .andExpect(jsonPath("$.elevenRelevance").value(DEFAULT_ELEVEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.twelve").value(DEFAULT_TWELVE))
            .andExpect(jsonPath("$.twelveRelevance").value(DEFAULT_TWELVE_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.thirteen").value(DEFAULT_THIRTEEN))
            .andExpect(jsonPath("$.thirteenRelevance").value(DEFAULT_THIRTEEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.fourteen").value(DEFAULT_FOURTEEN))
            .andExpect(jsonPath("$.fourteenRelevance").value(DEFAULT_FOURTEEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.fifteen").value(DEFAULT_FIFTEEN))
            .andExpect(jsonPath("$.fifteenRelevance").value(DEFAULT_FIFTEEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.sixteen").value(DEFAULT_SIXTEEN))
            .andExpect(jsonPath("$.sixteenRelevance").value(DEFAULT_SIXTEEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.seventeen").value(DEFAULT_SEVENTEEN))
            .andExpect(jsonPath("$.seventeenRelevance").value(DEFAULT_SEVENTEEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.eighteen").value(DEFAULT_EIGHTEEN))
            .andExpect(jsonPath("$.eighteenRelevance").value(DEFAULT_EIGHTEEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.nineteen").value(DEFAULT_NINETEEN))
            .andExpect(jsonPath("$.nineteenRelevance").value(DEFAULT_NINETEEN_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.twenty").value(DEFAULT_TWENTY))
            .andExpect(jsonPath("$.twentyRelevance").value(DEFAULT_TWENTY_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.twentyOne").value(DEFAULT_TWENTY_ONE))
            .andExpect(jsonPath("$.twentyOneRelevance").value(DEFAULT_TWENTY_ONE_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.twentyTwo").value(DEFAULT_TWENTY_TWO))
            .andExpect(jsonPath("$.twentyTwoRelevance").value(DEFAULT_TWENTY_TWO_RELEVANCE.booleanValue()))
            .andExpect(jsonPath("$.twentyThree").value(DEFAULT_TWENTY_THREE));
    }

    @Test
    @Transactional
    void getNonExistingWeatherDataPoint() throws Exception {
        // Get the weatherDataPoint
        restWeatherDataPointMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWeatherDataPoint() throws Exception {
        // Initialize the database
        weatherDataPointRepository.saveAndFlush(weatherDataPoint);

        int databaseSizeBeforeUpdate = weatherDataPointRepository.findAll().size();

        // Update the weatherDataPoint
        WeatherDataPoint updatedWeatherDataPoint = weatherDataPointRepository.findById(weatherDataPoint.getId()).get();
        // Disconnect from session so that the updates on updatedWeatherDataPoint are not directly saved in db
        em.detach(updatedWeatherDataPoint);
        updatedWeatherDataPoint
            .property(UPDATED_PROPERTY)
            .midNight(UPDATED_MID_NIGHT)
            .migNightRelevance(UPDATED_MIG_NIGHT_RELEVANCE)
            .one(UPDATED_ONE)
            .oneRelevance(UPDATED_ONE_RELEVANCE)
            .two(UPDATED_TWO)
            .twoRelevance(UPDATED_TWO_RELEVANCE)
            .three(UPDATED_THREE)
            .threeRelevance(UPDATED_THREE_RELEVANCE)
            .four(UPDATED_FOUR)
            .fourRelevance(UPDATED_FOUR_RELEVANCE)
            .five(UPDATED_FIVE)
            .fiveRelevance(UPDATED_FIVE_RELEVANCE)
            .six(UPDATED_SIX)
            .sixRelevance(UPDATED_SIX_RELEVANCE)
            .seven(UPDATED_SEVEN)
            .sevenRelevance(UPDATED_SEVEN_RELEVANCE)
            .eight(UPDATED_EIGHT)
            .eightRelevance(UPDATED_EIGHT_RELEVANCE)
            .nine(UPDATED_NINE)
            .nineRelevance(UPDATED_NINE_RELEVANCE)
            .ten(UPDATED_TEN)
            .tenRelevance(UPDATED_TEN_RELEVANCE)
            .eleven(UPDATED_ELEVEN)
            .elevenRelevance(UPDATED_ELEVEN_RELEVANCE)
            .twelve(UPDATED_TWELVE)
            .twelveRelevance(UPDATED_TWELVE_RELEVANCE)
            .thirteen(UPDATED_THIRTEEN)
            .thirteenRelevance(UPDATED_THIRTEEN_RELEVANCE)
            .fourteen(UPDATED_FOURTEEN)
            .fourteenRelevance(UPDATED_FOURTEEN_RELEVANCE)
            .fifteen(UPDATED_FIFTEEN)
            .fifteenRelevance(UPDATED_FIFTEEN_RELEVANCE)
            .sixteen(UPDATED_SIXTEEN)
            .sixteenRelevance(UPDATED_SIXTEEN_RELEVANCE)
            .seventeen(UPDATED_SEVENTEEN)
            .seventeenRelevance(UPDATED_SEVENTEEN_RELEVANCE)
            .eighteen(UPDATED_EIGHTEEN)
            .eighteenRelevance(UPDATED_EIGHTEEN_RELEVANCE)
            .nineteen(UPDATED_NINETEEN)
            .nineteenRelevance(UPDATED_NINETEEN_RELEVANCE)
            .twenty(UPDATED_TWENTY)
            .twentyRelevance(UPDATED_TWENTY_RELEVANCE)
            .twentyOne(UPDATED_TWENTY_ONE)
            .twentyOneRelevance(UPDATED_TWENTY_ONE_RELEVANCE)
            .twentyTwo(UPDATED_TWENTY_TWO)
            .twentyTwoRelevance(UPDATED_TWENTY_TWO_RELEVANCE)
            .twentyThree(UPDATED_TWENTY_THREE);

        restWeatherDataPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedWeatherDataPoint.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedWeatherDataPoint))
            )
            .andExpect(status().isOk());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeUpdate);
        WeatherDataPoint testWeatherDataPoint = weatherDataPointList.get(weatherDataPointList.size() - 1);
        assertThat(testWeatherDataPoint.getProperty()).isEqualTo(UPDATED_PROPERTY);
        assertThat(testWeatherDataPoint.getMidNight()).isEqualTo(UPDATED_MID_NIGHT);
        assertThat(testWeatherDataPoint.getMigNightRelevance()).isEqualTo(UPDATED_MIG_NIGHT_RELEVANCE);
        assertThat(testWeatherDataPoint.getOne()).isEqualTo(UPDATED_ONE);
        assertThat(testWeatherDataPoint.getOneRelevance()).isEqualTo(UPDATED_ONE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwo()).isEqualTo(UPDATED_TWO);
        assertThat(testWeatherDataPoint.getTwoRelevance()).isEqualTo(UPDATED_TWO_RELEVANCE);
        assertThat(testWeatherDataPoint.getThree()).isEqualTo(UPDATED_THREE);
        assertThat(testWeatherDataPoint.getThreeRelevance()).isEqualTo(UPDATED_THREE_RELEVANCE);
        assertThat(testWeatherDataPoint.getFour()).isEqualTo(UPDATED_FOUR);
        assertThat(testWeatherDataPoint.getFourRelevance()).isEqualTo(UPDATED_FOUR_RELEVANCE);
        assertThat(testWeatherDataPoint.getFive()).isEqualTo(UPDATED_FIVE);
        assertThat(testWeatherDataPoint.getFiveRelevance()).isEqualTo(UPDATED_FIVE_RELEVANCE);
        assertThat(testWeatherDataPoint.getSix()).isEqualTo(UPDATED_SIX);
        assertThat(testWeatherDataPoint.getSixRelevance()).isEqualTo(UPDATED_SIX_RELEVANCE);
        assertThat(testWeatherDataPoint.getSeven()).isEqualTo(UPDATED_SEVEN);
        assertThat(testWeatherDataPoint.getSevenRelevance()).isEqualTo(UPDATED_SEVEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEight()).isEqualTo(UPDATED_EIGHT);
        assertThat(testWeatherDataPoint.getEightRelevance()).isEqualTo(UPDATED_EIGHT_RELEVANCE);
        assertThat(testWeatherDataPoint.getNine()).isEqualTo(UPDATED_NINE);
        assertThat(testWeatherDataPoint.getNineRelevance()).isEqualTo(UPDATED_NINE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testWeatherDataPoint.getTenRelevance()).isEqualTo(UPDATED_TEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEleven()).isEqualTo(UPDATED_ELEVEN);
        assertThat(testWeatherDataPoint.getElevenRelevance()).isEqualTo(UPDATED_ELEVEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwelve()).isEqualTo(UPDATED_TWELVE);
        assertThat(testWeatherDataPoint.getTwelveRelevance()).isEqualTo(UPDATED_TWELVE_RELEVANCE);
        assertThat(testWeatherDataPoint.getThirteen()).isEqualTo(UPDATED_THIRTEEN);
        assertThat(testWeatherDataPoint.getThirteenRelevance()).isEqualTo(UPDATED_THIRTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getFourteen()).isEqualTo(UPDATED_FOURTEEN);
        assertThat(testWeatherDataPoint.getFourteenRelevance()).isEqualTo(UPDATED_FOURTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getFifteen()).isEqualTo(UPDATED_FIFTEEN);
        assertThat(testWeatherDataPoint.getFifteenRelevance()).isEqualTo(UPDATED_FIFTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getSixteen()).isEqualTo(UPDATED_SIXTEEN);
        assertThat(testWeatherDataPoint.getSixteenRelevance()).isEqualTo(UPDATED_SIXTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getSeventeen()).isEqualTo(UPDATED_SEVENTEEN);
        assertThat(testWeatherDataPoint.getSeventeenRelevance()).isEqualTo(UPDATED_SEVENTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEighteen()).isEqualTo(UPDATED_EIGHTEEN);
        assertThat(testWeatherDataPoint.getEighteenRelevance()).isEqualTo(UPDATED_EIGHTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getNineteen()).isEqualTo(UPDATED_NINETEEN);
        assertThat(testWeatherDataPoint.getNineteenRelevance()).isEqualTo(UPDATED_NINETEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwenty()).isEqualTo(UPDATED_TWENTY);
        assertThat(testWeatherDataPoint.getTwentyRelevance()).isEqualTo(UPDATED_TWENTY_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyOne()).isEqualTo(UPDATED_TWENTY_ONE);
        assertThat(testWeatherDataPoint.getTwentyOneRelevance()).isEqualTo(UPDATED_TWENTY_ONE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyTwo()).isEqualTo(UPDATED_TWENTY_TWO);
        assertThat(testWeatherDataPoint.getTwentyTwoRelevance()).isEqualTo(UPDATED_TWENTY_TWO_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyThree()).isEqualTo(UPDATED_TWENTY_THREE);
    }

    @Test
    @Transactional
    void putNonExistingWeatherDataPoint() throws Exception {
        int databaseSizeBeforeUpdate = weatherDataPointRepository.findAll().size();
        weatherDataPoint.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWeatherDataPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, weatherDataPoint.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(weatherDataPoint))
            )
            .andExpect(status().isBadRequest());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWeatherDataPoint() throws Exception {
        int databaseSizeBeforeUpdate = weatherDataPointRepository.findAll().size();
        weatherDataPoint.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWeatherDataPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(weatherDataPoint))
            )
            .andExpect(status().isBadRequest());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWeatherDataPoint() throws Exception {
        int databaseSizeBeforeUpdate = weatherDataPointRepository.findAll().size();
        weatherDataPoint.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWeatherDataPointMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(weatherDataPoint))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWeatherDataPointWithPatch() throws Exception {
        // Initialize the database
        weatherDataPointRepository.saveAndFlush(weatherDataPoint);

        int databaseSizeBeforeUpdate = weatherDataPointRepository.findAll().size();

        // Update the weatherDataPoint using partial update
        WeatherDataPoint partialUpdatedWeatherDataPoint = new WeatherDataPoint();
        partialUpdatedWeatherDataPoint.setId(weatherDataPoint.getId());

        partialUpdatedWeatherDataPoint
            .two(UPDATED_TWO)
            .twoRelevance(UPDATED_TWO_RELEVANCE)
            .three(UPDATED_THREE)
            .threeRelevance(UPDATED_THREE_RELEVANCE)
            .four(UPDATED_FOUR)
            .fiveRelevance(UPDATED_FIVE_RELEVANCE)
            .sixRelevance(UPDATED_SIX_RELEVANCE)
            .seven(UPDATED_SEVEN)
            .sevenRelevance(UPDATED_SEVEN_RELEVANCE)
            .eight(UPDATED_EIGHT)
            .nineRelevance(UPDATED_NINE_RELEVANCE)
            .tenRelevance(UPDATED_TEN_RELEVANCE)
            .twelve(UPDATED_TWELVE)
            .twelveRelevance(UPDATED_TWELVE_RELEVANCE)
            .thirteen(UPDATED_THIRTEEN)
            .fourteen(UPDATED_FOURTEEN)
            .fifteenRelevance(UPDATED_FIFTEEN_RELEVANCE)
            .seventeenRelevance(UPDATED_SEVENTEEN_RELEVANCE)
            .eighteen(UPDATED_EIGHTEEN)
            .nineteenRelevance(UPDATED_NINETEEN_RELEVANCE)
            .twentyOneRelevance(UPDATED_TWENTY_ONE_RELEVANCE)
            .twentyTwo(UPDATED_TWENTY_TWO)
            .twentyTwoRelevance(UPDATED_TWENTY_TWO_RELEVANCE)
            .twentyThree(UPDATED_TWENTY_THREE);

        restWeatherDataPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWeatherDataPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWeatherDataPoint))
            )
            .andExpect(status().isOk());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeUpdate);
        WeatherDataPoint testWeatherDataPoint = weatherDataPointList.get(weatherDataPointList.size() - 1);
        assertThat(testWeatherDataPoint.getProperty()).isEqualTo(DEFAULT_PROPERTY);
        assertThat(testWeatherDataPoint.getMidNight()).isEqualTo(DEFAULT_MID_NIGHT);
        assertThat(testWeatherDataPoint.getMigNightRelevance()).isEqualTo(DEFAULT_MIG_NIGHT_RELEVANCE);
        assertThat(testWeatherDataPoint.getOne()).isEqualTo(DEFAULT_ONE);
        assertThat(testWeatherDataPoint.getOneRelevance()).isEqualTo(DEFAULT_ONE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwo()).isEqualTo(UPDATED_TWO);
        assertThat(testWeatherDataPoint.getTwoRelevance()).isEqualTo(UPDATED_TWO_RELEVANCE);
        assertThat(testWeatherDataPoint.getThree()).isEqualTo(UPDATED_THREE);
        assertThat(testWeatherDataPoint.getThreeRelevance()).isEqualTo(UPDATED_THREE_RELEVANCE);
        assertThat(testWeatherDataPoint.getFour()).isEqualTo(UPDATED_FOUR);
        assertThat(testWeatherDataPoint.getFourRelevance()).isEqualTo(DEFAULT_FOUR_RELEVANCE);
        assertThat(testWeatherDataPoint.getFive()).isEqualTo(DEFAULT_FIVE);
        assertThat(testWeatherDataPoint.getFiveRelevance()).isEqualTo(UPDATED_FIVE_RELEVANCE);
        assertThat(testWeatherDataPoint.getSix()).isEqualTo(DEFAULT_SIX);
        assertThat(testWeatherDataPoint.getSixRelevance()).isEqualTo(UPDATED_SIX_RELEVANCE);
        assertThat(testWeatherDataPoint.getSeven()).isEqualTo(UPDATED_SEVEN);
        assertThat(testWeatherDataPoint.getSevenRelevance()).isEqualTo(UPDATED_SEVEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEight()).isEqualTo(UPDATED_EIGHT);
        assertThat(testWeatherDataPoint.getEightRelevance()).isEqualTo(DEFAULT_EIGHT_RELEVANCE);
        assertThat(testWeatherDataPoint.getNine()).isEqualTo(DEFAULT_NINE);
        assertThat(testWeatherDataPoint.getNineRelevance()).isEqualTo(UPDATED_NINE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testWeatherDataPoint.getTenRelevance()).isEqualTo(UPDATED_TEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEleven()).isEqualTo(DEFAULT_ELEVEN);
        assertThat(testWeatherDataPoint.getElevenRelevance()).isEqualTo(DEFAULT_ELEVEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwelve()).isEqualTo(UPDATED_TWELVE);
        assertThat(testWeatherDataPoint.getTwelveRelevance()).isEqualTo(UPDATED_TWELVE_RELEVANCE);
        assertThat(testWeatherDataPoint.getThirteen()).isEqualTo(UPDATED_THIRTEEN);
        assertThat(testWeatherDataPoint.getThirteenRelevance()).isEqualTo(DEFAULT_THIRTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getFourteen()).isEqualTo(UPDATED_FOURTEEN);
        assertThat(testWeatherDataPoint.getFourteenRelevance()).isEqualTo(DEFAULT_FOURTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getFifteen()).isEqualTo(DEFAULT_FIFTEEN);
        assertThat(testWeatherDataPoint.getFifteenRelevance()).isEqualTo(UPDATED_FIFTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getSixteen()).isEqualTo(DEFAULT_SIXTEEN);
        assertThat(testWeatherDataPoint.getSixteenRelevance()).isEqualTo(DEFAULT_SIXTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getSeventeen()).isEqualTo(DEFAULT_SEVENTEEN);
        assertThat(testWeatherDataPoint.getSeventeenRelevance()).isEqualTo(UPDATED_SEVENTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEighteen()).isEqualTo(UPDATED_EIGHTEEN);
        assertThat(testWeatherDataPoint.getEighteenRelevance()).isEqualTo(DEFAULT_EIGHTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getNineteen()).isEqualTo(DEFAULT_NINETEEN);
        assertThat(testWeatherDataPoint.getNineteenRelevance()).isEqualTo(UPDATED_NINETEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwenty()).isEqualTo(DEFAULT_TWENTY);
        assertThat(testWeatherDataPoint.getTwentyRelevance()).isEqualTo(DEFAULT_TWENTY_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyOne()).isEqualTo(DEFAULT_TWENTY_ONE);
        assertThat(testWeatherDataPoint.getTwentyOneRelevance()).isEqualTo(UPDATED_TWENTY_ONE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyTwo()).isEqualTo(UPDATED_TWENTY_TWO);
        assertThat(testWeatherDataPoint.getTwentyTwoRelevance()).isEqualTo(UPDATED_TWENTY_TWO_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyThree()).isEqualTo(UPDATED_TWENTY_THREE);
    }

    @Test
    @Transactional
    void fullUpdateWeatherDataPointWithPatch() throws Exception {
        // Initialize the database
        weatherDataPointRepository.saveAndFlush(weatherDataPoint);

        int databaseSizeBeforeUpdate = weatherDataPointRepository.findAll().size();

        // Update the weatherDataPoint using partial update
        WeatherDataPoint partialUpdatedWeatherDataPoint = new WeatherDataPoint();
        partialUpdatedWeatherDataPoint.setId(weatherDataPoint.getId());

        partialUpdatedWeatherDataPoint
            .property(UPDATED_PROPERTY)
            .midNight(UPDATED_MID_NIGHT)
            .migNightRelevance(UPDATED_MIG_NIGHT_RELEVANCE)
            .one(UPDATED_ONE)
            .oneRelevance(UPDATED_ONE_RELEVANCE)
            .two(UPDATED_TWO)
            .twoRelevance(UPDATED_TWO_RELEVANCE)
            .three(UPDATED_THREE)
            .threeRelevance(UPDATED_THREE_RELEVANCE)
            .four(UPDATED_FOUR)
            .fourRelevance(UPDATED_FOUR_RELEVANCE)
            .five(UPDATED_FIVE)
            .fiveRelevance(UPDATED_FIVE_RELEVANCE)
            .six(UPDATED_SIX)
            .sixRelevance(UPDATED_SIX_RELEVANCE)
            .seven(UPDATED_SEVEN)
            .sevenRelevance(UPDATED_SEVEN_RELEVANCE)
            .eight(UPDATED_EIGHT)
            .eightRelevance(UPDATED_EIGHT_RELEVANCE)
            .nine(UPDATED_NINE)
            .nineRelevance(UPDATED_NINE_RELEVANCE)
            .ten(UPDATED_TEN)
            .tenRelevance(UPDATED_TEN_RELEVANCE)
            .eleven(UPDATED_ELEVEN)
            .elevenRelevance(UPDATED_ELEVEN_RELEVANCE)
            .twelve(UPDATED_TWELVE)
            .twelveRelevance(UPDATED_TWELVE_RELEVANCE)
            .thirteen(UPDATED_THIRTEEN)
            .thirteenRelevance(UPDATED_THIRTEEN_RELEVANCE)
            .fourteen(UPDATED_FOURTEEN)
            .fourteenRelevance(UPDATED_FOURTEEN_RELEVANCE)
            .fifteen(UPDATED_FIFTEEN)
            .fifteenRelevance(UPDATED_FIFTEEN_RELEVANCE)
            .sixteen(UPDATED_SIXTEEN)
            .sixteenRelevance(UPDATED_SIXTEEN_RELEVANCE)
            .seventeen(UPDATED_SEVENTEEN)
            .seventeenRelevance(UPDATED_SEVENTEEN_RELEVANCE)
            .eighteen(UPDATED_EIGHTEEN)
            .eighteenRelevance(UPDATED_EIGHTEEN_RELEVANCE)
            .nineteen(UPDATED_NINETEEN)
            .nineteenRelevance(UPDATED_NINETEEN_RELEVANCE)
            .twenty(UPDATED_TWENTY)
            .twentyRelevance(UPDATED_TWENTY_RELEVANCE)
            .twentyOne(UPDATED_TWENTY_ONE)
            .twentyOneRelevance(UPDATED_TWENTY_ONE_RELEVANCE)
            .twentyTwo(UPDATED_TWENTY_TWO)
            .twentyTwoRelevance(UPDATED_TWENTY_TWO_RELEVANCE)
            .twentyThree(UPDATED_TWENTY_THREE);

        restWeatherDataPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWeatherDataPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWeatherDataPoint))
            )
            .andExpect(status().isOk());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeUpdate);
        WeatherDataPoint testWeatherDataPoint = weatherDataPointList.get(weatherDataPointList.size() - 1);
        assertThat(testWeatherDataPoint.getProperty()).isEqualTo(UPDATED_PROPERTY);
        assertThat(testWeatherDataPoint.getMidNight()).isEqualTo(UPDATED_MID_NIGHT);
        assertThat(testWeatherDataPoint.getMigNightRelevance()).isEqualTo(UPDATED_MIG_NIGHT_RELEVANCE);
        assertThat(testWeatherDataPoint.getOne()).isEqualTo(UPDATED_ONE);
        assertThat(testWeatherDataPoint.getOneRelevance()).isEqualTo(UPDATED_ONE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwo()).isEqualTo(UPDATED_TWO);
        assertThat(testWeatherDataPoint.getTwoRelevance()).isEqualTo(UPDATED_TWO_RELEVANCE);
        assertThat(testWeatherDataPoint.getThree()).isEqualTo(UPDATED_THREE);
        assertThat(testWeatherDataPoint.getThreeRelevance()).isEqualTo(UPDATED_THREE_RELEVANCE);
        assertThat(testWeatherDataPoint.getFour()).isEqualTo(UPDATED_FOUR);
        assertThat(testWeatherDataPoint.getFourRelevance()).isEqualTo(UPDATED_FOUR_RELEVANCE);
        assertThat(testWeatherDataPoint.getFive()).isEqualTo(UPDATED_FIVE);
        assertThat(testWeatherDataPoint.getFiveRelevance()).isEqualTo(UPDATED_FIVE_RELEVANCE);
        assertThat(testWeatherDataPoint.getSix()).isEqualTo(UPDATED_SIX);
        assertThat(testWeatherDataPoint.getSixRelevance()).isEqualTo(UPDATED_SIX_RELEVANCE);
        assertThat(testWeatherDataPoint.getSeven()).isEqualTo(UPDATED_SEVEN);
        assertThat(testWeatherDataPoint.getSevenRelevance()).isEqualTo(UPDATED_SEVEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEight()).isEqualTo(UPDATED_EIGHT);
        assertThat(testWeatherDataPoint.getEightRelevance()).isEqualTo(UPDATED_EIGHT_RELEVANCE);
        assertThat(testWeatherDataPoint.getNine()).isEqualTo(UPDATED_NINE);
        assertThat(testWeatherDataPoint.getNineRelevance()).isEqualTo(UPDATED_NINE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testWeatherDataPoint.getTenRelevance()).isEqualTo(UPDATED_TEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEleven()).isEqualTo(UPDATED_ELEVEN);
        assertThat(testWeatherDataPoint.getElevenRelevance()).isEqualTo(UPDATED_ELEVEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwelve()).isEqualTo(UPDATED_TWELVE);
        assertThat(testWeatherDataPoint.getTwelveRelevance()).isEqualTo(UPDATED_TWELVE_RELEVANCE);
        assertThat(testWeatherDataPoint.getThirteen()).isEqualTo(UPDATED_THIRTEEN);
        assertThat(testWeatherDataPoint.getThirteenRelevance()).isEqualTo(UPDATED_THIRTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getFourteen()).isEqualTo(UPDATED_FOURTEEN);
        assertThat(testWeatherDataPoint.getFourteenRelevance()).isEqualTo(UPDATED_FOURTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getFifteen()).isEqualTo(UPDATED_FIFTEEN);
        assertThat(testWeatherDataPoint.getFifteenRelevance()).isEqualTo(UPDATED_FIFTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getSixteen()).isEqualTo(UPDATED_SIXTEEN);
        assertThat(testWeatherDataPoint.getSixteenRelevance()).isEqualTo(UPDATED_SIXTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getSeventeen()).isEqualTo(UPDATED_SEVENTEEN);
        assertThat(testWeatherDataPoint.getSeventeenRelevance()).isEqualTo(UPDATED_SEVENTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getEighteen()).isEqualTo(UPDATED_EIGHTEEN);
        assertThat(testWeatherDataPoint.getEighteenRelevance()).isEqualTo(UPDATED_EIGHTEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getNineteen()).isEqualTo(UPDATED_NINETEEN);
        assertThat(testWeatherDataPoint.getNineteenRelevance()).isEqualTo(UPDATED_NINETEEN_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwenty()).isEqualTo(UPDATED_TWENTY);
        assertThat(testWeatherDataPoint.getTwentyRelevance()).isEqualTo(UPDATED_TWENTY_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyOne()).isEqualTo(UPDATED_TWENTY_ONE);
        assertThat(testWeatherDataPoint.getTwentyOneRelevance()).isEqualTo(UPDATED_TWENTY_ONE_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyTwo()).isEqualTo(UPDATED_TWENTY_TWO);
        assertThat(testWeatherDataPoint.getTwentyTwoRelevance()).isEqualTo(UPDATED_TWENTY_TWO_RELEVANCE);
        assertThat(testWeatherDataPoint.getTwentyThree()).isEqualTo(UPDATED_TWENTY_THREE);
    }

    @Test
    @Transactional
    void patchNonExistingWeatherDataPoint() throws Exception {
        int databaseSizeBeforeUpdate = weatherDataPointRepository.findAll().size();
        weatherDataPoint.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWeatherDataPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, weatherDataPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(weatherDataPoint))
            )
            .andExpect(status().isBadRequest());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWeatherDataPoint() throws Exception {
        int databaseSizeBeforeUpdate = weatherDataPointRepository.findAll().size();
        weatherDataPoint.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWeatherDataPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(weatherDataPoint))
            )
            .andExpect(status().isBadRequest());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWeatherDataPoint() throws Exception {
        int databaseSizeBeforeUpdate = weatherDataPointRepository.findAll().size();
        weatherDataPoint.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWeatherDataPointMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(weatherDataPoint))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WeatherDataPoint in the database
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWeatherDataPoint() throws Exception {
        // Initialize the database
        weatherDataPointRepository.saveAndFlush(weatherDataPoint);

        int databaseSizeBeforeDelete = weatherDataPointRepository.findAll().size();

        // Delete the weatherDataPoint
        restWeatherDataPointMockMvc
            .perform(delete(ENTITY_API_URL_ID, weatherDataPoint.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WeatherDataPoint> weatherDataPointList = weatherDataPointRepository.findAll();
        assertThat(weatherDataPointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
