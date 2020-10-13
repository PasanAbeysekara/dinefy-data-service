package com.solution.x.data.facade.dto;

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
@EqualsAndHashCode(callSuper = true)
public class ChoiceModel extends RepresentationModel<ChoiceModel>
{
	private Long choiceId;
	private String name;
	private String description;
}
