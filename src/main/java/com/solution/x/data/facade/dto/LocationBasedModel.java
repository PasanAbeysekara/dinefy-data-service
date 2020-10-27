package com.solution.x.data.facade.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solution.x.dao.LocationState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Tharindu Aththanayake
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationBasedModel
{
	private Long locationId;
	private String name;
	private LocationState state;
}
