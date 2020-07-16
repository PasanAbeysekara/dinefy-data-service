package com.solution.x.data.controller.assembler;

import com.solution.x.dao.Menu;
import com.solution.x.data.controller.MenuController;
import com.solution.x.data.controller.service.HATEOASProvider;
import com.solution.x.data.facade.dto.MenuModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayaka
 */
@Component
public class MenuModelAssembler extends RepresentationModelAssemblerSupport<Menu, MenuModel>
{

    public MenuModelAssembler(){ super( MenuController.class, MenuModel.class ); }

    @Override
    public MenuModel toModel(Menu entity)
    {
        MenuModel menuModel = new MenuModel();
        menuModel.setMenuId( entity.getMenuId() );
        menuModel.setName( entity.getName() );
        menuModel.setDescription( entity.getDescription() );

        menuModel.add( HATEOASProvider.menuSelfLinkProvider( entity.getMenuId() ));

        return menuModel;
    }

    @Override
    public CollectionModel<MenuModel> toCollectionModel(Iterable<? extends Menu> entities)
    {
        CollectionModel<MenuModel> menuModels = super.toCollectionModel( entities );

        menuModels.add( HATEOASProvider.menuSelfLinkProvider( 1 ));

        return menuModels;
    }
}
