package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.controller.service.AvailDataSearchService;
import com.thaprobit.resengine.controller.service.PropertyService;
import com.thaprobit.resengine.dao.PropFacilities;
import com.thaprobit.resengine.dao.Property;
import com.thaprobit.resengine.facade.dto.AvailDataWrapper;
import com.thaprobit.resengine.facade.dto.MenuModel;
import com.thaprobit.resengine.facade.dto.PropMenuWrapper;
import com.thaprobit.resengine.facade.dto.PropertyModel;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class PropertyController
{
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private AvailDataSearchService availDataSearchService;

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
	 * Get all property menus by prop code
	 *
	 * @param code     PropertyModel Code
	 * @return return All property menus
	 */
	@GetMapping("/properties/{code}/menus")
	public ResponseEntity<ResponseWrapper<PropMenuWrapper>> getPropMenusByPropCode( @PathVariable("code") String code)
	{
		return propertyService.getPropertyMenus( code );
	}

	/**
	 * Get all property menus
	 *
	 * @param id       PropertyModel ID
	 * @param pageable Pageable
	 * @return return All property menus
	 */
	@GetMapping("/properties/id/{id}/menus")
	public ResponseEntity<ResponseWrapper<PagedModel<MenuModel>>> getPropMenusByPropId( @PathVariable("id") long id, Pageable pageable )
	{
		return propertyService.getPropertyMenus( id, pageable );
	}

	/**
	 * Get all property availabilities for a given time period
	 *
	 * @param propId               PropertyModel ID
	 * @param dateFrom             Search start date
	 * @param dateTo               Search end date
	 * @param timeFrom             Search start time
	 * @param timeTo               Search end time
	 * @param availabilityUnitType Availability Unit Type
	 * @param availabilityUnit     Availability Unit Name
	 * @param pageable             Pageable
	 * @return return all availabilities in requested time period
	 */
	@GetMapping("/properties/avail-units")
	public ResponseEntity<ResponseWrapper<AvailDataWrapper>> getPropertyAvailableDataInAGivenTime( @RequestParam(name = "prop_id", required = true) Long propId,
																								   @RequestParam(name = "date_from", required = true)
																								   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
																								   @RequestParam(name = "date_to", required = true)
																								   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
																								   @RequestParam(name = "time_from", required = true)
																								   @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeFrom,
																								   @RequestParam(name = "time_to", required = true)
																								   @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeTo,
																								   @RequestParam(name = "avail_unit_type") String availabilityUnitType,
																								   @RequestParam(name = "avail_unit") String availabilityUnit,
																								   Pageable pageable )
	{
		return availDataSearchService.getPropertyAvailabilities( propId, dateFrom, dateTo, timeFrom, timeTo, availabilityUnitType, availabilityUnit, pageable );
	}

	/**
	 * Get Single property
	 *
	 * @param id property ID
	 * @return The PropertyModel
	 */
	@GetMapping("/properties/{id}")
	public ResponseEntity<ResponseWrapper<PropertyModel>> getProperty( @PathVariable("id") long id )
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
	 * Get PropertyModel Names
	 *
	 * @return all property names
	 */
	@GetMapping("/properties/names")
	public ResponseEntity<List<String>> getPropertyNames()
	{
		return propertyService.getPropertyNames();
	}
}
