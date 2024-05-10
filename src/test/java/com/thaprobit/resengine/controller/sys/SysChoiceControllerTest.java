package com.thaprobit.resengine.controller.sys;

import static org.junit.jupiter.api.Assertions.*;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.service.sys.SysChoiceService;
import com.thaprobit.resengine.dao.sys.Choices;
import com.thaprobit.resengine.facade.dto.ChoiceModel;
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
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class SysChoiceControllerTest {
    @Mock
    private SysChoiceService choiceService;

    @InjectMocks
    private SysChoiceController sysChoiceController;

    @Mock
    private Choices choice;

    @Mock
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getChoices() {
        // Arrange
        ChoiceModel emptyChoiceModel = new ChoiceModel();
        CollectionModel<ChoiceModel> choiceModels = CollectionModel.of(Collections.singletonList(emptyChoiceModel));
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(10, 0, 100);
        PagedModel<ChoiceModel> pagedModel = PagedModel.of(choiceModels.getContent(), pageMetadata);
        ResponseWrapper<PagedModel<ChoiceModel>> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, pagedModel);
        ResponseEntity<ResponseWrapper<PagedModel<ChoiceModel>>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(choiceService.getChoices(pageable)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<PagedModel<ChoiceModel>>> result = sysChoiceController.getChoices(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
        verify(choiceService).getChoices(pageable);
    }

    @Test
    void getChoice_WhenChoiceExists() {
        // Arrange
        int id = 1;
        Choices existingChoice = new Choices();
        existingChoice.setChoiceId(id);
        ResponseWrapper<Choices> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, existingChoice);
        ResponseEntity<ResponseWrapper<Choices>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(choiceService.getChoice(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Choices>> result = sysChoiceController.getChoice(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void getChoice_WhenChoiceDoesNotExist() {
        // Arrange
        int id = 1;
        ResponseEntity<ResponseWrapper<Choices>> expectedResponse = buildNotFoundResponseWrapped();

        when(choiceService.getChoice(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Choices>> result = sysChoiceController.getChoice(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(expectedResponse.getBody(), result.getBody());
    }

    @Test
    void createChoice_Success() {
        // Arrange
        Choices savedChoice = new Choices();
        savedChoice.setChoiceId(1);
        ResponseWrapper<Choices> responseWrapper = new ResponseWrapper<>(SystemOperation.CREATE.withSuccess(), SystemMessages.CHOICE_CREATE_SUCCESS, savedChoice);
        ResponseEntity<ResponseWrapper<Choices>> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(responseWrapper);

        when(choiceService.createChoice(choice)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Choices>> result = sysChoiceController.createChoice(choice);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void createChoice_Failure() {
        // Arrange
        when(choiceService.createChoice(choice)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            sysChoiceController.createChoice(choice);
        });
        assertEquals("Database error", exception.getMessage());
    }

    // Helper method
    private ResponseEntity<ResponseWrapper<Choices>> buildNotFoundResponseWrapped() {
        ResponseWrapper<Choices> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withError(), SystemMessages.NOT_FOUND, (String) null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseWrapper);
    }
}
