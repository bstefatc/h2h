package com.rbc.gam.head2head.service;

import com.rbc.gam.head2head.service.dto.ComparisonHoldingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ComparisonHolding.
 */
public interface ComparisonHoldingService {

    /**
     * Save a comparisonHolding.
     *
     * @param comparisonHoldingDTO the entity to save
     * @return the persisted entity
     */
    ComparisonHoldingDTO save(ComparisonHoldingDTO comparisonHoldingDTO);

    /**
     * Get all the comparisonHoldings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ComparisonHoldingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" comparisonHolding.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ComparisonHoldingDTO> findOne(Long id);

    /**
     * Delete the "id" comparisonHolding.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
