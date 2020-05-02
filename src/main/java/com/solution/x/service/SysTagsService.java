package com.solution.x.service;

import com.solution.x.controller.AbstractController;
import com.solution.x.dao.sys.Tags;
import com.solution.x.facade.ResponseWrapper;
import com.solution.x.facade.SystemMessages;
import com.solution.x.repo.sys.TagsRepository;
import com.solution.x.util.HATEOASProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@Service
@Slf4j
public class SysTagsService extends AbstractController<Tags>
{
	@Autowired
	private TagsRepository tagsRepository;

	/**
	 * Get All Tags
	 *
	 * @return all sys Tags
	 */
	public ResponseEntity<ResponseWrapper<List<Tags>>> getTags()
	{
		List<Tags> tags = tagsRepository.findAll( Sort.by( Sort.Direction.ASC, "name" ) );
		tags.forEach( fac -> fac.add( HATEOASProvider.sysTagsSelfLinkProvider( fac.getTagId() ) ) );

		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( new ResponseWrapper<>( "OK", tags ) );
	}

	/**
	 * Get Single Tag
	 *
	 * @param id Tag ID
	 * @return The Tag
	 */
	public ResponseEntity<ResponseWrapper<Tags>> getTag( int id )
	{
		Optional<Tags> optionalTags = tagsRepository.findById( id );

		ResponseEntity<ResponseWrapper<Tags>> response;

		if( optionalTags.isPresent() )
		{
			Tags tags = optionalTags.get();
			Link selfRel = HATEOASProvider.sysTagsSelfLinkProvider( tags.getTagId() );
			tags.add( selfRel );

			response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) ).body( new ResponseWrapper<>( "OK", tags ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
		}

		return response;

	}

	/**
	 * Create a Tag
	 *
	 * @param tag The Tag
	 * @return Saved Tag response
	 */
	public ResponseEntity<ResponseWrapper<Tags>> createTag( Tags tag )
	{
		ResponseEntity<ResponseWrapper<Tags>> response;

		try
		{
			Tags savedTag = tagsRepository.save( tag );

			Link selfRel = HATEOASProvider.sysTagsSelfLinkProvider( savedTag.getTagId() );
			savedTag.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( "CREATED", SystemMessages.TAG_CREATE_SUCCESS.getReasonPhrase(), savedTag ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildErrorResponse( SystemMessages.TAG_CREATE_FAILED, e );
		}

		return response;
	}


	/**
	 * Update a Tag
	 *
	 * @param id   The Tag ID
	 * @param tags The Tag
	 * @return Updated Tag response
	 */
	public ResponseEntity<ResponseWrapper<Tags>> updateTag( int id, Tags tags )
	{
		ResponseEntity<ResponseWrapper<Tags>> response;

		try
		{
			tags.setTagId( id );
			Tags savedTag = tagsRepository.save( tags );

			Link selfRel = HATEOASProvider.sysTagsSelfLinkProvider( savedTag.getTagId() );
			savedTag.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( "UPDATED", SystemMessages.TAG_UPDATE_SUCCESS.getReasonPhrase(), savedTag ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildErrorResponse( SystemMessages.TAG_UPDATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Delete a Tag
	 *
	 * @param id The Tag ID
	 * @return Delete response
	 */
	public ResponseEntity<ResponseWrapper<Tags>> deleteTag( int id )
	{
		ResponseEntity<ResponseWrapper<Tags>> response;

		try
		{
			tagsRepository.deleteById( id );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( "DELETED", SystemMessages.TAG_DELETE_SUCCESS.getReasonPhrase(), null ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildErrorResponse( SystemMessages.TAG_DELETE_FAILED, e );
		}

		return response;
	}


}
