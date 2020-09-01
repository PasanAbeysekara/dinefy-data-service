package com.solution.x.data.controller.service;

import com.solution.x.dao.projection.PropContractAndTimeSlots;
import com.solution.x.dao.WidenPropData;
import com.solution.x.dao.sys.AvailabilityUnit;
import com.solution.x.data.facade.dto.AvailDataWrapper;
import com.solution.x.data.facade.dto.DateWiseAvailData;
import com.solution.x.data.facade.dto.TimeWiseAvailData;
import com.solution.x.global.SystemOperation;
import com.solution.x.repo.AvailDataRepository;
import com.solution.x.repo.PropertyRepository;
import com.solution.x.service.AbstractService;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Tharindu Aththanayake
 * @since 7/28/2020 10:05 PM
 */
@Service
@Slf4j
public class AvailDataSearchService extends AbstractService<AvailDataWrapper> {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private AvailDataRepository availDataRepository;

    /**
     * Get all property availabilities for a given time period
     *
     *  @param propertyId               Property ID
     * 	@param dateFrom				    Search start date
     * 	@param dateTo				    Search end date
     * 	@param timeFrom				    Search start time
     * 	@param timeTo				    Search end time
     * 	@param availabilityUnitType 	Availability Unit Type
     * 	@param availabilityUnitName		Availability Unit Name
     *  @param pageable                 Pageable
     *
     * @return return all availabilities in requested time period
     */
    public ResponseEntity<ResponseWrapper<AvailDataWrapper>> getPropertyAvailabilities(Long propertyId, LocalDate dateFrom, LocalDate dateTo, LocalTime timeFrom, LocalTime timeTo, String availabilityUnitType, String availabilityUnitName, Pageable pageable)
    {

        ResponseEntity<ResponseWrapper<AvailDataWrapper>> response = null;

        Optional<PropContractAndTimeSlots> optionalPropContractAndTimeSlot = propertyRepository.findByPropId( propertyId );

        if( optionalPropContractAndTimeSlot.isPresent() )
        {
            PropContractAndTimeSlots propContractAndTimeSlots = optionalPropContractAndTimeSlot.get();

            // Long currentContractId = (long)propContractAndTimeSlots.getCurrentContId();
            Long currentContractId = 170L;

            List<AvailabilityUnit> availabilityUnits = propertyRepository.findPropertyAvailableUnits( propertyId, availabilityUnitName, availabilityUnitType );

            List<Integer> availabilityUnitIds = availabilityUnits.stream().map( i -> i.getUnitId() ).collect( Collectors.toList() );

            List<WidenPropData> widenPropDataList = availDataRepository.getPropertyAvailabilitiesPaged( propertyId, currentContractId, dateFrom, dateTo, timeFrom, timeTo , availabilityUnitIds, pageable ).getContent();

            List<LocalTime> timesSlots = propContractAndTimeSlots.getTimeSlots().stream()
                    .filter( i -> ( i.isAfter(timeFrom) || i.equals(timeFrom) ) && ( i.isBefore(timeTo) || i.equals(timeTo) ) )
                    .collect(Collectors.toList());

            List<DateWiseAvailData> dateWiseAvailDataList = new ArrayList<>();

            List<LocalDate> dateList = new ArrayList<>();

            while ( dateFrom.isBefore( dateTo ) || dateFrom.equals( dateTo ) )
            {
                dateList.add( dateFrom );
                dateFrom = dateFrom.plusDays( 1L );
            }

            for (LocalDate date : dateList )
            {
                List<TimeWiseAvailData> timeWiseAvailDataList = widenPropDataList.stream()
                        .filter( i -> i.getWidenDataGridKey().getDate().equals(date) )
                        .map( i -> new TimeWiseAvailData(i.getWidenDataGridKey().getTimeSlot(),
                                i.getWidenDataGridKey().getAvailUnitId(),
                                i.getContractAvailCount(),
                                i.getOpen(),
                                i.getClose(),
                                i.getBookable(),
                                i.getHold(),
                                i.getBooked()))
                        .collect(Collectors.toList() );

                if (timeWiseAvailDataList.size() > 0) {

                    DateWiseAvailData dateWiseAvailData = new DateWiseAvailData();

                    dateWiseAvailData.setDate( date );
                    dateWiseAvailData.setTimeWiseAvailData( timeWiseAvailDataList );

                    dateWiseAvailDataList.add( dateWiseAvailData );

                }

            }

            AvailDataWrapper availDataWrapper = new AvailDataWrapper();
            availDataWrapper.setPropId( propertyId );
            availDataWrapper.setContractId( (long) propContractAndTimeSlots.getCurrentContId() );
            availDataWrapper.setFromDate( dateList.get( 0 )  );
            availDataWrapper.setToDate( dateTo );
            availDataWrapper.setTimeSlots( timesSlots );
            availDataWrapper.setAvailabilityUnits( availabilityUnits );
            availDataWrapper.setDateWiseAvailData( dateWiseAvailDataList );

            response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) )
                    .body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, availDataWrapper ) );

        }
        else
        {
            response = buildNotFoundResponseWrapped();
        }
        return response;
    }

}
