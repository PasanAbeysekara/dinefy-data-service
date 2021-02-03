package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.dao.Seasons;
import com.thaprobit.resengine.facade.dto.SeasonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 11:12 AM
 */
@Component
public class SeasonModelConverter implements Converter< Seasons, SeasonModel >
{
	@Autowired
	private ContractAvailabilitiesConverter contractAvailabilitiesConverter;

	@Override
	public SeasonModel convert( Seasons seasons )
	{
		SeasonModel seasonModel = new SeasonModel();
		seasonModel.setSeasonId( seasons.getSeasonId() );
		seasonModel.setFrom( seasons.getFrom() );
		seasonModel.setTo( seasons.getTo() );

		if ( seasons.getAvailabilities() != null )
		{
			seasonModel.setAvailabilities( seasons.getAvailabilities().stream().map( contractAvailabilitiesConverter :: convert ).collect( Collectors.toSet()) );
		}

		return seasonModel;
	}
}
