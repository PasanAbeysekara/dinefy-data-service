package com.solution.x.data.controller.sys;

import com.solution.x.dao.sys.AvailabilityUnit;
import com.solution.x.data.controller.service.sys.SysAvailabilityUnitService;
import com.solution.x.data.facade.dto.AvailabilityUnitModel;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.URLProvider;
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
public class SysAvailabilityUnitController
{
	@Autowired
	private SysAvailabilityUnitService availabilityUnitService;

	/**
	 * Get All AvailabilityUnit
	 *
	 * @param pageable Pageable
	 * @return all sys AvailabilityUnits
	 */
	@GetMapping("/availability-units")
	public ResponseEntity<ResponseWrapper<PagedModel<AvailabilityUnitModel>>> getAvailabilityUnits( Pageable pageable )
	{
		return availabilityUnitService.getAvailabilityUnits( pageable );
	}


	/**
	 * Get Single AvailabilityUnit
	 *
	 * @param id AvailabilityUnit ID
	 * @return The AvailabilityUnit
	 */
	@GetMapping("/availability-units/{id}")
	public ResponseEntity<ResponseWrapper<AvailabilityUnit>> getAvailabilityUnit( @PathVariable("id") int id )
	{
		return availabilityUnitService.getAvailabilityUnit( id );
	}

	/**
	 * Create a AvailabilityUnit
	 *
	 * @param availabilityUnit The AvailabilityUnit
	 * @return Saved AvailabilityUnit response
	 */
	@PostMapping("/availability-units")
	public ResponseEntity<ResponseWrapper<AvailabilityUnit>> createAvailabilityUnit( @RequestBody AvailabilityUnit availabilityUnit )
	{
		return availabilityUnitService.createAvailabilityUnit( availabilityUnit );
	}

	/**
	 * Update a AvailabilityUnit
	 *
	 * @param id               The AvailabilityUnit ID
	 * @param availabilityUnit The AvailabilityUnit
	 * @return Updated AvailabilityUnit response
	 */
	@PutMapping("/availability-units/{id}")
	public ResponseEntity<ResponseWrapper<AvailabilityUnit>> updateAvailabilityUnit( @PathVariable("id") int id, @RequestBody AvailabilityUnit availabilityUnit )
	{
		return availabilityUnitService.updateAvailabilityUnit( id, availabilityUnit );
	}

	/**
	 * Delete a AvailabilityUnit
	 *
	 * @param id The AvailabilityUnit ID
	 * @return Delete response
	 */
	@DeleteMapping("/availability-units/{id}")
	public ResponseEntity<ResponseWrapper<AvailabilityUnit>> deleteTag( @PathVariable("id") int id )
	{
		return availabilityUnitService.deleteAvailabilityUnit( id );
	}


}
