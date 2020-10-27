package com.thaprobit.resengine.controller.sys;

import com.thaprobit.resengine.controller.service.sys.PropSpecialityService;
import com.thaprobit.resengine.dao.sys.PropertySpeciality;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tharindu Aththanayake
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class PropSpecialityController
{

	@Autowired
	private PropSpecialityService propSpecialityService;

	/**
	 * Get All Specialities
	 *
	 * @return all Specialities
	 */
	@GetMapping("/specialities")
	public ResponseEntity<ResponseWrapper<List<PropertySpeciality>>> getSpecialities()
	{
		return propSpecialityService.getSpecialities();
	}

	/**
	 * Get Single Speciality
	 *
	 * @param id Speciality ID
	 * @return The Speciality
	 */
	@GetMapping("/specialities/{id}")
	public ResponseEntity<ResponseWrapper<PropertySpeciality>> getSpeciality( @PathVariable("id") short id )
	{
		return propSpecialityService.getSpeciality( id );
	}

	/**
	 * Create a Speciality
	 *
	 * @param propertySpeciality The Speciality
	 * @return Saved Speciality response
	 */
	@PostMapping("/specialities")
	public ResponseEntity<ResponseWrapper<PropertySpeciality>> createSpeciality( @RequestBody PropertySpeciality propertySpeciality )
	{
		return propSpecialityService.createSpeciality( propertySpeciality );
	}

	/**
	 * Update a Speciality
	 *
	 * @param id                 The Speciality ID
	 * @param propertySpeciality The Speciality
	 * @return Updated Speciality response
	 */
	@PutMapping("/specialities/{id}")
	public ResponseEntity<ResponseWrapper<PropertySpeciality>> updateSpeciality( @PathVariable("id") short id, @RequestBody PropertySpeciality propertySpeciality )
	{
		return propSpecialityService.updateSpeciality( id, propertySpeciality );
	}

	/**
	 * Delete a Speciality
	 *
	 * @param id The Speciality ID
	 * @return Delete Speciality response
	 */
	@DeleteMapping("/specialities/{id}")
	public ResponseEntity<ResponseWrapper<PropertySpeciality>> deleteSpeciality( @PathVariable("id") short id )
	{
		return propSpecialityService.deleteSpeciality( id );
	}
}
