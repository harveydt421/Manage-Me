package com.manageme.app.service;

import com.manageme.app.domain.SeparationApplication;
import com.manageme.app.repository.SeparationApplicationRepository;
import com.manageme.app.security.AuthoritiesConstants;
import com.manageme.app.security.SecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing SeparationApplication.
 */
@Service
@Transactional
public class SeparationApplicationService {

    private final Logger log = LoggerFactory.getLogger(SeparationApplicationService.class);

    private final SeparationApplicationRepository separationApplicationRepository;

    public SeparationApplicationService(SeparationApplicationRepository separationApplicationRepository) {
        this.separationApplicationRepository = separationApplicationRepository;
    }

    /**
     * Save a separationApplication.
     *
     * @param separationApplication the entity to save
     * @return the persisted entity
     */
    public SeparationApplication save(SeparationApplication separationApplication) {
        log.debug("Request to save SeparationApplication : {}", separationApplication);
        return separationApplicationRepository.save(separationApplication);
    }

    /**
     * Get all the separationApplications.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<SeparationApplication> findAll() {
        log.debug("Request to get all SeparationApplications");
        List<SeparationApplication> result;
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {	
        	result = separationApplicationRepository.findAll();
        } else {
        	 result = separationApplicationRepository.findAllByEmployeeUserLogin(SecurityUtils.getCurrentUserLogin().get());
        }
        return result;
    }


    /**
     * Get one separationApplication by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<SeparationApplication> findOne(Long id) {
        log.debug("Request to get SeparationApplication : {}", id);
        Optional<SeparationApplication> result;
        if  (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
        	result = separationApplicationRepository.findById(id);        	
        } else {
        	result = separationApplicationRepository.findOneByIdAndEmployeeUserLogin(SecurityUtils.getCurrentUserLogin().get(), id);
        }
        return result;
    }

    /**
     * Delete the separationApplication by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SeparationApplication : {}", id);
        separationApplicationRepository.deleteById(id);
    }
}
