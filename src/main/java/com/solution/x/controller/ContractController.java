package com.solution.x.controller;

import com.solution.x.controller.service.ContractService;
import com.solution.x.dao.Contract;
import com.solution.x.facade.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class ContractController extends AbstractController<Contract>
{
	@Autowired
	private ContractService contractService;

	@GetMapping("/contracts")
	public ResponseEntity<List<Contract>> getProperty()
	{
		return contractService.getProperty();
	}

	/**
	 * Get Single property
	 *
	 * @param identification property ID
	 * @return The Property
	 */
	@GetMapping("/contracts/{id~version}")
	public ResponseEntity<ResponseWrapper<Contract>> getContract( @PathVariable("id~version") String identification )
	{
		String[] ids = identification.split( "~" );

		return contractService.getContract( Long.parseLong( ids[0] ), Short.parseShort( ids[1] ) );

	}

}
