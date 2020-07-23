package com.solution.x.data.controller.service.sys;

import com.solution.x.dao.sys.AvailabilityUnit;
import com.solution.x.data.controller.assembler.AvailUnitModelAssembler;
import com.solution.x.data.controller.service.HATEOASProvider;
import com.solution.x.data.facade.dto.AvailabilityUnitModel;
import com.solution.x.global.SystemOperation;
import com.solution.x.repo.sys.AvailabilityUnitRepository;
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

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@Service
@Slf4j
public class SysAvailabilityUnitService extends AbstractService<AvailabilityUnit>
{
	@Autowired
	private AvailabilityUnitRepository availabilityUnitRepository;

	@Autowired
	private PagedResourcesAssembler<AvailabilityUnit> pagedResourcesAssembler;

	@Autowired
	private AvailUnitModelAssembler availUnitModelAssembler;

	/**
	 * Get All AvailabilityUnit
	 *
	 * @param pageable Pageable
	 * @return all sys AvailabilityUnits
	 */
	public ResponseEntity<ResponseWrapper<PagedModel<AvailabilityUnitModel>>> getAvailabilityUnits( Pageable pageable )
	{
		Page<AvailabilityUnit> facilitiesPage = availabilityUnitRepository.findAll( pageable );

		PagedModel<AvailabilityUnitModel> facilitiesPageModel = pagedResourcesAssembler.toModel( facilitiesPage, availUnitModelAssembler );

		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, facilitiesPageModel ) );
	}

	/**
	 * Get Single AvailabilityUnit
	 *
	 * @param id AvailabilityUnit ID
	 * @return The AvailabilityUnit
	 */
	public ResponseEntity<ResponseWrapper<AvailabilityUnit>> getAvailabilityUnit( int id )
	{
		Optional<AvailabilityUnit> optionalAvailabilityUnit = availabilityUnitRepository.findById( id );

		ResponseEntity<ResponseWrapper<AvailabilityUnit>> response;

		if( optionalAvailabilityUnit.isPresent() )
		{
			AvailabilityUnit availUnit = optionalAvailabilityUnit.get();
			Link selfRel = HATEOASProvider.sysAvailabilityUnitSelfLinkProvider( availUnit.getUnitId() );
			availUnit.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, availUnit ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
		}

		return response;
	}

	/**
	 * Create a AvailabilityUnit
	 *
	 * @param availUnit The AvailabilityUnit
	 * @return Saved AvailabilityUnit response
	 */
	@Transactional
	public ResponseEntity<ResponseWrapper<AvailabilityUnit>> createAvailabilityUnit( AvailabilityUnit availUnit )
	{
		ResponseEntity<ResponseWrapper<AvailabilityUnit>> response;

		try
		{
			AvailabilityUnit savedAvailabilityUnit = availabilityUnitRepository.save( availUnit );

			Link selfRel = HATEOASProvider.sysAvailabilityUnitSelfLinkProvider( savedAvailabilityUnit.getUnitId() );
			savedAvailabilityUnit.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.AVAIL_UNIT_CREATE_SUCCESS, savedAvailabilityUnit ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during availUnit creating : ", e );
			response = buildExceptionErrorResponse( SystemOperation.CREATE, SystemMessages.AVAIL_UNIT_CREATE_FAILED, e );
		}

		return response;
	}


	/**
	 * Update a AvailabilityUnit
	 *
	 * @param id        The AvailabilityUnit ID
	 * @param availUnit The AvailabilityUnit
	 * @return Updated AvailabilityUnit response
	 */
	public ResponseEntity<ResponseWrapper<AvailabilityUnit>> updateAvailabilityUnit( int id, AvailabilityUnit availUnit )
	{
		ResponseEntity<ResponseWrapper<AvailabilityUnit>> response;

		try
		{
			availUnit.setUnitId( id );
			AvailabilityUnit savedAvailabilityUnit = availabilityUnitRepository.save( availUnit );

			Link selfRel = HATEOASProvider.sysAvailabilityUnitSelfLinkProvider( savedAvailabilityUnit.getUnitId() );
			savedAvailabilityUnit.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.AVAIL_UNIT_UPDATE_SUCCESS, savedAvailabilityUnit ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.MODIFY, SystemMessages.AVAIL_UNIT_UPDATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Delete a AvailabilityUnit
	 *
	 * @param id The AvailabilityUnit ID
	 * @return Delete response
	 */
	public ResponseEntity<ResponseWrapper<AvailabilityUnit>> deleteAvailabilityUnit( int id )
	{
		ResponseEntity<ResponseWrapper<AvailabilityUnit>> response;

		try
		{
			availabilityUnitRepository.deleteById( id );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.AVAIL_UNIT_DELETE_SUCCESS ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.DELETE, SystemMessages.AVAIL_UNIT_DELETE_FAILED, e );
		}

		return response;
	}

}
