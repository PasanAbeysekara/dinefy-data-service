package com.solution.x.controller.service;

import com.solution.x.controller.AbstractController;
import com.solution.x.controller.OrganizationController;
import com.solution.x.controller.validator.PropertyValidator;
import com.solution.x.dao.PropAvailabilityUnit;
import com.solution.x.dao.PropFacilities;
import com.solution.x.dao.PropTags;
import com.solution.x.dao.Property;
import com.solution.x.facade.ResponseWrapper;
import com.solution.x.facade.SystemMessages;
import com.solution.x.global.DataCarrier;
import com.solution.x.global.SystemOperation;
import com.solution.x.messaging.producer.PropertyQueueProducer;
import com.solution.x.repo.PropFacilitiesRepository;
import com.solution.x.repo.PropertyRepository;
import com.solution.x.util.HATEOASProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Tharinda Wickramaarachchi
 */
@Service
@Slf4j
public class PropertyService extends AbstractController<Property>
{
	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private PropertyQueueProducer queueProducer;

	@Autowired
	private PropFacilitiesRepository propFacilitiesRepository;

	@Autowired
	private PropertyValidator validator;

	/**
	 * Get all properties
	 *
	 * @return return All properties
	 */
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
	public ResponseEntity<List<PropFacilities>> getPropFacilities( @PathVariable("id") long id )
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propFacilitiesRepository.findByPropFacilityIdPropId( (int) id ) );
	}


	/**
	 * Get Single property
	 *
	 * @param id property ID
	 * @return The Property
	 */
	public ResponseEntity<Property> getProperty( long id )
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

		if( property.getFacilities() != null )
		{
			for( PropFacilities facility : property.getFacilities() )
			{
				int sysFacilityID = facility.getSysFacility().getFacilityId();

				Link selfRelSysFacility = HATEOASProvider.sysFacilitySelfLinkProvider( sysFacilityID );
				Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( facility.getPropFacilityId().getPropId() );

				facility.getSysFacility().add( selfRelSysFacility );
				facility.add( selfRelPropFacility );
			}
		}

		if( property.getPropTags() != null )
		{
			for( PropTags tags : property.getPropTags() )
			{
				Link selfRelSysTags = HATEOASProvider.sysTagsSelfLinkProvider( tags.getSysTags().getTagId() );
				//Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( tags.getPropTagID().getPropId() );

				tags.getSysTags().add( selfRelSysTags );
				//tags.add( selfRelPropFacility );
			}
		}

		if( property.getAvailabilityUnits() != null )
		{
			for( PropAvailabilityUnit availabilityUnit : property.getAvailabilityUnits() )
			{
				Link selfRelSysAvailabilityUnit = HATEOASProvider.sysAvailabilityUnitSelfLinkProvider( availabilityUnit.getSysAvailabilityUnit().getUnitId() );
				//Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( facility.getPropFacilityID().getPropId() );

				availabilityUnit.getSysAvailabilityUnit().add( selfRelSysAvailabilityUnit );
				//facility.add( selfRelPropFacility );
			}
		}
	}


	/**
	 * Save a property
	 *
	 * @param property property
	 * @return saved property
	 */
	//@org.springframework.transaction.annotation.Transactional // TODO transactional not working
	public ResponseEntity<ResponseWrapper<Property>> saveProperty( Property property )
	{
		Property savedProp = null;
		ResponseEntity<ResponseWrapper<Property>> response;

		try
		{
			DataCarrier<ResponseEntity<ResponseWrapper<Property>>> dataCarrierValidation = validator.validateCreate( property );

			if( dataCarrierValidation.isSuccess() )
			{
				savedProp = propertyRepository.saveAndFlush( property );
				//queueProducer.produceMessage( property );
				linkPropertyEntities( savedProp );

				response = ResponseEntity.ok()
						.headers( addCommonHeaders( new HttpHeaders() ) )
						.body( new ResponseWrapper<>( SystemOperation.CREATE, SystemMessages.PROPERTY_CREATE_SUCCESS, savedProp ) );
			}
			else
			{
				response = dataCarrierValidation.getData();
			}

		}
		catch( Exception e )
		{
			log.error( "Error Occurred during property creating : ", e );
			response = buildExceptionErrorResponse( SystemMessages.PROPERTY_CREATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Update a property
	 *
	 * @param id       The property ID
	 * @param property The property
	 * @return Updated property response
	 */
	@Transactional
	public ResponseEntity<ResponseWrapper<Property>> updateProperty( long id, Property property )
	{
		ResponseEntity<ResponseWrapper<Property>> response;

		try
		{
			property.setPropId( id );
			Property savedProperty = propertyRepository.save( property );
			linkPropertyEntities( savedProperty );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( "UPDATED", SystemMessages.PROPERTY_UPDATE_SUCCESS.getReasonPhrase(), savedProperty ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemMessages.PROPERTY_UPDATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Get Property Names
	 *
	 * @return all property names
	 */
	public ResponseEntity<List<String>> getPropertyNames()
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propertyRepository.findAll().stream().map( Property::getName ).collect( Collectors.toList() ) );
	}
}
