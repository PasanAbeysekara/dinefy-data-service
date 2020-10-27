package com.solution.x.data.facade.dto;

import com.solution.x.dao.*;
import com.solution.x.dao.global.Point;
import com.solution.x.dao.sys.Facilities;
import com.solution.x.dao.sys.PaymentOptions;
import com.solution.x.dao.sys.PropertySpeciality;
import com.solution.x.data.controller.OrganizationController;
import com.solution.x.data.controller.service.HATEOASProvider;
import lombok.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalTime;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@EqualsAndHashCode(callSuper = true) //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel extends RepresentationModel<PropertyModel>
{
	private long propId;
	private String code;
	private String name;
	private String description;
	private Point geoLocation;
	private Integer currentContId;
	private LocalTime startTime;
	private LocalTime endTime;
	private Set<OperationHours> operationHours;
	private Set<PropAvailabilityUnit> availabilityUnits;
	private LocationBased basedLocation;
	private Contract currentContract;
	private Set<PropFacilityModel> facilities;
	private Set<PropTagsModel> tags;
	private ContactDetails contactDetails;
	private Set<PropertySpeciality> propertySpecialities;
	private Set<PaymentOptions> paymentOptions;
	private Set<Promotion> livePromotions;
	private OrganizationModel organizations;
	private List<LocalTime> timeSlots;
	private Set<MenuModel> menus;
	private Set<PropChoicesModel> choices;
	private PropertyMedia propertyMedia;

	public void setPropertyMedia( Set<PropMedia> propMedia )
	{
		PropertyMedia propertyMedia = new PropertyMedia();
		propertyMedia.processPropertyMedia( propMedia );
		this.propertyMedia = propertyMedia;
	}

	public void setMenus( Set<Menu> menus ){
		Set<MenuModel> menuModels = new HashSet<>();

		for(Menu menu: menus)
		{
			MenuModel menuModel = new MenuModel();
			menuModel.setMenuId( menu.getMenuId() );
			menuModel.setDescription( menu.getDescription() );
			menuModel.setMenuCategories( menu.getMenuCategories() );
			menuModels.add( menuModel );
		}

		this.menus = menuModels;
	}

	public void setBasedLocation( LocationBased locationBased )
	{
		LocationBasedModel locationBasedModel = new LocationBasedModel();
		locationBasedModel.setLocationId( locationBased.getLocationId() );
		locationBasedModel.setName( locationBased.getName() );
		this.basedLocation = locationBased;
	}

	public void setFacilities( Set<PropFacilities> facilities )
	{
		Set<PropFacilityModel> facilitiesModels = new HashSet<>();

		for(PropFacilities facility: facilities )
		{
			PropFacilityModel propFacilityModel = new PropFacilityModel();
			propFacilityModel.setPropFacilityId( facility.getPropFacilityId() );
			propFacilityModel.setName( facility.getName() );
			propFacilityModel.setDescription( facility.getDescription() );
			propFacilityModel.setSysFacility( facility.getSysFacility() );

			facilitiesModels.add( propFacilityModel );
		}

		this.facilities = facilitiesModels;
	}

	public void setTags( Set<PropTags> propTags )
	{
		Set<PropTagsModel> propTagsModels = new HashSet<>();

		for(PropTags tag : propTags)
		{
			PropTagsModel propTagsModel = new PropTagsModel();
			propTagsModel.setPropTagID( tag.getPropTagID() );
			propTagsModel.setName( tag.getName() );
			propTagsModel.setDescription( tag.getDescription() );
			propTagsModel.setSysTags( tag.getSysTags() );

			propTagsModels.add( propTagsModel );
		}

		this.tags = propTagsModels;
	}

	public void setOrganizations( Organization organization )
	{
		OrganizationModel organizationModel = new OrganizationModel();

		organizationModel.setOrgId( organization.getOrgId() );
		organizationModel.setName( organization.getName() );
		organizationModel.setCode( organization.getCode() );

		this.organizations = organizationModel;
	}

	public void setChoices( Set<PropChoices> propChoices)
	{
		Set<PropChoicesModel> propChoicesModels = new HashSet<>();

		for(PropChoices propChoice : propChoices)
		{
			PropChoicesModel propChoicesModel = new PropChoicesModel();
			propChoicesModel.setPropChoiceId( propChoice.getPropChoiceId() );
			propChoicesModel.setPropId( propChoice.getPropId() );
			propChoicesModel.setChoiceId( propChoice.getChoiceId() );
			propChoicesModel.setName( propChoice.getName() );
			propChoicesModel.setDescription( propChoice.getDescription() );
			propChoicesModel.setSysChoice( propChoice.getSysChoice() );

			propChoicesModels.add( propChoicesModel );
		}

		this.choices = propChoicesModels;
	}

	/**
	 * Add HATEOAS links for entities which are related to the property
	 */
	public void linkPropertyEntities()
	{
		Link selfRel = HATEOASProvider.propertySelfLinkProvider( this.getPropId() );
		this.add( selfRel );

		if( this.getOrganizations() != null )
		{
			Link orgSelfLink = linkTo( methodOn( OrganizationController.class ).getOrganization( this.getOrganizations().getOrgId() ) ).withRel( "org" );
			this.add( orgSelfLink );
		}

		if( this.getFacilities() != null )
		{
			for( PropFacilityModel facility : this.getFacilities() )
			{
				if( facility.getSysFacility() != null )
				{
					int sysFacilityID = facility.getSysFacility().getFacilityId();

					Link selfRelSysFacility = HATEOASProvider.sysFacilitySelfLinkProvider( sysFacilityID );
					//Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( facility.getPropFacilityId().getPropId() );

					facility.getSysFacility().add( selfRelSysFacility );
					//facility.add( selfRelPropFacility );
				}
			}
		}

		if( this.getTags() != null )
		{
			for( PropTagsModel tags : this.getTags() )
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

		if( this.getAvailabilityUnits() != null )
		{
			for( PropAvailabilityUnit availabilityUnit : this.getAvailabilityUnits() )
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

		if( this.getLivePromotions() != null )
		{
			this.getLivePromotions().forEach( promotion -> promotion.add( HATEOASProvider.promotionSelfLinkProvider( promotion.getPromoId() ) ) );
		}

		if( this.getMenus() != null )
		{
			this.getMenus().forEach( menu -> menu.add( HATEOASProvider.menuSelfLinkProvider( menu.getMenuId() ) ) );
		}

		if( this.getChoices() != null )
		{
			for( PropChoicesModel choices : this.getChoices() )
			{
				if( choices.getSysChoice() != null )
				{
					Link selfRelSysChoices = HATEOASProvider.sysChoicesSelfLinkProvider( choices.getSysChoice().getChoiceId() );

					choices.getSysChoice().add( selfRelSysChoices );
				}
			}
		}
	}

}
