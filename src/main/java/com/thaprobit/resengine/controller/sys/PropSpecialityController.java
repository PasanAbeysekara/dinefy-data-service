package com.thaprobit.resengine.controller.sys;

import com.thaprobit.resengine.controller.service.sys.PropSpecialityService;
import com.thaprobit.resengine.dao.sys.PropertySpeciality;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Tharindu Aththanayake
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class PropSpecialityController {

    @Autowired
    private PropSpecialityService propSpecialityService;

    /**
     * Get All Specialities
     *
     * @param pageable Pageable
     * @return all Specialities
     */
    @GetMapping("/specialities")
    public ResponseEntity<ResponseWrapper<Page<PropertySpeciality>>> getSpecialities(Pageable pageable) {
        return propSpecialityService.getSpecialities(pageable);
    }

    /**
     * Get Single Speciality
     *
     * @param id Speciality ID
     * @return The Speciality
     */
    @GetMapping("/specialities/{id}")
    public ResponseEntity<ResponseWrapper<PropertySpeciality>> getSpeciality(@PathVariable("id") short id) {
        return propSpecialityService.getSpeciality(id);
    }

    /**
     * Create a Speciality
     *
     * @param propertySpeciality The Speciality
     * @return Saved Speciality response
     */
    @PostMapping("/specialities")
    public ResponseEntity<ResponseWrapper<PropertySpeciality>> createSpeciality(@RequestBody PropertySpeciality propertySpeciality) {
        return propSpecialityService.createSpeciality(propertySpeciality);
    }

    /**
     * Update a Speciality
     *
     * @param id                 The Speciality ID
     * @param propertySpeciality The Speciality
     * @return Updated Speciality response
     */
    @PutMapping("/specialities/{id}")
    public ResponseEntity<ResponseWrapper<PropertySpeciality>> updateSpeciality(@PathVariable("id") short id, @RequestBody PropertySpeciality propertySpeciality) {
        return propSpecialityService.updateSpeciality(id, propertySpeciality);
    }

    /**
     * Delete a Speciality
     *
     * @param id The Speciality ID
     * @return Delete Speciality response
     */
    @DeleteMapping("/specialities/{id}")
    public ResponseEntity<ResponseWrapper<PropertySpeciality>> deleteSpeciality(@PathVariable("id") short id) {
        return propSpecialityService.deleteSpeciality(id);
    }
}
