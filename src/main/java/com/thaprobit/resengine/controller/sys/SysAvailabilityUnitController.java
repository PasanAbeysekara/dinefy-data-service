package com.thaprobit.resengine.controller.sys;

import com.thaprobit.resengine.controller.service.sys.SysAvailabilityUnitService;
import com.thaprobit.resengine.dao.sys.AvailabilityUnit;
import com.thaprobit.resengine.facade.dto.AvailabilityUnitModel;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class SysAvailabilityUnitController {
    @Autowired
    private SysAvailabilityUnitService availabilityUnitService;

    /**
     * Get All AvailabilityUnit
     *
     * @param pageable Pageable
     * @return all sys AvailabilityUnits
     */
    @GetMapping("/availability-units")
    public ResponseEntity<ResponseWrapper<PagedModel<AvailabilityUnitModel>>> getAvailabilityUnits(Pageable pageable) {
        return availabilityUnitService.getAvailabilityUnits(pageable);
    }


    /**
     * Get Single AvailabilityUnit
     *
     * @param id AvailabilityUnit ID
     * @return The AvailabilityUnit
     */
    @GetMapping("/availability-units/{id}")
    public ResponseEntity<ResponseWrapper<AvailabilityUnit>> getAvailabilityUnit(@PathVariable("id") int id) {
        return availabilityUnitService.getAvailabilityUnit(id);
    }

    /**
     * Create a AvailabilityUnit
     *
     * @param availabilityUnit The AvailabilityUnit
     * @return Saved AvailabilityUnit response
     */
    @PostMapping("/availability-units")
    public ResponseEntity<ResponseWrapper<AvailabilityUnit>> createAvailabilityUnit(@RequestBody AvailabilityUnit availabilityUnit) {
        return availabilityUnitService.createAvailabilityUnit(availabilityUnit);
    }

    /**
     * Update a AvailabilityUnit
     *
     * @param id               The AvailabilityUnit ID
     * @param availabilityUnit The AvailabilityUnit
     * @return Updated AvailabilityUnit response
     */
    @PutMapping("/availability-units/{id}")
    public ResponseEntity<ResponseWrapper<AvailabilityUnit>> updateAvailabilityUnit(@PathVariable("id") int id, @RequestBody AvailabilityUnit availabilityUnit) {
        return availabilityUnitService.updateAvailabilityUnit(id, availabilityUnit);
    }

    /**
     * Delete a AvailabilityUnit
     *
     * @param id The AvailabilityUnit ID
     * @return Delete response
     */
    @DeleteMapping("/availability-units/{id}")
    public ResponseEntity<ResponseWrapper<AvailabilityUnit>> deleteTag(@PathVariable("id") int id) {
        return availabilityUnitService.deleteAvailabilityUnit(id);
    }


}
