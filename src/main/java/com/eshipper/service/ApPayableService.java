package com.eshipper.service;

import com.eshipper.service.dto.ApPayableDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ApPayable}.
 */
public interface ApPayableService {

    /**
     * Save a apPayable.
     *
     * @param apPayableDTO the entity to save.
     * @return the persisted entity.
     */
    ApPayableDTO save(ApPayableDTO apPayableDTO);

    /**
     * Get all the apPayables.
     *
     * @return the list of entities.
     */
    List<ApPayableDTO> findAll();


    /**
     * Get the "id" apPayable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApPayableDTO> findOne(Long id);

    /**
     * Delete the "id" apPayable.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
