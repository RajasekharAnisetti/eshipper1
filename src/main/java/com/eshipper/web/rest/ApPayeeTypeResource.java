package com.eshipper.web.rest;

import com.eshipper.service.ApPayeeTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ApPayeeTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ApPayeeType}.
 */
@RestController
@RequestMapping("/api")
public class ApPayeeTypeResource {

    private final Logger log = LoggerFactory.getLogger(ApPayeeTypeResource.class);

    private static final String ENTITY_NAME = "apPayeeType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApPayeeTypeService apPayeeTypeService;

    public ApPayeeTypeResource(ApPayeeTypeService apPayeeTypeService) {
        this.apPayeeTypeService = apPayeeTypeService;
    }

    /**
     * {@code POST  /ap-payee-types} : Create a new apPayeeType.
     *
     * @param apPayeeTypeDTO the apPayeeTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apPayeeTypeDTO, or with status {@code 400 (Bad Request)} if the apPayeeType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ap-payee-types")
    public ResponseEntity<ApPayeeTypeDTO> createApPayeeType(@Valid @RequestBody ApPayeeTypeDTO apPayeeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ApPayeeType : {}", apPayeeTypeDTO);
        if (apPayeeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new apPayeeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApPayeeTypeDTO result = apPayeeTypeService.save(apPayeeTypeDTO);
        return ResponseEntity.created(new URI("/api/ap-payee-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ap-payee-types} : Updates an existing apPayeeType.
     *
     * @param apPayeeTypeDTO the apPayeeTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apPayeeTypeDTO,
     * or with status {@code 400 (Bad Request)} if the apPayeeTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apPayeeTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ap-payee-types")
    public ResponseEntity<ApPayeeTypeDTO> updateApPayeeType(@Valid @RequestBody ApPayeeTypeDTO apPayeeTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ApPayeeType : {}", apPayeeTypeDTO);
        if (apPayeeTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApPayeeTypeDTO result = apPayeeTypeService.save(apPayeeTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apPayeeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ap-payee-types} : get all the apPayeeTypes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apPayeeTypes in body.
     */
    @GetMapping("/ap-payee-types")
    public List<ApPayeeTypeDTO> getAllApPayeeTypes() {
        log.debug("REST request to get all ApPayeeTypes");
        return apPayeeTypeService.findAll();
    }

    /**
     * {@code GET  /ap-payee-types/:id} : get the "id" apPayeeType.
     *
     * @param id the id of the apPayeeTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apPayeeTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ap-payee-types/{id}")
    public ResponseEntity<ApPayeeTypeDTO> getApPayeeType(@PathVariable Long id) {
        log.debug("REST request to get ApPayeeType : {}", id);
        Optional<ApPayeeTypeDTO> apPayeeTypeDTO = apPayeeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apPayeeTypeDTO);
    }

    /**
     * {@code DELETE  /ap-payee-types/:id} : delete the "id" apPayeeType.
     *
     * @param id the id of the apPayeeTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ap-payee-types/{id}")
    public ResponseEntity<Void> deleteApPayeeType(@PathVariable Long id) {
        log.debug("REST request to delete ApPayeeType : {}", id);
        apPayeeTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
