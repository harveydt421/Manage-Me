package com.manageme.app.web.rest;

import com.manageme.app.ManageMeApp;

import com.manageme.app.domain.LineItem;
import com.manageme.app.repository.LineItemRepository;
import com.manageme.app.service.LineItemService;
import com.manageme.app.service.dto.LineItemDTO;
import com.manageme.app.service.mapper.LineItemMapper;
import com.manageme.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.manageme.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LineItemResource REST controller.
 *
 * @see LineItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageMeApp.class)
public class LineItemResourceIntTest {

    private static final String DEFAULT_FEEDBACK = "AAAAAAAAAA";
    private static final String UPDATED_FEEDBACK = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CLEARED = false;
    private static final Boolean UPDATED_CLEARED = true;

    @Autowired
    private LineItemRepository lineItemRepository;


    @Autowired
    private LineItemMapper lineItemMapper;
    

    @Autowired
    private LineItemService lineItemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLineItemMockMvc;

    private LineItem lineItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LineItemResource lineItemResource = new LineItemResource(lineItemService);
        this.restLineItemMockMvc = MockMvcBuilders.standaloneSetup(lineItemResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LineItem createEntity(EntityManager em) {
        LineItem lineItem = new LineItem()
            .feedback(DEFAULT_FEEDBACK)
            .cleared(DEFAULT_CLEARED);
        return lineItem;
    }

    @Before
    public void initTest() {
        lineItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createLineItem() throws Exception {
        int databaseSizeBeforeCreate = lineItemRepository.findAll().size();

        // Create the LineItem
        LineItemDTO lineItemDTO = lineItemMapper.toDto(lineItem);
        restLineItemMockMvc.perform(post("/api/line-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lineItemDTO)))
            .andExpect(status().isCreated());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeCreate + 1);
        LineItem testLineItem = lineItemList.get(lineItemList.size() - 1);
        assertThat(testLineItem.getFeedback()).isEqualTo(DEFAULT_FEEDBACK);
        assertThat(testLineItem.isCleared()).isEqualTo(DEFAULT_CLEARED);
    }

    @Test
    @Transactional
    public void createLineItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lineItemRepository.findAll().size();

        // Create the LineItem with an existing ID
        lineItem.setId(1L);
        LineItemDTO lineItemDTO = lineItemMapper.toDto(lineItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLineItemMockMvc.perform(post("/api/line-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lineItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLineItems() throws Exception {
        // Initialize the database
        lineItemRepository.saveAndFlush(lineItem);

        // Get all the lineItemList
        restLineItemMockMvc.perform(get("/api/line-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lineItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].feedback").value(hasItem(DEFAULT_FEEDBACK.toString())))
            .andExpect(jsonPath("$.[*].cleared").value(hasItem(DEFAULT_CLEARED.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getLineItem() throws Exception {
        // Initialize the database
        lineItemRepository.saveAndFlush(lineItem);

        // Get the lineItem
        restLineItemMockMvc.perform(get("/api/line-items/{id}", lineItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lineItem.getId().intValue()))
            .andExpect(jsonPath("$.feedback").value(DEFAULT_FEEDBACK.toString()))
            .andExpect(jsonPath("$.cleared").value(DEFAULT_CLEARED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingLineItem() throws Exception {
        // Get the lineItem
        restLineItemMockMvc.perform(get("/api/line-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLineItem() throws Exception {
        // Initialize the database
        lineItemRepository.saveAndFlush(lineItem);

        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();

        // Update the lineItem
        LineItem updatedLineItem = lineItemRepository.findById(lineItem.getId()).get();
        // Disconnect from session so that the updates on updatedLineItem are not directly saved in db
        em.detach(updatedLineItem);
        updatedLineItem
            .feedback(UPDATED_FEEDBACK)
            .cleared(UPDATED_CLEARED);
        LineItemDTO lineItemDTO = lineItemMapper.toDto(updatedLineItem);

        restLineItemMockMvc.perform(put("/api/line-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lineItemDTO)))
            .andExpect(status().isOk());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
        LineItem testLineItem = lineItemList.get(lineItemList.size() - 1);
        assertThat(testLineItem.getFeedback()).isEqualTo(UPDATED_FEEDBACK);
        assertThat(testLineItem.isCleared()).isEqualTo(UPDATED_CLEARED);
    }

    @Test
    @Transactional
    public void updateNonExistingLineItem() throws Exception {
        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();

        // Create the LineItem
        LineItemDTO lineItemDTO = lineItemMapper.toDto(lineItem);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLineItemMockMvc.perform(put("/api/line-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lineItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLineItem() throws Exception {
        // Initialize the database
        lineItemRepository.saveAndFlush(lineItem);

        int databaseSizeBeforeDelete = lineItemRepository.findAll().size();

        // Get the lineItem
        restLineItemMockMvc.perform(delete("/api/line-items/{id}", lineItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LineItem.class);
        LineItem lineItem1 = new LineItem();
        lineItem1.setId(1L);
        LineItem lineItem2 = new LineItem();
        lineItem2.setId(lineItem1.getId());
        assertThat(lineItem1).isEqualTo(lineItem2);
        lineItem2.setId(2L);
        assertThat(lineItem1).isNotEqualTo(lineItem2);
        lineItem1.setId(null);
        assertThat(lineItem1).isNotEqualTo(lineItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LineItemDTO.class);
        LineItemDTO lineItemDTO1 = new LineItemDTO();
        lineItemDTO1.setId(1L);
        LineItemDTO lineItemDTO2 = new LineItemDTO();
        assertThat(lineItemDTO1).isNotEqualTo(lineItemDTO2);
        lineItemDTO2.setId(lineItemDTO1.getId());
        assertThat(lineItemDTO1).isEqualTo(lineItemDTO2);
        lineItemDTO2.setId(2L);
        assertThat(lineItemDTO1).isNotEqualTo(lineItemDTO2);
        lineItemDTO1.setId(null);
        assertThat(lineItemDTO1).isNotEqualTo(lineItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(lineItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(lineItemMapper.fromId(null)).isNull();
    }
}
