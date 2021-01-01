package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.key.SeasonID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 10:39 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SeasonModel
{
	private SeasonID seasonId;
	private String name;
	private LocalDate from;
	private LocalDate to;
	private Set<ContractAvailabilityModel> availabilities;
}
