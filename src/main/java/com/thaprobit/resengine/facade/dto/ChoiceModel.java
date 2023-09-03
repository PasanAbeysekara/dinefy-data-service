package com.thaprobit.resengine.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Tharindu Aththanayake
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ChoiceModel extends RepresentationModel<ChoiceModel> {
    private int choiceId;
    private String name;
    private String description;
}
