package com.solution.hangouts.controller.sys;

import com.solution.hangouts.controller.HngoutAbstractController;
import com.solution.hangouts.dao.sys.AvailabilityUnit;
import com.solution.hangouts.repo.sys.AvailabilityUnitRepository;
import com.solution.hangouts.util.HATEOASProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class SysAvailabilityUnitController extends HngoutAbstractController<AvailabilityUnit>
{
	@Autowired
	private AvailabilityUnitRepository availabilityUnitRepository;

	/**
	 * Get All AvailabilityUnit
	 *
	 * @return all sys AvailabilityUnits
	 */
	@GetMapping("/availability-units")
	public ResponseEntity<List<AvailabilityUnit>> getAvailabilityUnits()
	{
		List<AvailabilityUnit> availabilityUnits = availabilityUnitRepository.findAll().stream().map( unit -> unit.add( HATEOASProvider.sysAvailabilityUnitSelfLinkProvider( unit.getUnit_id() ) ) ).collect( Collectors.toList() );


		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( availabilityUnits );
	}


	/**
	 * Get Single AvailabilityUnit
	 *
	 * @param id AvailabilityUnit ID
	 * @return The AvailabilityUnit
	 */
	@GetMapping("/availability-units/{id}")
	public ResponseEntity<AvailabilityUnit> getAvailabilityUnit( @PathVariable("id") int id )
	{
		Optional<AvailabilityUnit> optionalAvailabilityUnit = availabilityUnitRepository.findById( id );

		return optionalAvailabilityUnit.map( availabilityUnit -> ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( availabilityUnit.add( HATEOASProvider.sysAvailabilityUnitSelfLinkProvider( availabilityUnit.getUnit_id() ) ) ) ).orElseGet( this::buildNotFoundResponse );
	}


}
