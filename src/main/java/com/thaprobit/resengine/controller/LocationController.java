package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.controller.service.LocationService;
import com.thaprobit.resengine.dao.LocationBased;
import com.thaprobit.resengine.facade.dto.LocationsWrapper;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
 * @since 12/28/2020 21:22 PM
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class LocationController
{
	@Autowired
	private LocationService locationService;

	/**
	 * Get All based locations
	 *
	 * @param pageable The Pageable
	 * @return All base locations
	 */
	@GetMapping("/locations")
	public ResponseEntity<ResponseWrapper<LocationsWrapper>> getLocations( Pageable pageable )
	{
		return locationService.getLocations( pageable );
	}

	/**
	 * Get Single Location
	 *
	 * @param locationId  Based Location ID
	 * @return The Based Location
	 */
	@GetMapping("/locations/{id}")
	public ResponseEntity<ResponseWrapper<LocationBased>> getBasedLocation( @PathVariable("id") Long locationId )
	{
		return locationService.getBasedLocation( locationId );
	}

	/**
	 * Create a new base location
	 *
	 * @param locationBased LocationBased
	 * @return Saved LocationBased
	 */
	@PostMapping("/locations")
	public ResponseEntity<ResponseWrapper<LocationBased>> createLocationBased( @RequestBody LocationBased locationBased )
	{
		return locationService.createLocationBased( locationBased );
	}


	/**
	 * Update a based location
	 *
	 * @param locationId   LocationBased ID
	 * @param locationBased LocationBased
	 * @return Updated LocationBased
	 */
	@PutMapping("/locations/{id}")
	public ResponseEntity<ResponseWrapper<LocationBased>> updateLocationBased( @PathVariable("id") Long locationId, @RequestBody LocationBased locationBased )
	{
		return locationService.updateLocationBased( locationId, locationBased );
	}

	/**
	 * Delete LocationBased
	 *
	 * @param locationId LocationBased ID
	 * @return Delete LocationBased
	 */
	@DeleteMapping("/locations/{id}")
	public ResponseEntity<ResponseWrapper<LocationBased>> deleteLocationBased( @PathVariable("id") Long locationId )
	{
		return locationService.deleteLocationBased( locationId );
	}
}
