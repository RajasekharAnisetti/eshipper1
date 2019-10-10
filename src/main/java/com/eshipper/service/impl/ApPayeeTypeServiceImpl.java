package com.eshipper.service.impl;

import com.eshipper.service.ApPayeeTypeService;
import com.eshipper.domain.ApPayeeType;
import com.eshipper.repository.ApPayeeTypeRepository;
import com.eshipper.service.dto.ApPayeeTypeDTO;
import com.eshipper.service.mapper.ApPayeeTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ApPayeeType}.
 */
@Service
@Transactional
public class ApPayeeTypeServiceImpl implements ApPayeeTypeService {

    private final Logger log = LoggerFactory.getLogger(ApPayeeTypeServiceImpl.class);

    private final ApPayeeTypeRepository apPayeeTypeRepository;

    private final ApPayeeTypeMapper apPayeeTypeMapper;

    public ApPayeeTypeServiceImpl(ApPayeeTypeRepository apPayeeTypeRepository, ApPayeeTypeMapper apPayeeTypeMapper) {
        this.apPayeeTypeRepository = apPayeeTypeRepository;
        this.apPayeeTypeMapper = apPayeeTypeMapper;
    }

    /**
     * Save a apPayeeType.
     *
     * @param apPayeeTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ApPayeeTypeDTO save(ApPayeeTypeDTO apPayeeTypeDTO) {
        log.debug("Request to save ApPayeeType : {}", apPayeeTypeDTO);
        ApPayeeType apPayeeType = apPayeeTypeMapper.toEntity(apPayeeTypeDTO);
        apPayeeType = apPayeeTypeRepository.save(apPayeeType);
        return apPayeeTypeMapper.toDto(apPayeeType);
    }

    /**
     * Get all the apPayeeTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ApPayeeTypeDTO> findAll() {
        log.debug("Request to get all ApPayeeTypes");
        return apPayeeTypeRepository.findAll().stream()
            .map(apPayeeTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one apPayeeType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApPayeeTypeDTO> findOne(Long id) {
        log.debug("Request to get ApPayeeType : {}", id);
        return apPayeeTypeRepository.findById(id)
            .map(apPayeeTypeMapper::toDto);
    }

    /**
     * Delete the apPayeeType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApPayeeType : {}", id);
        apPayeeTypeRepository.deleteById(id);
    }
}
