package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.key.PropEventID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 03:39 PM
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PropEventModel
{
	private PropEventID propEventID;
	private EventModel sysEvent;
}
