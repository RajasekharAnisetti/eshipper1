package com.eshipper.service.impl;

import com.eshipper.service.ApPayableCreditNotesTransService;
import com.eshipper.domain.ApPayableCreditNotesTrans;
import com.eshipper.repository.ApPayableCreditNotesTransRepository;
import com.eshipper.service.dto.ApPayableCreditNotesTransDTO;
import com.eshipper.service.mapper.ApPayableCreditNotesTransMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ApPayableCreditNotesTrans}.
 */
@Service
@Transactional
public class ApPayableCreditNotesTransServiceImpl implements ApPayableCreditNotesTransService {

    private final Logger log = LoggerFactory.getLogger(ApPayableCreditNotesTransServiceImpl.class);

    private final ApPayableCreditNotesTransRepository apPayableCreditNotesTransRepository;

    private final ApPayableCreditNotesTransMapper apPayableCreditNotesTransMapper;

    public ApPayableCreditNotesTransServiceImpl(ApPayableCreditNotesTransRepository apPayableCreditNotesTransRepository, ApPayableCreditNotesTransMapper apPayableCreditNotesTransMapper) {
        this.apPayableCreditNotesTransRepository = apPayableCreditNotesTransRepository;
        this.apPayableCreditNotesTransMapper = apPayableCreditNotesTransMapper;
    }

    /**
     * Save a apPayableCreditNotesTrans.
     *
     * @param apPayableCreditNotesTransDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ApPayableCreditNotesTransDTO save(ApPayableCreditNotesTransDTO apPayableCreditNotesTransDTO) {
        log.debug("Request to save ApPayableCreditNotesTrans : {}", apPayableCreditNotesTransDTO);
        ApPayableCreditNotesTrans apPayableCreditNotesTrans = apPayableCreditNotesTransMapper.toEntity(apPayableCreditNotesTransDTO);
        apPayableCreditNotesTrans = apPayableCreditNotesTransRepository.save(apPayableCreditNotesTrans);
        return apPayableCreditNotesTransMapper.toDto(apPayableCreditNotesTrans);
    }

    /**
     * Get all the apPayableCreditNotesTrans.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ApPayableCreditNotesTransDTO> findAll() {
        log.debug("Request to get all ApPayableCreditNotesTrans");
        return apPayableCreditNotesTransRepository.findAll().stream()
            .map(apPayableCreditNotesTransMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one apPayableCreditNotesTrans by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApPayableCreditNotesTransDTO> findOne(Long id) {
        log.debug("Request to get ApPayableCreditNotesTrans : {}", id);
        return apPayableCreditNotesTransRepository.findById(id)
            .map(apPayableCreditNotesTransMapper::toDto);
    }

    /**
     * Delete the apPayableCreditNotesTrans by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApPayableCreditNotesTrans : {}", id);
        apPayableCreditNotesTransRepository.deleteById(id);
    }
}
