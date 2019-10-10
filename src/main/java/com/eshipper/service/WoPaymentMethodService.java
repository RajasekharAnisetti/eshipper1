package com.eshipper.service;

import com.eshipper.service.dto.WoPaymentMethodDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoPaymentMethod}.
 */
public interface WoPaymentMethodService {

    /**
     * Save a woPaymentMethod.
     *
     * @param woPaymentMethodDTO the entity to save.
     * @return the persisted entity.
     */
    WoPaymentMethodDTO save(WoPaymentMethodDTO woPaymentMethodDTO);

    /**
     * Get all the woPaymentMethods.
     *
     * @return the list of entities.
     */
    List<WoPaymentMethodDTO> findAll();


    /**
     * Get the "id" woPaymentMethod.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoPaymentMethodDTO> findOne(Long id);

    /**
     * Delete the "id" woPaymentMethod.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
