package com.eshipper.web.rest;

import com.eshipper.Eshipper1App;
import com.eshipper.config.TestSecurityConfiguration;
import com.eshipper.domain.ApPayee;
import com.eshipper.repository.ApPayeeRepository;
import com.eshipper.service.ApPayeeService;
import com.eshipper.service.dto.ApPayeeDTO;
import com.eshipper.service.mapper.ApPayeeMapper;
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
 * Integration tests for the {@link ApPayeeResource} REST controller.
 */
@SpringBootTest(classes = {Eshipper1App.class, TestSecurityConfiguration.class})
public class ApPayeeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ApPayeeRepository apPayeeRepository;

    @Autowired
    private ApPayeeMapper apPayeeMapper;

    @Autowired
    private ApPayeeService apPayeeService;

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

    private MockMvc restApPayeeMockMvc;

    private ApPayee apPayee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApPayeeResource apPayeeResource = new ApPayeeResource(apPayeeService);
        this.restApPayeeMockMvc = MockMvcBuilders.standaloneSetup(apPayeeResource)
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
    public static ApPayee createEntity(EntityManager em) {
        ApPayee apPayee = new ApPayee()
            .name(DEFAULT_NAME);
        return apPayee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApPayee createUpdatedEntity(EntityManager em) {
        ApPayee apPayee = new ApPayee()
            .name(UPDATED_NAME);
        return apPayee;
    }

    @BeforeEach
    public void initTest() {
        apPayee = createEntity(em);
    }

    @Test
    @Transactional
    public void createApPayee() throws Exception {
        int databaseSizeBeforeCreate = apPayeeRepository.findAll().size();

        // Create the ApPayee
        ApPayeeDTO apPayeeDTO = apPayeeMapper.toDto(apPayee);
        restApPayeeMockMvc.perform(post("/api/ap-payees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayeeDTO)))
            .andExpect(status().isCreated());

        // Validate the ApPayee in the database
        List<ApPayee> apPayeeList = apPayeeRepository.findAll();
        assertThat(apPayeeList).hasSize(databaseSizeBeforeCreate + 1);
        ApPayee testApPayee = apPayeeList.get(apPayeeList.size() - 1);
        assertThat(testApPayee.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createApPayeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apPayeeRepository.findAll().size();

        // Create the ApPayee with an existing ID
        apPayee.setId(1L);
        ApPayeeDTO apPayeeDTO = apPayeeMapper.toDto(apPayee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApPayeeMockMvc.perform(post("/api/ap-payees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApPayee in the database
        List<ApPayee> apPayeeList = apPayeeRepository.findAll();
        assertThat(apPayeeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllApPayees() throws Exception {
        // Initialize the database
        apPayeeRepository.saveAndFlush(apPayee);

        // Get all the apPayeeList
        restApPayeeMockMvc.perform(get("/api/ap-payees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apPayee.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getApPayee() throws Exception {
        // Initialize the database
        apPayeeRepository.saveAndFlush(apPayee);

        // Get the apPayee
        restApPayeeMockMvc.perform(get("/api/ap-payees/{id}", apPayee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apPayee.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApPayee() throws Exception {
        // Get the apPayee
        restApPayeeMockMvc.perform(get("/api/ap-payees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApPayee() throws Exception {
        // Initialize the database
        apPayeeRepository.saveAndFlush(apPayee);

        int databaseSizeBeforeUpdate = apPayeeRepository.findAll().size();

        // Update the apPayee
        ApPayee updatedApPayee = apPayeeRepository.findById(apPayee.getId()).get();
        // Disconnect from session so that the updates on updatedApPayee are not directly saved in db
        em.detach(updatedApPayee);
        updatedApPayee
            .name(UPDATED_NAME);
        ApPayeeDTO apPayeeDTO = apPayeeMapper.toDto(updatedApPayee);

        restApPayeeMockMvc.perform(put("/api/ap-payees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayeeDTO)))
            .andExpect(status().isOk());

        // Validate the ApPayee in the database
        List<ApPayee> apPayeeList = apPayeeRepository.findAll();
        assertThat(apPayeeList).hasSize(databaseSizeBeforeUpdate);
        ApPayee testApPayee = apPayeeList.get(apPayeeList.size() - 1);
        assertThat(testApPayee.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingApPayee() throws Exception {
        int databaseSizeBeforeUpdate = apPayeeRepository.findAll().size();

        // Create the ApPayee
        ApPayeeDTO apPayeeDTO = apPayeeMapper.toDto(apPayee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApPayeeMockMvc.perform(put("/api/ap-payees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApPayee in the database
        List<ApPayee> apPayeeList = apPayeeRepository.findAll();
        assertThat(apPayeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApPayee() throws Exception {
        // Initialize the database
        apPayeeRepository.saveAndFlush(apPayee);

        int databaseSizeBeforeDelete = apPayeeRepository.findAll().size();

        // Delete the apPayee
        restApPayeeMockMvc.perform(delete("/api/ap-payees/{id}", apPayee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApPayee> apPayeeList = apPayeeRepository.findAll();
        assertThat(apPayeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApPayee.class);
        ApPayee apPayee1 = new ApPayee();
        apPayee1.setId(1L);
        ApPayee apPayee2 = new ApPayee();
        apPayee2.setId(apPayee1.getId());
        assertThat(apPayee1).isEqualTo(apPayee2);
        apPayee2.setId(2L);
        assertThat(apPayee1).isNotEqualTo(apPayee2);
        apPayee1.setId(null);
        assertThat(apPayee1).isNotEqualTo(apPayee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApPayeeDTO.class);
        ApPayeeDTO apPayeeDTO1 = new ApPayeeDTO();
        apPayeeDTO1.setId(1L);
        ApPayeeDTO apPayeeDTO2 = new ApPayeeDTO();
        assertThat(apPayeeDTO1).isNotEqualTo(apPayeeDTO2);
        apPayeeDTO2.setId(apPayeeDTO1.getId());
        assertThat(apPayeeDTO1).isEqualTo(apPayeeDTO2);
        apPayeeDTO2.setId(2L);
        assertThat(apPayeeDTO1).isNotEqualTo(apPayeeDTO2);
        apPayeeDTO1.setId(null);
        assertThat(apPayeeDTO1).isNotEqualTo(apPayeeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(apPayeeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(apPayeeMapper.fromId(null)).isNull();
    }
}
