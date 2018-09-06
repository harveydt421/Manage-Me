package com.manageme.app.service;

import com.manageme.app.domain.LineItem;
import com.manageme.app.repository.LineItemRepository;
import com.manageme.app.service.dto.LineItemDTO;
import com.manageme.app.service.mapper.LineItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing LineItem.
 */
@Service
@Transactional
public class LineItemService {

    private final Logger log = LoggerFactory.getLogger(LineItemService.class);

    private final LineItemRepository lineItemRepository;

    private final LineItemMapper lineItemMapper;

    public LineItemService(LineItemRepository lineItemRepository, LineItemMapper lineItemMapper) {
        this.lineItemRepository = lineItemRepository;
        this.lineItemMapper = lineItemMapper;
    }

    /**
     * Save a lineItem.
     *
     * @param lineItemDTO the entity to save
     * @return the persisted entity
     */
    public LineItemDTO save(LineItemDTO lineItemDTO) {
        log.debug("Request to save LineItem : {}", lineItemDTO);
        LineItem lineItem = lineItemMapper.toEntity(lineItemDTO);
        lineItem = lineItemRepository.save(lineItem);
        return lineItemMapper.toDto(lineItem);
    }

    /**
     * Get all the lineItems.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<LineItemDTO> findAll() {
        log.debug("Request to get all LineItems");
        return lineItemRepository.findAll().stream()
            .map(lineItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one lineItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<LineItemDTO> findOne(Long id) {
        log.debug("Request to get LineItem : {}", id);
        return lineItemRepository.findById(id)
            .map(lineItemMapper::toDto);
    }

    /**
     * Delete the lineItem by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LineItem : {}", id);
        lineItemRepository.deleteById(id);
    }
}
