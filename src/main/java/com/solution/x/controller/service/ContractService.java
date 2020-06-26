package com.solution.x.controller.service;

import com.solution.x.controller.service.functionality.PropAvailDataAsyncExecutor;
import com.solution.x.dao.Contract;
import com.solution.x.dao.ContractAvailability;
import com.solution.x.dao.Seasons;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@Service
@Slf4j
public class ContractService extends AbstractService<Contract>
{
	@Autowired
	private ContractsRepository contractsRepository;

	@Autowired
	private PropAvailDataAsyncExecutor availDataAsyncExecutor;


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
	 * @param id contract ID
	 * @return The Contract
	 */
	public ResponseEntity<ResponseWrapper<Contract>> getContract( long id )
	{
		Optional<Contract> contractOptional = contractsRepository.findById( id );

		ResponseEntity<ResponseWrapper<Contract>> response;

		if( contractOptional.isPresent() )
		{
			Contract contract = contractOptional.get();
			Link selfRel = HATEOASProvider.contractSelfLinkProvider( contract.getContractId() );
			contract.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, contract ) );

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
	 * @param draft
	 * @param contract Contract
	 * @return Saved Contract Response wrapper
	 */
	@Transactional
	public ResponseEntity<ResponseWrapper<Contract>> createContract( boolean draft, Contract contract )
	{
		ResponseEntity<ResponseWrapper<Contract>> response;

		try
		{
			Long contractNextVal = contractsRepository.getNextVal();
			contract.setContractId( contractNextVal );

			preProcess( contract );

			Contract savedContract = contractsRepository.saveAndFlush( contract );

			Link selfRel = HATEOASProvider.contractSelfLinkProvider( contract.getContractId() );
			savedContract.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.CONTRACT_CREATE_SUCCESS, savedContract ) );

			if( !draft )
			{
				availDataAsyncExecutor.executeAsynchronouslyTx( contract );
			}

		}
		catch( Exception e )
		{
			log.error( "Error Occurred during contract saving : ", e );
			response = buildExceptionErrorResponse( SystemOperation.CREATE, SystemMessages.CONTRACT_CREATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Create a new Contract
	 *
	 * @param contract Contract
	 * @return Saved Contract Response wrapper
	 */
	public ResponseEntity<ResponseWrapper<Contract>> updateContract( long id, Contract contract )
	{
		ResponseEntity<ResponseWrapper<Contract>> response;

		try
		{
			contract.setContractId( id );
			preProcess( contract );

			Contract savedContract = contractsRepository.save( contract );

			Link selfRel = HATEOASProvider.contractSelfLinkProvider( contract.getContractId() );
			savedContract.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.CONTRACT_UPDATE_SUCCESS, savedContract ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during contract updating : ", e );
			response = buildExceptionErrorResponse( SystemOperation.MODIFY, SystemMessages.CONTRACT_UPDATE_FAILED, e );
		}

		return response;
	}


	private void preProcess( Contract contract )
	{
		if( contract.getSeasons() != null )
		{
			long contractId = contract.getContractId();

			//contract.getSeasons().forEach( seasons -> seasons.setWeekDefinitions( null ) ); // TODO Do a proper fix  Issue : Hibernate generate unnecessary insert query  : insert into hngout.contract_availability (contract_id, season_id, contract_version, week_def_id) values (?, ?, ?, ?)

			for( Seasons season : contract.getSeasons() )
			{
				season.getSeasonId().setContractId( contractId );

				if( season.getAvailabilities() != null )
				{
					for( ContractAvailability availability : season.getAvailabilities() )
					{
						availability.getAvailabilityID().setContractId( contractId );
						availability.getAvailabilityID().setSeasonId( season.getSeasonId().getSeasonId() );
					}
				}
			}
		}

	}

	/**
	 * Delete contract
	 *
	 * @param id contract ID
	 * @return
	 */
	public ResponseEntity<ResponseWrapper<Contract>> deleteContract( long id )
	{
		ResponseEntity<ResponseWrapper<Contract>> response;

		try
		{
			contractsRepository.deleteById( id );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.CONTRACT_DELETE_SUCCESS, "" ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during contract deleting : ", e );
			response = buildExceptionErrorResponse( SystemOperation.DELETE, SystemMessages.CONTRACT_DELETE_FAILED, e );
		}

		return response;
	}
}
