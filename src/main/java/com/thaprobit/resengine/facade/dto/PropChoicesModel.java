package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.sys.Choices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

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
	private BigDecimal amount;
	private String amountCurrency;
	private ChoiceModel sysChoice;
}
