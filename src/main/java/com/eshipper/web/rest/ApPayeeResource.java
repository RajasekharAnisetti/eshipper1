package com.eshipper.web.rest;

import com.eshipper.service.ApPayeeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ApPayeeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ApPayee}.
 */
@RestController
@RequestMapping("/api")
public class ApPayeeResource {

    private final Logger log = LoggerFactory.getLogger(ApPayeeResource.class);

    private static final String ENTITY_NAME = "apPayee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApPayeeService apPayeeService;

    public ApPayeeResource(ApPayeeService apPayeeService) {
        this.apPayeeService = apPayeeService;
    }

    /**
     * {@code POST  /ap-payees} : Create a new apPayee.
     *
     * @param apPayeeDTO the apPayeeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apPayeeDTO, or with status {@code 400 (Bad Request)} if the apPayee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ap-payees")
    public ResponseEntity<ApPayeeDTO> createApPayee(@Valid @RequestBody ApPayeeDTO apPayeeDTO) throws URISyntaxException {
        log.debug("REST request to save ApPayee : {}", apPayeeDTO);
        if (apPayeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new apPayee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApPayeeDTO result = apPayeeService.save(apPayeeDTO);
        return ResponseEntity.created(new URI("/api/ap-payees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ap-payees} : Updates an existing apPayee.
     *
     * @param apPayeeDTO the apPayeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apPayeeDTO,
     * or with status {@code 400 (Bad Request)} if the apPayeeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apPayeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ap-payees")
    public ResponseEntity<ApPayeeDTO> updateApPayee(@Valid @RequestBody ApPayeeDTO apPayeeDTO) throws URISyntaxException {
        log.debug("REST request to update ApPayee : {}", apPayeeDTO);
        if (apPayeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApPayeeDTO result = apPayeeService.save(apPayeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apPayeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ap-payees} : get all the apPayees.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apPayees in body.
     */
    @GetMapping("/ap-payees")
    public List<ApPayeeDTO> getAllApPayees() {
        log.debug("REST request to get all ApPayees");
        return apPayeeService.findAll();
    }

    /**
     * {@code GET  /ap-payees/:id} : get the "id" apPayee.
     *
     * @param id the id of the apPayeeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apPayeeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ap-payees/{id}")
    public ResponseEntity<ApPayeeDTO> getApPayee(@PathVariable Long id) {
        log.debug("REST request to get ApPayee : {}", id);
        Optional<ApPayeeDTO> apPayeeDTO = apPayeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apPayeeDTO);
    }

    /**
     * {@code DELETE  /ap-payees/:id} : delete the "id" apPayee.
     *
     * @param id the id of the apPayeeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ap-payees/{id}")
    public ResponseEntity<Void> deleteApPayee(@PathVariable Long id) {
        log.debug("REST request to delete ApPayee : {}", id);
        apPayeeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
