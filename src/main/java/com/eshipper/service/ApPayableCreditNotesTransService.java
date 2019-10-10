package com.eshipper.service;

import com.eshipper.service.dto.ApPayableCreditNotesTransDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ApPayableCreditNotesTrans}.
 */
public interface ApPayableCreditNotesTransService {

    /**
     * Save a apPayableCreditNotesTrans.
     *
     * @param apPayableCreditNotesTransDTO the entity to save.
     * @return the persisted entity.
     */
    ApPayableCreditNotesTransDTO save(ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO);

    /**
     * Get all the apPayableCreditNotesTrans.
     *
     * @return the list of entities.
     */
    List<ApPayableCreditNotesTransDTO> findAll();


    /**
     * Get the "id" apPayableCreditNotesTrans.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApPayableCreditNotesTransDTO> findOne(Long id);

    /**
     * Delete the "id" apPayableCreditNotesTrans.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
