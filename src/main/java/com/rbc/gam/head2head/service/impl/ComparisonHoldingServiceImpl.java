package com.rbc.gam.head2head.service.impl;

import com.rbc.gam.head2head.service.ComparisonHoldingService;
import com.rbc.gam.head2head.domain.ComparisonHolding;
import com.rbc.gam.head2head.repository.ComparisonHoldingRepository;
import com.rbc.gam.head2head.service.dto.ComparisonHoldingDTO;
import com.rbc.gam.head2head.service.mapper.ComparisonHoldingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ComparisonHolding.
 */
@Service
@Transactional
public class ComparisonHoldingServiceImpl implements ComparisonHoldingService {

    private final Logger log = LoggerFactory.getLogger(ComparisonHoldingServiceImpl.class);

    private final ComparisonHoldingRepository comparisonHoldingRepository;

    private final ComparisonHoldingMapper comparisonHoldingMapper;

    public ComparisonHoldingServiceImpl(ComparisonHoldingRepository comparisonHoldingRepository, ComparisonHoldingMapper comparisonHoldingMapper) {
        this.comparisonHoldingRepository = comparisonHoldingRepository;
        this.comparisonHoldingMapper = comparisonHoldingMapper;
    }

    /**
     * Save a comparisonHolding.
     *
     * @param comparisonHoldingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComparisonHoldingDTO save(ComparisonHoldingDTO comparisonHoldingDTO) {
        log.debug("Request to save ComparisonHolding : {}", comparisonHoldingDTO);
        ComparisonHolding comparisonHolding = comparisonHoldingMapper.toEntity(comparisonHoldingDTO);
        comparisonHolding = comparisonHoldingRepository.save(comparisonHolding);
        return comparisonHoldingMapper.toDto(comparisonHolding);
    }

    /**
     * Get all the comparisonHoldings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComparisonHoldingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ComparisonHoldings");
        return comparisonHoldingRepository.findAll(pageable)
            .map(comparisonHoldingMapper::toDto);
    }


    /**
     * Get one comparisonHolding by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComparisonHoldingDTO> findOne(Long id) {
        log.debug("Request to get ComparisonHolding : {}", id);
        return comparisonHoldingRepository.findById(id)
            .map(comparisonHoldingMapper::toDto);
    }

    /**
     * Delete the comparisonHolding by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComparisonHolding : {}", id);        comparisonHoldingRepository.deleteById(id);
    }
}
