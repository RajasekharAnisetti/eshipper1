package com.eshipper.service;

import com.eshipper.service.dto.ApPayeeTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ApPayeeType}.
 */
public interface ApPayeeTypeService {

    /**
     * Save a apPayeeType.
     *
     * @param apPayeeTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ApPayeeTypeDTO save(ApPayeeTypeDTO apPayeeTypeDTO);

    /**
     * Get all the apPayeeTypes.
     *
     * @return the list of entities.
     */
    List<ApPayeeTypeDTO> findAll();


    /**
     * Get the "id" apPayeeType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApPayeeTypeDTO> findOne(Long id);

    /**
     * Delete the "id" apPayeeType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
