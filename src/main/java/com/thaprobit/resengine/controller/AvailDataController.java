package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.controller.service.AvailDataService;
import com.thaprobit.resengine.dao.WidenPropData;
import com.thaprobit.resengine.dao.key.WidenDataGridKey;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tharindu Aththanayake
 * @since 8/22/2020 00:03 AM
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class AvailDataController {

    @Autowired
    private AvailDataService availDataService;

    // Update Avail Data
    @PutMapping("/avail-data")
    public ResponseEntity<ResponseWrapper<WidenPropData>> updateAvailData(@RequestBody WidenPropData widenPropData) {
        return availDataService.updateAvailData(widenPropData);
    }

    // Get Availability Data by ID
    @GetMapping("/avail-data/{id}")
    public ResponseEntity<ResponseWrapper<WidenPropData>> getAvailDataById(@PathVariable WidenDataGridKey id) {
        return availDataService.getAvailDataById(id);
    }

    // Get All Availability Data
    @GetMapping("/avail-data")
    public ResponseEntity<ResponseWrapper<List<WidenPropData>>> getAllAvailData() {
        return availDataService.getAllAvailData();
    }

    // Create New Availability Data
//    @PostMapping("/avail-data")
//    public ResponseEntity<ResponseWrapper<WidenPropData>> createAvailData(@RequestBody WidenPropData widenPropData) {
//        return availDataService.createAvailData(widenPropData);
//    }
}
