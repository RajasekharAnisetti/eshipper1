package com.eshipper.web.rest;

import com.eshipper.Eshipper1App;
import com.eshipper.config.TestSecurityConfiguration;
import com.eshipper.domain.ApPayableCreditNotesTrans;
import com.eshipper.repository.ApPayableCreditNotesTransRepository;
import com.eshipper.service.ApPayableCreditNotesTransService;
import com.eshipper.service.dto.ApPayableCreditNotesTransDTO;
import com.eshipper.service.mapper.ApPayableCreditNotesTransMapper;
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
 * Integration tests for the {@link ApPayableCreditNotesTransResource} REST controller.
 */
@SpringBootTest(classes = {Eshipper1App.class, TestSecurityConfiguration.class})
public class ApPayableCreditNotesTransResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AMOUNT = 11;
    private static final Integer UPDATED_AMOUNT = 10;
    private static final Integer SMALLER_AMOUNT = 11 - 1;

    @Autowired
    private ApPayableCreditNotesTransRepository apPayableCreditNotesTransRepository;

    @Autowired
    private ApPayableCreditNotesTransMapper apPayableCreditNotesTransMapper;

    @Autowired
    private ApPayableCreditNotesTransService apPayableCreditNotesTransService;

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

    private MockMvc restApPayableCreditNotesTransMockMvc;

    private ApPayableCreditNotesTrans apPayableCreditNotesTrans;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApPayableCreditNotesTransResource apPayableCreditNotesTransResource = new ApPayableCreditNotesTransResource(apPayableCreditNotesTransService);
        this.restApPayableCreditNotesTransMockMvc = MockMvcBuilders.standaloneSetup(apPayableCreditNotesTransResource)
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
    public static ApPayableCreditNotesTrans createEntity(EntityManager em) {
        ApPayableCreditNotesTrans apPayableCreditNotesTrans = new ApPayableCreditNotesTrans()
            .type(DEFAULT_TYPE)
            .amount(DEFAULT_AMOUNT);
        return apPayableCreditNotesTrans;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApPayableCreditNotesTrans createUpdatedEntity(EntityManager em) {
        ApPayableCreditNotesTrans apPayableCreditNotesTrans = new ApPayableCreditNotesTrans()
            .type(UPDATED_TYPE)
            .amount(UPDATED_AMOUNT);
        return apPayableCreditNotesTrans;
    }

    @BeforeEach
    public void initTest() {
        apPayableCreditNotesTrans = createEntity(em);
    }

    @Test
    @Transactional
    public void createApPayableCreditNotesTrans() throws Exception {
        int databaseSizeBeforeCreate = apPayableCreditNotesTransRepository.findAll().size();

        // Create the ApPayableCreditNotesTrans
        ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO = apPayableCreditNotesTransMapper.toDto(apPayableCreditNotesTrans);
        restApPayableCreditNotesTransMockMvc.perform(post("/api/ap-payable-credit-notes-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayableCreditNotesTransDTO)))
            .andExpect(status().isCreated());

        // Validate the ApPayableCreditNotesTrans in the database
        List<ApPayableCreditNotesTrans> apPayableCreditNotesTransList = apPayableCreditNotesTransRepository.findAll();
        assertThat(apPayableCreditNotesTransList).hasSize(databaseSizeBeforeCreate + 1);
        ApPayableCreditNotesTrans testApPayableCreditNotesTrans = apPayableCreditNotesTransList.get(apPayableCreditNotesTransList.size() - 1);
        assertThat(testApPayableCreditNotesTrans.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testApPayableCreditNotesTrans.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createApPayableCreditNotesTransWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apPayableCreditNotesTransRepository.findAll().size();

        // Create the ApPayableCreditNotesTrans with an existing ID
        apPayableCreditNotesTrans.setId(1L);
        ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO = apPayableCreditNotesTransMapper.toDto(apPayableCreditNotesTrans);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApPayableCreditNotesTransMockMvc.perform(post("/api/ap-payable-credit-notes-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayableCreditNotesTransDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApPayableCreditNotesTrans in the database
        List<ApPayableCreditNotesTrans> apPayableCreditNotesTransList = apPayableCreditNotesTransRepository.findAll();
        assertThat(apPayableCreditNotesTransList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllApPayableCreditNotesTrans() throws Exception {
        // Initialize the database
        apPayableCreditNotesTransRepository.saveAndFlush(apPayableCreditNotesTrans);

        // Get all the apPayableCreditNotesTransList
        restApPayableCreditNotesTransMockMvc.perform(get("/api/ap-payable-credit-notes-trans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apPayableCreditNotesTrans.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getApPayableCreditNotesTrans() throws Exception {
        // Initialize the database
        apPayableCreditNotesTransRepository.saveAndFlush(apPayableCreditNotesTrans);

        // Get the apPayableCreditNotesTrans
        restApPayableCreditNotesTransMockMvc.perform(get("/api/ap-payable-credit-notes-trans/{id}", apPayableCreditNotesTrans.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apPayableCreditNotesTrans.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT));
    }

    @Test
    @Transactional
    public void getNonExistingApPayableCreditNotesTrans() throws Exception {
        // Get the apPayableCreditNotesTrans
        restApPayableCreditNotesTransMockMvc.perform(get("/api/ap-payable-credit-notes-trans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApPayableCreditNotesTrans() throws Exception {
        // Initialize the database
        apPayableCreditNotesTransRepository.saveAndFlush(apPayableCreditNotesTrans);

        int databaseSizeBeforeUpdate = apPayableCreditNotesTransRepository.findAll().size();

        // Update the apPayableCreditNotesTrans
        ApPayableCreditNotesTrans updatedApPayableCreditNotesTrans = apPayableCreditNotesTransRepository.findById(apPayableCreditNotesTrans.getId()).get();
        // Disconnect from session so that the updates on updatedApPayableCreditNotesTrans are not directly saved in db
        em.detach(updatedApPayableCreditNotesTrans);
        updatedApPayableCreditNotesTrans
            .type(UPDATED_TYPE)
            .amount(UPDATED_AMOUNT);
        ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO = apPayableCreditNotesTransMapper.toDto(updatedApPayableCreditNotesTrans);

        restApPayableCreditNotesTransMockMvc.perform(put("/api/ap-payable-credit-notes-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayableCreditNotesTransDTO)))
            .andExpect(status().isOk());

        // Validate the ApPayableCreditNotesTrans in the database
        List<ApPayableCreditNotesTrans> apPayableCreditNotesTransList = apPayableCreditNotesTransRepository.findAll();
        assertThat(apPayableCreditNotesTransList).hasSize(databaseSizeBeforeUpdate);
        ApPayableCreditNotesTrans testApPayableCreditNotesTrans = apPayableCreditNotesTransList.get(apPayableCreditNotesTransList.size() - 1);
        assertThat(testApPayableCreditNotesTrans.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testApPayableCreditNotesTrans.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingApPayableCreditNotesTrans() throws Exception {
        int databaseSizeBeforeUpdate = apPayableCreditNotesTransRepository.findAll().size();

        // Create the ApPayableCreditNotesTrans
        ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO = apPayableCreditNotesTransMapper.toDto(apPayableCreditNotesTrans);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApPayableCreditNotesTransMockMvc.perform(put("/api/ap-payable-credit-notes-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayableCreditNotesTransDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApPayableCreditNotesTrans in the database
        List<ApPayableCreditNotesTrans> apPayableCreditNotesTransList = apPayableCreditNotesTransRepository.findAll();
        assertThat(apPayableCreditNotesTransList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApPayableCreditNotesTrans() throws Exception {
        // Initialize the database
        apPayableCreditNotesTransRepository.saveAndFlush(apPayableCreditNotesTrans);

        int databaseSizeBeforeDelete = apPayableCreditNotesTransRepository.findAll().size();

        // Delete the apPayableCreditNotesTrans
        restApPayableCreditNotesTransMockMvc.perform(delete("/api/ap-payable-credit-notes-trans/{id}", apPayableCreditNotesTrans.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApPayableCreditNotesTrans> apPayableCreditNotesTransList = apPayableCreditNotesTransRepository.findAll();
        assertThat(apPayableCreditNotesTransList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApPayableCreditNotesTrans.class);
        ApPayableCreditNotesTrans apPayableCreditNotesTrans1 = new ApPayableCreditNotesTrans();
        apPayableCreditNotesTrans1.setId(1L);
        ApPayableCreditNotesTrans apPayableCreditNotesTrans2 = new ApPayableCreditNotesTrans();
        apPayableCreditNotesTrans2.setId(apPayableCreditNotesTrans1.getId());
        assertThat(apPayableCreditNotesTrans1).isEqualTo(apPayableCreditNotesTrans2);
        apPayableCreditNotesTrans2.setId(2L);
        assertThat(apPayableCreditNotesTrans1).isNotEqualTo(apPayableCreditNotesTrans2);
        apPayableCreditNotesTrans1.setId(null);
        assertThat(apPayableCreditNotesTrans1).isNotEqualTo(apPayableCreditNotesTrans2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApPayableCreditNotesTransDTO.class);
        ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO1 = new ApPayableCreditNotesTransDTO();
        apPayableCreditNotesTransDTO1.setId(1L);
        ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO2 = new ApPayableCreditNotesTransDTO();
        assertThat(apPayableCreditNotesTransDTO1).isNotEqualTo(apPayableCreditNotesTransDTO2);
        apPayableCreditNotesTransDTO2.setId(apPayableCreditNotesTransDTO1.getId());
        assertThat(apPayableCreditNotesTransDTO1).isEqualTo(apPayableCreditNotesTransDTO2);
        apPayableCreditNotesTransDTO2.setId(2L);
        assertThat(apPayableCreditNotesTransDTO1).isNotEqualTo(apPayableCreditNotesTransDTO2);
        apPayableCreditNotesTransDTO1.setId(null);
        assertThat(apPayableCreditNotesTransDTO1).isNotEqualTo(apPayableCreditNotesTransDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(apPayableCreditNotesTransMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(apPayableCreditNotesTransMapper.fromId(null)).isNull();
    }
}
