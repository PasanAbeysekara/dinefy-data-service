package com.solution.x.data.facade.dto;

import java.time.LocalTime;

/**
 * @author Tharinda Wickramaarachchi
 * @since 7/28/2020 1:05 AM
 */
public class TimeWiseAvailData
{
	private LocalTime timeSlot;

	private Short contractAvailCount;
	private Short open;
	private Short close;
	private Short bookable;
	private Short hold;
	private Short booked;
}
