package com.eshipper.web.rest;

import com.eshipper.service.WoPaymentMethodService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.WoPaymentMethodDTO;

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
 * REST controller for managing {@link com.eshipper.domain.WoPaymentMethod}.
 */
@RestController
@RequestMapping("/api")
public class WoPaymentMethodResource {

    private final Logger log = LoggerFactory.getLogger(WoPaymentMethodResource.class);

    private static final String ENTITY_NAME = "woPaymentMethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoPaymentMethodService woPaymentMethodService;

    public WoPaymentMethodResource(WoPaymentMethodService woPaymentMethodService) {
        this.woPaymentMethodService = woPaymentMethodService;
    }

    /**
     * {@code POST  /wo-payment-methods} : Create a new woPaymentMethod.
     *
     * @param woPaymentMethodDTO the woPaymentMethodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woPaymentMethodDTO, or with status {@code 400 (Bad Request)} if the woPaymentMethod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-payment-methods")
    public ResponseEntity<WoPaymentMethodDTO> createWoPaymentMethod(@Valid @RequestBody WoPaymentMethodDTO woPaymentMethodDTO) throws URISyntaxException {
        log.debug("REST request to save WoPaymentMethod : {}", woPaymentMethodDTO);
        if (woPaymentMethodDTO.getId() != null) {
            throw new BadRequestAlertException("A new woPaymentMethod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoPaymentMethodDTO result = woPaymentMethodService.save(woPaymentMethodDTO);
        return ResponseEntity.created(new URI("/api/wo-payment-methods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-payment-methods} : Updates an existing woPaymentMethod.
     *
     * @param woPaymentMethodDTO the woPaymentMethodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woPaymentMethodDTO,
     * or with status {@code 400 (Bad Request)} if the woPaymentMethodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woPaymentMethodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-payment-methods")
    public ResponseEntity<WoPaymentMethodDTO> updateWoPaymentMethod(@Valid @RequestBody WoPaymentMethodDTO woPaymentMethodDTO) throws URISyntaxException {
        log.debug("REST request to update WoPaymentMethod : {}", woPaymentMethodDTO);
        if (woPaymentMethodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoPaymentMethodDTO result = woPaymentMethodService.save(woPaymentMethodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, woPaymentMethodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-payment-methods} : get all the woPaymentMethods.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woPaymentMethods in body.
     */
    @GetMapping("/wo-payment-methods")
    public List<WoPaymentMethodDTO> getAllWoPaymentMethods() {
        log.debug("REST request to get all WoPaymentMethods");
        return woPaymentMethodService.findAll();
    }

    /**
     * {@code GET  /wo-payment-methods/:id} : get the "id" woPaymentMethod.
     *
     * @param id the id of the woPaymentMethodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woPaymentMethodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-payment-methods/{id}")
    public ResponseEntity<WoPaymentMethodDTO> getWoPaymentMethod(@PathVariable Long id) {
        log.debug("REST request to get WoPaymentMethod : {}", id);
        Optional<WoPaymentMethodDTO> woPaymentMethodDTO = woPaymentMethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woPaymentMethodDTO);
    }

    /**
     * {@code DELETE  /wo-payment-methods/:id} : delete the "id" woPaymentMethod.
     *
     * @param id the id of the woPaymentMethodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-payment-methods/{id}")
    public ResponseEntity<Void> deleteWoPaymentMethod(@PathVariable Long id) {
        log.debug("REST request to delete WoPaymentMethod : {}", id);
        woPaymentMethodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
