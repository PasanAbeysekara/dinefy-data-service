package com.thaprobit.resengine.controller.sys;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.controller.service.sys.SysTagsService;
import com.thaprobit.resengine.dao.sys.Tags;
import com.thaprobit.resengine.facade.dto.TagsModel;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SysTagsControllerTest {
    @Mock
    private SysTagsService sysTagsService;

    @InjectMocks
    private SysTagsController sysTagsController;

    @Mock
    private Tags tag;

    @Mock
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getTags() {
        // Arrange
        TagsModel emptyTagModel = new TagsModel();
        CollectionModel<TagsModel> tagModels = CollectionModel.of(Collections.singletonList(emptyTagModel));
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(10, 0, 100);
        PagedModel<TagsModel> pagedModel = PagedModel.of(tagModels.getContent(), pageMetadata);
        ResponseWrapper<PagedModel<TagsModel>> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, pagedModel);
        ResponseEntity<ResponseWrapper<PagedModel<TagsModel>>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(sysTagsService.getTags(pageable)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<PagedModel<TagsModel>>> result = sysTagsController.getTags(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
        verify(sysTagsService).getTags(pageable);
    }

    // Helper method
    private ResponseEntity<ResponseWrapper<Tags>> buildNotFoundResponseWrapped() {
        ResponseWrapper<Tags> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withError(), SystemMessages.NOT_FOUND, (String) null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseWrapper);
    }

    @Test
    void getTag_WhenTagExists() {
        // Arrange
        int id = 1;
        Tags existingTag = new Tags();
        existingTag.add(HATEOASProvider.sysTagsSelfLinkProvider(id));
        ResponseWrapper<Tags> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, existingTag);
        ResponseEntity<ResponseWrapper<Tags>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(sysTagsService.getTag(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Tags>> result = sysTagsController.getTag(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void getTag_WhenTagDoesNotExist() {
        // Arrange
        int id = 1;
        ResponseEntity<ResponseWrapper<Tags>> expectedResponse = buildNotFoundResponseWrapped();

        when(sysTagsService.getTag(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Tags>> result = sysTagsController.getTag(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(expectedResponse.getBody(), result.getBody());
    }

    @Test
    void createTag_Success() {
        // Arrange
        Tags savedTag = new Tags(); // Assuming Tags has a no-arg constructor
        savedTag.setTagId(1); // Assuming there's a method to set ID
        savedTag.add(HATEOASProvider.sysTagsSelfLinkProvider(savedTag.getTagId()));

        ResponseWrapper<Tags> responseWrapper = new ResponseWrapper<>(SystemOperation.CREATE.withSuccess(), SystemMessages.TAG_CREATE_SUCCESS, savedTag);
        ResponseEntity<ResponseWrapper<Tags>> expectedResponse = ResponseEntity.status(HttpStatus.CREATED)
                .body(responseWrapper);

        when(sysTagsService.createTag(tag)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Tags>> result = sysTagsController.createTag(tag);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void createTag_Failure() {
        // Arrange
        when(sysTagsService.createTag(tag)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            sysTagsController.createTag(tag);
        });
        assertEquals("Database error", exception.getMessage());
    }
}
