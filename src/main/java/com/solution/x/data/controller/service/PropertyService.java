package com.solution.x.data.controller.service;

import com.solution.x.dao.*;
import com.solution.x.data.controller.OrganizationController;
import com.solution.x.data.controller.validator.PropertyValidator;
import com.solution.x.data.messaging.producer.PropertyQueueProducer;
import com.solution.x.global.DataCarrier;
import com.solution.x.global.SystemOperation;
import com.solution.x.repo.PropFacilitiesRepository;
import com.solution.x.repo.PropertyRepository;
import com.solution.x.service.AbstractService;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
public class PropertyService extends AbstractService<Property>
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
	public ResponseEntity<List<PropFacilities>> getPropFacilities( long id )
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
	public ResponseEntity<ResponseWrapper<Property>> getProperty( long id )
	{
		Optional<Property> optionalProperty = propertyRepository.findById( id );

		ResponseEntity<ResponseWrapper<Property>> response;

		if( optionalProperty.isPresent() )
		{
			Property property = optionalProperty.get();
			linkPropertyEntities( property );

			response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, property ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
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
				if( facility.getSysFacility() != null )
				{
					int sysFacilityID = facility.getSysFacility().getFacilityId();

					Link selfRelSysFacility = HATEOASProvider.sysFacilitySelfLinkProvider( sysFacilityID );
					Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( facility.getPropFacilityId().getPropId() );

					facility.getSysFacility().add( selfRelSysFacility );
					facility.add( selfRelPropFacility );
				}
			}
		}

		if( property.getTags() != null )
		{
			for( PropTags tags : property.getTags() )
			{
				if( tags.getSysTags() != null )
				{
					Link selfRelSysTags = HATEOASProvider.sysTagsSelfLinkProvider( tags.getSysTags().getTagId() );
					//Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( tags.getPropTagID().getPropId() );

					tags.getSysTags().add( selfRelSysTags );
					//tags.add( selfRelPropFacility );
				}
			}
		}

		if( property.getAvailabilityUnits() != null )
		{
			for( PropAvailabilityUnit availabilityUnit : property.getAvailabilityUnits() )
			{
				if( availabilityUnit.getSysAvailabilityUnit() != null )
				{
					Link selfRelSysAvailabilityUnit = HATEOASProvider.sysAvailabilityUnitSelfLinkProvider( availabilityUnit.getSysAvailabilityUnit().getUnitId() );
					//Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( facility.getPropFacilityID().getPropId() );

					availabilityUnit.getSysAvailabilityUnit().add( selfRelSysAvailabilityUnit );
					//facility.add( selfRelPropFacility );
				}
			}
		}

		if( property.getLivePromotions() != null )
		{
			property.getLivePromotions().forEach( promotion -> promotion.add( HATEOASProvider.promotionSelfLinkProvider( promotion.getPromoId() )) );
		}

		if( property.getMenus() != null )
		{
			property.getMenus().forEach( menu -> menu.add( HATEOASProvider.menuSelfLinkProvider( menu.getMenuId() )));
		}

		if( property.getChoices() != null )
		{
			for( PropChoices choices : property.getChoices() )
			{
				if( choices.getSysChoice() != null )
				{
					Link selfRelSysChoices = HATEOASProvider.sysChoicesSelfLinkProvider( choices.getSysChoice().getChoiceId() );

					choices.getSysChoice().add( selfRelSysChoices );
				}
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
				Long propNextVal = propertyRepository.getNextVal();
				property.setPropId( propNextVal );

				preProcess( property );

				savedProp = propertyRepository.save( property );
				//queueProducer.produceMessage( property );
				linkPropertyEntities( savedProp );

				response = ResponseEntity.status( HttpStatus.CREATED )
						.headers( addCommonHeaders( new HttpHeaders() ) )
						.body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.PROPERTY_CREATE_SUCCESS, savedProp ) );
			}
			else
			{
				response = dataCarrierValidation.getData();
			}

		}
		catch( Exception e )
		{
			log.error( "Error Occurred during property creating : ", e );
			response = buildExceptionErrorResponse( SystemOperation.CREATE, SystemMessages.PROPERTY_CREATE_FAILED, e );
		}

		return response;
	}

	private void preProcess( Property property )
	{
		long propId = property.getPropId();

		if( property.getFacilities() != null )
		{
			for( PropFacilities facility : property.getFacilities() )
			{
				facility.getPropFacilityId().setPropId( propId );
			}
		}

		if( property.getTags() != null )
		{
			for( PropTags propTag : property.getTags() )
			{
				propTag.getPropTagID().setPropId( propId );
			}
		}

		if( property.getChoices() != null)
		{
			for( PropChoices propChoice : property.getChoices() )
			{
				propChoice.setPropId( propId );
			}
		}
	}

	/**
	 * Update a property
	 *
	 * @param id       The property ID
	 * @param property The property
	 * @return Updated property response
	 */
	public ResponseEntity<ResponseWrapper<Property>> updateProperty( long id, Property property )
	{
		ResponseEntity<ResponseWrapper<Property>> response;

		try
		{
			property.setPropId( id );
			preProcess( property );


			Property savedProperty = propertyRepository.save( property );
			linkPropertyEntities( savedProperty );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.PROPERTY_UPDATE_SUCCESS, savedProperty ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.MODIFY, SystemMessages.PROPERTY_UPDATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Delete property
	 *
	 * @param id contract ID
	 * @return
	 */
	public ResponseEntity<ResponseWrapper<Property>> deleteProperty( long id )
	{
		ResponseEntity<ResponseWrapper<Property>> response;

		try
		{
			propertyRepository.deleteById( id );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.PROPERTY_DELETE_SUCCESS, "" ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during property deleting : ", e );
			response = buildExceptionErrorResponse( SystemOperation.DELETE, SystemMessages.PROPERTY_DELETE_FAILED, e );
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
