package com.solution.x.controller;

import com.solution.x.controller.service.ContractService;
import com.solution.x.dao.Contract;
import com.solution.x.facade.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	 * Get Single Contract
	 *
	 * @param identification Contract ID ~ Contract version
	 * @return The Contract
	 */
	@GetMapping("/contracts/{id~version}")
	public ResponseEntity<ResponseWrapper<Contract>> getContract( @PathVariable("id~version") String identification )
	{
		String[] ids = identification.split( "~" );

		return contractService.getContract( Long.parseLong( ids[0] ), Short.parseShort( ids[1] ) );

	}

	/**
	 * Create Single Contract
	 *
	 * @param contract Contract
	 * @return The Contract
	 */
	@PostMapping("/contracts")
	public ResponseEntity<ResponseWrapper<Contract>> createContract( @RequestBody Contract contract )
	{
		return contractService.createContract( contract );

	}


	/**
	 * Update a Contract
	 *
	 * @param identification Contract ID ~ Contract version
	 * @param contract       Contract
	 * @return The Updated Contract
	 */
	@PutMapping("/contracts/{id~version}")
	public ResponseEntity<ResponseWrapper<Contract>> updateContract( @PathVariable("id~version") String identification, @RequestBody Contract contract )
	{
		String[] ids = identification.split( "~" );
		return contractService.updateContract( Long.parseLong( ids[0] ), Short.parseShort( ids[1] ), contract );

	}

	/**
	 * Delete a Contract
	 *
	 * @param identification Contract ID ~ Contract version
	 * @return Delete status
	 */
	@PutMapping("/contracts/{id~version}")
	public ResponseEntity<ResponseWrapper<Contract>> updateContract( @PathVariable("id~version") String identification )
	{
		String[] ids = identification.split( "~" );
		return contractService.deleteContract( Long.parseLong( ids[0] ), Short.parseShort( ids[1] ) );

	}


}
