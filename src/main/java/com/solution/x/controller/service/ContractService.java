package com.solution.x.controller.service;

import com.solution.x.controller.AbstractController;
import com.solution.x.dao.Contract;
import com.solution.x.dao.key.ContractID;
import com.solution.x.facade.ResponseWrapper;
import com.solution.x.facade.SystemMessages;
import com.solution.x.global.SystemOperation;
import com.solution.x.repo.ContractsRepository;
import com.solution.x.util.HATEOASProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
@Slf4j
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


	/**
	 * Create a new Contract
	 *
	 * @param contract Contract
	 * @return Saved Contract Response wrapper
	 */
	public ResponseEntity<ResponseWrapper<Contract>> createContract( Contract contract )
	{
		ResponseEntity<ResponseWrapper<Contract>> response;

		try
		{
			preProcess( contract );

			Contract savedContract = contractsRepository.save( contract );

			Link selfRel = HATEOASProvider.contractSelfLinkProvider( contract.getContractId().getContractId(), contract.getContractId().getVersion() );
			contract.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.CREATE, SystemMessages.CONTRACT_CREATE_SUCCESS, savedContract ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during contract saving : ", e );
			response = buildExceptionErrorResponse( SystemMessages.CONTRACT_CREATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Create a new Contract
	 *
	 * @param contract Contract
	 * @return Saved Contract Response wrapper
	 */
	public ResponseEntity<ResponseWrapper<Contract>> updateContract( long id, short version, Contract contract )
	{
		ResponseEntity<ResponseWrapper<Contract>> response;

		try
		{
			preProcess( contract );

			contract.setContractId( new ContractID( id, version ) );
			Contract savedContract = contractsRepository.save( contract );

			Link selfRel = HATEOASProvider.contractSelfLinkProvider( contract.getContractId().getContractId(), contract.getContractId().getVersion() );
			contract.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY, SystemMessages.CONTRACT_UPDATE_SUCCESS, savedContract ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during contract updating : ", e );
			response = buildExceptionErrorResponse( SystemMessages.CONTRACT_UPDATE_FAILED, e );
		}

		return response;
	}


	private void preProcess( Contract contract )
	{
		if( contract.getSeasons() != null )
		{
			contract.getSeasons().forEach( seasons -> seasons.setWeekDefinitions( null ) ); // TODO Do a proper fix  Issue : Hibernate generate unnecessary insert query  : insert into hngout.contract_availability (contract_id, season_id, contract_version, week_def_id) values (?, ?, ?, ?)
		}
	}

	/**
	 * Delete contract
	 *
	 * @param id      contract ID
	 * @param version contract version
	 * @return
	 */
	public ResponseEntity<ResponseWrapper<Contract>> deleteContract( long id, short version )
	{
		ResponseEntity<ResponseWrapper<Contract>> response;

		try
		{
			contractsRepository.deleteById( new ContractID( id, version ) );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE, SystemMessages.CONTRACT_DELETE_SUCCESS, null ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during contract deleting : ", e );
			response = buildExceptionErrorResponse( SystemMessages.CONTRACT_DELETE_FAILED, e );
		}

		return response;
	}
}
