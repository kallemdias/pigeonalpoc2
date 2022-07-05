package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.RelevanceHeader;
import de.mellak.pigeonal.repository.RelevanceHeaderRepository;
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
 * Integration tests for the {@link RelevanceHeaderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RelevanceHeaderResourceIT {

    private static final Boolean DEFAULT_MIDNIGHT = false;
    private static final Boolean UPDATED_MIDNIGHT = true;

    private static final Boolean DEFAULT_ONE = false;
    private static final Boolean UPDATED_ONE = true;

    private static final Boolean DEFAULT_TWO = false;
    private static final Boolean UPDATED_TWO = true;

    private static final Boolean DEFAULT_THREE = false;
    private static final Boolean UPDATED_THREE = true;

    private static final Boolean DEFAULT_FOUR = false;
    private static final Boolean UPDATED_FOUR = true;

    private static final Boolean DEFAULT_FIVE = false;
    private static final Boolean UPDATED_FIVE = true;

    private static final Boolean DEFAULT_SIX = false;
    private static final Boolean UPDATED_SIX = true;

    private static final Boolean DEFAULT_SEVEN = false;
    private static final Boolean UPDATED_SEVEN = true;

    private static final Boolean DEFAULT_EIGHT = false;
    private static final Boolean UPDATED_EIGHT = true;

    private static final Boolean DEFAULT_NINE = false;
    private static final Boolean UPDATED_NINE = true;

    private static final Boolean DEFAULT_TEN = false;
    private static final Boolean UPDATED_TEN = true;

    private static final Boolean DEFAULT_ELEVEN = false;
    private static final Boolean UPDATED_ELEVEN = true;

    private static final Boolean DEFAULT_TWELVE = false;
    private static final Boolean UPDATED_TWELVE = true;

    private static final Boolean DEFAULT_THIRTEEN = false;
    private static final Boolean UPDATED_THIRTEEN = true;

    private static final Boolean DEFAULT_FOURTEEN = false;
    private static final Boolean UPDATED_FOURTEEN = true;

    private static final Boolean DEFAULT_FIFTEEN = false;
    private static final Boolean UPDATED_FIFTEEN = true;

    private static final Boolean DEFAULT_SIXTEEN = false;
    private static final Boolean UPDATED_SIXTEEN = true;

    private static final Boolean DEFAULT_SEVENTEEN = false;
    private static final Boolean UPDATED_SEVENTEEN = true;

    private static final Boolean DEFAULT_EIGHTEEN = false;
    private static final Boolean UPDATED_EIGHTEEN = true;

    private static final Boolean DEFAULT_NINETEEN = false;
    private static final Boolean UPDATED_NINETEEN = true;

    private static final Boolean DEFAULT_TWENTY = false;
    private static final Boolean UPDATED_TWENTY = true;

    private static final Boolean DEFAULT_TWENTY_ONE = false;
    private static final Boolean UPDATED_TWENTY_ONE = true;

    private static final Boolean DEFAULT_TWENTY_TWO = false;
    private static final Boolean UPDATED_TWENTY_TWO = true;

    private static final Boolean DEFAULT_TWENTY_THREE = false;
    private static final Boolean UPDATED_TWENTY_THREE = true;

    private static final String ENTITY_API_URL = "/api/relevance-headers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RelevanceHeaderRepository relevanceHeaderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelevanceHeaderMockMvc;

    private RelevanceHeader relevanceHeader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelevanceHeader createEntity(EntityManager em) {
        RelevanceHeader relevanceHeader = new RelevanceHeader()
            .midnight(DEFAULT_MIDNIGHT)
            .one(DEFAULT_ONE)
            .two(DEFAULT_TWO)
            .three(DEFAULT_THREE)
            .four(DEFAULT_FOUR)
            .five(DEFAULT_FIVE)
            .six(DEFAULT_SIX)
            .seven(DEFAULT_SEVEN)
            .eight(DEFAULT_EIGHT)
            .nine(DEFAULT_NINE)
            .ten(DEFAULT_TEN)
            .eleven(DEFAULT_ELEVEN)
            .twelve(DEFAULT_TWELVE)
            .thirteen(DEFAULT_THIRTEEN)
            .fourteen(DEFAULT_FOURTEEN)
            .fifteen(DEFAULT_FIFTEEN)
            .sixteen(DEFAULT_SIXTEEN)
            .seventeen(DEFAULT_SEVENTEEN)
            .eighteen(DEFAULT_EIGHTEEN)
            .nineteen(DEFAULT_NINETEEN)
            .twenty(DEFAULT_TWENTY)
            .twentyOne(DEFAULT_TWENTY_ONE)
            .twentyTwo(DEFAULT_TWENTY_TWO)
            .twentyThree(DEFAULT_TWENTY_THREE);
        return relevanceHeader;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelevanceHeader createUpdatedEntity(EntityManager em) {
        RelevanceHeader relevanceHeader = new RelevanceHeader()
            .midnight(UPDATED_MIDNIGHT)
            .one(UPDATED_ONE)
            .two(UPDATED_TWO)
            .three(UPDATED_THREE)
            .four(UPDATED_FOUR)
            .five(UPDATED_FIVE)
            .six(UPDATED_SIX)
            .seven(UPDATED_SEVEN)
            .eight(UPDATED_EIGHT)
            .nine(UPDATED_NINE)
            .ten(UPDATED_TEN)
            .eleven(UPDATED_ELEVEN)
            .twelve(UPDATED_TWELVE)
            .thirteen(UPDATED_THIRTEEN)
            .fourteen(UPDATED_FOURTEEN)
            .fifteen(UPDATED_FIFTEEN)
            .sixteen(UPDATED_SIXTEEN)
            .seventeen(UPDATED_SEVENTEEN)
            .eighteen(UPDATED_EIGHTEEN)
            .nineteen(UPDATED_NINETEEN)
            .twenty(UPDATED_TWENTY)
            .twentyOne(UPDATED_TWENTY_ONE)
            .twentyTwo(UPDATED_TWENTY_TWO)
            .twentyThree(UPDATED_TWENTY_THREE);
        return relevanceHeader;
    }

    @BeforeEach
    public void initTest() {
        relevanceHeader = createEntity(em);
    }

    @Test
    @Transactional
    void createRelevanceHeader() throws Exception {
        int databaseSizeBeforeCreate = relevanceHeaderRepository.findAll().size();
        // Create the RelevanceHeader
        restRelevanceHeaderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(relevanceHeader))
            )
            .andExpect(status().isCreated());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        RelevanceHeader testRelevanceHeader = relevanceHeaderList.get(relevanceHeaderList.size() - 1);
        assertThat(testRelevanceHeader.getMidnight()).isEqualTo(DEFAULT_MIDNIGHT);
        assertThat(testRelevanceHeader.getOne()).isEqualTo(DEFAULT_ONE);
        assertThat(testRelevanceHeader.getTwo()).isEqualTo(DEFAULT_TWO);
        assertThat(testRelevanceHeader.getThree()).isEqualTo(DEFAULT_THREE);
        assertThat(testRelevanceHeader.getFour()).isEqualTo(DEFAULT_FOUR);
        assertThat(testRelevanceHeader.getFive()).isEqualTo(DEFAULT_FIVE);
        assertThat(testRelevanceHeader.getSix()).isEqualTo(DEFAULT_SIX);
        assertThat(testRelevanceHeader.getSeven()).isEqualTo(DEFAULT_SEVEN);
        assertThat(testRelevanceHeader.getEight()).isEqualTo(DEFAULT_EIGHT);
        assertThat(testRelevanceHeader.getNine()).isEqualTo(DEFAULT_NINE);
        assertThat(testRelevanceHeader.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testRelevanceHeader.getEleven()).isEqualTo(DEFAULT_ELEVEN);
        assertThat(testRelevanceHeader.getTwelve()).isEqualTo(DEFAULT_TWELVE);
        assertThat(testRelevanceHeader.getThirteen()).isEqualTo(DEFAULT_THIRTEEN);
        assertThat(testRelevanceHeader.getFourteen()).isEqualTo(DEFAULT_FOURTEEN);
        assertThat(testRelevanceHeader.getFifteen()).isEqualTo(DEFAULT_FIFTEEN);
        assertThat(testRelevanceHeader.getSixteen()).isEqualTo(DEFAULT_SIXTEEN);
        assertThat(testRelevanceHeader.getSeventeen()).isEqualTo(DEFAULT_SEVENTEEN);
        assertThat(testRelevanceHeader.getEighteen()).isEqualTo(DEFAULT_EIGHTEEN);
        assertThat(testRelevanceHeader.getNineteen()).isEqualTo(DEFAULT_NINETEEN);
        assertThat(testRelevanceHeader.getTwenty()).isEqualTo(DEFAULT_TWENTY);
        assertThat(testRelevanceHeader.getTwentyOne()).isEqualTo(DEFAULT_TWENTY_ONE);
        assertThat(testRelevanceHeader.getTwentyTwo()).isEqualTo(DEFAULT_TWENTY_TWO);
        assertThat(testRelevanceHeader.getTwentyThree()).isEqualTo(DEFAULT_TWENTY_THREE);
    }

    @Test
    @Transactional
    void createRelevanceHeaderWithExistingId() throws Exception {
        // Create the RelevanceHeader with an existing ID
        relevanceHeader.setId(1L);

        int databaseSizeBeforeCreate = relevanceHeaderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelevanceHeaderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(relevanceHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRelevanceHeaders() throws Exception {
        // Initialize the database
        relevanceHeaderRepository.saveAndFlush(relevanceHeader);

        // Get all the relevanceHeaderList
        restRelevanceHeaderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relevanceHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].midnight").value(hasItem(DEFAULT_MIDNIGHT.booleanValue())))
            .andExpect(jsonPath("$.[*].one").value(hasItem(DEFAULT_ONE.booleanValue())))
            .andExpect(jsonPath("$.[*].two").value(hasItem(DEFAULT_TWO.booleanValue())))
            .andExpect(jsonPath("$.[*].three").value(hasItem(DEFAULT_THREE.booleanValue())))
            .andExpect(jsonPath("$.[*].four").value(hasItem(DEFAULT_FOUR.booleanValue())))
            .andExpect(jsonPath("$.[*].five").value(hasItem(DEFAULT_FIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].six").value(hasItem(DEFAULT_SIX.booleanValue())))
            .andExpect(jsonPath("$.[*].seven").value(hasItem(DEFAULT_SEVEN.booleanValue())))
            .andExpect(jsonPath("$.[*].eight").value(hasItem(DEFAULT_EIGHT.booleanValue())))
            .andExpect(jsonPath("$.[*].nine").value(hasItem(DEFAULT_NINE.booleanValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN.booleanValue())))
            .andExpect(jsonPath("$.[*].eleven").value(hasItem(DEFAULT_ELEVEN.booleanValue())))
            .andExpect(jsonPath("$.[*].twelve").value(hasItem(DEFAULT_TWELVE.booleanValue())))
            .andExpect(jsonPath("$.[*].thirteen").value(hasItem(DEFAULT_THIRTEEN.booleanValue())))
            .andExpect(jsonPath("$.[*].fourteen").value(hasItem(DEFAULT_FOURTEEN.booleanValue())))
            .andExpect(jsonPath("$.[*].fifteen").value(hasItem(DEFAULT_FIFTEEN.booleanValue())))
            .andExpect(jsonPath("$.[*].sixteen").value(hasItem(DEFAULT_SIXTEEN.booleanValue())))
            .andExpect(jsonPath("$.[*].seventeen").value(hasItem(DEFAULT_SEVENTEEN.booleanValue())))
            .andExpect(jsonPath("$.[*].eighteen").value(hasItem(DEFAULT_EIGHTEEN.booleanValue())))
            .andExpect(jsonPath("$.[*].nineteen").value(hasItem(DEFAULT_NINETEEN.booleanValue())))
            .andExpect(jsonPath("$.[*].twenty").value(hasItem(DEFAULT_TWENTY.booleanValue())))
            .andExpect(jsonPath("$.[*].twentyOne").value(hasItem(DEFAULT_TWENTY_ONE.booleanValue())))
            .andExpect(jsonPath("$.[*].twentyTwo").value(hasItem(DEFAULT_TWENTY_TWO.booleanValue())))
            .andExpect(jsonPath("$.[*].twentyThree").value(hasItem(DEFAULT_TWENTY_THREE.booleanValue())));
    }

    @Test
    @Transactional
    void getRelevanceHeader() throws Exception {
        // Initialize the database
        relevanceHeaderRepository.saveAndFlush(relevanceHeader);

        // Get the relevanceHeader
        restRelevanceHeaderMockMvc
            .perform(get(ENTITY_API_URL_ID, relevanceHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relevanceHeader.getId().intValue()))
            .andExpect(jsonPath("$.midnight").value(DEFAULT_MIDNIGHT.booleanValue()))
            .andExpect(jsonPath("$.one").value(DEFAULT_ONE.booleanValue()))
            .andExpect(jsonPath("$.two").value(DEFAULT_TWO.booleanValue()))
            .andExpect(jsonPath("$.three").value(DEFAULT_THREE.booleanValue()))
            .andExpect(jsonPath("$.four").value(DEFAULT_FOUR.booleanValue()))
            .andExpect(jsonPath("$.five").value(DEFAULT_FIVE.booleanValue()))
            .andExpect(jsonPath("$.six").value(DEFAULT_SIX.booleanValue()))
            .andExpect(jsonPath("$.seven").value(DEFAULT_SEVEN.booleanValue()))
            .andExpect(jsonPath("$.eight").value(DEFAULT_EIGHT.booleanValue()))
            .andExpect(jsonPath("$.nine").value(DEFAULT_NINE.booleanValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN.booleanValue()))
            .andExpect(jsonPath("$.eleven").value(DEFAULT_ELEVEN.booleanValue()))
            .andExpect(jsonPath("$.twelve").value(DEFAULT_TWELVE.booleanValue()))
            .andExpect(jsonPath("$.thirteen").value(DEFAULT_THIRTEEN.booleanValue()))
            .andExpect(jsonPath("$.fourteen").value(DEFAULT_FOURTEEN.booleanValue()))
            .andExpect(jsonPath("$.fifteen").value(DEFAULT_FIFTEEN.booleanValue()))
            .andExpect(jsonPath("$.sixteen").value(DEFAULT_SIXTEEN.booleanValue()))
            .andExpect(jsonPath("$.seventeen").value(DEFAULT_SEVENTEEN.booleanValue()))
            .andExpect(jsonPath("$.eighteen").value(DEFAULT_EIGHTEEN.booleanValue()))
            .andExpect(jsonPath("$.nineteen").value(DEFAULT_NINETEEN.booleanValue()))
            .andExpect(jsonPath("$.twenty").value(DEFAULT_TWENTY.booleanValue()))
            .andExpect(jsonPath("$.twentyOne").value(DEFAULT_TWENTY_ONE.booleanValue()))
            .andExpect(jsonPath("$.twentyTwo").value(DEFAULT_TWENTY_TWO.booleanValue()))
            .andExpect(jsonPath("$.twentyThree").value(DEFAULT_TWENTY_THREE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingRelevanceHeader() throws Exception {
        // Get the relevanceHeader
        restRelevanceHeaderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRelevanceHeader() throws Exception {
        // Initialize the database
        relevanceHeaderRepository.saveAndFlush(relevanceHeader);

        int databaseSizeBeforeUpdate = relevanceHeaderRepository.findAll().size();

        // Update the relevanceHeader
        RelevanceHeader updatedRelevanceHeader = relevanceHeaderRepository.findById(relevanceHeader.getId()).get();
        // Disconnect from session so that the updates on updatedRelevanceHeader are not directly saved in db
        em.detach(updatedRelevanceHeader);
        updatedRelevanceHeader
            .midnight(UPDATED_MIDNIGHT)
            .one(UPDATED_ONE)
            .two(UPDATED_TWO)
            .three(UPDATED_THREE)
            .four(UPDATED_FOUR)
            .five(UPDATED_FIVE)
            .six(UPDATED_SIX)
            .seven(UPDATED_SEVEN)
            .eight(UPDATED_EIGHT)
            .nine(UPDATED_NINE)
            .ten(UPDATED_TEN)
            .eleven(UPDATED_ELEVEN)
            .twelve(UPDATED_TWELVE)
            .thirteen(UPDATED_THIRTEEN)
            .fourteen(UPDATED_FOURTEEN)
            .fifteen(UPDATED_FIFTEEN)
            .sixteen(UPDATED_SIXTEEN)
            .seventeen(UPDATED_SEVENTEEN)
            .eighteen(UPDATED_EIGHTEEN)
            .nineteen(UPDATED_NINETEEN)
            .twenty(UPDATED_TWENTY)
            .twentyOne(UPDATED_TWENTY_ONE)
            .twentyTwo(UPDATED_TWENTY_TWO)
            .twentyThree(UPDATED_TWENTY_THREE);

        restRelevanceHeaderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRelevanceHeader.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRelevanceHeader))
            )
            .andExpect(status().isOk());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeUpdate);
        RelevanceHeader testRelevanceHeader = relevanceHeaderList.get(relevanceHeaderList.size() - 1);
        assertThat(testRelevanceHeader.getMidnight()).isEqualTo(UPDATED_MIDNIGHT);
        assertThat(testRelevanceHeader.getOne()).isEqualTo(UPDATED_ONE);
        assertThat(testRelevanceHeader.getTwo()).isEqualTo(UPDATED_TWO);
        assertThat(testRelevanceHeader.getThree()).isEqualTo(UPDATED_THREE);
        assertThat(testRelevanceHeader.getFour()).isEqualTo(UPDATED_FOUR);
        assertThat(testRelevanceHeader.getFive()).isEqualTo(UPDATED_FIVE);
        assertThat(testRelevanceHeader.getSix()).isEqualTo(UPDATED_SIX);
        assertThat(testRelevanceHeader.getSeven()).isEqualTo(UPDATED_SEVEN);
        assertThat(testRelevanceHeader.getEight()).isEqualTo(UPDATED_EIGHT);
        assertThat(testRelevanceHeader.getNine()).isEqualTo(UPDATED_NINE);
        assertThat(testRelevanceHeader.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testRelevanceHeader.getEleven()).isEqualTo(UPDATED_ELEVEN);
        assertThat(testRelevanceHeader.getTwelve()).isEqualTo(UPDATED_TWELVE);
        assertThat(testRelevanceHeader.getThirteen()).isEqualTo(UPDATED_THIRTEEN);
        assertThat(testRelevanceHeader.getFourteen()).isEqualTo(UPDATED_FOURTEEN);
        assertThat(testRelevanceHeader.getFifteen()).isEqualTo(UPDATED_FIFTEEN);
        assertThat(testRelevanceHeader.getSixteen()).isEqualTo(UPDATED_SIXTEEN);
        assertThat(testRelevanceHeader.getSeventeen()).isEqualTo(UPDATED_SEVENTEEN);
        assertThat(testRelevanceHeader.getEighteen()).isEqualTo(UPDATED_EIGHTEEN);
        assertThat(testRelevanceHeader.getNineteen()).isEqualTo(UPDATED_NINETEEN);
        assertThat(testRelevanceHeader.getTwenty()).isEqualTo(UPDATED_TWENTY);
        assertThat(testRelevanceHeader.getTwentyOne()).isEqualTo(UPDATED_TWENTY_ONE);
        assertThat(testRelevanceHeader.getTwentyTwo()).isEqualTo(UPDATED_TWENTY_TWO);
        assertThat(testRelevanceHeader.getTwentyThree()).isEqualTo(UPDATED_TWENTY_THREE);
    }

    @Test
    @Transactional
    void putNonExistingRelevanceHeader() throws Exception {
        int databaseSizeBeforeUpdate = relevanceHeaderRepository.findAll().size();
        relevanceHeader.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelevanceHeaderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, relevanceHeader.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relevanceHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRelevanceHeader() throws Exception {
        int databaseSizeBeforeUpdate = relevanceHeaderRepository.findAll().size();
        relevanceHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelevanceHeaderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relevanceHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRelevanceHeader() throws Exception {
        int databaseSizeBeforeUpdate = relevanceHeaderRepository.findAll().size();
        relevanceHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelevanceHeaderMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(relevanceHeader))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRelevanceHeaderWithPatch() throws Exception {
        // Initialize the database
        relevanceHeaderRepository.saveAndFlush(relevanceHeader);

        int databaseSizeBeforeUpdate = relevanceHeaderRepository.findAll().size();

        // Update the relevanceHeader using partial update
        RelevanceHeader partialUpdatedRelevanceHeader = new RelevanceHeader();
        partialUpdatedRelevanceHeader.setId(relevanceHeader.getId());

        partialUpdatedRelevanceHeader
            .midnight(UPDATED_MIDNIGHT)
            .three(UPDATED_THREE)
            .four(UPDATED_FOUR)
            .six(UPDATED_SIX)
            .seven(UPDATED_SEVEN)
            .nine(UPDATED_NINE)
            .twelve(UPDATED_TWELVE)
            .eighteen(UPDATED_EIGHTEEN)
            .twentyOne(UPDATED_TWENTY_ONE)
            .twentyTwo(UPDATED_TWENTY_TWO);

        restRelevanceHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelevanceHeader.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelevanceHeader))
            )
            .andExpect(status().isOk());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeUpdate);
        RelevanceHeader testRelevanceHeader = relevanceHeaderList.get(relevanceHeaderList.size() - 1);
        assertThat(testRelevanceHeader.getMidnight()).isEqualTo(UPDATED_MIDNIGHT);
        assertThat(testRelevanceHeader.getOne()).isEqualTo(DEFAULT_ONE);
        assertThat(testRelevanceHeader.getTwo()).isEqualTo(DEFAULT_TWO);
        assertThat(testRelevanceHeader.getThree()).isEqualTo(UPDATED_THREE);
        assertThat(testRelevanceHeader.getFour()).isEqualTo(UPDATED_FOUR);
        assertThat(testRelevanceHeader.getFive()).isEqualTo(DEFAULT_FIVE);
        assertThat(testRelevanceHeader.getSix()).isEqualTo(UPDATED_SIX);
        assertThat(testRelevanceHeader.getSeven()).isEqualTo(UPDATED_SEVEN);
        assertThat(testRelevanceHeader.getEight()).isEqualTo(DEFAULT_EIGHT);
        assertThat(testRelevanceHeader.getNine()).isEqualTo(UPDATED_NINE);
        assertThat(testRelevanceHeader.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testRelevanceHeader.getEleven()).isEqualTo(DEFAULT_ELEVEN);
        assertThat(testRelevanceHeader.getTwelve()).isEqualTo(UPDATED_TWELVE);
        assertThat(testRelevanceHeader.getThirteen()).isEqualTo(DEFAULT_THIRTEEN);
        assertThat(testRelevanceHeader.getFourteen()).isEqualTo(DEFAULT_FOURTEEN);
        assertThat(testRelevanceHeader.getFifteen()).isEqualTo(DEFAULT_FIFTEEN);
        assertThat(testRelevanceHeader.getSixteen()).isEqualTo(DEFAULT_SIXTEEN);
        assertThat(testRelevanceHeader.getSeventeen()).isEqualTo(DEFAULT_SEVENTEEN);
        assertThat(testRelevanceHeader.getEighteen()).isEqualTo(UPDATED_EIGHTEEN);
        assertThat(testRelevanceHeader.getNineteen()).isEqualTo(DEFAULT_NINETEEN);
        assertThat(testRelevanceHeader.getTwenty()).isEqualTo(DEFAULT_TWENTY);
        assertThat(testRelevanceHeader.getTwentyOne()).isEqualTo(UPDATED_TWENTY_ONE);
        assertThat(testRelevanceHeader.getTwentyTwo()).isEqualTo(UPDATED_TWENTY_TWO);
        assertThat(testRelevanceHeader.getTwentyThree()).isEqualTo(DEFAULT_TWENTY_THREE);
    }

    @Test
    @Transactional
    void fullUpdateRelevanceHeaderWithPatch() throws Exception {
        // Initialize the database
        relevanceHeaderRepository.saveAndFlush(relevanceHeader);

        int databaseSizeBeforeUpdate = relevanceHeaderRepository.findAll().size();

        // Update the relevanceHeader using partial update
        RelevanceHeader partialUpdatedRelevanceHeader = new RelevanceHeader();
        partialUpdatedRelevanceHeader.setId(relevanceHeader.getId());

        partialUpdatedRelevanceHeader
            .midnight(UPDATED_MIDNIGHT)
            .one(UPDATED_ONE)
            .two(UPDATED_TWO)
            .three(UPDATED_THREE)
            .four(UPDATED_FOUR)
            .five(UPDATED_FIVE)
            .six(UPDATED_SIX)
            .seven(UPDATED_SEVEN)
            .eight(UPDATED_EIGHT)
            .nine(UPDATED_NINE)
            .ten(UPDATED_TEN)
            .eleven(UPDATED_ELEVEN)
            .twelve(UPDATED_TWELVE)
            .thirteen(UPDATED_THIRTEEN)
            .fourteen(UPDATED_FOURTEEN)
            .fifteen(UPDATED_FIFTEEN)
            .sixteen(UPDATED_SIXTEEN)
            .seventeen(UPDATED_SEVENTEEN)
            .eighteen(UPDATED_EIGHTEEN)
            .nineteen(UPDATED_NINETEEN)
            .twenty(UPDATED_TWENTY)
            .twentyOne(UPDATED_TWENTY_ONE)
            .twentyTwo(UPDATED_TWENTY_TWO)
            .twentyThree(UPDATED_TWENTY_THREE);

        restRelevanceHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelevanceHeader.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelevanceHeader))
            )
            .andExpect(status().isOk());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeUpdate);
        RelevanceHeader testRelevanceHeader = relevanceHeaderList.get(relevanceHeaderList.size() - 1);
        assertThat(testRelevanceHeader.getMidnight()).isEqualTo(UPDATED_MIDNIGHT);
        assertThat(testRelevanceHeader.getOne()).isEqualTo(UPDATED_ONE);
        assertThat(testRelevanceHeader.getTwo()).isEqualTo(UPDATED_TWO);
        assertThat(testRelevanceHeader.getThree()).isEqualTo(UPDATED_THREE);
        assertThat(testRelevanceHeader.getFour()).isEqualTo(UPDATED_FOUR);
        assertThat(testRelevanceHeader.getFive()).isEqualTo(UPDATED_FIVE);
        assertThat(testRelevanceHeader.getSix()).isEqualTo(UPDATED_SIX);
        assertThat(testRelevanceHeader.getSeven()).isEqualTo(UPDATED_SEVEN);
        assertThat(testRelevanceHeader.getEight()).isEqualTo(UPDATED_EIGHT);
        assertThat(testRelevanceHeader.getNine()).isEqualTo(UPDATED_NINE);
        assertThat(testRelevanceHeader.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testRelevanceHeader.getEleven()).isEqualTo(UPDATED_ELEVEN);
        assertThat(testRelevanceHeader.getTwelve()).isEqualTo(UPDATED_TWELVE);
        assertThat(testRelevanceHeader.getThirteen()).isEqualTo(UPDATED_THIRTEEN);
        assertThat(testRelevanceHeader.getFourteen()).isEqualTo(UPDATED_FOURTEEN);
        assertThat(testRelevanceHeader.getFifteen()).isEqualTo(UPDATED_FIFTEEN);
        assertThat(testRelevanceHeader.getSixteen()).isEqualTo(UPDATED_SIXTEEN);
        assertThat(testRelevanceHeader.getSeventeen()).isEqualTo(UPDATED_SEVENTEEN);
        assertThat(testRelevanceHeader.getEighteen()).isEqualTo(UPDATED_EIGHTEEN);
        assertThat(testRelevanceHeader.getNineteen()).isEqualTo(UPDATED_NINETEEN);
        assertThat(testRelevanceHeader.getTwenty()).isEqualTo(UPDATED_TWENTY);
        assertThat(testRelevanceHeader.getTwentyOne()).isEqualTo(UPDATED_TWENTY_ONE);
        assertThat(testRelevanceHeader.getTwentyTwo()).isEqualTo(UPDATED_TWENTY_TWO);
        assertThat(testRelevanceHeader.getTwentyThree()).isEqualTo(UPDATED_TWENTY_THREE);
    }

    @Test
    @Transactional
    void patchNonExistingRelevanceHeader() throws Exception {
        int databaseSizeBeforeUpdate = relevanceHeaderRepository.findAll().size();
        relevanceHeader.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelevanceHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, relevanceHeader.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relevanceHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRelevanceHeader() throws Exception {
        int databaseSizeBeforeUpdate = relevanceHeaderRepository.findAll().size();
        relevanceHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelevanceHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relevanceHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRelevanceHeader() throws Exception {
        int databaseSizeBeforeUpdate = relevanceHeaderRepository.findAll().size();
        relevanceHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelevanceHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relevanceHeader))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RelevanceHeader in the database
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRelevanceHeader() throws Exception {
        // Initialize the database
        relevanceHeaderRepository.saveAndFlush(relevanceHeader);

        int databaseSizeBeforeDelete = relevanceHeaderRepository.findAll().size();

        // Delete the relevanceHeader
        restRelevanceHeaderMockMvc
            .perform(delete(ENTITY_API_URL_ID, relevanceHeader.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelevanceHeader> relevanceHeaderList = relevanceHeaderRepository.findAll();
        assertThat(relevanceHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
