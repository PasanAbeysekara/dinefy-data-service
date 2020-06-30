package com.solution.x.controller;

import com.solution.x.controller.service.ContractService;
import com.solution.x.dao.Contract;
import com.solution.x.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class ContractController
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
	 * @param id Contract ID
	 * @return The Contract
	 */
	@GetMapping("/contracts/{id}")
	public ResponseEntity<ResponseWrapper<Contract>> getContract( @PathVariable("id") Long id )
	{
		return contractService.getContract( id );
	}

	/**
	 * Create Single Contract
	 *
	 * @param contract Contract
	 * @return The Contract
	 */
	@PostMapping("/contracts")
	public ResponseEntity<ResponseWrapper<Contract>> createContract( @RequestParam(value = "draft", required = false) boolean draft, @RequestBody Contract contract )
	{
		return contractService.createContract( draft, contract );
	}


	/**
	 * Update a Contract
	 *
	 * @param id       Contract ID
	 * @param contract Contract
	 * @return The Updated Contract
	 */
	@PutMapping("/contracts/{id}")
	public ResponseEntity<ResponseWrapper<Contract>> updateContract( @PathVariable("id") Long id, @RequestBody Contract contract )
	{
		return contractService.updateContract( id, contract );
	}

	/**
	 * Delete a Contract
	 *
	 * @param id Contract ID
	 * @return Delete status
	 */
	@DeleteMapping("/contracts/{id}")
	public ResponseEntity<ResponseWrapper<Contract>> updateContract( @PathVariable("id") Long id )
	{
		return contractService.deleteContract( id );
	}


}
