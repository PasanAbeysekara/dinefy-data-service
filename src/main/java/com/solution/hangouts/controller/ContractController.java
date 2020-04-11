package com.solution.hangouts.controller;

import com.solution.hangouts.dao.Contracts;
import com.solution.hangouts.dao.Organization;
import com.solution.hangouts.repo.ContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ContractController extends HngoutAbstractController<Contracts>
{
	@Autowired
	private ContractsRepository contractsRepository;

	@GetMapping("/contracts")
	public ResponseEntity<List<Contracts>> getProperty()
	{

		List<Contracts> orgList = contractsRepository.findAll();

		ResponseEntity<List<Contracts>> responseEntity = null;
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

}
