package com.eshipper.service.impl;

import com.eshipper.service.ApPayableService;
import com.eshipper.domain.ApPayable;
import com.eshipper.repository.ApPayableRepository;
import com.eshipper.service.dto.ApPayableDTO;
import com.eshipper.service.mapper.ApPayableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ApPayable}.
 */
@Service
@Transactional
public class ApPayableServiceImpl implements ApPayableService {

    private final Logger log = LoggerFactory.getLogger(ApPayableServiceImpl.class);

    private final ApPayableRepository apPayableRepository;

    private final ApPayableMapper apPayableMapper;

    public ApPayableServiceImpl(ApPayableRepository apPayableRepository, ApPayableMapper apPayableMapper) {
        this.apPayableRepository = apPayableRepository;
        this.apPayableMapper = apPayableMapper;
    }

    /**
     * Save a apPayable.
     *
     * @param apPayableDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ApPayableDTO save(ApPayableDTO apPayableDTO) {
        log.debug("Request to save ApPayable : {}", apPayableDTO);
        ApPayable apPayable = apPayableMapper.toEntity(apPayableDTO);
        apPayable = apPayableRepository.save(apPayable);
        return apPayableMapper.toDto(apPayable);
    }

    /**
     * Get all the apPayables.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ApPayableDTO> findAll() {
        log.debug("Request to get all ApPayables");
        return apPayableRepository.findAll().stream()
            .map(apPayableMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one apPayable by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApPayableDTO> findOne(Long id) {
        log.debug("Request to get ApPayable : {}", id);
        return apPayableRepository.findById(id)
            .map(apPayableMapper::toDto);
    }

    /**
     * Delete the apPayable by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApPayable : {}", id);
        apPayableRepository.deleteById(id);
    }
}
