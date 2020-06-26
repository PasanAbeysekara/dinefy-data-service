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
import com.solution.x.dao.sys.WeekDefinition;
import com.solution.x.global.DataCarrier;
import com.solution.x.repo.AvailDataRepository;
import com.solution.x.repo.ContractsRepository;
import com.solution.x.repo.PropertyRepository;
import com.solution.x.repo.sys.WeekDefinitionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 *
 * @author Tharinda Wickramaarachchi
 * @since 6/6/2020 12:04 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PropAvailDataExploder implements Callable<DataCarrier<String>>
{
	private Contract contract;

	@Autowired
	private ApplicationPropertyConfig config;

	@Autowired
	private AvailDataRepository availDataRepository;

	@Autowired
	private WeekDefinitionRepository weekDefinitionRepository;

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private ContractsRepository contractsRepository;

	@Override
	public DataCarrier<String> call() throws Exception
	{
		DataCarrier<String> dataCarrier;

		try
		{
			Optional<Property> optionalProperty = propertyRepository.findById( this.contract.getPropId() );
			Property property = optionalProperty.get();

			short bookableHorizon = ( this.contract.getBookableHorizon() == null || config.BOOKABLE_HORIZON < this.contract.getBookableHorizon() ) ? config.BOOKABLE_HORIZON : this.contract.getBookableHorizon();
			log.info( "Sys BOOKABLE_HORIZON : " + config.BOOKABLE_HORIZON + " | Contract BOOKABLE_HORIZON : " + this.contract.getBookableHorizon() + " | Final : " + bookableHorizon );


			LocalDate now = LocalDate.now();// TODO this is based on server time , Better to USE some kind of API
			LocalDate validFrom = this.contract.getValidFrom().toLocalDate();
			LocalDate validTo = this.contract.getValidTo().toLocalDate();

			LocalDate expandFrom = validFrom.isBefore( now ) ? now : validFrom;
			LocalDate expandTo = expandFrom.plusDays( bookableHorizon ).isBefore( validTo ) ? expandFrom.plusDays( bookableHorizon ) : validTo;

			log.info( "Data exploded horizon - Expand From : " + expandFrom.toString() + " Expand To : " + expandTo.toString() );
			LocalDate currentDate = expandFrom;

			List<LocalTime> timeSlots = property.getTimeSlots();

			List<Seasons> sortedSeasons = this.contract.getSeasons().stream().sorted( Comparator.comparing( Seasons::getFrom ) ).collect( Collectors.toList() );

			Map<Short, WeekDefinition> weekDefinitionMap = weekDefinitionRepository.findAll().stream().collect( Collectors.toMap( WeekDefinition::getWeekDefId, o -> o ) );// TODO cache this

			List<WidenPropData> data = new ArrayList<>();

			while( currentDate.isBefore( expandTo ) )
			{
				LocalDate date = currentDate;

				Optional<Seasons> optionalSeason = sortedSeasons.stream()
						.filter( o -> date.isEqual( o.getFrom() ) || date.isEqual( o.getTo() ) || ( date.isBefore( o.getTo() ) && date.isAfter( o.getFrom() ) ) )
						.findFirst();

				LocalDate maxDateWithingTheSeason;
				if( optionalSeason.isPresent() )
				{
					Seasons matchingSeason = optionalSeason.get();

					if( matchingSeason.getTo().isBefore( expandTo ) )
					{
						maxDateWithingTheSeason = matchingSeason.getTo().plusDays( 1L ); // +1 to match edge case while checking isBefore : date.isBefore(date) == false
					}
					else
					{
						maxDateWithingTheSeason = expandTo.plusDays( 1L ); // +1 to match edge case while checking isBefore : date.isBefore(date) == false
					}

					log.info( "Data exploding... Season : " + matchingSeason.getName() );

					while( currentDate.isBefore( maxDateWithingTheSeason ) )
					{
						log.debug( "Data exploding... Date : " + currentDate.toString() );

						for( PropAvailabilityUnit availabilityUnit : property.getAvailabilityUnits() )
						{
							log.debug( "Data exploding... Avail Unit : " + availabilityUnit.getName() );
							PropAvailabilityUnitKey availUnitKey = availabilityUnit.getPropAvailabilityUnitId();

							DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

							Optional<ContractAvailability> availabilityOpt = matchingSeason.getAvailabilities().stream()
									.filter( a -> ( availUnitKey.getUnit_id().equals( a.getAvailabilityID().getAvailUnitId() ) )
											&& ( weekDefinitionMap.get( a.getAvailabilityID().getWeekDefId() ).isValidDay( dayOfWeek ) ) ).findFirst();

							if( availabilityOpt.isPresent() )
							{
								ContractAvailability availability = availabilityOpt.get();

								for( LocalTime timeSlot : timeSlots )
								{
									WidenPropData widenPropData = new WidenPropData();
									widenPropData.setWidenDataGridKey( new WidenDataGridKey( availUnitKey.getPropId(), availUnitKey.getUnit_id(), currentDate, timeSlot ) );
									widenPropData.setContractId( contract.getContractId() );
									widenPropData.setContractAvailCount( availability.getCount() );
									widenPropData.calculateBookable();

									data.add( widenPropData );

								}

							}
							else
							{
								log.warn( "Data exploding... Cannot find matching availability for Avail Unit : " + availabilityUnit.getName() );
								//return DataCarrier.<String>init().withError().setMessage( "Cannot find matching availability for " + dayOfWeek + " & Avail Unit : " + availUnitKey );
							}
						}

						currentDate = currentDate.plusDays( 1L );
					}
				}
				else
				{
					log.error( "Cannot find matching season for the day " + currentDate );
					return DataCarrier.<String>init().withError().setMessage( "Cannot find matching season for the day " + currentDate );
				}
			}

			availDataRepository.saveAll( data );

			String msg = "Successfully Exploded into  " + data.size() + " availability points";
			log.info( msg );
			dataCarrier = DataCarrier.<String>init().withSuccess().setMessage( msg );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			log.error( "Availability Explosion Failed : ", e );

			dataCarrier = DataCarrier.<String>init()
					.withError()
					.setMessage( "Availability Explosion Failed : " + e.getMessage() )
					.setException( e );
		}

		return dataCarrier;
	}
}
