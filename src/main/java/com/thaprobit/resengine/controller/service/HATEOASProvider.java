package com.thaprobit.resengine.controller.service;

import com.thaprobit.resengine.controller.ContractController;
import com.thaprobit.resengine.controller.MenuController;
import com.thaprobit.resengine.controller.OrganizationController;
import com.thaprobit.resengine.controller.PromotionController;
import com.thaprobit.resengine.controller.PropertyController;
import com.thaprobit.resengine.controller.sys.SysAvailabilityUnitController;
import com.thaprobit.resengine.controller.sys.SysChoiceController;
import com.thaprobit.resengine.controller.sys.SysEventController;
import com.thaprobit.resengine.controller.sys.SysFacilityController;
import com.thaprobit.resengine.controller.sys.SysTagsController;
import com.thaprobit.resengine.dao.key.PromoID;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/17/2020 11:55 PM
 * <p>
 * TODO This is very basic temporally solution. Need to implement another implementation
 */
@Component
public class HATEOASProvider
{
	public static Link organizationSelfLinkProvider( long id )
	{
		return linkTo( methodOn( OrganizationController.class ).getOrganization( id ) ).withSelfRel();
	}

	public static Link propertySelfLinkProvider( long id )
	{
		return linkTo( methodOn( PropertyController.class ).getProperty( id ) ).withSelfRel();
	}

	public static Link sysFacilitySelfLinkProvider( int id )
	{
		return linkTo( methodOn( SysFacilityController.class ).getFacility( id ) ).withSelfRel();//.withRel("sysFacility");
	}

	public static Link sysAvailabilityUnitSelfLinkProvider( int id )
	{
		return linkTo( methodOn( SysAvailabilityUnitController.class ).getAvailabilityUnit( id ) ).withSelfRel();
	}

	public static Link sysTagsSelfLinkProvider( int id )
	{
		return linkTo( methodOn( SysTagsController.class ).getTag( id ) ).withSelfRel();
	}

	public static Link propFacilitySelfLinkProvider( long id )
	{
		return linkTo( methodOn( PropertyController.class ).getPropFacilities( id ) ).withSelfRel();
	}

	public static Link contractSelfLinkProvider( long id )
	{
		return linkTo( methodOn( ContractController.class ).getContract( id ) ).withSelfRel();
	}

	public static Link promotionSelfLinkProvider( PromoID promoId )
	{
		return linkTo( methodOn( PromotionController.class ).getPromotion( promoId.getPropId(), promoId.getPromoId() ) ).withSelfRel();
	}

	public static Link menuSelfLinkProvider( long id )
	{
		return linkTo( methodOn( MenuController.class ).getMenu( id ) ).withSelfRel();
	}

	public static Link sysChoicesSelfLinkProvider( long id )
	{
		return linkTo( methodOn( SysChoiceController.class ).getChoice( id ) ).withSelfRel();
	}

	public static Link sysEventSelfLinkProvider( int id ) { return linkTo( methodOn( SysEventController.class ).getEvent( id ) ).withSelfRel(); }

}
