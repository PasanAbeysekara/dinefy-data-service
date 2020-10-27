package com.thaprobit.resengine.controller.assembler;

import com.thaprobit.resengine.controller.PropertyController;
import com.thaprobit.resengine.dao.Property;
import com.thaprobit.resengine.facade.dto.PropertyModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayaka
 * @Since 14/09/2020 17:30 p.m.
 */
@Component
public class PropertyModelAssembler extends RepresentationModelAssemblerSupport<Property, PropertyModel>
{

	public PropertyModelAssembler()
	{
		super( PropertyController.class, PropertyModel.class );
	}

	@Override
	public PropertyModel toModel( Property entity )
	{
		PropertyModel propertyModel = new PropertyModel();
		propertyModel.setPropId( entity.getPropId() );
		propertyModel.setCode( entity.getCode() );
		propertyModel.setName( entity.getName() );
		propertyModel.setDescription( entity.getDescription() );
		propertyModel.setGeoLocation( null );
		propertyModel.setCurrentContId( entity.getCurrentContId() );
		propertyModel.setStartTime( entity.getStartTime() );
		propertyModel.setEndTime( entity.getEndTime() );
		propertyModel.setOperationHours( entity.getOperationHours() );
		propertyModel.setAvailabilityUnits( entity.getAvailabilityUnits() );
		propertyModel.setBasedLocation( entity.getBasedLocation() );
		propertyModel.setCurrentContract( entity.getCurrentContract() );
		propertyModel.setFacilities( entity.getFacilities() );
		propertyModel.setTags( entity.getTags() );
		propertyModel.setContactDetails( entity.getContactDetails() );
		propertyModel.setPropertySpecialities( entity.getPropertySpecialities() );
		propertyModel.setPaymentOptions( entity.getPaymentOptions() );
		propertyModel.setLivePromotions( entity.getLivePromotions() );
		propertyModel.setOrganizations( entity.getOrganizations() );
		propertyModel.setTimeSlots( entity.getTimeSlots() );
		propertyModel.setMenus( entity.getMenus() );
		propertyModel.setChoices( entity.getChoices() );
		propertyModel.setPropertyMedia( entity.getPropertyMedia() );

		return propertyModel;

	}
}
