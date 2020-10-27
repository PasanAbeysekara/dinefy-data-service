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
import com.thaprobit.resengine.dao.sys.PaymentOptions;
import com.thaprobit.resengine.dao.sys.PropertySpeciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalTime;
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
	private Set<OperationHours> operationHours;
	private Set<PropAvailabilityUnit> availabilityUnits;
	private LocationBased basedLocation;
	private Contract currentContract;
	private Set<PropFacilities> facilities;
	private Set<PropTags> tags;
	private ContactDetails contactDetails;
	private Set<PropertySpeciality> propertySpecialities;
	private Set<PaymentOptions> paymentOptions;
	private Set<Promotion> livePromotions;
	private Organization organizations;
	private List<LocalTime> timeSlots;
	private Set<Menu> menus;
	private Set<PropChoices> choices;
	private PropertyMedia propertyMedia;

	public void setPropertyMedia( Set<PropMedia> propMedia )
	{
		PropertyMedia propertyMedia = new PropertyMedia();
		propertyMedia.processPropertyMedia( propMedia );
		this.propertyMedia = propertyMedia;
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
			for( PropFacilities facility : this.getFacilities() )
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

		if( this.getTags() != null )
		{
			for( PropTags tags : this.getTags() )
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
			for( PropChoices choices : this.getChoices() )
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
