package com.thaprobit.resengine.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.service.PromotionService;
import com.thaprobit.resengine.facade.dto.PromotionModel;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PromotionControllerTest {
    @Mock
    private PromotionService promotionService;

    @InjectMocks
    private PromotionController promotionController;

    @Mock
    private PromotionModel promotionModel;

    @Mock
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPromotions() {
        // Arrange
        PromotionModel emptyPromotionModel = new PromotionModel();
        CollectionModel<PromotionModel> promotionModels = CollectionModel.of(Collections.singletonList(emptyPromotionModel));
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(10, 0, 100);
        PagedModel<PromotionModel> pagedModel = PagedModel.of(promotionModels.getContent(), pageMetadata);
        ResponseWrapper<PagedModel<PromotionModel>> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, pagedModel);
        ResponseEntity<ResponseWrapper<PagedModel<PromotionModel>>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(promotionService.getAllPromotions(pageable)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<PagedModel<PromotionModel>>> result = promotionController.getAllPromotions(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
        verify(promotionService).getAllPromotions(pageable);
    }
}

