package com.solution.hangouts.controller.sys;

import com.solution.hangouts.controller.HngoutAbstractController;
import com.solution.hangouts.dao.sys.Facilities;
import com.solution.hangouts.repo.sys.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysFacilityController extends HngoutAbstractController<Facilities>
{
	@Autowired
	private FacilitiesRepository facilitiesRepository;

	@GetMapping("/facilities")
	public ResponseEntity<List<Facilities>> getFacilities()
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( facilitiesRepository.findAll() );
	}
}
