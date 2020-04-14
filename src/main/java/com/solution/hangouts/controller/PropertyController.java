package com.solution.hangouts.controller;

import com.solution.hangouts.dao.PropFacilities;
import com.solution.hangouts.dao.Property;
import com.solution.hangouts.messaging.producer.PropertyQueueProducer;
import com.solution.hangouts.repo.PropFacilitiesRepository;
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

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class PropertyController extends HngoutAbstractController<Property>
{
	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private PropertyQueueProducer queueProducer;

	@Autowired
	private PropFacilitiesRepository propFacilitiesRepository;

	/**
	 * Get all properties
	 *
	 * @return return All properties
	 */
	@GetMapping("/properties")
	public ResponseEntity<List<Property>> getProperties()
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propertyRepository.findAll() );
	}

	/**
	 * Get all properties
	 *
	 * @return return All properties
	 */
	@GetMapping("/properties/{id}/facilities")
	public ResponseEntity<List<PropFacilities>> getPropFacilities( @PathVariable("id") long id )
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propFacilitiesRepository.findByPropFacilityIDPropId( (int) id ) );
	}


	/**
	 * Get Single property
	 *
	 * @param id property ID
	 * @return The Property
	 */
	@GetMapping("/properties/{id}")
	public ResponseEntity<Property> getProperty( @PathVariable("id") long id )
	{
		Optional<Property> optionalPropertyDAO = propertyRepository.findById( id );

		return optionalPropertyDAO.map( propertyDAO -> ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propertyDAO ) ).orElseGet( this::buildNotFoundResponse );

	}


	/**
	 * Save a property
	 *
	 * @param property property
	 * @return saved property
	 */
	@PostMapping("/properties")
	public ResponseEntity<Property> saveProperty( @RequestBody Property property )
	{
		HttpHeaders responseHeaders = new HttpHeaders();

		Property savedProp = null;
		ResponseEntity<Property> response;

		try
		{
			savedProp = propertyRepository.save( property );
			queueProducer.produceMessage( property );

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
				.body( propertyRepository.findAll().stream().map( Property::getName ).collect( Collectors.toList() ) );
	}
}
