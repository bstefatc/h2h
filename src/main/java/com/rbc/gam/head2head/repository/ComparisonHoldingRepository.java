package com.rbc.gam.head2head.repository;

import com.rbc.gam.head2head.domain.ComparisonHolding;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ComparisonHolding entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComparisonHoldingRepository extends JpaRepository<ComparisonHolding, Long> {

}
