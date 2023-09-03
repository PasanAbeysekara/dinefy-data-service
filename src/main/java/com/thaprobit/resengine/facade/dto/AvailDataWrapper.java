package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.sys.AvailabilityUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 * @since 7/28/2020 12:15 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AvailDataWrapper {
    private Long propId;
    private Long contractId;

    private LocalDate fromDate;
    private LocalDate toDate;

    private List<LocalTime> timeSlots;

    private List<AvailabilityUnit> availabilityUnits;

    private List<DateWiseAvailData> dateWiseAvailData;
}
