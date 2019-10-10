package com.eshipper.service;

import com.eshipper.service.dto.ApCategoryTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ApCategoryType}.
 */
public interface ApCategoryTypeService {

    /**
     * Save a apCategoryType.
     *
     * @param apCategoryTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ApCategoryTypeDTO save(ApCategoryTypeDTO apCategoryTypeDTO);

    /**
     * Get all the apCategoryTypes.
     *
     * @return the list of entities.
     */
    List<ApCategoryTypeDTO> findAll();


    /**
     * Get the "id" apCategoryType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApCategoryTypeDTO> findOne(Long id);

    /**
     * Delete the "id" apCategoryType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
