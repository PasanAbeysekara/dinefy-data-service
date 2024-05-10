package com.thaprobit.resengine.controller.sys;

import com.thaprobit.resengine.controller.service.sys.SysEventService;
import com.thaprobit.resengine.dao.sys.Event;
import com.thaprobit.resengine.facade.dto.EventModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import org.junit.jupiter.api.BeforeEach;
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


class SysEventControllerTest {
    @Mock
    private SysEventService sysEventService;

    @InjectMocks
    private SysEventController sysEventController;

    @Mock
    private Event event;

    @Mock
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getEvents() {
        // Arrange
        EventModel emptyEventModel = new EventModel();
        CollectionModel<EventModel> eventModels = CollectionModel.of(Collections.singletonList(emptyEventModel));
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(10, 0, 100);
        PagedModel<EventModel> pagedModel = PagedModel.of(eventModels.getContent(), pageMetadata);
        ResponseWrapper<PagedModel<EventModel>> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, pagedModel);
        ResponseEntity<ResponseWrapper<PagedModel<EventModel>>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(sysEventService.getEvents(pageable)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<PagedModel<EventModel>>> result = sysEventController.getEvents(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
        verify(sysEventService).getEvents(pageable);
    }

    @Test
    void getEvent_WhenEventExists() {
        // Arrange
        int id = 1;
        Event existingEvent = new Event();
        existingEvent.setEventId(id);
        ResponseWrapper<Event> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, existingEvent);
        ResponseEntity<ResponseWrapper<Event>> expectedResponse = ResponseEntity.ok().body(responseWrapper);

        when(sysEventService.getEvent(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Event>> result = sysEventController.getEvent(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void getEvent_WhenEventDoesNotExist() {
        // Arrange
        int id = 1;
        ResponseEntity<ResponseWrapper<Event>> expectedResponse = buildNotFoundResponseWrapped();

        when(sysEventService.getEvent(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Event>> result = sysEventController.getEvent(id);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(expectedResponse.getBody(), result.getBody());
    }

    @Test
    void createEvent_Success() {
        // Arrange
        Event savedEvent = new Event();
        savedEvent.setEventId(1);
        ResponseWrapper<Event> responseWrapper = new ResponseWrapper<>(SystemOperation.CREATE.withSuccess(), SystemMessages.EVENT_CREATE_SUCCESS, savedEvent);
        ResponseEntity<ResponseWrapper<Event>> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(responseWrapper);

        when(sysEventService.createEvent(event)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseWrapper<Event>> result = sysEventController.createEvent(event);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(responseWrapper, result.getBody());
    }

    @Test
    void createEvent_Failure() {
        // Arrange
        when(sysEventService.createEvent(event)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            sysEventController.createEvent(event);
        });
        assertEquals("Database error", exception.getMessage());
    }

    // Helper method
    private ResponseEntity<ResponseWrapper<Event>> buildNotFoundResponseWrapped() {
        ResponseWrapper<Event> responseWrapper = new ResponseWrapper<>(SystemOperation.READ.withError(), SystemMessages.NOT_FOUND, (String) null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseWrapper);
    }
}
