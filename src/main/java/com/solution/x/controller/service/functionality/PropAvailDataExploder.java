package com.solution.x.controller.service.functionality;

import com.solution.x.app.config.ApplicationPropertyConfig;
import com.solution.x.dao.Contract;
import com.solution.x.dao.ContractAvailability;
import com.solution.x.dao.PropAvailabilityUnit;
import com.solution.x.dao.Property;
import com.solution.x.dao.Seasons;
import com.solution.x.dao.WidenPropData;
import com.solution.x.dao.key.PropAvailabilityUnitKey;
import com.solution.x.dao.key.WidenDataGridKey;
import com.solution.x.repo.AvailDataRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/6/2020 12:04 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PropAvailDataExploder implements Callable<String>
{
	private Contract contract;

	@Autowired
	private ApplicationPropertyConfig config;

	@Autowired
	private AvailDataRepository availDataRepository;

	@Override
	public String call() throws Exception
	{

		short bookableHorizon = ( contract.getBookableHorizon() == null || config.BOOKABLE_HORIZON < contract.getBookableHorizon() ) ? config.BOOKABLE_HORIZON : contract.getBookableHorizon();
		log.info( "Sys BOOKABLE_HORIZON : " + config.BOOKABLE_HORIZON + " | Contract BOOKABLE_HORIZON : " + contract.getBookableHorizon() + " | Final : " + bookableHorizon );


		LocalDate now = LocalDate.now();
		LocalDate validFrom = contract.getValidFrom().toLocalDate();
		LocalDate validTo = contract.getValidTo().toLocalDate();

		LocalDate from = validFrom.isBefore( now ) ? now : validFrom;

		LocalDate to = from.plusDays( bookableHorizon ).isBefore( validTo ) ? from.plusDays( bookableHorizon ) : validTo;

		LocalDate currentDate = from;
		Property property = contract.getProperty();

		List<LocalTime> timeSlots = property.getTimeSlots();

		//List<OperationHours> sortedOperationHours = operationHours.stream().sorted( Comparator.comparing( OperationHours::getTimeStart ) ).collect( Collectors.toList() );
		List<Seasons> collect = contract.getSeasons().stream().sorted( Comparator.comparing( Seasons::getFrom ) ).collect( Collectors.toList() );

		for( Seasons season : contract.getSeasons() )
		{
			boolean seasonValid = false;
			boolean dateAtEdge = from.isEqual( season.getFrom() ) || from.isEqual( season.getTo() ) || to.isEqual( season.getFrom() ) || to.isEqual( season.getTo() );
			if( dateAtEdge )
			{
				seasonValid = true;
			}
			else
			{
				boolean fromInsideSeason = from.isAfter( season.getFrom() ) && ( from.isBefore( season.getTo() ) );
				if( fromInsideSeason )
				{
					seasonValid = true;
				}
				else
				{
					boolean toInsideSeason = to.isAfter( season.getFrom() ) && ( to.isBefore( season.getTo() ) );
					if( toInsideSeason )
					{
						seasonValid = true;
					}
				}
			}

			if( seasonValid )
			{
				Set<ContractAvailability> availabilities = season.getAvailabilities();
			}
		}

		List<WidenPropData> data = new ArrayList<>();
		while( currentDate.isBefore( to ) )
		{
			for( PropAvailabilityUnit availabilityUnit : property.getAvailabilityUnits() )
			{
				PropAvailabilityUnitKey availUnitKey = availabilityUnit.getPropAvailabilityUnitId();
				for( LocalTime timeSlot : timeSlots )
				{
					WidenPropData widenPropData = new WidenPropData();
					widenPropData.setWidenDataGridKey( new WidenDataGridKey( availUnitKey.getPropId(), availUnitKey.getUnit_id(), currentDate, timeSlot ) );

					data.add( widenPropData );

				}
			}

			availDataRepository.saveAll( data );

			currentDate = currentDate.plusDays( 1 );
		}

		return null;
	}
}
