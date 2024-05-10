package com.thaprobit.resengine.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.service.LocationService;
import com.thaprobit.resengine.dao.LocationBased;
import com.thaprobit.resengine.facade.dto.LocationBasedModel;
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

class LocationControllerTest {
    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @Mock
    private LocationBasedModel locationBasedModel;

    @Mock
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getLocations() {
        // Arrange
        LocationBasedModel emptyLocationBasedModel = new LocationBasedModel();
        CollectionModel<LocationBasedModel> locationBasedModels = CollectionModel.of(Collections.singletonList(emptyLocationBasedModel));
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(10, 0, 100);
        PagedModel<LocationBasedModel> pagedModel = PagedModel.of(locationBasedModels.getContent(), pageMetadata);
        ResponseWrapper<PagedModel<LocationBasedModel>> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, pagedModel);
        ResponseEntity<ResponseWrapper<PagedModel<LocationBasedModel>>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(locationService.getLocations(pageable)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<PagedModel<LocationBasedModel>>> result = locationController.getTags(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
        verify(locationService).getLocations(pageable);
    }

    @Test
    void getBasedLocation_WhenLocationExists() {
        // Arrange
        Long locationId = 1L;
        LocationBased existingLocation = new LocationBased();
        existingLocation.setLocationId(locationId);
        ResponseWrapper<LocationBased> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, existingLocation);
        ResponseEntity<ResponseWrapper<LocationBased>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(locationService.getBasedLocation(locationId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<LocationBased>> result = locationController.getBasedLocation(locationId);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void getBasedLocation_WhenLocationDoesNotExist() {
        // Arrange
        Long locationId = 1L;
        ResponseEntity<ResponseWrapper<LocationBased>> expectedResponse = buildNotFoundResponseWrapped();

        when(locationService.getBasedLocation(locationId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<LocationBased>> result = locationController.getBasedLocation(locationId);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(expectedResponse.getBody(), result.getBody());
    }

    // Helper method
    private ResponseEntity<ResponseWrapper<LocationBased>> buildNotFoundResponseWrapped() {
        ResponseWrapper<LocationBased> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withError(), SystemMessages.NOT_FOUND, (String) null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseWrapper);
    }
}

