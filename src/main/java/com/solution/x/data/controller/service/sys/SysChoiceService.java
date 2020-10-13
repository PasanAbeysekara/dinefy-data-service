package com.solution.x.data.controller.service.sys;

import com.solution.x.dao.sys.Choices;
import com.solution.x.data.controller.assembler.ChoiceModelAssembler;
import com.solution.x.data.controller.service.HATEOASProvider;
import com.solution.x.data.facade.dto.ChoiceModel;
import com.solution.x.global.SystemOperation;
import com.solution.x.repo.sys.ChoicesRepository;
import com.solution.x.service.AbstractService;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.SystemMessages;
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
public class SysChoiceService extends AbstractService<Choices>
{
	@Autowired
	private ChoicesRepository choicesRepository;

	@Autowired
	private PagedResourcesAssembler<Choices> pagedResourcesAssembler;

	@Autowired
	private ChoiceModelAssembler choiceModelAssembler;

	/**
	 * Get All Choices
	 *
	 * @return all sys choices
	 */
	public ResponseEntity<ResponseWrapper<PagedModel<ChoiceModel>>> getChoices( Pageable pageable )
	{
		Page<Choices> choicesPaged = choicesRepository.findAll( pageable );
		PagedModel<ChoiceModel> choiceModels = pagedResourcesAssembler.toModel( choicesPaged, choiceModelAssembler );

		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, choiceModels ) );
	}

	/**
	 * Get Single Choices
	 *
	 * @param id Choice ID
	 * @return The Choice
	 */
	public ResponseEntity<ResponseWrapper<Choices>> getChoice( long id )
	{
		Optional<Choices> optionalChoice = choicesRepository.findById( id );

		ResponseEntity<ResponseWrapper<Choices>> response;

		if( optionalChoice.isPresent() )
		{
			Choices choice = optionalChoice.get();
			Link selfRel = HATEOASProvider.sysChoicesSelfLinkProvider( choice.getChoiceId() );
			choice.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, choice ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
		}

		return response;

	}

	/**
	 * Create a Choice
	 *
	 * @param choice The Choice
	 * @return Saved Choice response
	 */
	public ResponseEntity<ResponseWrapper<Choices>> createChoice( Choices choice )
	{
		ResponseEntity<ResponseWrapper<Choices>> response;

		try
		{
			Choices savedChoice = choicesRepository.save( choice );

			Link selfRel = HATEOASProvider.sysChoicesSelfLinkProvider( savedChoice.getChoiceId() );
			savedChoice.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.CHOICE_CREATE_SUCCESS, savedChoice ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.CREATE, SystemMessages.CHOICE_CREATE_FAILED, e );
		}

		return response;
	}


	/**
	 * Update a Choice
	 *
	 * @param id     The Choice ID
	 * @param choice The Choice
	 * @return Updated Choice response
	 */
	public ResponseEntity<ResponseWrapper<Choices>> updateChoice( long id, Choices choice )
	{
		ResponseEntity<ResponseWrapper<Choices>> response;

		try
		{
			choice.setChoiceId( id );
			Choices savedChoice = choicesRepository.save( choice );

			Link selfRel = HATEOASProvider.sysChoicesSelfLinkProvider( savedChoice.getChoiceId() );
			savedChoice.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.CHOICE_UPDATE_SUCCESS, savedChoice ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.MODIFY, SystemMessages.CHOICE_UPDATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Delete a Choice
	 *
	 * @param id The Choice ID
	 * @return Delete Choice response
	 */
	public ResponseEntity<ResponseWrapper<Choices>> deleteChoice( long id )
	{
		ResponseEntity<ResponseWrapper<Choices>> response;

		try
		{
			choicesRepository.deleteById( id );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.CHOICE_DELETE_SUCCESS, "" ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.DELETE, SystemMessages.CHOICE_DELETE_FAILED, e );
		}

		return response;
	}


}
