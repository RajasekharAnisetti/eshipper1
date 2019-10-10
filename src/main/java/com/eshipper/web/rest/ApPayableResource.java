package com.eshipper.web.rest;

import com.eshipper.service.ApPayableService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ApPayableDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ApPayable}.
 */
@RestController
@RequestMapping("/api")
public class ApPayableResource {

    private final Logger log = LoggerFactory.getLogger(ApPayableResource.class);

    private static final String ENTITY_NAME = "apPayable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApPayableService apPayableService;

    public ApPayableResource(ApPayableService apPayableService) {
        this.apPayableService = apPayableService;
    }

    /**
     * {@code POST  /ap-payables} : Create a new apPayable.
     *
     * @param apPayableDTO the apPayableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apPayableDTO, or with status {@code 400 (Bad Request)} if the apPayable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ap-payables")
    public ResponseEntity<ApPayableDTO> createApPayable(@Valid @RequestBody ApPayableDTO apPayableDTO) throws URISyntaxException {
        log.debug("REST request to save ApPayable : {}", apPayableDTO);
        if (apPayableDTO.getId() != null) {
            throw new BadRequestAlertException("A new apPayable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApPayableDTO result = apPayableService.save(apPayableDTO);
        return ResponseEntity.created(new URI("/api/ap-payables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ap-payables} : Updates an existing apPayable.
     *
     * @param apPayableDTO the apPayableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apPayableDTO,
     * or with status {@code 400 (Bad Request)} if the apPayableDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apPayableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ap-payables")
    public ResponseEntity<ApPayableDTO> updateApPayable(@Valid @RequestBody ApPayableDTO apPayableDTO) throws URISyntaxException {
        log.debug("REST request to update ApPayable : {}", apPayableDTO);
        if (apPayableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApPayableDTO result = apPayableService.save(apPayableDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apPayableDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ap-payables} : get all the apPayables.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apPayables in body.
     */
    @GetMapping("/ap-payables")
    public List<ApPayableDTO> getAllApPayables() {
        log.debug("REST request to get all ApPayables");
        return apPayableService.findAll();
    }

    /**
     * {@code GET  /ap-payables/:id} : get the "id" apPayable.
     *
     * @param id the id of the apPayableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apPayableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ap-payables/{id}")
    public ResponseEntity<ApPayableDTO> getApPayable(@PathVariable Long id) {
        log.debug("REST request to get ApPayable : {}", id);
        Optional<ApPayableDTO> apPayableDTO = apPayableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apPayableDTO);
    }

    /**
     * {@code DELETE  /ap-payables/:id} : delete the "id" apPayable.
     *
     * @param id the id of the apPayableDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ap-payables/{id}")
    public ResponseEntity<Void> deleteApPayable(@PathVariable Long id) {
        log.debug("REST request to delete ApPayable : {}", id);
        apPayableService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
