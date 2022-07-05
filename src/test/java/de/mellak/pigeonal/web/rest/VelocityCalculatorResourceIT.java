package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.VelocityCalculator;
import de.mellak.pigeonal.repository.VelocityCalculatorRepository;
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
 * Integration tests for the {@link VelocityCalculatorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VelocityCalculatorResourceIT {

    private static final Instant DEFAULT_RELEASE_DATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RELEASE_DATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ARRIVAL_DATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ARRIVAL_DATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_DISTANCE_KM = 1;
    private static final Integer UPDATED_DISTANCE_KM = 2;

    private static final Integer DEFAULT_DISTANCE_M = 1;
    private static final Integer UPDATED_DISTANCE_M = 2;

    private static final Double DEFAULT_VELOCITY = 1D;
    private static final Double UPDATED_VELOCITY = 2D;

    private static final String DEFAULT_VELOCITY_DISP_VAL = "AAAAAAAAAA";
    private static final String UPDATED_VELOCITY_DISP_VAL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/velocity-calculators";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VelocityCalculatorRepository velocityCalculatorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVelocityCalculatorMockMvc;

    private VelocityCalculator velocityCalculator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VelocityCalculator createEntity(EntityManager em) {
        VelocityCalculator velocityCalculator = new VelocityCalculator()
            .releaseDateTime(DEFAULT_RELEASE_DATE_TIME)
            .arrivalDateTime(DEFAULT_ARRIVAL_DATE_TIME)
            .distanceKM(DEFAULT_DISTANCE_KM)
            .distanceM(DEFAULT_DISTANCE_M)
            .velocity(DEFAULT_VELOCITY)
            .velocityDispVal(DEFAULT_VELOCITY_DISP_VAL);
        return velocityCalculator;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VelocityCalculator createUpdatedEntity(EntityManager em) {
        VelocityCalculator velocityCalculator = new VelocityCalculator()
            .releaseDateTime(UPDATED_RELEASE_DATE_TIME)
            .arrivalDateTime(UPDATED_ARRIVAL_DATE_TIME)
            .distanceKM(UPDATED_DISTANCE_KM)
            .distanceM(UPDATED_DISTANCE_M)
            .velocity(UPDATED_VELOCITY)
            .velocityDispVal(UPDATED_VELOCITY_DISP_VAL);
        return velocityCalculator;
    }

    @BeforeEach
    public void initTest() {
        velocityCalculator = createEntity(em);
    }

    @Test
    @Transactional
    void createVelocityCalculator() throws Exception {
        int databaseSizeBeforeCreate = velocityCalculatorRepository.findAll().size();
        // Create the VelocityCalculator
        restVelocityCalculatorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(velocityCalculator))
            )
            .andExpect(status().isCreated());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeCreate + 1);
        VelocityCalculator testVelocityCalculator = velocityCalculatorList.get(velocityCalculatorList.size() - 1);
        assertThat(testVelocityCalculator.getReleaseDateTime()).isEqualTo(DEFAULT_RELEASE_DATE_TIME);
        assertThat(testVelocityCalculator.getArrivalDateTime()).isEqualTo(DEFAULT_ARRIVAL_DATE_TIME);
        assertThat(testVelocityCalculator.getDistanceKM()).isEqualTo(DEFAULT_DISTANCE_KM);
        assertThat(testVelocityCalculator.getDistanceM()).isEqualTo(DEFAULT_DISTANCE_M);
        assertThat(testVelocityCalculator.getVelocity()).isEqualTo(DEFAULT_VELOCITY);
        assertThat(testVelocityCalculator.getVelocityDispVal()).isEqualTo(DEFAULT_VELOCITY_DISP_VAL);
    }

    @Test
    @Transactional
    void createVelocityCalculatorWithExistingId() throws Exception {
        // Create the VelocityCalculator with an existing ID
        velocityCalculator.setId(1L);

        int databaseSizeBeforeCreate = velocityCalculatorRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVelocityCalculatorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(velocityCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVelocityCalculators() throws Exception {
        // Initialize the database
        velocityCalculatorRepository.saveAndFlush(velocityCalculator);

        // Get all the velocityCalculatorList
        restVelocityCalculatorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(velocityCalculator.getId().intValue())))
            .andExpect(jsonPath("$.[*].releaseDateTime").value(hasItem(DEFAULT_RELEASE_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].arrivalDateTime").value(hasItem(DEFAULT_ARRIVAL_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].distanceKM").value(hasItem(DEFAULT_DISTANCE_KM)))
            .andExpect(jsonPath("$.[*].distanceM").value(hasItem(DEFAULT_DISTANCE_M)))
            .andExpect(jsonPath("$.[*].velocity").value(hasItem(DEFAULT_VELOCITY.doubleValue())))
            .andExpect(jsonPath("$.[*].velocityDispVal").value(hasItem(DEFAULT_VELOCITY_DISP_VAL)));
    }

    @Test
    @Transactional
    void getVelocityCalculator() throws Exception {
        // Initialize the database
        velocityCalculatorRepository.saveAndFlush(velocityCalculator);

        // Get the velocityCalculator
        restVelocityCalculatorMockMvc
            .perform(get(ENTITY_API_URL_ID, velocityCalculator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(velocityCalculator.getId().intValue()))
            .andExpect(jsonPath("$.releaseDateTime").value(DEFAULT_RELEASE_DATE_TIME.toString()))
            .andExpect(jsonPath("$.arrivalDateTime").value(DEFAULT_ARRIVAL_DATE_TIME.toString()))
            .andExpect(jsonPath("$.distanceKM").value(DEFAULT_DISTANCE_KM))
            .andExpect(jsonPath("$.distanceM").value(DEFAULT_DISTANCE_M))
            .andExpect(jsonPath("$.velocity").value(DEFAULT_VELOCITY.doubleValue()))
            .andExpect(jsonPath("$.velocityDispVal").value(DEFAULT_VELOCITY_DISP_VAL));
    }

    @Test
    @Transactional
    void getNonExistingVelocityCalculator() throws Exception {
        // Get the velocityCalculator
        restVelocityCalculatorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewVelocityCalculator() throws Exception {
        // Initialize the database
        velocityCalculatorRepository.saveAndFlush(velocityCalculator);

        int databaseSizeBeforeUpdate = velocityCalculatorRepository.findAll().size();

        // Update the velocityCalculator
        VelocityCalculator updatedVelocityCalculator = velocityCalculatorRepository.findById(velocityCalculator.getId()).get();
        // Disconnect from session so that the updates on updatedVelocityCalculator are not directly saved in db
        em.detach(updatedVelocityCalculator);
        updatedVelocityCalculator
            .releaseDateTime(UPDATED_RELEASE_DATE_TIME)
            .arrivalDateTime(UPDATED_ARRIVAL_DATE_TIME)
            .distanceKM(UPDATED_DISTANCE_KM)
            .distanceM(UPDATED_DISTANCE_M)
            .velocity(UPDATED_VELOCITY)
            .velocityDispVal(UPDATED_VELOCITY_DISP_VAL);

        restVelocityCalculatorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVelocityCalculator.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedVelocityCalculator))
            )
            .andExpect(status().isOk());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeUpdate);
        VelocityCalculator testVelocityCalculator = velocityCalculatorList.get(velocityCalculatorList.size() - 1);
        assertThat(testVelocityCalculator.getReleaseDateTime()).isEqualTo(UPDATED_RELEASE_DATE_TIME);
        assertThat(testVelocityCalculator.getArrivalDateTime()).isEqualTo(UPDATED_ARRIVAL_DATE_TIME);
        assertThat(testVelocityCalculator.getDistanceKM()).isEqualTo(UPDATED_DISTANCE_KM);
        assertThat(testVelocityCalculator.getDistanceM()).isEqualTo(UPDATED_DISTANCE_M);
        assertThat(testVelocityCalculator.getVelocity()).isEqualTo(UPDATED_VELOCITY);
        assertThat(testVelocityCalculator.getVelocityDispVal()).isEqualTo(UPDATED_VELOCITY_DISP_VAL);
    }

    @Test
    @Transactional
    void putNonExistingVelocityCalculator() throws Exception {
        int databaseSizeBeforeUpdate = velocityCalculatorRepository.findAll().size();
        velocityCalculator.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVelocityCalculatorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, velocityCalculator.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(velocityCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVelocityCalculator() throws Exception {
        int databaseSizeBeforeUpdate = velocityCalculatorRepository.findAll().size();
        velocityCalculator.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVelocityCalculatorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(velocityCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVelocityCalculator() throws Exception {
        int databaseSizeBeforeUpdate = velocityCalculatorRepository.findAll().size();
        velocityCalculator.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVelocityCalculatorMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(velocityCalculator))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVelocityCalculatorWithPatch() throws Exception {
        // Initialize the database
        velocityCalculatorRepository.saveAndFlush(velocityCalculator);

        int databaseSizeBeforeUpdate = velocityCalculatorRepository.findAll().size();

        // Update the velocityCalculator using partial update
        VelocityCalculator partialUpdatedVelocityCalculator = new VelocityCalculator();
        partialUpdatedVelocityCalculator.setId(velocityCalculator.getId());

        partialUpdatedVelocityCalculator
            .releaseDateTime(UPDATED_RELEASE_DATE_TIME)
            .distanceM(UPDATED_DISTANCE_M)
            .velocity(UPDATED_VELOCITY)
            .velocityDispVal(UPDATED_VELOCITY_DISP_VAL);

        restVelocityCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVelocityCalculator.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVelocityCalculator))
            )
            .andExpect(status().isOk());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeUpdate);
        VelocityCalculator testVelocityCalculator = velocityCalculatorList.get(velocityCalculatorList.size() - 1);
        assertThat(testVelocityCalculator.getReleaseDateTime()).isEqualTo(UPDATED_RELEASE_DATE_TIME);
        assertThat(testVelocityCalculator.getArrivalDateTime()).isEqualTo(DEFAULT_ARRIVAL_DATE_TIME);
        assertThat(testVelocityCalculator.getDistanceKM()).isEqualTo(DEFAULT_DISTANCE_KM);
        assertThat(testVelocityCalculator.getDistanceM()).isEqualTo(UPDATED_DISTANCE_M);
        assertThat(testVelocityCalculator.getVelocity()).isEqualTo(UPDATED_VELOCITY);
        assertThat(testVelocityCalculator.getVelocityDispVal()).isEqualTo(UPDATED_VELOCITY_DISP_VAL);
    }

    @Test
    @Transactional
    void fullUpdateVelocityCalculatorWithPatch() throws Exception {
        // Initialize the database
        velocityCalculatorRepository.saveAndFlush(velocityCalculator);

        int databaseSizeBeforeUpdate = velocityCalculatorRepository.findAll().size();

        // Update the velocityCalculator using partial update
        VelocityCalculator partialUpdatedVelocityCalculator = new VelocityCalculator();
        partialUpdatedVelocityCalculator.setId(velocityCalculator.getId());

        partialUpdatedVelocityCalculator
            .releaseDateTime(UPDATED_RELEASE_DATE_TIME)
            .arrivalDateTime(UPDATED_ARRIVAL_DATE_TIME)
            .distanceKM(UPDATED_DISTANCE_KM)
            .distanceM(UPDATED_DISTANCE_M)
            .velocity(UPDATED_VELOCITY)
            .velocityDispVal(UPDATED_VELOCITY_DISP_VAL);

        restVelocityCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVelocityCalculator.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVelocityCalculator))
            )
            .andExpect(status().isOk());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeUpdate);
        VelocityCalculator testVelocityCalculator = velocityCalculatorList.get(velocityCalculatorList.size() - 1);
        assertThat(testVelocityCalculator.getReleaseDateTime()).isEqualTo(UPDATED_RELEASE_DATE_TIME);
        assertThat(testVelocityCalculator.getArrivalDateTime()).isEqualTo(UPDATED_ARRIVAL_DATE_TIME);
        assertThat(testVelocityCalculator.getDistanceKM()).isEqualTo(UPDATED_DISTANCE_KM);
        assertThat(testVelocityCalculator.getDistanceM()).isEqualTo(UPDATED_DISTANCE_M);
        assertThat(testVelocityCalculator.getVelocity()).isEqualTo(UPDATED_VELOCITY);
        assertThat(testVelocityCalculator.getVelocityDispVal()).isEqualTo(UPDATED_VELOCITY_DISP_VAL);
    }

    @Test
    @Transactional
    void patchNonExistingVelocityCalculator() throws Exception {
        int databaseSizeBeforeUpdate = velocityCalculatorRepository.findAll().size();
        velocityCalculator.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVelocityCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, velocityCalculator.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(velocityCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVelocityCalculator() throws Exception {
        int databaseSizeBeforeUpdate = velocityCalculatorRepository.findAll().size();
        velocityCalculator.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVelocityCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(velocityCalculator))
            )
            .andExpect(status().isBadRequest());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVelocityCalculator() throws Exception {
        int databaseSizeBeforeUpdate = velocityCalculatorRepository.findAll().size();
        velocityCalculator.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVelocityCalculatorMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(velocityCalculator))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VelocityCalculator in the database
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVelocityCalculator() throws Exception {
        // Initialize the database
        velocityCalculatorRepository.saveAndFlush(velocityCalculator);

        int databaseSizeBeforeDelete = velocityCalculatorRepository.findAll().size();

        // Delete the velocityCalculator
        restVelocityCalculatorMockMvc
            .perform(delete(ENTITY_API_URL_ID, velocityCalculator.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VelocityCalculator> velocityCalculatorList = velocityCalculatorRepository.findAll();
        assertThat(velocityCalculatorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
