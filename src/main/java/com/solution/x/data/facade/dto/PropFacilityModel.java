package com.solution.x.data.facade.dto;

import com.solution.x.dao.key.PropFacilityID;
import com.solution.x.dao.sys.Facilities;
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
public class PropFacilityModel
{
	private PropFacilityID propFacilityId;
	private String name;
	private String description;
	private Facilities sysFacility;
}
