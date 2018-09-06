package com.manageme.app.web.rest;

import com.manageme.app.ManageMeApp;

import com.manageme.app.domain.SeparationApplication;
import com.manageme.app.repository.SeparationApplicationRepository;
import com.manageme.app.service.SeparationApplicationService;
import com.manageme.app.service.dto.SeparationApplicationDTO;
import com.manageme.app.service.mapper.SeparationApplicationMapper;
import com.manageme.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


import static com.manageme.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SeparationApplicationResource REST controller.
 *
 * @see SeparationApplicationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageMeApp.class)
public class SeparationApplicationResourceIntTest {

    private static final Instant DEFAULT_DATE_OF_LEAVING = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF_LEAVING = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_OF_SUBMISSION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF_SUBMISSION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REASON_FOR_LEAVING = "AAAAAAAAAA";
    private static final String UPDATED_REASON_FOR_LEAVING = "BBBBBBBBBB";

    private static final Boolean DEFAULT_COMPLETED = false;
    private static final Boolean UPDATED_COMPLETED = true;

    @Autowired
    private SeparationApplicationRepository separationApplicationRepository;
    @Mock
    private SeparationApplicationRepository separationApplicationRepositoryMock;

    @Autowired
    private SeparationApplicationMapper separationApplicationMapper;
    
    @Mock
    private SeparationApplicationService separationApplicationServiceMock;

    @Autowired
    private SeparationApplicationService separationApplicationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSeparationApplicationMockMvc;

    private SeparationApplication separationApplication;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SeparationApplicationResource separationApplicationResource = new SeparationApplicationResource(separationApplicationService);
        this.restSeparationApplicationMockMvc = MockMvcBuilders.standaloneSetup(separationApplicationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SeparationApplication createEntity(EntityManager em) {
        SeparationApplication separationApplication = new SeparationApplication()
            .dateOfLeaving(DEFAULT_DATE_OF_LEAVING)
            .dateOfSubmission(DEFAULT_DATE_OF_SUBMISSION)
            .reasonForLeaving(DEFAULT_REASON_FOR_LEAVING)
            .completed(DEFAULT_COMPLETED);
        return separationApplication;
    }

    @Before
    public void initTest() {
        separationApplication = createEntity(em);
    }

    @Test
    @Transactional
    public void createSeparationApplication() throws Exception {
        int databaseSizeBeforeCreate = separationApplicationRepository.findAll().size();

        // Create the SeparationApplication
        SeparationApplicationDTO separationApplicationDTO = separationApplicationMapper.toDto(separationApplication);
        restSeparationApplicationMockMvc.perform(post("/api/separation-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(separationApplicationDTO)))
            .andExpect(status().isCreated());

        // Validate the SeparationApplication in the database
        List<SeparationApplication> separationApplicationList = separationApplicationRepository.findAll();
        assertThat(separationApplicationList).hasSize(databaseSizeBeforeCreate + 1);
        SeparationApplication testSeparationApplication = separationApplicationList.get(separationApplicationList.size() - 1);
        assertThat(testSeparationApplication.getDateOfLeaving()).isEqualTo(DEFAULT_DATE_OF_LEAVING);
        assertThat(testSeparationApplication.getDateOfSubmission()).isEqualTo(DEFAULT_DATE_OF_SUBMISSION);
        assertThat(testSeparationApplication.getReasonForLeaving()).isEqualTo(DEFAULT_REASON_FOR_LEAVING);
        assertThat(testSeparationApplication.isCompleted()).isEqualTo(DEFAULT_COMPLETED);
    }

    @Test
    @Transactional
    public void createSeparationApplicationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = separationApplicationRepository.findAll().size();

        // Create the SeparationApplication with an existing ID
        separationApplication.setId(1L);
        SeparationApplicationDTO separationApplicationDTO = separationApplicationMapper.toDto(separationApplication);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSeparationApplicationMockMvc.perform(post("/api/separation-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(separationApplicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SeparationApplication in the database
        List<SeparationApplication> separationApplicationList = separationApplicationRepository.findAll();
        assertThat(separationApplicationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDateOfLeavingIsRequired() throws Exception {
        int databaseSizeBeforeTest = separationApplicationRepository.findAll().size();
        // set the field null
        separationApplication.setDateOfLeaving(null);

        // Create the SeparationApplication, which fails.
        SeparationApplicationDTO separationApplicationDTO = separationApplicationMapper.toDto(separationApplication);

        restSeparationApplicationMockMvc.perform(post("/api/separation-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(separationApplicationDTO)))
            .andExpect(status().isBadRequest());

        List<SeparationApplication> separationApplicationList = separationApplicationRepository.findAll();
        assertThat(separationApplicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReasonForLeavingIsRequired() throws Exception {
        int databaseSizeBeforeTest = separationApplicationRepository.findAll().size();
        // set the field null
        separationApplication.setReasonForLeaving(null);

        // Create the SeparationApplication, which fails.
        SeparationApplicationDTO separationApplicationDTO = separationApplicationMapper.toDto(separationApplication);

        restSeparationApplicationMockMvc.perform(post("/api/separation-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(separationApplicationDTO)))
            .andExpect(status().isBadRequest());

        List<SeparationApplication> separationApplicationList = separationApplicationRepository.findAll();
        assertThat(separationApplicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSeparationApplications() throws Exception {
        // Initialize the database
        separationApplicationRepository.saveAndFlush(separationApplication);

        // Get all the separationApplicationList
        restSeparationApplicationMockMvc.perform(get("/api/separation-applications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(separationApplication.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateOfLeaving").value(hasItem(DEFAULT_DATE_OF_LEAVING.toString())))
            .andExpect(jsonPath("$.[*].dateOfSubmission").value(hasItem(DEFAULT_DATE_OF_SUBMISSION.toString())))
            .andExpect(jsonPath("$.[*].reasonForLeaving").value(hasItem(DEFAULT_REASON_FOR_LEAVING.toString())))
            .andExpect(jsonPath("$.[*].completed").value(hasItem(DEFAULT_COMPLETED.booleanValue())));
    }
    
    public void getAllSeparationApplicationsWithEagerRelationshipsIsEnabled() throws Exception {
        SeparationApplicationResource separationApplicationResource = new SeparationApplicationResource(separationApplicationServiceMock);
        when(separationApplicationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restSeparationApplicationMockMvc = MockMvcBuilders.standaloneSetup(separationApplicationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSeparationApplicationMockMvc.perform(get("/api/separation-applications?eagerload=true"))
        .andExpect(status().isOk());

        verify(separationApplicationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllSeparationApplicationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        SeparationApplicationResource separationApplicationResource = new SeparationApplicationResource(separationApplicationServiceMock);
            when(separationApplicationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restSeparationApplicationMockMvc = MockMvcBuilders.standaloneSetup(separationApplicationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSeparationApplicationMockMvc.perform(get("/api/separation-applications?eagerload=true"))
        .andExpect(status().isOk());

            verify(separationApplicationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getSeparationApplication() throws Exception {
        // Initialize the database
        separationApplicationRepository.saveAndFlush(separationApplication);

        // Get the separationApplication
        restSeparationApplicationMockMvc.perform(get("/api/separation-applications/{id}", separationApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(separationApplication.getId().intValue()))
            .andExpect(jsonPath("$.dateOfLeaving").value(DEFAULT_DATE_OF_LEAVING.toString()))
            .andExpect(jsonPath("$.dateOfSubmission").value(DEFAULT_DATE_OF_SUBMISSION.toString()))
            .andExpect(jsonPath("$.reasonForLeaving").value(DEFAULT_REASON_FOR_LEAVING.toString()))
            .andExpect(jsonPath("$.completed").value(DEFAULT_COMPLETED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSeparationApplication() throws Exception {
        // Get the separationApplication
        restSeparationApplicationMockMvc.perform(get("/api/separation-applications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSeparationApplication() throws Exception {
        // Initialize the database
        separationApplicationRepository.saveAndFlush(separationApplication);

        int databaseSizeBeforeUpdate = separationApplicationRepository.findAll().size();

        // Update the separationApplication
        SeparationApplication updatedSeparationApplication = separationApplicationRepository.findById(separationApplication.getId()).get();
        // Disconnect from session so that the updates on updatedSeparationApplication are not directly saved in db
        em.detach(updatedSeparationApplication);
        updatedSeparationApplication
            .dateOfLeaving(UPDATED_DATE_OF_LEAVING)
            .dateOfSubmission(UPDATED_DATE_OF_SUBMISSION)
            .reasonForLeaving(UPDATED_REASON_FOR_LEAVING)
            .completed(UPDATED_COMPLETED);
        SeparationApplicationDTO separationApplicationDTO = separationApplicationMapper.toDto(updatedSeparationApplication);

        restSeparationApplicationMockMvc.perform(put("/api/separation-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(separationApplicationDTO)))
            .andExpect(status().isOk());

        // Validate the SeparationApplication in the database
        List<SeparationApplication> separationApplicationList = separationApplicationRepository.findAll();
        assertThat(separationApplicationList).hasSize(databaseSizeBeforeUpdate);
        SeparationApplication testSeparationApplication = separationApplicationList.get(separationApplicationList.size() - 1);
        assertThat(testSeparationApplication.getDateOfLeaving()).isEqualTo(UPDATED_DATE_OF_LEAVING);
        assertThat(testSeparationApplication.getDateOfSubmission()).isEqualTo(UPDATED_DATE_OF_SUBMISSION);
        assertThat(testSeparationApplication.getReasonForLeaving()).isEqualTo(UPDATED_REASON_FOR_LEAVING);
        assertThat(testSeparationApplication.isCompleted()).isEqualTo(UPDATED_COMPLETED);
    }

    @Test
    @Transactional
    public void updateNonExistingSeparationApplication() throws Exception {
        int databaseSizeBeforeUpdate = separationApplicationRepository.findAll().size();

        // Create the SeparationApplication
        SeparationApplicationDTO separationApplicationDTO = separationApplicationMapper.toDto(separationApplication);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSeparationApplicationMockMvc.perform(put("/api/separation-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(separationApplicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SeparationApplication in the database
        List<SeparationApplication> separationApplicationList = separationApplicationRepository.findAll();
        assertThat(separationApplicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSeparationApplication() throws Exception {
        // Initialize the database
        separationApplicationRepository.saveAndFlush(separationApplication);

        int databaseSizeBeforeDelete = separationApplicationRepository.findAll().size();

        // Get the separationApplication
        restSeparationApplicationMockMvc.perform(delete("/api/separation-applications/{id}", separationApplication.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SeparationApplication> separationApplicationList = separationApplicationRepository.findAll();
        assertThat(separationApplicationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SeparationApplication.class);
        SeparationApplication separationApplication1 = new SeparationApplication();
        separationApplication1.setId(1L);
        SeparationApplication separationApplication2 = new SeparationApplication();
        separationApplication2.setId(separationApplication1.getId());
        assertThat(separationApplication1).isEqualTo(separationApplication2);
        separationApplication2.setId(2L);
        assertThat(separationApplication1).isNotEqualTo(separationApplication2);
        separationApplication1.setId(null);
        assertThat(separationApplication1).isNotEqualTo(separationApplication2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SeparationApplicationDTO.class);
        SeparationApplicationDTO separationApplicationDTO1 = new SeparationApplicationDTO();
        separationApplicationDTO1.setId(1L);
        SeparationApplicationDTO separationApplicationDTO2 = new SeparationApplicationDTO();
        assertThat(separationApplicationDTO1).isNotEqualTo(separationApplicationDTO2);
        separationApplicationDTO2.setId(separationApplicationDTO1.getId());
        assertThat(separationApplicationDTO1).isEqualTo(separationApplicationDTO2);
        separationApplicationDTO2.setId(2L);
        assertThat(separationApplicationDTO1).isNotEqualTo(separationApplicationDTO2);
        separationApplicationDTO1.setId(null);
        assertThat(separationApplicationDTO1).isNotEqualTo(separationApplicationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(separationApplicationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(separationApplicationMapper.fromId(null)).isNull();
    }
}
