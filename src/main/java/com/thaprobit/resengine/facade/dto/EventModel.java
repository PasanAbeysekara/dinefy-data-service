package com.thaprobit.resengine.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Tharindu Aththanayake
 * @since 12/29/2020 08:51 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class EventModel extends RepresentationModel<EventModel> {
    private Integer eventId;
    private String name;
    private String description;
    private int order;
}
