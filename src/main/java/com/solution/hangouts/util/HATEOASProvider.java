package com.solution.hangouts.util;

import com.solution.hangouts.controller.OrganizationController;
import com.solution.hangouts.controller.PropertyController;
import com.solution.hangouts.controller.sys.SysFacilityController;
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

	public static Link propFacilitySelfLinkProvider( long id )
	{
		return linkTo( methodOn( PropertyController.class ).getPropFacilities( id ) ).withSelfRel();//.withRel("sysFacility");
	}

}
