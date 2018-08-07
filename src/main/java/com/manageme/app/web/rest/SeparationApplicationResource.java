package com.manageme.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.manageme.app.domain.SeparationApplication;
import com.manageme.app.service.SeparationApplicationService;
import com.manageme.app.web.rest.errors.BadRequestAlertException;
import com.manageme.app.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SeparationApplication.
 */
@RestController
@RequestMapping("/api")
public class SeparationApplicationResource {

    private final Logger log = LoggerFactory.getLogger(SeparationApplicationResource.class);

    private static final String ENTITY_NAME = "separationApplication";

    private final SeparationApplicationService separationApplicationService;

    public SeparationApplicationResource(SeparationApplicationService separationApplicationService) {
        this.separationApplicationService = separationApplicationService;
    }

    /**
     * POST  /separation-applications : Create a new separationApplication.
     *
     * @param separationApplication the separationApplication to create
     * @return the ResponseEntity with status 201 (Created) and with body the new separationApplication, or with status 400 (Bad Request) if the separationApplication has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/separation-applications")
    @Timed
    public ResponseEntity<SeparationApplication> createSeparationApplication(@Valid @RequestBody SeparationApplication separationApplication) throws URISyntaxException {
        log.debug("REST request to save SeparationApplication : {}", separationApplication);
        if (separationApplication.getId() != null) {
            throw new BadRequestAlertException("A new separationApplication cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SeparationApplication result = separationApplicationService.save(separationApplication);
        return ResponseEntity.created(new URI("/api/separation-applications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /separation-applications : Updates an existing separationApplication.
     *
     * @param separationApplication the separationApplication to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated separationApplication,
     * or with status 400 (Bad Request) if the separationApplication is not valid,
     * or with status 500 (Internal Server Error) if the separationApplication couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/separation-applications")
    @Timed
    public ResponseEntity<SeparationApplication> updateSeparationApplication(@Valid @RequestBody SeparationApplication separationApplication) throws URISyntaxException {
        log.debug("REST request to update SeparationApplication : {}", separationApplication);
        if (separationApplication.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SeparationApplication result = separationApplicationService.save(separationApplication);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, separationApplication.getId().toString()))
            .body(result);
    }

    /**
     * GET  /separation-applications : get all the separationApplications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of separationApplications in body
     */
    @GetMapping("/separation-applications")
    @Timed
    public List<SeparationApplication> getAllSeparationApplications() {
        log.debug("REST request to get all SeparationApplications");
        return separationApplicationService.findAll();
    }

    /**
     * GET  /separation-applications/:id : get the "id" separationApplication.
     *
     * @param id the id of the separationApplication to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the separationApplication, or with status 404 (Not Found)
     */
    @GetMapping("/separation-applications/{id}")
    @Timed
    public ResponseEntity<SeparationApplication> getSeparationApplication(@PathVariable Long id) {
        log.debug("REST request to get SeparationApplication : {}", id);
        Optional<SeparationApplication> separationApplication = separationApplicationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(separationApplication);
    }

    /**
     * DELETE  /separation-applications/:id : delete the "id" separationApplication.
     *
     * @param id the id of the separationApplication to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/separation-applications/{id}")
    @Timed
    public ResponseEntity<Void> deleteSeparationApplication(@PathVariable Long id) {
        log.debug("REST request to delete SeparationApplication : {}", id);
        separationApplicationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
