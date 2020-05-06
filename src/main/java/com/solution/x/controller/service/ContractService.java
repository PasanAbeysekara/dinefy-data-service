package com.solution.x.controller.service;

import com.solution.x.controller.AbstractController;
import com.solution.x.dao.Contract;
import com.solution.x.dao.key.ContractID;
import com.solution.x.facade.ResponseWrapper;
import com.solution.x.repo.ContractsRepository;
import com.solution.x.util.HATEOASProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class ContractService extends AbstractController<Contract>
{
	@Autowired
	private ContractsRepository contractsRepository;


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
	 * Get Single Contract
	 *
	 * @param id      contract ID
	 * @param version contract version
	 * @return The Contract
	 */
	public ResponseEntity<ResponseWrapper<Contract>> getContract( long id, short version )
	{
		Optional<Contract> contractOptional = contractsRepository.findById( new ContractID( id, version ) );

		ResponseEntity<ResponseWrapper<Contract>> response;

		if( contractOptional.isPresent() )
		{
			Contract contract = contractOptional.get();
			Link selfRel = HATEOASProvider.contractSelfLinkProvider( contract.getContractId().getContractId(), contract.getContractId().getVersion() );
			contract.add( selfRel );

			response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) ).body( new ResponseWrapper<>( "OK", contract ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
		}

		return response;
	}

}
