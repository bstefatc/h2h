package com.rbc.gam.head2head.service.mapper;

import com.rbc.gam.head2head.domain.*;
import com.rbc.gam.head2head.service.dto.ComparisonHoldingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ComparisonHolding and its DTO ComparisonHoldingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ComparisonHoldingMapper extends EntityMapper<ComparisonHoldingDTO, ComparisonHolding> {



    default ComparisonHolding fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComparisonHolding comparisonHolding = new ComparisonHolding();
        comparisonHolding.setId(id);
        return comparisonHolding;
    }
}
