package com.solution.x.data.controller.service;

import com.solution.x.dao.key.PromoID;
import com.solution.x.data.controller.ContractController;
import com.solution.x.data.controller.OrganizationController;
import com.solution.x.data.controller.PromotionController;
import com.solution.x.data.controller.PropertyController;
import com.solution.x.data.controller.sys.SysAvailabilityUnitController;
import com.solution.x.data.controller.sys.SysFacilityController;
import com.solution.x.data.controller.sys.SysTagsController;
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

}
