package com.eshipper.web.rest;

import com.eshipper.service.ApPayableCreditNotesTransService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ApPayableCreditNotesTransDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.ApPayableCreditNotesTrans}.
 */
@RestController
@RequestMapping("/api")
public class ApPayableCreditNotesTransResource {

    private final Logger log = LoggerFactory.getLogger(ApPayableCreditNotesTransResource.class);

    private static final String ENTITY_NAME = "apPayableCreditNotesTrans";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApPayableCreditNotesTransService apPayableCreditNotesTransService;

    public ApPayableCreditNotesTransResource(ApPayableCreditNotesTransService apPayableCreditNotesTransService) {
        this.apPayableCreditNotesTransService = apPayableCreditNotesTransService;
    }

    /**
     * {@code POST  /ap-payable-credit-notes-trans} : Create a new apPayableCreditNotesTrans.
     *
     * @param apPayableCreditNotesTransDTO the apPayableCreditNotesTransDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apPayableCreditNotesTransDTO, or with status {@code 400 (Bad Request)} if the apPayableCreditNotesTrans has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ap-payable-credit-notes-trans")
    public ResponseEntity<ApPayableCreditNotesTransDTO> createApPayableCreditNotesTrans(@Valid @RequestBody ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO) throws URISyntaxException {
        log.debug("REST request to save ApPayableCreditNotesTrans : {}", apPayableCreditNotesTransDTO);
        if (apPayableCreditNotesTransDTO.getId() != null) {
            throw new BadRequestAlertException("A new apPayableCreditNotesTrans cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApPayableCreditNotesTransDTO result = apPayableCreditNotesTransService.save(apPayableCreditNotesTransDTO);
        return ResponseEntity.created(new URI("/api/ap-payable-credit-notes-trans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ap-payable-credit-notes-trans} : Updates an existing apPayableCreditNotesTrans.
     *
     * @param apPayableCreditNotesTransDTO the apPayableCreditNotesTransDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apPayableCreditNotesTransDTO,
     * or with status {@code 400 (Bad Request)} if the apPayableCreditNotesTransDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apPayableCreditNotesTransDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ap-payable-credit-notes-trans")
    public ResponseEntity<ApPayableCreditNotesTransDTO> updateApPayableCreditNotesTrans(@Valid @RequestBody ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO) throws URISyntaxException {
        log.debug("REST request to update ApPayableCreditNotesTrans : {}", apPayableCreditNotesTransDTO);
        if (apPayableCreditNotesTransDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApPayableCreditNotesTransDTO result = apPayableCreditNotesTransService.save(apPayableCreditNotesTransDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apPayableCreditNotesTransDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ap-payable-credit-notes-trans} : get all the apPayableCreditNotesTrans.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apPayableCreditNotesTrans in body.
     */
    @GetMapping("/ap-payable-credit-notes-trans")
    public List<ApPayableCreditNotesTransDTO> getAllApPayableCreditNotesTrans() {
        log.debug("REST request to get all ApPayableCreditNotesTrans");
        return apPayableCreditNotesTransService.findAll();
    }

    /**
     * {@code GET  /ap-payable-credit-notes-trans/:id} : get the "id" apPayableCreditNotesTrans.
     *
     * @param id the id of the apPayableCreditNotesTransDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apPayableCreditNotesTransDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ap-payable-credit-notes-trans/{id}")
    public ResponseEntity<ApPayableCreditNotesTransDTO> getApPayableCreditNotesTrans(@PathVariable Long id) {
        log.debug("REST request to get ApPayableCreditNotesTrans : {}", id);
        Optional<ApPayableCreditNotesTransDTO> apPayableCreditNotesTransDTO = apPayableCreditNotesTransService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apPayableCreditNotesTransDTO);
    }

    /**
     * {@code DELETE  /ap-payable-credit-notes-trans/:id} : delete the "id" apPayableCreditNotesTrans.
     *
     * @param id the id of the apPayableCreditNotesTransDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ap-payable-credit-notes-trans/{id}")
    public ResponseEntity<Void> deleteApPayableCreditNotesTrans(@PathVariable Long id) {
        log.debug("REST request to delete ApPayableCreditNotesTrans : {}", id);
        apPayableCreditNotesTransService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
