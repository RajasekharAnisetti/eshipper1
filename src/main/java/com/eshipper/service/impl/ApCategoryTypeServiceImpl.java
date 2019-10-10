package com.eshipper.service.impl;

import com.eshipper.service.ApCategoryTypeService;
import com.eshipper.domain.ApCategoryType;
import com.eshipper.repository.ApCategoryTypeRepository;
import com.eshipper.service.dto.ApCategoryTypeDTO;
import com.eshipper.service.mapper.ApCategoryTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ApCategoryType}.
 */
@Service
@Transactional
public class ApCategoryTypeServiceImpl implements ApCategoryTypeService {

    private final Logger log = LoggerFactory.getLogger(ApCategoryTypeServiceImpl.class);

    private final ApCategoryTypeRepository apCategoryTypeRepository;

    private final ApCategoryTypeMapper apCategoryTypeMapper;

    public ApCategoryTypeServiceImpl(ApCategoryTypeRepository apCategoryTypeRepository, ApCategoryTypeMapper apCategoryTypeMapper) {
        this.apCategoryTypeRepository = apCategoryTypeRepository;
        this.apCategoryTypeMapper = apCategoryTypeMapper;
    }

    /**
     * Save a apCategoryType.
     *
     * @param apCategoryTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ApCategoryTypeDTO save(ApCategoryTypeDTO apCategoryTypeDTO) {
        log.debug("Request to save ApCategoryType : {}", apCategoryTypeDTO);
        ApCategoryType apCategoryType = apCategoryTypeMapper.toEntity(apCategoryTypeDTO);
        apCategoryType = apCategoryTypeRepository.save(apCategoryType);
        return apCategoryTypeMapper.toDto(apCategoryType);
    }

    /**
     * Get all the apCategoryTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ApCategoryTypeDTO> findAll() {
        log.debug("Request to get all ApCategoryTypes");
        return apCategoryTypeRepository.findAll().stream()
            .map(apCategoryTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one apCategoryType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApCategoryTypeDTO> findOne(Long id) {
        log.debug("Request to get ApCategoryType : {}", id);
        return apCategoryTypeRepository.findById(id)
            .map(apCategoryTypeMapper::toDto);
    }

    /**
     * Delete the apCategoryType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApCategoryType : {}", id);
        apCategoryTypeRepository.deleteById(id);
    }
}
