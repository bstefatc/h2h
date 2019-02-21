package com.rbc.gam.head2head.web.rest;

import com.rbc.gam.head2head.H2HApp;

import com.rbc.gam.head2head.domain.ComparisonHolding;
import com.rbc.gam.head2head.repository.ComparisonHoldingRepository;
import com.rbc.gam.head2head.service.ComparisonHoldingService;
import com.rbc.gam.head2head.service.dto.ComparisonHoldingDTO;
import com.rbc.gam.head2head.service.mapper.ComparisonHoldingMapper;
import com.rbc.gam.head2head.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.rbc.gam.head2head.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ComparisonHoldingResource REST controller.
 *
 * @see ComparisonHoldingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = H2HApp.class)
public class ComparisonHoldingResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOLDINGS = "AAAAAAAAAA";
    private static final String UPDATED_HOLDINGS = "BBBBBBBBBB";

    @Autowired
    private ComparisonHoldingRepository comparisonHoldingRepository;

    @Autowired
    private ComparisonHoldingMapper comparisonHoldingMapper;

    @Autowired
    private ComparisonHoldingService comparisonHoldingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restComparisonHoldingMockMvc;

    private ComparisonHolding comparisonHolding;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComparisonHoldingResource comparisonHoldingResource = new ComparisonHoldingResource(comparisonHoldingService);
        this.restComparisonHoldingMockMvc = MockMvcBuilders.standaloneSetup(comparisonHoldingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ComparisonHolding createEntity(EntityManager em) {
        ComparisonHolding comparisonHolding = new ComparisonHolding()
            .name(DEFAULT_NAME)
            .holdings(DEFAULT_HOLDINGS);
        return comparisonHolding;
    }

    @Before
    public void initTest() {
        comparisonHolding = createEntity(em);
    }

    @Test
    @Transactional
    public void createComparisonHolding() throws Exception {
        int databaseSizeBeforeCreate = comparisonHoldingRepository.findAll().size();

        // Create the ComparisonHolding
        ComparisonHoldingDTO comparisonHoldingDTO = comparisonHoldingMapper.toDto(comparisonHolding);
        restComparisonHoldingMockMvc.perform(post("/api/comparison-holdings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comparisonHoldingDTO)))
            .andExpect(status().isCreated());

        // Validate the ComparisonHolding in the database
        List<ComparisonHolding> comparisonHoldingList = comparisonHoldingRepository.findAll();
        assertThat(comparisonHoldingList).hasSize(databaseSizeBeforeCreate + 1);
        ComparisonHolding testComparisonHolding = comparisonHoldingList.get(comparisonHoldingList.size() - 1);
        assertThat(testComparisonHolding.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testComparisonHolding.getHoldings()).isEqualTo(DEFAULT_HOLDINGS);
    }

    @Test
    @Transactional
    public void createComparisonHoldingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comparisonHoldingRepository.findAll().size();

        // Create the ComparisonHolding with an existing ID
        comparisonHolding.setId(1L);
        ComparisonHoldingDTO comparisonHoldingDTO = comparisonHoldingMapper.toDto(comparisonHolding);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComparisonHoldingMockMvc.perform(post("/api/comparison-holdings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comparisonHoldingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComparisonHolding in the database
        List<ComparisonHolding> comparisonHoldingList = comparisonHoldingRepository.findAll();
        assertThat(comparisonHoldingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllComparisonHoldings() throws Exception {
        // Initialize the database
        comparisonHoldingRepository.saveAndFlush(comparisonHolding);

        // Get all the comparisonHoldingList
        restComparisonHoldingMockMvc.perform(get("/api/comparison-holdings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comparisonHolding.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].holdings").value(hasItem(DEFAULT_HOLDINGS.toString())));
    }
    
    @Test
    @Transactional
    public void getComparisonHolding() throws Exception {
        // Initialize the database
        comparisonHoldingRepository.saveAndFlush(comparisonHolding);

        // Get the comparisonHolding
        restComparisonHoldingMockMvc.perform(get("/api/comparison-holdings/{id}", comparisonHolding.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(comparisonHolding.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.holdings").value(DEFAULT_HOLDINGS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComparisonHolding() throws Exception {
        // Get the comparisonHolding
        restComparisonHoldingMockMvc.perform(get("/api/comparison-holdings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComparisonHolding() throws Exception {
        // Initialize the database
        comparisonHoldingRepository.saveAndFlush(comparisonHolding);

        int databaseSizeBeforeUpdate = comparisonHoldingRepository.findAll().size();

        // Update the comparisonHolding
        ComparisonHolding updatedComparisonHolding = comparisonHoldingRepository.findById(comparisonHolding.getId()).get();
        // Disconnect from session so that the updates on updatedComparisonHolding are not directly saved in db
        em.detach(updatedComparisonHolding);
        updatedComparisonHolding
            .name(UPDATED_NAME)
            .holdings(UPDATED_HOLDINGS);
        ComparisonHoldingDTO comparisonHoldingDTO = comparisonHoldingMapper.toDto(updatedComparisonHolding);

        restComparisonHoldingMockMvc.perform(put("/api/comparison-holdings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comparisonHoldingDTO)))
            .andExpect(status().isOk());

        // Validate the ComparisonHolding in the database
        List<ComparisonHolding> comparisonHoldingList = comparisonHoldingRepository.findAll();
        assertThat(comparisonHoldingList).hasSize(databaseSizeBeforeUpdate);
        ComparisonHolding testComparisonHolding = comparisonHoldingList.get(comparisonHoldingList.size() - 1);
        assertThat(testComparisonHolding.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testComparisonHolding.getHoldings()).isEqualTo(UPDATED_HOLDINGS);
    }

    @Test
    @Transactional
    public void updateNonExistingComparisonHolding() throws Exception {
        int databaseSizeBeforeUpdate = comparisonHoldingRepository.findAll().size();

        // Create the ComparisonHolding
        ComparisonHoldingDTO comparisonHoldingDTO = comparisonHoldingMapper.toDto(comparisonHolding);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComparisonHoldingMockMvc.perform(put("/api/comparison-holdings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comparisonHoldingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComparisonHolding in the database
        List<ComparisonHolding> comparisonHoldingList = comparisonHoldingRepository.findAll();
        assertThat(comparisonHoldingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComparisonHolding() throws Exception {
        // Initialize the database
        comparisonHoldingRepository.saveAndFlush(comparisonHolding);

        int databaseSizeBeforeDelete = comparisonHoldingRepository.findAll().size();

        // Delete the comparisonHolding
        restComparisonHoldingMockMvc.perform(delete("/api/comparison-holdings/{id}", comparisonHolding.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ComparisonHolding> comparisonHoldingList = comparisonHoldingRepository.findAll();
        assertThat(comparisonHoldingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComparisonHolding.class);
        ComparisonHolding comparisonHolding1 = new ComparisonHolding();
        comparisonHolding1.setId(1L);
        ComparisonHolding comparisonHolding2 = new ComparisonHolding();
        comparisonHolding2.setId(comparisonHolding1.getId());
        assertThat(comparisonHolding1).isEqualTo(comparisonHolding2);
        comparisonHolding2.setId(2L);
        assertThat(comparisonHolding1).isNotEqualTo(comparisonHolding2);
        comparisonHolding1.setId(null);
        assertThat(comparisonHolding1).isNotEqualTo(comparisonHolding2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComparisonHoldingDTO.class);
        ComparisonHoldingDTO comparisonHoldingDTO1 = new ComparisonHoldingDTO();
        comparisonHoldingDTO1.setId(1L);
        ComparisonHoldingDTO comparisonHoldingDTO2 = new ComparisonHoldingDTO();
        assertThat(comparisonHoldingDTO1).isNotEqualTo(comparisonHoldingDTO2);
        comparisonHoldingDTO2.setId(comparisonHoldingDTO1.getId());
        assertThat(comparisonHoldingDTO1).isEqualTo(comparisonHoldingDTO2);
        comparisonHoldingDTO2.setId(2L);
        assertThat(comparisonHoldingDTO1).isNotEqualTo(comparisonHoldingDTO2);
        comparisonHoldingDTO1.setId(null);
        assertThat(comparisonHoldingDTO1).isNotEqualTo(comparisonHoldingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(comparisonHoldingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(comparisonHoldingMapper.fromId(null)).isNull();
    }
}
