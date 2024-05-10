package com.thaprobit.resengine.controller.sys;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.controller.service.sys.SysFacilityService;
import com.thaprobit.resengine.controller.service.sys.SysTagsService;
import com.thaprobit.resengine.dao.sys.Facilities;
import com.thaprobit.resengine.dao.sys.Tags;
import com.thaprobit.resengine.facade.dto.FacilitiesModel;
import com.thaprobit.resengine.facade.dto.TagsModel;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SysFacilityControllerTest {

    @Mock
    private SysFacilityService sysFacilityService;

    @InjectMocks
    private SysFacilityController sysFacilityController;

    @Mock
    private Facilities facility;

    @Mock
    private FacilitiesModel facilitiesModel;

    @Mock
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getFacilities() {
        // Arrange
        FacilitiesModel emptyFacilityModel = new FacilitiesModel();
        CollectionModel<FacilitiesModel> facilitiesModels = CollectionModel.of(Collections.singletonList(emptyFacilityModel));
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(10, 0, 100);
        PagedModel<FacilitiesModel> pagedModel = PagedModel.of(facilitiesModels.getContent(), pageMetadata);
        ResponseWrapper<PagedModel<FacilitiesModel>> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, pagedModel);
        ResponseEntity<ResponseWrapper<PagedModel<FacilitiesModel>>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(sysFacilityService.getFacilities(pageable)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<PagedModel<FacilitiesModel>>> result = sysFacilityService.getFacilities(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
        verify(sysFacilityService).getFacilities(pageable);
    }

    // Helper method
    private ResponseEntity<ResponseWrapper<Facilities>> buildNotFoundResponseWrapped() {
        ResponseWrapper<Facilities> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withError(), SystemMessages.NOT_FOUND, (String) null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseWrapper);
    }

    @Test
    void getFacility_WhenFacilityExists() {
        // Arrange
        int id = 1;
        Facilities existingFacility = new Facilities();
        existingFacility.add(HATEOASProvider.sysTagsSelfLinkProvider(id));
        ResponseWrapper<Facilities> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, existingFacility);
        ResponseEntity<ResponseWrapper<Facilities>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(sysFacilityService.getFacility(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Facilities>> result = sysFacilityService.getFacility(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void getFacility_WhenFacilityDoesNotExist() {
        // Arrange
        int id = 1;
        ResponseEntity<ResponseWrapper<Facilities>> expectedResponse = buildNotFoundResponseWrapped();

        when(sysFacilityService.getFacility(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Facilities>> result = sysFacilityService.getFacility(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(expectedResponse.getBody(), result.getBody());
    }

    @Test
    void createFacility_Success() {
        // Arrange
        Facilities savedFacility = new Facilities(); // Assuming Tags has a no-arg constructor
        savedFacility.setFacilityId(1); // Assuming there's a method to set ID
        savedFacility.add(HATEOASProvider.sysTagsSelfLinkProvider(savedFacility.getFacilityId()));

        ResponseWrapper<Facilities> responseWrapper = new ResponseWrapper<>(SystemOperation.CREATE.withSuccess(), SystemMessages.TAG_CREATE_SUCCESS, savedFacility);
        ResponseEntity<ResponseWrapper<Facilities>> expectedResponse = ResponseEntity.status(HttpStatus.CREATED)
                .body(responseWrapper);

        when(sysFacilityService.createFacility(facility)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Facilities>> result = sysFacilityService.createFacility(facility);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void createTag_Failure() {
        // Arrange
        when(sysFacilityService.createFacility(facility)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            sysFacilityController.createFacility(facility);
        });
        assertEquals("Database error", exception.getMessage());
    }

}
