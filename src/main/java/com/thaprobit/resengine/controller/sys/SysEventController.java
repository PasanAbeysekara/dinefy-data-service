package com.thaprobit.resengine.controller.sys;

import com.thaprobit.resengine.controller.service.sys.SysEventService;
import com.thaprobit.resengine.dao.sys.Event;
import com.thaprobit.resengine.facade.dto.EventModel;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Tharindu Aththanayake
 * @since 12/29/2020 09:16 PM
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class SysEventController
{
	@Autowired
	private SysEventService sysEventService;

	/**
	 * Get All Events
	 *
	 * @return All sys events
	 */
	@GetMapping("/events")
	public ResponseEntity<ResponseWrapper<PagedModel<EventModel>>> getEvents( Pageable pageable ){ return  sysEventService.getEvents( pageable ); }

	/**
	 * Get Single Event
	 *
	 * @param id Event ID
	 * @return The Event
	 */
	@GetMapping("/events/{id}")
	public ResponseEntity<ResponseWrapper<Event>> getEvent( @PathVariable("id") int id )
	{
		return sysEventService.getEvent( id );
	}

	/**
	 * Create an event
	 *
	 * @param event The Event
	 * @return Saved event response
	 */
	@PostMapping("/events")
	public ResponseEntity<ResponseWrapper<Event>> createEvent( @RequestBody Event event )
	{
		return sysEventService.createEvent( event );
	}


	/**
	 * Update an Event
	 *
	 * @param id The Event ID
	 * @param event The Event
	 * @return Updated Event response
	 */
	@PutMapping("/events/{id}")
	public ResponseEntity<ResponseWrapper<Event>> updateEvent( @PathVariable("id") int id, @RequestBody Event event )
	{
		return sysEventService.updateEvent( id , event );
	}

	/**
	 * Delete an Event
	 *
	 * @param id The Event ID
	 * @return Delete response
	 */
	@DeleteMapping("/events/{id}")
	public ResponseEntity<ResponseWrapper<Event>> deleteEvent( @PathVariable("id") int id )
	{
		return sysEventService.deleteEvent( id );
	}
}

