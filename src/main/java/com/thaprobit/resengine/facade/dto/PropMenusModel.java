package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.Menu;
import com.thaprobit.resengine.dao.PropChoices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Tharindu Aththanayake
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PropMenusModel
{
	private Set<MenuModel> menus;
	private Set<PropChoicesModel> choices;

	public void setMenus( Set<Menu> menus ){
		Set<MenuModel> menuModels = new HashSet<>();

		for(Menu menu: menus)
		{
			MenuModel menuModel = new MenuModel();
			menuModel.setMenuId( menu.getMenuId() );
			menuModel.setName( menu.getName() );
			menuModel.setDescription( menu.getDescription() );
			menuModel.setMenuCategories( menu.getMenuCategories() );
			menuModels.add( menuModel );
		}

		this.menus = menuModels;
	}

	public void setChoices( Set<PropChoices> propChoices)
	{
		Set<PropChoicesModel> propChoicesModels = new HashSet<>();

		for(PropChoices propChoice : propChoices)
		{
			PropChoicesModel propChoicesModel = new PropChoicesModel();
			propChoicesModel.setPropChoiceId( propChoice.getPropChoiceId() );
			propChoicesModel.setPropId( propChoice.getPropId() );
			propChoicesModel.setChoiceId( propChoice.getChoiceId() );
			propChoicesModel.setName( propChoice.getName() );
			propChoicesModel.setDescription( propChoice.getDescription() );
			propChoicesModel.setSysChoice( propChoice.getSysChoice() );

			propChoicesModels.add( propChoicesModel );
		}

		this.choices = propChoicesModels;
	}
}
