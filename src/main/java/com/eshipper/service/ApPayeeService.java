package com.eshipper.service;

import com.eshipper.service.dto.ApPayeeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ApPayee}.
 */
public interface ApPayeeService {

    /**
     * Save a apPayee.
     *
     * @param apPayeeDTO the entity to save.
     * @return the persisted entity.
     */
    ApPayeeDTO save(ApPayeeDTO apPayeeDTO);

    /**
     * Get all the apPayees.
     *
     * @return the list of entities.
     */
    List<ApPayeeDTO> findAll();


    /**
     * Get the "id" apPayee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApPayeeDTO> findOne(Long id);

    /**
     * Delete the "id" apPayee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
