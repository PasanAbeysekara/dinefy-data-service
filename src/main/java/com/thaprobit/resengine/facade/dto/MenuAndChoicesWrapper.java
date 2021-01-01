package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.Menu;
import com.thaprobit.resengine.dao.PropChoices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 12:50 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuAndChoicesWrapper
{
	private Set<Menu> menus;
	private Set<PropChoices> choices;
}
