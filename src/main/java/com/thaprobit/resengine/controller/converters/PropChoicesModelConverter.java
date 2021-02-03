package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.controller.assembler.ChoiceModelAssembler;
import com.thaprobit.resengine.dao.PropChoices;
import com.thaprobit.resengine.facade.dto.PropChoicesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 12:32 PM
 */
@Component
public class PropChoicesModelConverter implements Converter<PropChoices, PropChoicesModel>
{
	@Autowired
	private ChoiceModelAssembler choiceModelAssembler;

	@Override public PropChoicesModel convert( PropChoices propChoices )
	{
		PropChoicesModel propChoicesModel = new PropChoicesModel();
		propChoicesModel.setPropChoiceId( propChoices.getPropChoiceId() );
		propChoicesModel.setChoiceId( propChoices.getChoiceId() );
		propChoicesModel.setName( propChoices.getName() );
		propChoicesModel.setDescription( propChoices.getDescription() );
		propChoicesModel.setAmount( propChoices.getAmount() );
		propChoicesModel.setAmountCurrency( propChoices.getAmountCurrency() );

		if( propChoices.getSysChoice() != null )
		{
			propChoicesModel.setSysChoice( choiceModelAssembler.toModel( propChoices.getSysChoice() ) );
		}

		return propChoicesModel;
	}
}
