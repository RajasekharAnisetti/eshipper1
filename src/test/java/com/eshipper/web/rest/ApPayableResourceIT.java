package com.eshipper.web.rest;

import com.eshipper.Eshipper1App;
import com.eshipper.config.TestSecurityConfiguration;
import com.eshipper.domain.ApPayable;
import com.eshipper.repository.ApPayableRepository;
import com.eshipper.service.ApPayableService;
import com.eshipper.service.dto.ApPayableDTO;
import com.eshipper.service.mapper.ApPayableMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ApPayableResource} REST controller.
 */
@SpringBootTest(classes = {Eshipper1App.class, TestSecurityConfiguration.class})
public class ApPayableResourceIT {

    private static final LocalDate DEFAULT_INVOICE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INVOICE_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_INVOICE_DATE = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_INVOICE_AMOUNT = 11;
    private static final Integer UPDATED_INVOICE_AMOUNT = 10;
    private static final Integer SMALLER_INVOICE_AMOUNT = 11 - 1;

    private static final String DEFAULT_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CREDIT = false;
    private static final Boolean UPDATED_IS_CREDIT = true;

    private static final Boolean DEFAULT_IS_CREDIT_NOTE = false;
    private static final Boolean UPDATED_IS_CREDIT_NOTE = true;

    private static final Boolean DEFAULT_IS_DISPUTE = false;
    private static final Boolean UPDATED_IS_DISPUTE = true;

    private static final String DEFAULT_DOC_PATH = "AAAAAAAAAA";
    private static final String UPDATED_DOC_PATH = "BBBBBBBBBB";

    private static final Float DEFAULT_SUB_TOTAL = 1F;
    private static final Float UPDATED_SUB_TOTAL = 2F;
    private static final Float SMALLER_SUB_TOTAL = 1F - 1F;

    private static final Float DEFAULT_GST = 1F;
    private static final Float UPDATED_GST = 2F;
    private static final Float SMALLER_GST = 1F - 1F;

    private static final Float DEFAULT_HST = 1F;
    private static final Float UPDATED_HST = 2F;
    private static final Float SMALLER_HST = 1F - 1F;

    private static final Float DEFAULT_PST = 1F;
    private static final Float UPDATED_PST = 2F;
    private static final Float SMALLER_PST = 1F - 1F;

    private static final Float DEFAULT_QST = 1F;
    private static final Float UPDATED_QST = 2F;
    private static final Float SMALLER_QST = 1F - 1F;

    private static final Float DEFAULT_TOTAL_AMOUNT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT = 2F;
    private static final Float SMALLER_TOTAL_AMOUNT = 1F - 1F;

    private static final Float DEFAULT_BALANCE_DUE = 1F;
    private static final Float UPDATED_BALANCE_DUE = 2F;
    private static final Float SMALLER_BALANCE_DUE = 1F - 1F;

    @Autowired
    private ApPayableRepository apPayableRepository;

    @Autowired
    private ApPayableMapper apPayableMapper;

    @Autowired
    private ApPayableService apPayableService;

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

    private MockMvc restApPayableMockMvc;

    private ApPayable apPayable;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApPayableResource apPayableResource = new ApPayableResource(apPayableService);
        this.restApPayableMockMvc = MockMvcBuilders.standaloneSetup(apPayableResource)
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
    public static ApPayable createEntity(EntityManager em) {
        ApPayable apPayable = new ApPayable()
            .invoiceDate(DEFAULT_INVOICE_DATE)
            .invoiceAmount(DEFAULT_INVOICE_AMOUNT)
            .invoiceNo(DEFAULT_INVOICE_NO)
            .comment(DEFAULT_COMMENT)
            .isCredit(DEFAULT_IS_CREDIT)
            .isCreditNote(DEFAULT_IS_CREDIT_NOTE)
            .isDispute(DEFAULT_IS_DISPUTE)
            .docPath(DEFAULT_DOC_PATH)
            .subTotal(DEFAULT_SUB_TOTAL)
            .gst(DEFAULT_GST)
            .hst(DEFAULT_HST)
            .pst(DEFAULT_PST)
            .qst(DEFAULT_QST)
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .balanceDue(DEFAULT_BALANCE_DUE);
        return apPayable;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApPayable createUpdatedEntity(EntityManager em) {
        ApPayable apPayable = new ApPayable()
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .invoiceNo(UPDATED_INVOICE_NO)
            .comment(UPDATED_COMMENT)
            .isCredit(UPDATED_IS_CREDIT)
            .isCreditNote(UPDATED_IS_CREDIT_NOTE)
            .isDispute(UPDATED_IS_DISPUTE)
            .docPath(UPDATED_DOC_PATH)
            .subTotal(UPDATED_SUB_TOTAL)
            .gst(UPDATED_GST)
            .hst(UPDATED_HST)
            .pst(UPDATED_PST)
            .qst(UPDATED_QST)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .balanceDue(UPDATED_BALANCE_DUE);
        return apPayable;
    }

    @BeforeEach
    public void initTest() {
        apPayable = createEntity(em);
    }

    @Test
    @Transactional
    public void createApPayable() throws Exception {
        int databaseSizeBeforeCreate = apPayableRepository.findAll().size();

        // Create the ApPayable
        ApPayableDTO apPayableDTO = apPayableMapper.toDto(apPayable);
        restApPayableMockMvc.perform(post("/api/ap-payables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayableDTO)))
            .andExpect(status().isCreated());

        // Validate the ApPayable in the database
        List<ApPayable> apPayableList = apPayableRepository.findAll();
        assertThat(apPayableList).hasSize(databaseSizeBeforeCreate + 1);
        ApPayable testApPayable = apPayableList.get(apPayableList.size() - 1);
        assertThat(testApPayable.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
        assertThat(testApPayable.getInvoiceAmount()).isEqualTo(DEFAULT_INVOICE_AMOUNT);
        assertThat(testApPayable.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testApPayable.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testApPayable.isIsCredit()).isEqualTo(DEFAULT_IS_CREDIT);
        assertThat(testApPayable.isIsCreditNote()).isEqualTo(DEFAULT_IS_CREDIT_NOTE);
        assertThat(testApPayable.isIsDispute()).isEqualTo(DEFAULT_IS_DISPUTE);
        assertThat(testApPayable.getDocPath()).isEqualTo(DEFAULT_DOC_PATH);
        assertThat(testApPayable.getSubTotal()).isEqualTo(DEFAULT_SUB_TOTAL);
        assertThat(testApPayable.getGst()).isEqualTo(DEFAULT_GST);
        assertThat(testApPayable.getHst()).isEqualTo(DEFAULT_HST);
        assertThat(testApPayable.getPst()).isEqualTo(DEFAULT_PST);
        assertThat(testApPayable.getQst()).isEqualTo(DEFAULT_QST);
        assertThat(testApPayable.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testApPayable.getBalanceDue()).isEqualTo(DEFAULT_BALANCE_DUE);
    }

    @Test
    @Transactional
    public void createApPayableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apPayableRepository.findAll().size();

        // Create the ApPayable with an existing ID
        apPayable.setId(1L);
        ApPayableDTO apPayableDTO = apPayableMapper.toDto(apPayable);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApPayableMockMvc.perform(post("/api/ap-payables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApPayable in the database
        List<ApPayable> apPayableList = apPayableRepository.findAll();
        assertThat(apPayableList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllApPayables() throws Exception {
        // Initialize the database
        apPayableRepository.saveAndFlush(apPayable);

        // Get all the apPayableList
        restApPayableMockMvc.perform(get("/api/ap-payables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apPayable.getId().intValue())))
            .andExpect(jsonPath("$.[*].invoiceDate").value(hasItem(DEFAULT_INVOICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].invoiceAmount").value(hasItem(DEFAULT_INVOICE_AMOUNT)))
            .andExpect(jsonPath("$.[*].invoiceNo").value(hasItem(DEFAULT_INVOICE_NO.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].isCredit").value(hasItem(DEFAULT_IS_CREDIT.booleanValue())))
            .andExpect(jsonPath("$.[*].isCreditNote").value(hasItem(DEFAULT_IS_CREDIT_NOTE.booleanValue())))
            .andExpect(jsonPath("$.[*].isDispute").value(hasItem(DEFAULT_IS_DISPUTE.booleanValue())))
            .andExpect(jsonPath("$.[*].docPath").value(hasItem(DEFAULT_DOC_PATH.toString())))
            .andExpect(jsonPath("$.[*].subTotal").value(hasItem(DEFAULT_SUB_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].gst").value(hasItem(DEFAULT_GST.doubleValue())))
            .andExpect(jsonPath("$.[*].hst").value(hasItem(DEFAULT_HST.doubleValue())))
            .andExpect(jsonPath("$.[*].pst").value(hasItem(DEFAULT_PST.doubleValue())))
            .andExpect(jsonPath("$.[*].qst").value(hasItem(DEFAULT_QST.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].balanceDue").value(hasItem(DEFAULT_BALANCE_DUE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getApPayable() throws Exception {
        // Initialize the database
        apPayableRepository.saveAndFlush(apPayable);

        // Get the apPayable
        restApPayableMockMvc.perform(get("/api/ap-payables/{id}", apPayable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apPayable.getId().intValue()))
            .andExpect(jsonPath("$.invoiceDate").value(DEFAULT_INVOICE_DATE.toString()))
            .andExpect(jsonPath("$.invoiceAmount").value(DEFAULT_INVOICE_AMOUNT))
            .andExpect(jsonPath("$.invoiceNo").value(DEFAULT_INVOICE_NO.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.isCredit").value(DEFAULT_IS_CREDIT.booleanValue()))
            .andExpect(jsonPath("$.isCreditNote").value(DEFAULT_IS_CREDIT_NOTE.booleanValue()))
            .andExpect(jsonPath("$.isDispute").value(DEFAULT_IS_DISPUTE.booleanValue()))
            .andExpect(jsonPath("$.docPath").value(DEFAULT_DOC_PATH.toString()))
            .andExpect(jsonPath("$.subTotal").value(DEFAULT_SUB_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.gst").value(DEFAULT_GST.doubleValue()))
            .andExpect(jsonPath("$.hst").value(DEFAULT_HST.doubleValue()))
            .andExpect(jsonPath("$.pst").value(DEFAULT_PST.doubleValue()))
            .andExpect(jsonPath("$.qst").value(DEFAULT_QST.doubleValue()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.balanceDue").value(DEFAULT_BALANCE_DUE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingApPayable() throws Exception {
        // Get the apPayable
        restApPayableMockMvc.perform(get("/api/ap-payables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApPayable() throws Exception {
        // Initialize the database
        apPayableRepository.saveAndFlush(apPayable);

        int databaseSizeBeforeUpdate = apPayableRepository.findAll().size();

        // Update the apPayable
        ApPayable updatedApPayable = apPayableRepository.findById(apPayable.getId()).get();
        // Disconnect from session so that the updates on updatedApPayable are not directly saved in db
        em.detach(updatedApPayable);
        updatedApPayable
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .invoiceNo(UPDATED_INVOICE_NO)
            .comment(UPDATED_COMMENT)
            .isCredit(UPDATED_IS_CREDIT)
            .isCreditNote(UPDATED_IS_CREDIT_NOTE)
            .isDispute(UPDATED_IS_DISPUTE)
            .docPath(UPDATED_DOC_PATH)
            .subTotal(UPDATED_SUB_TOTAL)
            .gst(UPDATED_GST)
            .hst(UPDATED_HST)
            .pst(UPDATED_PST)
            .qst(UPDATED_QST)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .balanceDue(UPDATED_BALANCE_DUE);
        ApPayableDTO apPayableDTO = apPayableMapper.toDto(updatedApPayable);

        restApPayableMockMvc.perform(put("/api/ap-payables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayableDTO)))
            .andExpect(status().isOk());

        // Validate the ApPayable in the database
        List<ApPayable> apPayableList = apPayableRepository.findAll();
        assertThat(apPayableList).hasSize(databaseSizeBeforeUpdate);
        ApPayable testApPayable = apPayableList.get(apPayableList.size() - 1);
        assertThat(testApPayable.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testApPayable.getInvoiceAmount()).isEqualTo(UPDATED_INVOICE_AMOUNT);
        assertThat(testApPayable.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testApPayable.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testApPayable.isIsCredit()).isEqualTo(UPDATED_IS_CREDIT);
        assertThat(testApPayable.isIsCreditNote()).isEqualTo(UPDATED_IS_CREDIT_NOTE);
        assertThat(testApPayable.isIsDispute()).isEqualTo(UPDATED_IS_DISPUTE);
        assertThat(testApPayable.getDocPath()).isEqualTo(UPDATED_DOC_PATH);
        assertThat(testApPayable.getSubTotal()).isEqualTo(UPDATED_SUB_TOTAL);
        assertThat(testApPayable.getGst()).isEqualTo(UPDATED_GST);
        assertThat(testApPayable.getHst()).isEqualTo(UPDATED_HST);
        assertThat(testApPayable.getPst()).isEqualTo(UPDATED_PST);
        assertThat(testApPayable.getQst()).isEqualTo(UPDATED_QST);
        assertThat(testApPayable.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testApPayable.getBalanceDue()).isEqualTo(UPDATED_BALANCE_DUE);
    }

    @Test
    @Transactional
    public void updateNonExistingApPayable() throws Exception {
        int databaseSizeBeforeUpdate = apPayableRepository.findAll().size();

        // Create the ApPayable
        ApPayableDTO apPayableDTO = apPayableMapper.toDto(apPayable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApPayableMockMvc.perform(put("/api/ap-payables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apPayableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApPayable in the database
        List<ApPayable> apPayableList = apPayableRepository.findAll();
        assertThat(apPayableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApPayable() throws Exception {
        // Initialize the database
        apPayableRepository.saveAndFlush(apPayable);

        int databaseSizeBeforeDelete = apPayableRepository.findAll().size();

        // Delete the apPayable
        restApPayableMockMvc.perform(delete("/api/ap-payables/{id}", apPayable.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApPayable> apPayableList = apPayableRepository.findAll();
        assertThat(apPayableList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApPayable.class);
        ApPayable apPayable1 = new ApPayable();
        apPayable1.setId(1L);
        ApPayable apPayable2 = new ApPayable();
        apPayable2.setId(apPayable1.getId());
        assertThat(apPayable1).isEqualTo(apPayable2);
        apPayable2.setId(2L);
        assertThat(apPayable1).isNotEqualTo(apPayable2);
        apPayable1.setId(null);
        assertThat(apPayable1).isNotEqualTo(apPayable2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApPayableDTO.class);
        ApPayableDTO apPayableDTO1 = new ApPayableDTO();
        apPayableDTO1.setId(1L);
        ApPayableDTO apPayableDTO2 = new ApPayableDTO();
        assertThat(apPayableDTO1).isNotEqualTo(apPayableDTO2);
        apPayableDTO2.setId(apPayableDTO1.getId());
        assertThat(apPayableDTO1).isEqualTo(apPayableDTO2);
        apPayableDTO2.setId(2L);
        assertThat(apPayableDTO1).isNotEqualTo(apPayableDTO2);
        apPayableDTO1.setId(null);
        assertThat(apPayableDTO1).isNotEqualTo(apPayableDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(apPayableMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(apPayableMapper.fromId(null)).isNull();
    }
}
