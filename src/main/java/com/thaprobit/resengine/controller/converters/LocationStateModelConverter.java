package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.dao.LocationState;
import com.thaprobit.resengine.facade.dto.LocationStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 10:08 AM
 */
@Component
public class LocationStateModelConverter implements Converter<LocationState, LocationStateModel>
{

	@Override
	public LocationStateModel convert( LocationState locationState )
	{
		LocationStateModel locationStateModel = new LocationStateModel();
		locationStateModel.setStateID( locationState.getStateId() );
		locationStateModel.setName( locationState.getName() );

		return locationStateModel;
	}

}
