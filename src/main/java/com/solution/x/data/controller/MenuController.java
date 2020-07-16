package com.solution.x.data.controller;

import com.solution.x.dao.Menu;
import com.solution.x.data.controller.service.MenuService;
import com.solution.x.data.facade.dto.MenuModel;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tharindu Aththanayake
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class MenuController
{
    @Autowired
    private MenuService menuService;

    /**
     * Get All Menus
     *
     * @return all menus
     */
    @GetMapping("/menus")
    public ResponseEntity<ResponseWrapper<PagedModel<MenuModel>>> getMenus( Pageable pageable )
    {
        return menuService.getMenus(pageable);
    }

    /**
     * Get single menu
     *
     * @param id Menu ID
     * @return The menu
     */
    @GetMapping("/menus/{id}")
    public ResponseEntity<ResponseWrapper<Menu>> getMenu( @PathVariable("id") long id )
    {
        return menuService.getMenu( id );
    }

    /**
     * Create a menu
     *
     * @param menu The menu
     * @return saved menu response
     */
    @PostMapping("/menus")
    public ResponseEntity<ResponseWrapper<Menu>> createMenu( @RequestBody Menu menu )
    {
        return menuService.createMenu( menu );
    }

    /**
     * Update a menu
     *
     * @param id Menu ID
     * @param menu The menu
     * @return Updated menu response
     */
    @PutMapping("/menus/{id}")
    public ResponseEntity<ResponseWrapper<Menu>> updateMenu( @PathVariable("id") long id, @RequestBody Menu menu )
    {
        return menuService.updateMenu( id, menu);
    }

    /**
     * Delete a menu
     *
     * @param id Menu ID
     * @return Delete response
     */
    @DeleteMapping("/menus/{id}")
    public ResponseEntity<ResponseWrapper<Menu>> deleteMenu( @PathVariable("id") long id )
    {
        return menuService.deleteMenu( id );
    }
}
