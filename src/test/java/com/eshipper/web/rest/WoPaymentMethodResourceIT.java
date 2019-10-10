package com.eshipper.web.rest;

import com.eshipper.Eshipper1App;
import com.eshipper.config.TestSecurityConfiguration;
import com.eshipper.domain.WoPaymentMethod;
import com.eshipper.repository.WoPaymentMethodRepository;
import com.eshipper.service.WoPaymentMethodService;
import com.eshipper.service.dto.WoPaymentMethodDTO;
import com.eshipper.service.mapper.WoPaymentMethodMapper;
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
 * Integration tests for the {@link WoPaymentMethodResource} REST controller.
 */
@SpringBootTest(classes = {Eshipper1App.class, TestSecurityConfiguration.class})
public class WoPaymentMethodResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private WoPaymentMethodRepository woPaymentMethodRepository;

    @Autowired
    private WoPaymentMethodMapper woPaymentMethodMapper;

    @Autowired
    private WoPaymentMethodService woPaymentMethodService;

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

    private MockMvc restWoPaymentMethodMockMvc;

    private WoPaymentMethod woPaymentMethod;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoPaymentMethodResource woPaymentMethodResource = new WoPaymentMethodResource(woPaymentMethodService);
        this.restWoPaymentMethodMockMvc = MockMvcBuilders.standaloneSetup(woPaymentMethodResource)
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
    public static WoPaymentMethod createEntity(EntityManager em) {
        WoPaymentMethod woPaymentMethod = new WoPaymentMethod()
            .name(DEFAULT_NAME);
        return woPaymentMethod;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoPaymentMethod createUpdatedEntity(EntityManager em) {
        WoPaymentMethod woPaymentMethod = new WoPaymentMethod()
            .name(UPDATED_NAME);
        return woPaymentMethod;
    }

    @BeforeEach
    public void initTest() {
        woPaymentMethod = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoPaymentMethod() throws Exception {
        int databaseSizeBeforeCreate = woPaymentMethodRepository.findAll().size();

        // Create the WoPaymentMethod
        WoPaymentMethodDTO woPaymentMethodDTO = woPaymentMethodMapper.toDto(woPaymentMethod);
        restWoPaymentMethodMockMvc.perform(post("/api/wo-payment-methods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPaymentMethodDTO)))
            .andExpect(status().isCreated());

        // Validate the WoPaymentMethod in the database
        List<WoPaymentMethod> woPaymentMethodList = woPaymentMethodRepository.findAll();
        assertThat(woPaymentMethodList).hasSize(databaseSizeBeforeCreate + 1);
        WoPaymentMethod testWoPaymentMethod = woPaymentMethodList.get(woPaymentMethodList.size() - 1);
        assertThat(testWoPaymentMethod.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createWoPaymentMethodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woPaymentMethodRepository.findAll().size();

        // Create the WoPaymentMethod with an existing ID
        woPaymentMethod.setId(1L);
        WoPaymentMethodDTO woPaymentMethodDTO = woPaymentMethodMapper.toDto(woPaymentMethod);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoPaymentMethodMockMvc.perform(post("/api/wo-payment-methods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPaymentMethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoPaymentMethod in the database
        List<WoPaymentMethod> woPaymentMethodList = woPaymentMethodRepository.findAll();
        assertThat(woPaymentMethodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoPaymentMethods() throws Exception {
        // Initialize the database
        woPaymentMethodRepository.saveAndFlush(woPaymentMethod);

        // Get all the woPaymentMethodList
        restWoPaymentMethodMockMvc.perform(get("/api/wo-payment-methods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woPaymentMethod.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getWoPaymentMethod() throws Exception {
        // Initialize the database
        woPaymentMethodRepository.saveAndFlush(woPaymentMethod);

        // Get the woPaymentMethod
        restWoPaymentMethodMockMvc.perform(get("/api/wo-payment-methods/{id}", woPaymentMethod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woPaymentMethod.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoPaymentMethod() throws Exception {
        // Get the woPaymentMethod
        restWoPaymentMethodMockMvc.perform(get("/api/wo-payment-methods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoPaymentMethod() throws Exception {
        // Initialize the database
        woPaymentMethodRepository.saveAndFlush(woPaymentMethod);

        int databaseSizeBeforeUpdate = woPaymentMethodRepository.findAll().size();

        // Update the woPaymentMethod
        WoPaymentMethod updatedWoPaymentMethod = woPaymentMethodRepository.findById(woPaymentMethod.getId()).get();
        // Disconnect from session so that the updates on updatedWoPaymentMethod are not directly saved in db
        em.detach(updatedWoPaymentMethod);
        updatedWoPaymentMethod
            .name(UPDATED_NAME);
        WoPaymentMethodDTO woPaymentMethodDTO = woPaymentMethodMapper.toDto(updatedWoPaymentMethod);

        restWoPaymentMethodMockMvc.perform(put("/api/wo-payment-methods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPaymentMethodDTO)))
            .andExpect(status().isOk());

        // Validate the WoPaymentMethod in the database
        List<WoPaymentMethod> woPaymentMethodList = woPaymentMethodRepository.findAll();
        assertThat(woPaymentMethodList).hasSize(databaseSizeBeforeUpdate);
        WoPaymentMethod testWoPaymentMethod = woPaymentMethodList.get(woPaymentMethodList.size() - 1);
        assertThat(testWoPaymentMethod.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingWoPaymentMethod() throws Exception {
        int databaseSizeBeforeUpdate = woPaymentMethodRepository.findAll().size();

        // Create the WoPaymentMethod
        WoPaymentMethodDTO woPaymentMethodDTO = woPaymentMethodMapper.toDto(woPaymentMethod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoPaymentMethodMockMvc.perform(put("/api/wo-payment-methods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPaymentMethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoPaymentMethod in the database
        List<WoPaymentMethod> woPaymentMethodList = woPaymentMethodRepository.findAll();
        assertThat(woPaymentMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoPaymentMethod() throws Exception {
        // Initialize the database
        woPaymentMethodRepository.saveAndFlush(woPaymentMethod);

        int databaseSizeBeforeDelete = woPaymentMethodRepository.findAll().size();

        // Delete the woPaymentMethod
        restWoPaymentMethodMockMvc.perform(delete("/api/wo-payment-methods/{id}", woPaymentMethod.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WoPaymentMethod> woPaymentMethodList = woPaymentMethodRepository.findAll();
        assertThat(woPaymentMethodList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoPaymentMethod.class);
        WoPaymentMethod woPaymentMethod1 = new WoPaymentMethod();
        woPaymentMethod1.setId(1L);
        WoPaymentMethod woPaymentMethod2 = new WoPaymentMethod();
        woPaymentMethod2.setId(woPaymentMethod1.getId());
        assertThat(woPaymentMethod1).isEqualTo(woPaymentMethod2);
        woPaymentMethod2.setId(2L);
        assertThat(woPaymentMethod1).isNotEqualTo(woPaymentMethod2);
        woPaymentMethod1.setId(null);
        assertThat(woPaymentMethod1).isNotEqualTo(woPaymentMethod2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoPaymentMethodDTO.class);
        WoPaymentMethodDTO woPaymentMethodDTO1 = new WoPaymentMethodDTO();
        woPaymentMethodDTO1.setId(1L);
        WoPaymentMethodDTO woPaymentMethodDTO2 = new WoPaymentMethodDTO();
        assertThat(woPaymentMethodDTO1).isNotEqualTo(woPaymentMethodDTO2);
        woPaymentMethodDTO2.setId(woPaymentMethodDTO1.getId());
        assertThat(woPaymentMethodDTO1).isEqualTo(woPaymentMethodDTO2);
        woPaymentMethodDTO2.setId(2L);
        assertThat(woPaymentMethodDTO1).isNotEqualTo(woPaymentMethodDTO2);
        woPaymentMethodDTO1.setId(null);
        assertThat(woPaymentMethodDTO1).isNotEqualTo(woPaymentMethodDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woPaymentMethodMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woPaymentMethodMapper.fromId(null)).isNull();
    }
}
