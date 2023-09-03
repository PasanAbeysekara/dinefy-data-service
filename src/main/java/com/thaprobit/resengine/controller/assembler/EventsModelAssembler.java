package com.thaprobit.resengine.controller.assembler;

import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.controller.sys.SysEventController;
import com.thaprobit.resengine.dao.sys.Event;
import com.thaprobit.resengine.facade.dto.EventModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 12/29/2020 08:57 PM
 */
@Component
public class EventsModelAssembler extends RepresentationModelAssemblerSupport<Event, EventModel> {

    public EventsModelAssembler() {
        super(SysEventController.class, EventModel.class);
    }

    @Override
    public EventModel toModel(Event entity) {
        EventModel eventModel = new EventModel();
        eventModel.setEventId(entity.getEventId());
        eventModel.setName(entity.getName());
        eventModel.setDescription(entity.getDescription());
        eventModel.setOrder(entity.getOrder());

        eventModel.add(HATEOASProvider.sysEventSelfLinkProvider(entity.getEventId()));

        return eventModel;
    }
}
