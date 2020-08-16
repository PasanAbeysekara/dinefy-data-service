package com.solution.x.data.controller.sys;

import com.solution.x.dao.sys.Facilities;
import com.solution.x.data.controller.service.sys.SysFacilityService;
import com.solution.x.data.facade.dto.FacilitiesModel;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.URLProvider;
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
 * @author Tharinda Wickramaarachchi
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class SysFacilityController
{
	@Autowired
	private SysFacilityService facilityService;

	/**
	 * Get All Facilities
	 *
	 * @return all sys facilities
	 */
	@GetMapping("/facilities")
	public ResponseEntity<ResponseWrapper<PagedModel<FacilitiesModel>>> getFacilities( Pageable pageable )
	{
		return facilityService.getFacilities( pageable );
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
		return facilityService.getFacility( id );
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
		return facilityService.createFacility( facility );
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
		return facilityService.updateFacility( id, facility );
	}

	/**
	 * Delete a Facility
	 *
	 * @param id The Facility ID
	 * @return Delete response
	 */
	@DeleteMapping("/facilities/{id}")
	public ResponseEntity<ResponseWrapper<Facilities>> deleteTag( @PathVariable("id") int id )
	{
		return facilityService.deleteFacility( id );
	}
}
