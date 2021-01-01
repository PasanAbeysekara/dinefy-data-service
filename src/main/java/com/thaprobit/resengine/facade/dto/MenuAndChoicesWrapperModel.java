package com.thaprobit.resengine.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 12:52 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuAndChoicesWrapperModel
{
	private Set<MenuModel> menus;
	private Set<PropChoicesModel> choices;
}
