package com.solution.x.controller;

import com.solution.x.dao.Contract;
import com.solution.x.dao.key.ContractID;
import com.solution.x.repo.ContractsRepository;
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
	 * @param identification property ID
	 * @return The Property
	 */
	@GetMapping("/contracts/{id~version}")
	public ResponseEntity<Contract> getProperty( @PathVariable("id~version") String identification )
	{

		String[] ids = identification.split( "~" );

		Optional<Contract> contractOptional = contractsRepository.findById( new ContractID( Long.parseLong( ids[0] ), Short.parseShort( ids[1] ) ) );

		return contractOptional.map( propertyDAO -> ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( propertyDAO ) ).orElseGet( this::buildNotFoundResponse );

	}

}
