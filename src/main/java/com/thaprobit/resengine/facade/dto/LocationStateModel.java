package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.key.StateID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Tharindu Aththanayake
 * @since 12/31/2020 05:07 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LocationStateModel
{
	private StateID stateID;
	private String name;
}
