package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.controller.service.MenuService;
import com.thaprobit.resengine.dao.Menu;
import com.thaprobit.resengine.facade.dto.MenuModel;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return menuService.getMenus( pageable );
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
	 * @param id   Menu ID
	 * @param menu The menu
	 * @return Updated menu response
	 */
	@PutMapping("/menus/{id}")
	public ResponseEntity<ResponseWrapper<Menu>> updateMenu( @PathVariable("id") long id, @RequestBody Menu menu )
	{
		return menuService.updateMenu( id, menu );
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
