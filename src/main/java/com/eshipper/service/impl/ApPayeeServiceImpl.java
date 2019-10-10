package com.eshipper.service.impl;

import com.eshipper.service.ApPayeeService;
import com.eshipper.domain.ApPayee;
import com.eshipper.repository.ApPayeeRepository;
import com.eshipper.service.dto.ApPayeeDTO;
import com.eshipper.service.mapper.ApPayeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ApPayee}.
 */
@Service
@Transactional
public class ApPayeeServiceImpl implements ApPayeeService {

    private final Logger log = LoggerFactory.getLogger(ApPayeeServiceImpl.class);

    private final ApPayeeRepository apPayeeRepository;

    private final ApPayeeMapper apPayeeMapper;

    public ApPayeeServiceImpl(ApPayeeRepository apPayeeRepository, ApPayeeMapper apPayeeMapper) {
        this.apPayeeRepository = apPayeeRepository;
        this.apPayeeMapper = apPayeeMapper;
    }

    /**
     * Save a apPayee.
     *
     * @param apPayeeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ApPayeeDTO save(ApPayeeDTO apPayeeDTO) {
        log.debug("Request to save ApPayee : {}", apPayeeDTO);
        ApPayee apPayee = apPayeeMapper.toEntity(apPayeeDTO);
        apPayee = apPayeeRepository.save(apPayee);
        return apPayeeMapper.toDto(apPayee);
    }

    /**
     * Get all the apPayees.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ApPayeeDTO> findAll() {
        log.debug("Request to get all ApPayees");
        return apPayeeRepository.findAll().stream()
            .map(apPayeeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one apPayee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApPayeeDTO> findOne(Long id) {
        log.debug("Request to get ApPayee : {}", id);
        return apPayeeRepository.findById(id)
            .map(apPayeeMapper::toDto);
    }

    /**
     * Delete the apPayee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApPayee : {}", id);
        apPayeeRepository.deleteById(id);
    }
}
