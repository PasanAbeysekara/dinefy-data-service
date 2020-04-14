package com.solution.hangouts.controller;

import com.solution.hangouts.dao.Contract;
import com.solution.hangouts.repo.ContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class ContractController extends HngoutAbstractController<Contract>
{
	@Autowired
	private ContractsRepository contractsRepository;

	@GetMapping("/contracts")
	public ResponseEntity<List<Contract>> getProperty()
	{

		List<Contract> orgList = contractsRepository.findAll();

		ResponseEntity<List<Contract>> responseEntity = null;
		if( orgList.isEmpty() )
		{
			responseEntity = ResponseEntity.notFound().headers( addCommonHeaders( new HttpHeaders() ) ).build();
		}
		else
		{
			responseEntity = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) ).body( orgList );
		}


		return responseEntity;
	}

	/**
	 * Get Single property
	 *
	 * @param id property ID
	 * @return The Property
	 */
	@GetMapping("/contracts/{id}")
	public ResponseEntity<Contract> getProperty( @PathVariable("id") long id )
	{
		Optional<Contract> contractOptional = contractsRepository.findById( id );

		return contractOptional.map( propertyDAO -> ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propertyDAO ) ).orElseGet( this::buildNotFoundResponse );

	}

}
