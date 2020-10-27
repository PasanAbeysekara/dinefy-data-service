package com.solution.x.data.facade.dto;

import com.solution.x.dao.sys.Choices;
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
public class PropChoicesModel
{
	private Integer propChoiceId;
	private Long propId;
	private Long choiceId;
	private String name;
	private String description;
	private Choices sysChoice;
}
