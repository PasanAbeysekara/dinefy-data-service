package com.solution.x.data.controller.service.sys;

import com.solution.x.dao.sys.Facilities;
import com.solution.x.data.controller.assembler.FacilitiesModelAssembler;
import com.solution.x.data.controller.service.AbstractService;
import com.solution.x.data.controller.service.HATEOASProvider;
import com.solution.x.data.facade.dto.FacilitiesModel;
import com.solution.x.global.SystemOperation;
import com.solution.x.repo.sys.FacilitiesRepository;
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
 * @author Tharinda Wickramaarachchi
 * @since 5/2/2020 12:25 AM
 */
@Service
@Slf4j
public class SysFacilityService extends AbstractService<Facilities>
{
	@Autowired
	private FacilitiesRepository facilitiesRepository;

	@Autowired
	private PagedResourcesAssembler<Facilities> pagedResourcesAssembler;

	@Autowired
	private FacilitiesModelAssembler facilitiesModelAssembler;

	/**
	 * Get All Facilities
	 *
	 * @param pageable Pageable
	 * @return all sys facilities
	 */
	public ResponseEntity<ResponseWrapper<PagedModel<FacilitiesModel>>> getFacilities( Pageable pageable )
	{
		Page<Facilities> facilitiesPage = facilitiesRepository.findAll( pageable );

		PagedModel<FacilitiesModel> facilitiesPageModel = pagedResourcesAssembler.toModel( facilitiesPage, facilitiesModelAssembler );

		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, facilitiesPageModel ) );
	}


	/**
	 * Get Single Facility
	 *
	 * @param id Facility ID
	 * @return The Facility
	 */
	public ResponseEntity<ResponseWrapper<Facilities>> getFacility( int id )
	{
		Optional<Facilities> optionalFacility = facilitiesRepository.findById( id );

		ResponseEntity<ResponseWrapper<Facilities>> response;

		if( optionalFacility.isPresent() )
		{
			Facilities facility = optionalFacility.get();
			Link selfRel = HATEOASProvider.sysFacilitySelfLinkProvider( facility.getFacilityId() );
			facility.add( selfRel );

			response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, facility ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
		}

		return response;
	}

	/**
	 * Create a Facility
	 *
	 * @param facility The Facility
	 * @return Saved Facility response
	 */
	public ResponseEntity<ResponseWrapper<Facilities>> createFacility( Facilities facility )
	{
		ResponseEntity<ResponseWrapper<Facilities>> response;

		try
		{
			Facilities savedFacility = facilitiesRepository.save( facility );

			Link selfRel = HATEOASProvider.sysFacilitySelfLinkProvider( savedFacility.getFacilityId() );
			savedFacility.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.FACILITY_CREATE_SUCCESS, savedFacility ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during facility creating : ", e );
			response = buildExceptionErrorResponse( SystemOperation.CREATE, SystemMessages.FACILITY_CREATE_FAILED, e );
		}

		return response;
	}


	/**
	 * Update a Facility
	 *
	 * @param id       The Facility ID
	 * @param facility The Facility
	 * @return Updated Facility response
	 */
	public ResponseEntity<ResponseWrapper<Facilities>> updateFacility( int id, Facilities facility )
	{
		ResponseEntity<ResponseWrapper<Facilities>> response;

		try
		{
			facility.setFacilityId( id );
			Facilities savedFacility = facilitiesRepository.save( facility );

			Link selfRel = HATEOASProvider.sysFacilitySelfLinkProvider( savedFacility.getFacilityId() );
			savedFacility.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.FACILITY_UPDATE_SUCCESS, savedFacility ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.MODIFY, SystemMessages.FACILITY_UPDATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Delete a Facility
	 *
	 * @param id The Facility ID
	 * @return Delete response
	 */
	public ResponseEntity<ResponseWrapper<Facilities>> deleteFacility( int id )
	{
		ResponseEntity<ResponseWrapper<Facilities>> response;

		try
		{
			facilitiesRepository.deleteById( id );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.FACILITY_DELETE_SUCCESS, "" ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.DELETE, SystemMessages.FACILITY_DELETE_FAILED, e );
		}

		return response;
	}


}