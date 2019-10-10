package com.eshipper.web.rest;

import com.eshipper.service.ApCategoryTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ApCategoryTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ApCategoryType}.
 */
@RestController
@RequestMapping("/api")
public class ApCategoryTypeResource {

    private final Logger log = LoggerFactory.getLogger(ApCategoryTypeResource.class);

    private static final String ENTITY_NAME = "apCategoryType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApCategoryTypeService apCategoryTypeService;

    public ApCategoryTypeResource(ApCategoryTypeService apCategoryTypeService) {
        this.apCategoryTypeService = apCategoryTypeService;
    }

    /**
     * {@code POST  /ap-category-types} : Create a new apCategoryType.
     *
     * @param apCategoryTypeDTO the apCategoryTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apCategoryTypeDTO, or with status {@code 400 (Bad Request)} if the apCategoryType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ap-category-types")
    public ResponseEntity<ApCategoryTypeDTO> createApCategoryType(@Valid @RequestBody ApCategoryTypeDTO apCategoryTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ApCategoryType : {}", apCategoryTypeDTO);
        if (apCategoryTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new apCategoryType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApCategoryTypeDTO result = apCategoryTypeService.save(apCategoryTypeDTO);
        return ResponseEntity.created(new URI("/api/ap-category-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ap-category-types} : Updates an existing apCategoryType.
     *
     * @param apCategoryTypeDTO the apCategoryTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apCategoryTypeDTO,
     * or with status {@code 400 (Bad Request)} if the apCategoryTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apCategoryTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ap-category-types")
    public ResponseEntity<ApCategoryTypeDTO> updateApCategoryType(@Valid @RequestBody ApCategoryTypeDTO apCategoryTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ApCategoryType : {}", apCategoryTypeDTO);
        if (apCategoryTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApCategoryTypeDTO result = apCategoryTypeService.save(apCategoryTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apCategoryTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ap-category-types} : get all the apCategoryTypes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apCategoryTypes in body.
     */
    @GetMapping("/ap-category-types")
    public List<ApCategoryTypeDTO> getAllApCategoryTypes() {
        log.debug("REST request to get all ApCategoryTypes");
        return apCategoryTypeService.findAll();
    }

    /**
     * {@code GET  /ap-category-types/:id} : get the "id" apCategoryType.
     *
     * @param id the id of the apCategoryTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apCategoryTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ap-category-types/{id}")
    public ResponseEntity<ApCategoryTypeDTO> getApCategoryType(@PathVariable Long id) {
        log.debug("REST request to get ApCategoryType : {}", id);
        Optional<ApCategoryTypeDTO> apCategoryTypeDTO = apCategoryTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apCategoryTypeDTO);
    }

    /**
     * {@code DELETE  /ap-category-types/:id} : delete the "id" apCategoryType.
     *
     * @param id the id of the apCategoryTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ap-category-types/{id}")
    public ResponseEntity<Void> deleteApCategoryType(@PathVariable Long id) {
        log.debug("REST request to delete ApCategoryType : {}", id);
        apCategoryTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
