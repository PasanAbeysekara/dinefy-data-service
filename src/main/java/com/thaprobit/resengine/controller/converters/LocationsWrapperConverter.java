package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.dao.LocationBased;
import com.thaprobit.resengine.facade.dto.LocationBasedWrapper;
import com.thaprobit.resengine.facade.dto.LocationStateWrapper;
import com.thaprobit.resengine.facade.dto.LocationsWrapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tharindu Aththanayake
 * @since 01/02/2021 12:19 AM
 */
@Component
public class LocationsWrapperConverter implements Converter< List<LocationBased> , LocationsWrapper >
{
	@Override
	public LocationsWrapper convert( List<LocationBased> locationBasedList )
	{
		Set<LocationStateWrapper> locationStateWrappersSet = locationBasedList
				.stream()
				.map( i -> new LocationStateWrapper( i.getState().getStateId().getStateId(), i.getState().getName() ) )
				.collect( Collectors.toSet() );

		List<LocationBasedWrapper> locationBasedWrappers = locationBasedList
				.stream()
				.map( i -> new LocationBasedWrapper( i.getLocationId(), i.getName(), i.getState().getStateId().getStateId() ) )
				.collect( Collectors.toList() );

		Page<LocationBasedWrapper> locationBasedWrapperPage = new PageImpl<>( locationBasedWrappers );

		LocationsWrapper locationsWrapper = new LocationsWrapper();
		locationsWrapper.setCountryId( (long) locationBasedList.get( 0 ).getState().getStateId().getCountryId() );
		locationsWrapper.setStates( locationStateWrappersSet );
		locationsWrapper.setCities( locationBasedWrapperPage );

		return locationsWrapper;
	}

}
