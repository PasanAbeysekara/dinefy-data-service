package com.solution.x.controller;

import com.solution.x.dao.PropFacilities;
import com.solution.x.dao.Property;
import com.solution.x.search.controller.service.PropertyService;
import com.solution.x.search.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class PropertyController
{
	@Autowired
	private PropertyService propertyService;

	/**
	 * Get all properties
	 *
	 * @return return All properties
	 */
	@GetMapping("/properties")
	public ResponseEntity<List<Property>> getProperties()
	{
		return propertyService.getProperties();
	}

	/**
	 * Get all properties
	 *
	 * @return return All properties
	 */
	@GetMapping("/properties/{id}/facilities")
	public ResponseEntity<List<PropFacilities>> getPropFacilities( @PathVariable("id") long id )
	{
		return propertyService.getPropFacilities( id );
	}

	/**
	 * Get Single property
	 *
	 * @param id property ID
	 * @return The Property
	 */
	@GetMapping("/properties/{id}")
	public ResponseEntity<ResponseWrapper<Property>> getProperty( @PathVariable("id") long id )
	{
		return propertyService.getProperty( id );
	}

	/**
	 * Save a property
	 *
	 * @param property property
	 * @return saved property
	 */
	@PostMapping("/properties")
	public ResponseEntity<ResponseWrapper<Property>> saveProperty( @RequestBody Property property )
	{
		return propertyService.saveProperty( property );
	}

	@PutMapping("/properties/{id}")
	public ResponseEntity<ResponseWrapper<Property>> updateProperty( @PathVariable("id") long id, @RequestBody Property property )
	{
		return propertyService.updateProperty( id, property );
	}

	@DeleteMapping("/properties/{id}")
	public ResponseEntity<ResponseWrapper<Property>> deleteProperty( @PathVariable("id") long id )
	{
		return propertyService.deleteProperty( id );
	}

	/**
	 * Get Property Names
	 *
	 * @return all property names
	 */
	@GetMapping("/properties/names")
	public ResponseEntity<List<String>> getPropertyNames()
	{
		return propertyService.getPropertyNames();
	}
}
