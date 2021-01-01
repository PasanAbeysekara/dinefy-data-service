package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.controller.assembler.EventsModelAssembler;
import com.thaprobit.resengine.dao.PropEvent;
import com.thaprobit.resengine.facade.dto.PropEventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 03:40 PM
 */
@Component
public class PropEventModelConverter implements Converter<PropEvent, PropEventModel>
{
	@Autowired
	private EventsModelAssembler eventsModelAssembler;

	@Override
	public PropEventModel convert( PropEvent propEvent )
	{
		PropEventModel propEventModel = new PropEventModel();
		propEventModel.setPropEventID( propEvent.getPropEventID() );
		propEventModel.setSysEvent( eventsModelAssembler.toModel( propEvent.getSysEvent() ) );

		return propEventModel;
	}
}
