package com.eshipper.web.rest;

import com.eshipper.Eshipper1App;
import com.eshipper.config.TestSecurityConfiguration;
import com.eshipper.domain.ApCategoryType;
import com.eshipper.repository.ApCategoryTypeRepository;
import com.eshipper.service.ApCategoryTypeService;
import com.eshipper.service.dto.ApCategoryTypeDTO;
import com.eshipper.service.mapper.ApCategoryTypeMapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ApCategoryTypeResource} REST controller.
 */
@SpringBootTest(classes = {Eshipper1App.class, TestSecurityConfiguration.class})
public class ApCategoryTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ApCategoryTypeRepository apCategoryTypeRepository;

    @Autowired
    private ApCategoryTypeMapper apCategoryTypeMapper;

    @Autowired
    private ApCategoryTypeService apCategoryTypeService;

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

    private MockMvc restApCategoryTypeMockMvc;

    private ApCategoryType apCategoryType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApCategoryTypeResource apCategoryTypeResource = new ApCategoryTypeResource(apCategoryTypeService);
        this.restApCategoryTypeMockMvc = MockMvcBuilders.standaloneSetup(apCategoryTypeResource)
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
    public static ApCategoryType createEntity(EntityManager em) {
        ApCategoryType apCategoryType = new ApCategoryType()
            .name(DEFAULT_NAME);
        return apCategoryType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApCategoryType createUpdatedEntity(EntityManager em) {
        ApCategoryType apCategoryType = new ApCategoryType()
            .name(UPDATED_NAME);
        return apCategoryType;
    }

    @BeforeEach
    public void initTest() {
        apCategoryType = createEntity(em);
    }

    @Test
    @Transactional
    public void createApCategoryType() throws Exception {
        int databaseSizeBeforeCreate = apCategoryTypeRepository.findAll().size();

        // Create the ApCategoryType
        ApCategoryTypeDTO apCategoryTypeDTO = apCategoryTypeMapper.toDto(apCategoryType);
        restApCategoryTypeMockMvc.perform(post("/api/ap-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apCategoryTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ApCategoryType in the database
        List<ApCategoryType> apCategoryTypeList = apCategoryTypeRepository.findAll();
        assertThat(apCategoryTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ApCategoryType testApCategoryType = apCategoryTypeList.get(apCategoryTypeList.size() - 1);
        assertThat(testApCategoryType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createApCategoryTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apCategoryTypeRepository.findAll().size();

        // Create the ApCategoryType with an existing ID
        apCategoryType.setId(1L);
        ApCategoryTypeDTO apCategoryTypeDTO = apCategoryTypeMapper.toDto(apCategoryType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApCategoryTypeMockMvc.perform(post("/api/ap-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apCategoryTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApCategoryType in the database
        List<ApCategoryType> apCategoryTypeList = apCategoryTypeRepository.findAll();
        assertThat(apCategoryTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllApCategoryTypes() throws Exception {
        // Initialize the database
        apCategoryTypeRepository.saveAndFlush(apCategoryType);

        // Get all the apCategoryTypeList
        restApCategoryTypeMockMvc.perform(get("/api/ap-category-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apCategoryType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getApCategoryType() throws Exception {
        // Initialize the database
        apCategoryTypeRepository.saveAndFlush(apCategoryType);

        // Get the apCategoryType
        restApCategoryTypeMockMvc.perform(get("/api/ap-category-types/{id}", apCategoryType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apCategoryType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApCategoryType() throws Exception {
        // Get the apCategoryType
        restApCategoryTypeMockMvc.perform(get("/api/ap-category-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApCategoryType() throws Exception {
        // Initialize the database
        apCategoryTypeRepository.saveAndFlush(apCategoryType);

        int databaseSizeBeforeUpdate = apCategoryTypeRepository.findAll().size();

        // Update the apCategoryType
        ApCategoryType updatedApCategoryType = apCategoryTypeRepository.findById(apCategoryType.getId()).get();
        // Disconnect from session so that the updates on updatedApCategoryType are not directly saved in db
        em.detach(updatedApCategoryType);
        updatedApCategoryType
            .name(UPDATED_NAME);
        ApCategoryTypeDTO apCategoryTypeDTO = apCategoryTypeMapper.toDto(updatedApCategoryType);

        restApCategoryTypeMockMvc.perform(put("/api/ap-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apCategoryTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ApCategoryType in the database
        List<ApCategoryType> apCategoryTypeList = apCategoryTypeRepository.findAll();
        assertThat(apCategoryTypeList).hasSize(databaseSizeBeforeUpdate);
        ApCategoryType testApCategoryType = apCategoryTypeList.get(apCategoryTypeList.size() - 1);
        assertThat(testApCategoryType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingApCategoryType() throws Exception {
        int databaseSizeBeforeUpdate = apCategoryTypeRepository.findAll().size();

        // Create the ApCategoryType
        ApCategoryTypeDTO apCategoryTypeDTO = apCategoryTypeMapper.toDto(apCategoryType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApCategoryTypeMockMvc.perform(put("/api/ap-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apCategoryTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApCategoryType in the database
        List<ApCategoryType> apCategoryTypeList = apCategoryTypeRepository.findAll();
        assertThat(apCategoryTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApCategoryType() throws Exception {
        // Initialize the database
        apCategoryTypeRepository.saveAndFlush(apCategoryType);

        int databaseSizeBeforeDelete = apCategoryTypeRepository.findAll().size();

        // Delete the apCategoryType
        restApCategoryTypeMockMvc.perform(delete("/api/ap-category-types/{id}", apCategoryType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApCategoryType> apCategoryTypeList = apCategoryTypeRepository.findAll();
        assertThat(apCategoryTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApCategoryType.class);
        ApCategoryType apCategoryType1 = new ApCategoryType();
        apCategoryType1.setId(1L);
        ApCategoryType apCategoryType2 = new ApCategoryType();
        apCategoryType2.setId(apCategoryType1.getId());
        assertThat(apCategoryType1).isEqualTo(apCategoryType2);
        apCategoryType2.setId(2L);
        assertThat(apCategoryType1).isNotEqualTo(apCategoryType2);
        apCategoryType1.setId(null);
        assertThat(apCategoryType1).isNotEqualTo(apCategoryType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApCategoryTypeDTO.class);
        ApCategoryTypeDTO apCategoryTypeDTO1 = new ApCategoryTypeDTO();
        apCategoryTypeDTO1.setId(1L);
        ApCategoryTypeDTO apCategoryTypeDTO2 = new ApCategoryTypeDTO();
        assertThat(apCategoryTypeDTO1).isNotEqualTo(apCategoryTypeDTO2);
        apCategoryTypeDTO2.setId(apCategoryTypeDTO1.getId());
        assertThat(apCategoryTypeDTO1).isEqualTo(apCategoryTypeDTO2);
        apCategoryTypeDTO2.setId(2L);
        assertThat(apCategoryTypeDTO1).isNotEqualTo(apCategoryTypeDTO2);
        apCategoryTypeDTO1.setId(null);
        assertThat(apCategoryTypeDTO1).isNotEqualTo(apCategoryTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(apCategoryTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(apCategoryTypeMapper.fromId(null)).isNull();
    }
}
