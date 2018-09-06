package com.manageme.app.service;

import com.manageme.app.domain.SeparationApplication;
import com.manageme.app.repository.SeparationApplicationRepository;
import com.manageme.app.service.dto.SeparationApplicationDTO;
import com.manageme.app.service.mapper.SeparationApplicationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing SeparationApplication.
 */
@Service
@Transactional
public class SeparationApplicationService {

    private final Logger log = LoggerFactory.getLogger(SeparationApplicationService.class);

    private final SeparationApplicationRepository separationApplicationRepository;

    private final SeparationApplicationMapper separationApplicationMapper;

    public SeparationApplicationService(SeparationApplicationRepository separationApplicationRepository, SeparationApplicationMapper separationApplicationMapper) {
        this.separationApplicationRepository = separationApplicationRepository;
        this.separationApplicationMapper = separationApplicationMapper;
    }

    /**
     * Save a separationApplication.
     *
     * @param separationApplicationDTO the entity to save
     * @return the persisted entity
     */
    public SeparationApplicationDTO save(SeparationApplicationDTO separationApplicationDTO) {
        log.debug("Request to save SeparationApplication : {}", separationApplicationDTO);
        SeparationApplication separationApplication = separationApplicationMapper.toEntity(separationApplicationDTO);
        separationApplication = separationApplicationRepository.save(separationApplication);
        return separationApplicationMapper.toDto(separationApplication);
    }

    /**
     * Get all the separationApplications.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<SeparationApplicationDTO> findAll() {
        log.debug("Request to get all SeparationApplications");
        return separationApplicationRepository.findAllWithEagerRelationships().stream()
            .map(separationApplicationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the SeparationApplication with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SeparationApplicationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return separationApplicationRepository.findAllWithEagerRelationships(pageable).map(separationApplicationMapper::toDto);
    }
    

    /**
     * Get one separationApplication by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<SeparationApplicationDTO> findOne(Long id) {
        log.debug("Request to get SeparationApplication : {}", id);
        return separationApplicationRepository.findOneWithEagerRelationships(id)
            .map(separationApplicationMapper::toDto);
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
