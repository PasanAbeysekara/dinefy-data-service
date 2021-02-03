package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.controller.assembler.FacilitiesModelAssembler;
import com.thaprobit.resengine.dao.PropFacilities;
import com.thaprobit.resengine.facade.dto.PropFacilityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 11:39 AM
 */
@Component
public class PropFacilityModelConverter implements Converter<PropFacilities, PropFacilityModel>
{
	@Autowired
	private FacilitiesModelAssembler facilitiesModelAssembler;

	@Override
	public PropFacilityModel convert( PropFacilities propFacilities )
	{
		PropFacilityModel propFacilitiesModel = new PropFacilityModel();
		propFacilitiesModel.setPropFacilityId( propFacilities.getPropFacilityId() );
		propFacilitiesModel.setName( propFacilities.getName() );
		propFacilitiesModel.setDescription( propFacilities.getDescription() );
		propFacilitiesModel.setOrder( propFacilities.getOrder() );

		if( propFacilities.getSysFacility() != null)
		{
			propFacilitiesModel.setSysFacility( facilitiesModelAssembler.toModel( propFacilities.getSysFacility() ) );
		}

		return propFacilitiesModel;
	}
}
