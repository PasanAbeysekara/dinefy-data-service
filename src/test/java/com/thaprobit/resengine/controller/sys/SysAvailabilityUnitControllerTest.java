package com.thaprobit.resengine.controller.sys;

import static org.junit.jupiter.api.Assertions.*;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.service.sys.SysAvailabilityUnitService;
import com.thaprobit.resengine.dao.sys.AvailabilityUnit;
import com.thaprobit.resengine.facade.dto.AvailabilityUnitModel;
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


class SysAvailabilityUnitControllerTest {
    @Mock
    private SysAvailabilityUnitService availabilityUnitService;

    @InjectMocks
    private SysAvailabilityUnitController sysAvailabilityUnitController;

    @Mock
    private AvailabilityUnit availabilityUnit;

    @Mock
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAvailabilityUnits() {
        // Arrange
        AvailabilityUnitModel emptyAvailabilityUnitModel = new AvailabilityUnitModel();
        CollectionModel<AvailabilityUnitModel> availabilityUnitModels = CollectionModel.of(Collections.singletonList(emptyAvailabilityUnitModel));
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(10, 0, 100);
        PagedModel<AvailabilityUnitModel> pagedModel = PagedModel.of(availabilityUnitModels.getContent(), pageMetadata);
        ResponseWrapper<PagedModel<AvailabilityUnitModel>> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, pagedModel);
        ResponseEntity<ResponseWrapper<PagedModel<AvailabilityUnitModel>>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(availabilityUnitService.getAvailabilityUnits(pageable)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<PagedModel<AvailabilityUnitModel>>> result = sysAvailabilityUnitController.getAvailabilityUnits(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
        verify(availabilityUnitService).getAvailabilityUnits(pageable);
    }

    @Test
    void getAvailabilityUnit_WhenAvailabilityUnitExists() {
        // Arrange
        int id = 1;
        AvailabilityUnit existingAvailabilityUnit = new AvailabilityUnit();
        existingAvailabilityUnit.setUnitId(id);
        ResponseWrapper<AvailabilityUnit> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, existingAvailabilityUnit);
        ResponseEntity<ResponseWrapper<AvailabilityUnit>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(availabilityUnitService.getAvailabilityUnit(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<AvailabilityUnit>> result = sysAvailabilityUnitController.getAvailabilityUnit(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void getAvailabilityUnit_WhenAvailabilityUnitDoesNotExist() {
        // Arrange
        int id = 1;
        ResponseEntity<ResponseWrapper<AvailabilityUnit>> expectedResponse = buildNotFoundResponseWrapped();

        when(availabilityUnitService.getAvailabilityUnit(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<AvailabilityUnit>> result = sysAvailabilityUnitController.getAvailabilityUnit(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(expectedResponse.getBody(), result.getBody());
    }

    @Test
    void createAvailabilityUnit_Success() {
        // Arrange
        AvailabilityUnit savedAvailabilityUnit = new AvailabilityUnit();
        savedAvailabilityUnit.setUnitId(1);
        ResponseWrapper<AvailabilityUnit> responseWrapper = new ResponseWrapper<>(SystemOperation.CREATE.withSuccess(), SystemMessages.AVAIL_UNIT_CREATE_SUCCESS, savedAvailabilityUnit);
        ResponseEntity<ResponseWrapper<AvailabilityUnit>> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(responseWrapper);

        when(availabilityUnitService.createAvailabilityUnit(availabilityUnit)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<AvailabilityUnit>> result = sysAvailabilityUnitController.createAvailabilityUnit(availabilityUnit);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void createAvailabilityUnit_Failure() {
        // Arrange
        when(availabilityUnitService.createAvailabilityUnit(availabilityUnit)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            sysAvailabilityUnitController.createAvailabilityUnit(availabilityUnit);
        });
        assertEquals("Database error", exception.getMessage());
    }

    // Helper method
    private ResponseEntity<ResponseWrapper<AvailabilityUnit>> buildNotFoundResponseWrapped() {
        ResponseWrapper<AvailabilityUnit> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withError(), SystemMessages.NOT_FOUND, (String) null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseWrapper);
    }
}
