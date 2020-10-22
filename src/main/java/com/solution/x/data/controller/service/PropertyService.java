package com.solution.x.data.controller.service;

import com.solution.x.dao.Menu;
import com.solution.x.dao.OperationHours;
import com.solution.x.dao.PropAvailabilityUnit;
import com.solution.x.dao.PropChoices;
import com.solution.x.dao.PropFacilities;
import com.solution.x.dao.PropTags;
import com.solution.x.dao.Property;
import com.solution.x.dao.sys.PropertySpeciality;
import com.solution.x.data.controller.OrganizationController;
import com.solution.x.data.controller.assembler.MenuModelAssembler;
import com.solution.x.data.controller.assembler.PropertyModelAssembler;
import com.solution.x.data.controller.validator.PropertyValidator;
import com.solution.x.data.facade.dto.MenuModel;
import com.solution.x.data.facade.dto.PropertyModel;
import com.solution.x.data.messaging.producer.PropertyQueueProducer;
import com.solution.x.global.DataCarrier;
import com.solution.x.global.SystemOperation;
import com.solution.x.repo.PropFacilitiesRepository;
import com.solution.x.repo.PropertyRepository;
import com.solution.x.service.AbstractService;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.SystemMessages;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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

	@Autowired
	private PagedResourcesAssembler<Menu> pagedResourcesAssembler;

	@Autowired
	private MenuModelAssembler menuAssembler;

	@Autowired
	private PropertyModelAssembler propertyModelAssembler;

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
	 * Get all property menus
	 *
	 * @param id       Property ID
	 * @param pageable Pageable
	 * @return return All property menus
	 */
	public ResponseEntity<ResponseWrapper<PagedModel<MenuModel>>> getPropertyMenus( long id, Pageable pageable )
	{
		Page<Menu> menuPage = propertyRepository.findPropertyMenus( id, pageable );

		PagedModel<MenuModel> menuPagedModel = pagedResourcesAssembler.toModel( menuPage, menuAssembler );

		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, menuPagedModel ) );

	}


	/**
	 * Get Single property
	 *
	 * @param id property ID
	 * @return The Property
	 */
	public ResponseEntity<ResponseWrapper<PropertyModel>> getProperty( long id )
	{
		Optional<Property> optionalProperty = propertyRepository.findById( id );

		ResponseEntity<ResponseWrapper<PropertyModel>> response;

		if( optionalProperty.isPresent() )
		{
			Property property = optionalProperty.get();
			PropertyModel propertyModel = propertyModelAssembler.toModel( property );
			propertyModel.linkPropertyEntities();

			response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, propertyModel ) );
		}
		else
		{
			response = ResponseEntity.notFound().headers( new HttpHeaders() ).build();
		}

		return response;
	}


	public static final String ACCOUNT_SID = "AC8616f53c8c89dd86620f67d9404e2384";
	public static final String AUTH_TOKEN = "b29e2bd32b44b0aaf9017a5741ddd1c1";

	/**
	 * REMOVE
	 *
	 * @param property
	 */
	public void sms( Property property )
	{
		Twilio.init( ACCOUNT_SID, AUTH_TOKEN );

		Message message = Message.creator( new PhoneNumber( "+94718847252" ),//+94718847252
				new PhoneNumber( "+447412403311" ),
				"From Hangouts SMS gateway : " + property.getName() ).create();
		Message message2 = Message.creator( new PhoneNumber( "+94718847252" ),
				new PhoneNumber( "+447412403311" ),
				"From Hangouts SMS gateway : " + property.getDescription() ).create();

		Message message3 = Message.creator( new PhoneNumber( "+94718847252" ),
				new PhoneNumber( "+447412403311" ),
				"From Hangouts SMS gateway : Our cuisines : " + property.getPropertySpecialities().stream().map( PropertySpeciality::getName ).collect( Collectors.joining( "|" ) ) ).create();

		System.out.println( message.getSid() );
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
			property.getLivePromotions().forEach( promotion -> promotion.add( HATEOASProvider.promotionSelfLinkProvider( promotion.getPromoId() ) ) );
		}

		if( property.getMenus() != null )
		{
			property.getMenus().forEach( menu -> menu.add( HATEOASProvider.menuSelfLinkProvider( menu.getMenuId() ) ) );
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

		if( property.getChoices() != null )
		{
			for( PropChoices propChoice : property.getChoices() )
			{
				propChoice.setPropId( propId );
			}
		}

		if( property.getOperationHours() != null )
		{
			for( OperationHours operationHour : property.getOperationHours() )
			{
				operationHour.getOperationHourKey().setPropId( propId );
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
