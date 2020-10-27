package com.thaprobit.resengine.controller.service;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.assembler.MenuModelAssembler;
import com.thaprobit.resengine.dao.Menu;
import com.thaprobit.resengine.dao.MenuCategory;
import com.thaprobit.resengine.dao.MenuChoices;
import com.thaprobit.resengine.facade.dto.MenuModel;
import com.thaprobit.resengine.repo.MenuRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tharindu Aththanayake
 */
@Service
@Slf4j
public class MenuService extends AbstractService<Menu>
{
	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private PagedResourcesAssembler<Menu> pagedResourcesAssembler;

	@Autowired
	private MenuModelAssembler menuAssembler;


	/**
	 * Get all menus
	 *
	 * @param pageable Pageable
	 * @return return All menus
	 */
	public ResponseEntity<ResponseWrapper<PagedModel<MenuModel>>> getMenus( Pageable pageable )
	{
		Page<Menu> menuPage = menuRepository.findAll( pageable );

		PagedModel<MenuModel> menuPagedModel = pagedResourcesAssembler.toModel( menuPage, menuAssembler );

		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, menuPagedModel ) );
	}

	/**
	 * Get single menu
	 *
	 * @param id Menu ID
	 * @return return the menu
	 */
	public ResponseEntity<ResponseWrapper<Menu>> getMenu( long id )
	{
		Optional<Menu> optionalMenu = menuRepository.findById( id );

		ResponseEntity<ResponseWrapper<Menu>> response;

		if( optionalMenu.isPresent() )
		{
			Menu menu = optionalMenu.get();
			Link selfRel = HATEOASProvider.menuSelfLinkProvider( menu.getMenuId() );
			menu.add( selfRel );

			response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, menu ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
		}

		return response;
	}

	/**
	 * Create a menu
	 *
	 * @param menu Menu
	 * @return Saved menu response
	 */
	public ResponseEntity<ResponseWrapper<Menu>> createMenu( Menu menu )
	{
		ResponseEntity<ResponseWrapper<Menu>> response;

		try
		{
			Long menuId = menuRepository.getNextVal();
			menu.setMenuId( menuId );

			preProcess( menu );

			Menu savedMenu = menuRepository.saveAndFlush( menu );

			Link selfRel = HATEOASProvider.menuSelfLinkProvider( savedMenu.getMenuId() );
			savedMenu.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.MENU_CREATE_SUCCESS, savedMenu ) );
		}
		catch( Exception e )
		{
			log.error( "Error occurred during menu creating : " + e );
			response = buildExceptionErrorResponse( SystemOperation.CREATE, SystemMessages.MENU_CREATE_FAILED, e );
		}

		return response;
	}

	private void preProcess( Menu menu )
	{
		if( menu.getMenuCategories() != null )
		{
			long menuId = menu.getMenuId();

			for( MenuCategory menuCategory : menu.getMenuCategories() )
			{
				menuCategory.getMenuCategoryId().setMenuId( menuId );

				if( menuCategory.getCategoryChoices() != null )
				{

					short categoryId = menuCategory.getMenuCategoryId().getCategoryId();

					for( MenuChoices menuChoice : menuCategory.getCategoryChoices() )
					{
						menuChoice.getMenuChoiceID().setMenuId( menuId );
						menuChoice.getMenuChoiceID().setCategoryId( categoryId );
					}
				}
			}
		}
	}

	/**
	 * Update a menu
	 *
	 * @param id   The menu id
	 * @param menu Menu
	 * @return Updated menu response
	 */
	public ResponseEntity<ResponseWrapper<Menu>> updateMenu( long id, Menu menu )
	{
		ResponseEntity<ResponseWrapper<Menu>> response;

		try
		{
			menu.setMenuId( id );

			preProcess( menu );

			Menu savedMenu = menuRepository.saveAndFlush( menu );

			Link selfRel = HATEOASProvider.menuSelfLinkProvider( savedMenu.getMenuId() );
			savedMenu.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.MENU_UPDATE_SUCCESS, savedMenu ) );
		}
		catch( Exception e )
		{
			log.error( "Error occurred during menu updating : " + e );
			response = buildExceptionErrorResponse( SystemOperation.MODIFY, SystemMessages.MENU_UPDATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Delete a menu
	 *
	 * @param id Menu ID
	 * @return Delete response
	 */
	public ResponseEntity<ResponseWrapper<Menu>> deleteMenu( long id )
	{
		ResponseEntity<ResponseWrapper<Menu>> response;

		try
		{
			menuRepository.deleteById( id );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.MENU_DELETE_SUCCESS, "" ) );
		}
		catch( Exception e )
		{
			log.error( "Error occurred during menu deleting : " + e );
			response = buildExceptionErrorResponse( SystemOperation.DELETE, SystemMessages.MENU_DELETE_FAILED, e );
		}

		return response;
	}
}
