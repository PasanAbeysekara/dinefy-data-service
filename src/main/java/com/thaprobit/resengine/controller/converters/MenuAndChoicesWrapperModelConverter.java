package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.controller.assembler.MenuModelAssembler;
import com.thaprobit.resengine.facade.dto.MenuAndChoicesWrapper;
import com.thaprobit.resengine.facade.dto.MenuAndChoicesWrapperModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 12:54 PM
 */
@Component
public class MenuAndChoicesWrapperModelConverter implements Converter<MenuAndChoicesWrapper, MenuAndChoicesWrapperModel> {
    @Autowired
    private MenuModelAssembler menuModelAssembler;

    @Autowired
    private PropChoicesModelConverter propChoicesModelConverter;

    @Override
    public MenuAndChoicesWrapperModel convert(MenuAndChoicesWrapper menuAndChoicesWrapper) {
        MenuAndChoicesWrapperModel menuAndChoicesWrapperModel = new MenuAndChoicesWrapperModel();
        menuAndChoicesWrapperModel.setMenus(menuAndChoicesWrapper.getMenus().stream().map(menuModelAssembler::toModel).collect(Collectors.toSet()));
        menuAndChoicesWrapperModel.setChoices(menuAndChoicesWrapper.getChoices().stream().map(propChoicesModelConverter::convert).collect(Collectors.toSet()));

        return menuAndChoicesWrapperModel;
    }
}
