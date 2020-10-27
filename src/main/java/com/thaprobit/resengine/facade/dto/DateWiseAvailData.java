package com.thaprobit.resengine.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 * @since 7/28/2020 1:01 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DateWiseAvailData
{
	private LocalDate date;
	private List<TimeWiseAvailData> timeWiseAvailData;
}
