package com.thaprobit.resengine.controller.service.sys;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.assembler.EventsModelAssembler;
import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.dao.sys.Event;
import com.thaprobit.resengine.dao.sys.Tags;
import com.thaprobit.resengine.facade.dto.EventModel;
import com.thaprobit.resengine.facade.dto.TagsModel;
import com.thaprobit.resengine.repo.sys.EventsRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author Tharindu Aththanayake
 * @since 12/29/2020 08:45 PM
 */
@Service
@Slf4j
public class SysEventService extends AbstractService<Event> {
    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private PagedResourcesAssembler<Event> pagedResourcesAssembler;

    @Autowired
    private EventsModelAssembler eventsModelAssembler;

    /**
     * Get All Events
     *
     * @return All sys events
     */
    public ResponseEntity<ResponseWrapper<PagedModel<EventModel>>> getEvents(Pageable pageable) {
        Page<Event> eventsPaged = eventsRepository.findAll(pageable);
        PagedModel<EventModel> collModel = pagedResourcesAssembler.toModel(eventsPaged, eventsModelAssembler);

        return ResponseEntity.ok()
                .headers(addCommonHeaders(new HttpHeaders()))
                .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, collModel));
    }

    /**
     * Get Single Event
     *
     * @param eventId Event ID
     * @return The Event
     */
    public ResponseEntity<ResponseWrapper<Event>> getEvent(int eventId) {
        Optional<Event> optionalEvent = eventsRepository.findById(eventId);

        ResponseEntity<ResponseWrapper<Event>> response;

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            Link selfRel = HATEOASProvider.sysEventSelfLinkProvider(event.getEventId());
            event.add(selfRel);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, event));
        } else {
            response = buildNotFoundResponseWrapped();
        }

        return response;

    }

    /**
     * Create an event
     *
     * @param event The Event
     * @return Saved event response
     */
    public ResponseEntity<ResponseWrapper<Event>> createEvent(Event event) {
        ResponseEntity<ResponseWrapper<Event>> response;

        try {
            Event savedEvent = eventsRepository.save(event);

            Link selfRel = HATEOASProvider.sysEventSelfLinkProvider(savedEvent.getEventId());
            savedEvent.add(selfRel);

            response = ResponseEntity.status(HttpStatus.CREATED)
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.CREATE.withSuccess(), SystemMessages.EVENT_CREATE_SUCCESS, savedEvent));
        } catch (Exception e) {
            e.printStackTrace();
            response = buildExceptionErrorResponse(SystemOperation.CREATE, SystemMessages.EVENT_CREATE_FAILED, e);
        }

        return response;
    }


    /**
     * Update an Event
     *
     * @param eventId The Event ID
     * @param event   The Event
     * @return Updated Event response
     */
    public ResponseEntity<ResponseWrapper<Event>> updateEvent(int eventId, Event event) {
        ResponseEntity<ResponseWrapper<Event>> response;

        try {
            event.setEventId(eventId);
            Event savedEvent = eventsRepository.save(event);

            Link selfRel = HATEOASProvider.sysEventSelfLinkProvider(savedEvent.getEventId());
            savedEvent.add(selfRel);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.MODIFY.withSuccess(), SystemMessages.EVENT_UPDATE_SUCCESS, savedEvent));
        } catch (Exception e) {
            e.printStackTrace();
            response = buildExceptionErrorResponse(SystemOperation.MODIFY, SystemMessages.EVENT_UPDATE_FAILED, e);
        }

        return response;
    }

    /**
     * Delete an event
     *
     * @param eventId The Event ID
     * @return Delete response
     */
    public ResponseEntity<ResponseWrapper<Event>> deleteEvent(int eventId) {
        ResponseEntity<ResponseWrapper<Event>> response;

        try {
            eventsRepository.deleteById(eventId);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.DELETE.withSuccess(), SystemMessages.EVENT_DELETE_SUCCESS, ""));
        } catch (Exception e) {
            e.printStackTrace();
            response = buildExceptionErrorResponse(SystemOperation.DELETE, SystemMessages.EVENT_DELETE_FAILED, e);
        }

        return response;
    }
}
