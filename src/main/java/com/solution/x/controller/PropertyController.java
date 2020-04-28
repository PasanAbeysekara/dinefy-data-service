package com.solution.x.controller;

import com.solution.x.dao.PropAvailabilityUnit;
import com.solution.x.dao.PropFacilities;
import com.solution.x.dao.PropTags;
import com.solution.x.dao.Property;
import com.solution.x.messaging.producer.PropertyQueueProducer;
import com.solution.x.repo.PropFacilitiesRepository;
import com.solution.x.repo.PropertyRepository;
import com.solution.x.util.HATEOASProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class PropertyController extends AbstractController<Property>
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
		Optional<Property> optionalProperty = propertyRepository.findById( id );

		ResponseEntity<Property> response;

		if( optionalProperty.isPresent() )
		{
			Property property = optionalProperty.get();
			linkPropertyEntities( property );

			response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) ).body( property );
		}
		else
		{
			response = buildNotFoundResponse();
		}


		return response;

	}

	/**
	 * Add HATEOAS links for entities which are related to the property
	 *
	 * @param property property
	 */
	private void linkPropertyEntities( Property property )
	{
		Link selfRel = HATEOASProvider.propertySelfLinkProvider( property.getPropId() );
		property.add( selfRel );

		if( property.getOrganizations() != null )
		{
			Link orgSelfLink = linkTo( methodOn( OrganizationController.class ).getOrganization( property.getOrganizations().getOrgId() ) ).withRel( "org" );
			property.add( orgSelfLink );
		}

		for( PropFacilities facility : property.getFacilities() )
		{
			int sysFacilityID = facility.getSysFacility().getFacility_id();

			Link selfRelSysFacility = HATEOASProvider.sysFacilitySelfLinkProvider( sysFacilityID );
			Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( facility.getPropFacilityID().getPropId() );

			facility.getSysFacility().add( selfRelSysFacility );
			facility.add( selfRelPropFacility );
		}

		for( PropTags tags : property.getPropTags() )
		{
			Link selfRelSysTags = HATEOASProvider.sysTagsSelfLinkProvider( tags.getSysTags().getTag_id() );
			//Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( tags.getPropTagID().getPropId() );

			tags.getSysTags().add( selfRelSysTags );
			//tags.add( selfRelPropFacility );
		}

		for( PropAvailabilityUnit availabilityUnit : property.getAvailabilityUnits() )
		{
			Link selfRelSysAvailabilityUnit = HATEOASProvider.sysAvailabilityUnitSelfLinkProvider( availabilityUnit.getSysAvailabilityUnit().getUnit_id() );
			//Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( facility.getPropFacilityID().getPropId() );

			availabilityUnit.getSysAvailabilityUnit().add( selfRelSysAvailabilityUnit );
			//facility.add( selfRelPropFacility );
		}
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
