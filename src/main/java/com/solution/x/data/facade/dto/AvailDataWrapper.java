package com.solution.x.data.facade.dto;

import com.solution.x.dao.sys.AvailabilityUnit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 * @since 7/28/2020 12:15 AM
 */
public class AvailDataWrapper
{
	private Long propId;
	private Long contractId;

	private LocalDate fromDate;
	private LocalDate toDate;

	private List<LocalTime> timeSlots;

	private List<AvailabilityUnit> availabilityUnits;

	private List<DateWiseAvailData> dateWiseAvailData;
}
