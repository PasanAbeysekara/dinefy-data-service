package com.solution.x.data.controller;

import com.solution.x.dao.WidenPropData;
import com.solution.x.data.controller.service.AvailDataService;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tharindu Aththanayake
 * @since 8/22/2020 00:03 AM
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class AvailDataController
{

	@Autowired
	private AvailDataService availDataService;

	/**
	 * Update Avail Data
	 *
	 * @param widenPropData The WidenPropData
	 * @return Updated Availability Data
	 */
	@PutMapping("/avail-data")
	public ResponseEntity<ResponseWrapper<WidenPropData>> updateAvailData( @RequestBody WidenPropData widenPropData )
	{

		return availDataService.updateAvailData( widenPropData );

	}
}
