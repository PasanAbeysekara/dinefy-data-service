package com.solution.hangouts.controller;

import com.solution.hangouts.dao.PropertyDAO;
import com.solution.hangouts.messaging.producer.PropertyQueueProducer;
import com.solution.hangouts.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PropertyController extends HngoutAbstractController<PropertyDAO>
{
	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private PropertyQueueProducer queueProducer;


	/**
	 * Get all properties
	 *
	 * @return return All properties
	 */
	@GetMapping("/properties")
	public ResponseEntity<List<PropertyDAO>> getProperties()
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propertyRepository.findAll() );
	}


	/**
	 * Get Single property
	 *
	 * @param id property ID
	 * @return The Property
	 */
	@GetMapping("/properties/{id}")
	public ResponseEntity<PropertyDAO> getProperty( @PathVariable("id") long id )
	{
		Optional<PropertyDAO> optionalPropertyDAO = propertyRepository.findById( id );

		return optionalPropertyDAO.map( propertyDAO -> ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propertyDAO ) ).orElseGet( this::buildNotFoundResponse );

	}


	/**
	 * Save a property
	 *
	 * @param propertyDAO property
	 * @return saved property
	 */
	@PostMapping("/properties")
	public ResponseEntity<PropertyDAO> saveProperty( @RequestBody PropertyDAO propertyDAO )
	{
		HttpHeaders responseHeaders = new HttpHeaders();

		PropertyDAO savedProp = null;
		ResponseEntity<PropertyDAO> response;

		try
		{
			savedProp = propertyRepository.save( propertyDAO );
			queueProducer.produceMessage( propertyDAO );

			response = ResponseEntity.ok().headers( responseHeaders ).body( savedProp );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = ResponseEntity.noContent().headers( responseHeaders ).build();
		}

		return response;
	}


	/**
	 * Get Property Names
	 *
	 * @return all property names
	 */
	@GetMapping("/properties/names")
	public ResponseEntity<List<String>> getPropertyNames()
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propertyRepository.findAll().stream().map( PropertyDAO::getName ).collect( Collectors.toList() ) );
	}
}
