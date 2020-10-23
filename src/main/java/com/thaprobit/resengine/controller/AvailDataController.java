package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.controller.service.AvailDataService;
import com.thaprobit.resengine.dao.WidenPropData;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
