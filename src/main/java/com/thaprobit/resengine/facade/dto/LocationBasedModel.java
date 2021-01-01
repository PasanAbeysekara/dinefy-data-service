package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.LocationState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Tharindu Aththanayake
 * @since 12/31/2020 05:05 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationBasedModel
{
	private Long locationId;
	private String name;
	private LocationStateModel state;
}
