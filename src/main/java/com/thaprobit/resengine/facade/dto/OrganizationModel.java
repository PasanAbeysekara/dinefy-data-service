package com.thaprobit.resengine.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Tharindu Aththanayake
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationModel
{
	private long orgId;
	private String code;
	private String name;
}
