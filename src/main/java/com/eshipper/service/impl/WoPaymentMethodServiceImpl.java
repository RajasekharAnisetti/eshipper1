package com.eshipper.service.impl;

import com.eshipper.service.WoPaymentMethodService;
import com.eshipper.domain.WoPaymentMethod;
import com.eshipper.repository.WoPaymentMethodRepository;
import com.eshipper.service.dto.WoPaymentMethodDTO;
import com.eshipper.service.mapper.WoPaymentMethodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WoPaymentMethod}.
 */
@Service
@Transactional
public class WoPaymentMethodServiceImpl implements WoPaymentMethodService {

    private final Logger log = LoggerFactory.getLogger(WoPaymentMethodServiceImpl.class);

    private final WoPaymentMethodRepository woPaymentMethodRepository;

    private final WoPaymentMethodMapper woPaymentMethodMapper;

    public WoPaymentMethodServiceImpl(WoPaymentMethodRepository woPaymentMethodRepository, WoPaymentMethodMapper woPaymentMethodMapper) {
        this.woPaymentMethodRepository = woPaymentMethodRepository;
        this.woPaymentMethodMapper = woPaymentMethodMapper;
    }

    /**
     * Save a woPaymentMethod.
     *
     * @param woPaymentMethodDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoPaymentMethodDTO save(WoPaymentMethodDTO woPaymentMethodDTO) {
        log.debug("Request to save WoPaymentMethod : {}", woPaymentMethodDTO);
        WoPaymentMethod woPaymentMethod = woPaymentMethodMapper.toEntity(woPaymentMethodDTO);
        woPaymentMethod = woPaymentMethodRepository.save(woPaymentMethod);
        return woPaymentMethodMapper.toDto(woPaymentMethod);
    }

    /**
     * Get all the woPaymentMethods.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoPaymentMethodDTO> findAll() {
        log.debug("Request to get all WoPaymentMethods");
        return woPaymentMethodRepository.findAll().stream()
            .map(woPaymentMethodMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one woPaymentMethod by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoPaymentMethodDTO> findOne(Long id) {
        log.debug("Request to get WoPaymentMethod : {}", id);
        return woPaymentMethodRepository.findById(id)
            .map(woPaymentMethodMapper::toDto);
    }

    /**
     * Delete the woPaymentMethod by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoPaymentMethod : {}", id);
        woPaymentMethodRepository.deleteById(id);
    }
}
