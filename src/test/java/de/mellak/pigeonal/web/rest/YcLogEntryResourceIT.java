package de.mellak.pigeonal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.mellak.pigeonal.IntegrationTest;
import de.mellak.pigeonal.domain.YcLogEntry;
import de.mellak.pigeonal.domain.enumeration.LogType;
import de.mellak.pigeonal.repository.YcLogEntryRepository;
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
 * Integration tests for the {@link YcLogEntryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class YcLogEntryResourceIT {

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_STEP = "AAAAAAAAAA";
    private static final String UPDATED_STEP = "BBBBBBBBBB";

    private static final String DEFAULT_DISCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DISCRIPTION = "BBBBBBBBBB";

    private static final LogType DEFAULT_LOG_TYPE = LogType.INFO;
    private static final LogType UPDATED_LOG_TYPE = LogType.WARNING;

    private static final String ENTITY_API_URL = "/api/yc-log-entries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private YcLogEntryRepository ycLogEntryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restYcLogEntryMockMvc;

    private YcLogEntry ycLogEntry;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static YcLogEntry createEntity(EntityManager em) {
        YcLogEntry ycLogEntry = new YcLogEntry()
            .created(DEFAULT_CREATED)
            .serviceName(DEFAULT_SERVICE_NAME)
            .method(DEFAULT_METHOD)
            .step(DEFAULT_STEP)
            .discription(DEFAULT_DISCRIPTION)
            .logType(DEFAULT_LOG_TYPE);
        return ycLogEntry;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static YcLogEntry createUpdatedEntity(EntityManager em) {
        YcLogEntry ycLogEntry = new YcLogEntry()
            .created(UPDATED_CREATED)
            .serviceName(UPDATED_SERVICE_NAME)
            .method(UPDATED_METHOD)
            .step(UPDATED_STEP)
            .discription(UPDATED_DISCRIPTION)
            .logType(UPDATED_LOG_TYPE);
        return ycLogEntry;
    }

    @BeforeEach
    public void initTest() {
        ycLogEntry = createEntity(em);
    }

    @Test
    @Transactional
    void createYcLogEntry() throws Exception {
        int databaseSizeBeforeCreate = ycLogEntryRepository.findAll().size();
        // Create the YcLogEntry
        restYcLogEntryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ycLogEntry)))
            .andExpect(status().isCreated());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeCreate + 1);
        YcLogEntry testYcLogEntry = ycLogEntryList.get(ycLogEntryList.size() - 1);
        assertThat(testYcLogEntry.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testYcLogEntry.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
        assertThat(testYcLogEntry.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testYcLogEntry.getStep()).isEqualTo(DEFAULT_STEP);
        assertThat(testYcLogEntry.getDiscription()).isEqualTo(DEFAULT_DISCRIPTION);
        assertThat(testYcLogEntry.getLogType()).isEqualTo(DEFAULT_LOG_TYPE);
    }

    @Test
    @Transactional
    void createYcLogEntryWithExistingId() throws Exception {
        // Create the YcLogEntry with an existing ID
        ycLogEntry.setId(1L);

        int databaseSizeBeforeCreate = ycLogEntryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restYcLogEntryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ycLogEntry)))
            .andExpect(status().isBadRequest());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllYcLogEntries() throws Exception {
        // Initialize the database
        ycLogEntryRepository.saveAndFlush(ycLogEntry);

        // Get all the ycLogEntryList
        restYcLogEntryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ycLogEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].serviceName").value(hasItem(DEFAULT_SERVICE_NAME)))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD)))
            .andExpect(jsonPath("$.[*].step").value(hasItem(DEFAULT_STEP)))
            .andExpect(jsonPath("$.[*].discription").value(hasItem(DEFAULT_DISCRIPTION)))
            .andExpect(jsonPath("$.[*].logType").value(hasItem(DEFAULT_LOG_TYPE.toString())));
    }

    @Test
    @Transactional
    void getYcLogEntry() throws Exception {
        // Initialize the database
        ycLogEntryRepository.saveAndFlush(ycLogEntry);

        // Get the ycLogEntry
        restYcLogEntryMockMvc
            .perform(get(ENTITY_API_URL_ID, ycLogEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ycLogEntry.getId().intValue()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.serviceName").value(DEFAULT_SERVICE_NAME))
            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD))
            .andExpect(jsonPath("$.step").value(DEFAULT_STEP))
            .andExpect(jsonPath("$.discription").value(DEFAULT_DISCRIPTION))
            .andExpect(jsonPath("$.logType").value(DEFAULT_LOG_TYPE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingYcLogEntry() throws Exception {
        // Get the ycLogEntry
        restYcLogEntryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewYcLogEntry() throws Exception {
        // Initialize the database
        ycLogEntryRepository.saveAndFlush(ycLogEntry);

        int databaseSizeBeforeUpdate = ycLogEntryRepository.findAll().size();

        // Update the ycLogEntry
        YcLogEntry updatedYcLogEntry = ycLogEntryRepository.findById(ycLogEntry.getId()).get();
        // Disconnect from session so that the updates on updatedYcLogEntry are not directly saved in db
        em.detach(updatedYcLogEntry);
        updatedYcLogEntry
            .created(UPDATED_CREATED)
            .serviceName(UPDATED_SERVICE_NAME)
            .method(UPDATED_METHOD)
            .step(UPDATED_STEP)
            .discription(UPDATED_DISCRIPTION)
            .logType(UPDATED_LOG_TYPE);

        restYcLogEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedYcLogEntry.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedYcLogEntry))
            )
            .andExpect(status().isOk());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeUpdate);
        YcLogEntry testYcLogEntry = ycLogEntryList.get(ycLogEntryList.size() - 1);
        assertThat(testYcLogEntry.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testYcLogEntry.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
        assertThat(testYcLogEntry.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testYcLogEntry.getStep()).isEqualTo(UPDATED_STEP);
        assertThat(testYcLogEntry.getDiscription()).isEqualTo(UPDATED_DISCRIPTION);
        assertThat(testYcLogEntry.getLogType()).isEqualTo(UPDATED_LOG_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingYcLogEntry() throws Exception {
        int databaseSizeBeforeUpdate = ycLogEntryRepository.findAll().size();
        ycLogEntry.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restYcLogEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ycLogEntry.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ycLogEntry))
            )
            .andExpect(status().isBadRequest());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchYcLogEntry() throws Exception {
        int databaseSizeBeforeUpdate = ycLogEntryRepository.findAll().size();
        ycLogEntry.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restYcLogEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ycLogEntry))
            )
            .andExpect(status().isBadRequest());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamYcLogEntry() throws Exception {
        int databaseSizeBeforeUpdate = ycLogEntryRepository.findAll().size();
        ycLogEntry.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restYcLogEntryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ycLogEntry)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateYcLogEntryWithPatch() throws Exception {
        // Initialize the database
        ycLogEntryRepository.saveAndFlush(ycLogEntry);

        int databaseSizeBeforeUpdate = ycLogEntryRepository.findAll().size();

        // Update the ycLogEntry using partial update
        YcLogEntry partialUpdatedYcLogEntry = new YcLogEntry();
        partialUpdatedYcLogEntry.setId(ycLogEntry.getId());

        partialUpdatedYcLogEntry.step(UPDATED_STEP).discription(UPDATED_DISCRIPTION).logType(UPDATED_LOG_TYPE);

        restYcLogEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedYcLogEntry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedYcLogEntry))
            )
            .andExpect(status().isOk());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeUpdate);
        YcLogEntry testYcLogEntry = ycLogEntryList.get(ycLogEntryList.size() - 1);
        assertThat(testYcLogEntry.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testYcLogEntry.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
        assertThat(testYcLogEntry.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testYcLogEntry.getStep()).isEqualTo(UPDATED_STEP);
        assertThat(testYcLogEntry.getDiscription()).isEqualTo(UPDATED_DISCRIPTION);
        assertThat(testYcLogEntry.getLogType()).isEqualTo(UPDATED_LOG_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateYcLogEntryWithPatch() throws Exception {
        // Initialize the database
        ycLogEntryRepository.saveAndFlush(ycLogEntry);

        int databaseSizeBeforeUpdate = ycLogEntryRepository.findAll().size();

        // Update the ycLogEntry using partial update
        YcLogEntry partialUpdatedYcLogEntry = new YcLogEntry();
        partialUpdatedYcLogEntry.setId(ycLogEntry.getId());

        partialUpdatedYcLogEntry
            .created(UPDATED_CREATED)
            .serviceName(UPDATED_SERVICE_NAME)
            .method(UPDATED_METHOD)
            .step(UPDATED_STEP)
            .discription(UPDATED_DISCRIPTION)
            .logType(UPDATED_LOG_TYPE);

        restYcLogEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedYcLogEntry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedYcLogEntry))
            )
            .andExpect(status().isOk());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeUpdate);
        YcLogEntry testYcLogEntry = ycLogEntryList.get(ycLogEntryList.size() - 1);
        assertThat(testYcLogEntry.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testYcLogEntry.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
        assertThat(testYcLogEntry.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testYcLogEntry.getStep()).isEqualTo(UPDATED_STEP);
        assertThat(testYcLogEntry.getDiscription()).isEqualTo(UPDATED_DISCRIPTION);
        assertThat(testYcLogEntry.getLogType()).isEqualTo(UPDATED_LOG_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingYcLogEntry() throws Exception {
        int databaseSizeBeforeUpdate = ycLogEntryRepository.findAll().size();
        ycLogEntry.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restYcLogEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ycLogEntry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ycLogEntry))
            )
            .andExpect(status().isBadRequest());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchYcLogEntry() throws Exception {
        int databaseSizeBeforeUpdate = ycLogEntryRepository.findAll().size();
        ycLogEntry.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restYcLogEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ycLogEntry))
            )
            .andExpect(status().isBadRequest());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamYcLogEntry() throws Exception {
        int databaseSizeBeforeUpdate = ycLogEntryRepository.findAll().size();
        ycLogEntry.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restYcLogEntryMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ycLogEntry))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the YcLogEntry in the database
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteYcLogEntry() throws Exception {
        // Initialize the database
        ycLogEntryRepository.saveAndFlush(ycLogEntry);

        int databaseSizeBeforeDelete = ycLogEntryRepository.findAll().size();

        // Delete the ycLogEntry
        restYcLogEntryMockMvc
            .perform(delete(ENTITY_API_URL_ID, ycLogEntry.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<YcLogEntry> ycLogEntryList = ycLogEntryRepository.findAll();
        assertThat(ycLogEntryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
