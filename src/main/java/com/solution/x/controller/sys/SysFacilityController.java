package com.solution.x.controller.sys;

import com.solution.x.controller.AbstractController;
import com.solution.x.dao.sys.Facilities;
import com.solution.x.facade.ResponseWrapper;
import com.solution.x.facade.SystemMessages;
import com.solution.x.repo.sys.FacilitiesRepository;
import com.solution.x.util.HATEOASProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class SysFacilityController extends AbstractController<Facilities>
{
	@Autowired
	private FacilitiesRepository facilitiesRepository;

	/**
	 * Get All Facilities
	 *
	 * @return all sys facilities
	 */
	@GetMapping("/facilities")
	public ResponseEntity<ResponseWrapper<List<Facilities>>> getFacilities()
	{
		List<Facilities> facilities = facilitiesRepository.findAll( Sort.by( Sort.Direction.ASC, "name" ) );
		facilities.forEach( fac -> fac.add( HATEOASProvider.sysFacilitySelfLinkProvider( fac.getFacility_id() ) ) );

		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( new ResponseWrapper<>( "OK", facilities ) );
	}


	/**
	 * Get Single Facility
	 *
	 * @param id Facility ID
	 * @return The Facility
	 */
	@GetMapping("/facilities/{id}")
	public ResponseEntity<ResponseWrapper<Facilities>> getFacility( @PathVariable("id") int id )
	{
		Optional<Facilities> optionalFacility = facilitiesRepository.findById( id );

		ResponseEntity<ResponseWrapper<Facilities>> response;

		if( optionalFacility.isPresent() )
		{
			Facilities facility = optionalFacility.get();
			Link selfRel = HATEOASProvider.sysFacilitySelfLinkProvider( facility.getFacility_id() );
			facility.add( selfRel );

			response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) ).body( new ResponseWrapper<>( "OK", facility ) );
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
	@PostMapping("/facilities")
	public ResponseEntity<ResponseWrapper<Facilities>> createFacility( @RequestBody Facilities facility )
	{
		ResponseEntity<ResponseWrapper<Facilities>> response;

		try
		{
			Facilities savedFacility = facilitiesRepository.save( facility );

			Link selfRel = HATEOASProvider.sysFacilitySelfLinkProvider( savedFacility.getFacility_id() );
			savedFacility.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( "CREATED", SystemMessages.FACILITY_CREATE_SUCCESS.getReasonPhrase(), savedFacility ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildErrorResponse( SystemMessages.FACILITY_CREATE_FAILED, e );
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
	@PutMapping("/facilities/{id}")
	public ResponseEntity<ResponseWrapper<Facilities>> updateFacility( @PathVariable("id") int id, @RequestBody Facilities facility )
	{
		ResponseEntity<ResponseWrapper<Facilities>> response;

		try
		{
			facility.setFacility_id( id );
			Facilities savedFacility = facilitiesRepository.save( facility );

			Link selfRel = HATEOASProvider.sysFacilitySelfLinkProvider( savedFacility.getFacility_id() );
			savedFacility.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( "UPDATED", SystemMessages.FACILITY_UPDATE_SUCCESS.getReasonPhrase(), savedFacility ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildErrorResponse( SystemMessages.FACILITY_UPDATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Delete a Facility
	 *
	 * @param id The Facility ID
	 * @return Delete response
	 */
	@DeleteMapping("/facilities/{id}")
	public ResponseEntity<ResponseWrapper<Facilities>> updateFacility( @PathVariable("id") int id )
	{
		ResponseEntity<ResponseWrapper<Facilities>> response;

		try
		{
			facilitiesRepository.deleteById( id );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( "DELETED", SystemMessages.FACILITY_DELETE_SUCCESS.getReasonPhrase(), null ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildErrorResponse( SystemMessages.FACILITY_DELETE_FAILED, e );
		}

		return response;
	}


}
