package com.rbc.gam.head2head.web.rest;
import com.rbc.gam.head2head.service.ComparisonHoldingService;
import com.rbc.gam.head2head.web.rest.errors.BadRequestAlertException;
import com.rbc.gam.head2head.web.rest.util.HeaderUtil;
import com.rbc.gam.head2head.web.rest.util.PaginationUtil;
import com.rbc.gam.head2head.service.dto.ComparisonHoldingDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ComparisonHolding.
 */
@RestController
@RequestMapping("/api")
public class ComparisonHoldingResource {

    private final Logger log = LoggerFactory.getLogger(ComparisonHoldingResource.class);

    private static final String ENTITY_NAME = "comparisonHolding";

    private final ComparisonHoldingService comparisonHoldingService;

    public ComparisonHoldingResource(ComparisonHoldingService comparisonHoldingService) {
        this.comparisonHoldingService = comparisonHoldingService;
    }

    /**
     * POST  /comparison-holdings : Create a new comparisonHolding.
     *
     * @param comparisonHoldingDTO the comparisonHoldingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new comparisonHoldingDTO, or with status 400 (Bad Request) if the comparisonHolding has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/comparison-holdings")
    public ResponseEntity<ComparisonHoldingDTO> createComparisonHolding(@RequestBody ComparisonHoldingDTO comparisonHoldingDTO) throws URISyntaxException {
        log.debug("REST request to save ComparisonHolding : {}", comparisonHoldingDTO);
        if (comparisonHoldingDTO.getId() != null) {
            throw new BadRequestAlertException("A new comparisonHolding cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComparisonHoldingDTO result = comparisonHoldingService.save(comparisonHoldingDTO);
        return ResponseEntity.created(new URI("/api/comparison-holdings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /comparison-holdings : Updates an existing comparisonHolding.
     *
     * @param comparisonHoldingDTO the comparisonHoldingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated comparisonHoldingDTO,
     * or with status 400 (Bad Request) if the comparisonHoldingDTO is not valid,
     * or with status 500 (Internal Server Error) if the comparisonHoldingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/comparison-holdings")
    public ResponseEntity<ComparisonHoldingDTO> updateComparisonHolding(@RequestBody ComparisonHoldingDTO comparisonHoldingDTO) throws URISyntaxException {
        log.debug("REST request to update ComparisonHolding : {}", comparisonHoldingDTO);
        if (comparisonHoldingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComparisonHoldingDTO result = comparisonHoldingService.save(comparisonHoldingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, comparisonHoldingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /comparison-holdings : get all the comparisonHoldings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of comparisonHoldings in body
     */
    @GetMapping("/comparison-holdings")
    public ResponseEntity<List<ComparisonHoldingDTO>> getAllComparisonHoldings(Pageable pageable) {
        log.debug("REST request to get a page of ComparisonHoldings");
        Page<ComparisonHoldingDTO> page = comparisonHoldingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comparison-holdings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /comparison-holdings/:id : get the "id" comparisonHolding.
     *
     * @param id the id of the comparisonHoldingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the comparisonHoldingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/comparison-holdings/{id}")
    public ResponseEntity<ComparisonHoldingDTO> getComparisonHolding(@PathVariable Long id) {
        log.debug("REST request to get ComparisonHolding : {}", id);
        Optional<ComparisonHoldingDTO> comparisonHoldingDTO = comparisonHoldingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comparisonHoldingDTO);
    }

    /**
     * DELETE  /comparison-holdings/:id : delete the "id" comparisonHolding.
     *
     * @param id the id of the comparisonHoldingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/comparison-holdings/{id}")
    public ResponseEntity<Void> deleteComparisonHolding(@PathVariable Long id) {
        log.debug("REST request to delete ComparisonHolding : {}", id);
        comparisonHoldingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
