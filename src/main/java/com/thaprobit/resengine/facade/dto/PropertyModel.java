package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.controller.OrganizationController;
import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.dao.ContactDetails;
import com.thaprobit.resengine.dao.Contract;
import com.thaprobit.resengine.dao.LocationBased;
import com.thaprobit.resengine.dao.Menu;
import com.thaprobit.resengine.dao.OperationHours;
import com.thaprobit.resengine.dao.Organization;
import com.thaprobit.resengine.dao.Promotion;
import com.thaprobit.resengine.dao.PropAvailabilityUnit;
import com.thaprobit.resengine.dao.PropChoices;
import com.thaprobit.resengine.dao.PropFacilities;
import com.thaprobit.resengine.dao.PropMedia;
import com.thaprobit.resengine.dao.PropTags;
import com.thaprobit.resengine.dao.global.Point;
import com.thaprobit.resengine.dao.sys.Choices;
import com.thaprobit.resengine.dao.sys.PaymentOptions;
import com.thaprobit.resengine.dao.sys.PropertySpeciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
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
	private BigDecimal avgRating;
	private Integer totalRating;
	private BigDecimal amount;
	private String amountCurrency;
	private String amountCondition;
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
	//private Set<MenuModel> menus;
	//private Set<PropChoicesModel> choices;
	private PropMenusModel propMenus;
	private PropertyMedia propertyMedia;

	public void setPropertyMedia( Set<PropMedia> propMedia )
	{
		PropertyMedia propertyMedia = new PropertyMedia();
		propertyMedia.processPropertyMedia( propMedia );
		this.propertyMedia = propertyMedia;
	}

	/*public void setMenus( Set<Menu> menus ){
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
	}*/

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
			propFacilityModel.setOrder( facility.getOrder() );
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
			propTagsModel.setOrder( tag.getOrder() );
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

	/*public void setChoices( Set<PropChoices> propChoices)
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
	}*/

	public void setPropMenus( Set<Menu> menus, Set<PropChoices> propChoices )
	{
		PropMenusModel propMenusModel = new PropMenusModel();

		propMenusModel.setMenus( menus );
		propMenusModel.setChoices( propChoices );

		this.propMenus = propMenusModel;
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

		if( this.propMenus.getMenus() != null )
		{
			this.propMenus.getMenus().forEach( menu -> menu.add( HATEOASProvider.menuSelfLinkProvider( menu.getMenuId() ) ) );

			for( PropChoicesModel choices : this.propMenus.getChoices() )
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
