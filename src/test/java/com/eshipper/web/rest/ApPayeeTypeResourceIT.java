package com.eshipper.web.rest;

import com.eshipper.Eshipper1App;
import com.eshipper.config.TestSecurityConfiguration;
import com.eshipper.domain.ApPayeeType;
import com.eshipper.repository.ApPayeeTypeRepository;
import com.eshipper.service.ApPayeeTypeService;
import com.eshipper.service.dto.ApPayeeTypeDTO;
import com.eshipper.service.mapper.ApPayeeTypeMapper;
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
 * Integration tests for the {@link ApPayeeTypeResource} REST controller.
 */
@SpringBootTest(classes = {Eshipper1App.class, TestSecurityConfiguration.class})
public class ApPayeeTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ApPayeeTypeRepository apPayeeTypeRepository;

    @Autowired
    private ApPayeeTypeMapper apPayeeTypeMapper;

    @Autowired
    private ApPayeeTypeService apPayeeTypeService;

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

    private MockMvc restApPayeeTypeMockMvc;

    private ApPayeeType apPayeeType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApPayeeTypeResource apPayeeTypeResource = new ApPayeeTypeResource(apPayeeTypeService);
        this.restApPayeeTypeMockMvc = MockMvcBuilders.standaloneSetup(apPayeeTypeResource)
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
    public static ApPayeeType createEntity(EntityManager em) {
        ApPayeeType apPayeeType = new ApPayeeType()
            .name(DEFAULT_NAME);
        return apPayeeType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApPayeeType createUpdatedEntity(EntityManager em) {
        ApPayeeType apPayeeType = new ApPayeeType()
            .name(UPDATED_NAME);
        return apPayeeType;
    }

    @BeforeEach
    public void initTest() {
        apPayeeType = createEntity(em);
    }

    @Test
    @Transactional
    public void createApPayeeType() throws Exception {
        int databaseSizeBeforeCreate = apPayeeTypeRepository.findAll().size();

        // Create the ApPayeeType
        ApPayeeTypeDTO apPayeeTypeDTO = apPayeeTypeMapper.toDto(apPayeeType);
        restApPayeeTypeMockMvc.perform(post("/api/ap-payee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayeeTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ApPayeeType in the database
        List<ApPayeeType> apPayeeTypeList = apPayeeTypeRepository.findAll();
        assertThat(apPayeeTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ApPayeeType testApPayeeType = apPayeeTypeList.get(apPayeeTypeList.size() - 1);
        assertThat(testApPayeeType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createApPayeeTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apPayeeTypeRepository.findAll().size();

        // Create the ApPayeeType with an existing ID
        apPayeeType.setId(1L);
        ApPayeeTypeDTO apPayeeTypeDTO = apPayeeTypeMapper.toDto(apPayeeType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApPayeeTypeMockMvc.perform(post("/api/ap-payee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayeeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApPayeeType in the database
        List<ApPayeeType> apPayeeTypeList = apPayeeTypeRepository.findAll();
        assertThat(apPayeeTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllApPayeeTypes() throws Exception {
        // Initialize the database
        apPayeeTypeRepository.saveAndFlush(apPayeeType);

        // Get all the apPayeeTypeList
        restApPayeeTypeMockMvc.perform(get("/api/ap-payee-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apPayeeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getApPayeeType() throws Exception {
        // Initialize the database
        apPayeeTypeRepository.saveAndFlush(apPayeeType);

        // Get the apPayeeType
        restApPayeeTypeMockMvc.perform(get("/api/ap-payee-types/{id}", apPayeeType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apPayeeType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApPayeeType() throws Exception {
        // Get the apPayeeType
        restApPayeeTypeMockMvc.perform(get("/api/ap-payee-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApPayeeType() throws Exception {
        // Initialize the database
        apPayeeTypeRepository.saveAndFlush(apPayeeType);

        int databaseSizeBeforeUpdate = apPayeeTypeRepository.findAll().size();

        // Update the apPayeeType
        ApPayeeType updatedApPayeeType = apPayeeTypeRepository.findById(apPayeeType.getId()).get();
        // Disconnect from session so that the updates on updatedApPayeeType are not directly saved in db
        em.detach(updatedApPayeeType);
        updatedApPayeeType
            .name(UPDATED_NAME);
        ApPayeeTypeDTO apPayeeTypeDTO = apPayeeTypeMapper.toDto(updatedApPayeeType);

        restApPayeeTypeMockMvc.perform(put("/api/ap-payee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayeeTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ApPayeeType in the database
        List<ApPayeeType> apPayeeTypeList = apPayeeTypeRepository.findAll();
        assertThat(apPayeeTypeList).hasSize(databaseSizeBeforeUpdate);
        ApPayeeType testApPayeeType = apPayeeTypeList.get(apPayeeTypeList.size() - 1);
        assertThat(testApPayeeType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingApPayeeType() throws Exception {
        int databaseSizeBeforeUpdate = apPayeeTypeRepository.findAll().size();

        // Create the ApPayeeType
        ApPayeeTypeDTO apPayeeTypeDTO = apPayeeTypeMapper.toDto(apPayeeType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApPayeeTypeMockMvc.perform(put("/api/ap-payee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayeeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApPayeeType in the database
        List<ApPayeeType> apPayeeTypeList = apPayeeTypeRepository.findAll();
        assertThat(apPayeeTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApPayeeType() throws Exception {
        // Initialize the database
        apPayeeTypeRepository.saveAndFlush(apPayeeType);

        int databaseSizeBeforeDelete = apPayeeTypeRepository.findAll().size();

        // Delete the apPayeeType
        restApPayeeTypeMockMvc.perform(delete("/api/ap-payee-types/{id}", apPayeeType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApPayeeType> apPayeeTypeList = apPayeeTypeRepository.findAll();
        assertThat(apPayeeTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApPayeeType.class);
        ApPayeeType apPayeeType1 = new ApPayeeType();
        apPayeeType1.setId(1L);
        ApPayeeType apPayeeType2 = new ApPayeeType();
        apPayeeType2.setId(apPayeeType1.getId());
        assertThat(apPayeeType1).isEqualTo(apPayeeType2);
        apPayeeType2.setId(2L);
        assertThat(apPayeeType1).isNotEqualTo(apPayeeType2);
        apPayeeType1.setId(null);
        assertThat(apPayeeType1).isNotEqualTo(apPayeeType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApPayeeTypeDTO.class);
        ApPayeeTypeDTO apPayeeTypeDTO1 = new ApPayeeTypeDTO();
        apPayeeTypeDTO1.setId(1L);
        ApPayeeTypeDTO apPayeeTypeDTO2 = new ApPayeeTypeDTO();
        assertThat(apPayeeTypeDTO1).isNotEqualTo(apPayeeTypeDTO2);
        apPayeeTypeDTO2.setId(apPayeeTypeDTO1.getId());
        assertThat(apPayeeTypeDTO1).isEqualTo(apPayeeTypeDTO2);
        apPayeeTypeDTO2.setId(2L);
        assertThat(apPayeeTypeDTO1).isNotEqualTo(apPayeeTypeDTO2);
        apPayeeTypeDTO1.setId(null);
        assertThat(apPayeeTypeDTO1).isNotEqualTo(apPayeeTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(apPayeeTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(apPayeeTypeMapper.fromId(null)).isNull();
    }
}
