package com.solution.x.controller.sys;

import com.solution.x.controller.HngoutAbstractController;
import com.solution.x.dao.sys.Facilities;
import com.solution.x.facade.ResponseWrapper;
import com.solution.x.repo.sys.FacilitiesRepository;
import com.solution.x.util.HATEOASProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class SysFacilityController extends HngoutAbstractController<Facilities>
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


}
