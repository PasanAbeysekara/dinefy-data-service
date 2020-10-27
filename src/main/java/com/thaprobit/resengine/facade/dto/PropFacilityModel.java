package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.key.PropFacilityID;
import com.thaprobit.resengine.dao.sys.Facilities;
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
