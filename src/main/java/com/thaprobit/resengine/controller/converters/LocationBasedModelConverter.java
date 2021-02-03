package com.thaprobit.resengine.controller.converters;


import com.thaprobit.resengine.dao.LocationBased;
import com.thaprobit.resengine.facade.dto.LocationBasedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 10:08 AM
 */
@Component
public class LocationBasedModelConverter implements Converter<LocationBased , LocationBasedModel>
{
	@Autowired
	private LocationStateModelConverter locationStateModelConverter;

	@Override
	public LocationBasedModel convert( LocationBased locationBased )
	{
		LocationBasedModel locationBasedModel = new LocationBasedModel();
		locationBasedModel.setLocationId( locationBased.getLocationId() );
		locationBasedModel.setName( locationBased.getName() );

		if( locationBased.getState() != null )
		{
			locationBasedModel.setState( locationStateModelConverter.convert( locationBased.getState() ) );
		}

		return locationBasedModel;
	}
}
