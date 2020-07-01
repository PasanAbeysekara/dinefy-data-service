package com.solution.x.controller.validator;

import com.solution.x.dao.Property;
import com.solution.x.repo.ContractsRepository;
import com.solution.x.search.global.DataCarrier;
import com.solution.x.search.util.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/8/2020 11:40 PM
 */
@Component
@Slf4j
public class ContractValidator
{
	@Autowired
	private ContractsRepository contractsRepository;

	public DataCarrier<ResponseEntity<ResponseWrapper<Property>>> validateCreate( Property property )
	{
		DataCarrier<ResponseEntity<ResponseWrapper<Property>>> dataCarrier = DataCarrier.<ResponseEntity<ResponseWrapper<Property>>>init().withSuccess();


		//		Integer withSameContract = contractsRepository.findContractWithSameContract( property.getCurrentContId(), property.getCurrentContVersion() );
		//
		//		if( withSameContract > 0 )
		//		{
		//			String messageAppender = "Contact ID = " + property.getCurrentContId() + " Version : " + property.getCurrentContVersion();
		//
		//			ResponseEntity<ResponseWrapper<Property>> responseEntity = ResponseEntity.status( HttpStatus.CONFLICT )
		//					.body( new ResponseWrapper<>( SystemOperation.VALIDATE, SystemMessages.PROPERTY_VALIDATION_CONTRACT, messageAppender ) );
		//
		//			dataCarrier.setData( responseEntity );
		//			dataCarrier.setStatus( DataCarrier.CarrierStatus.ERROR );
		//			dataCarrier.setMessage( Objects.requireNonNull( responseEntity.getBody() ).getPrettyMessage() );
		//
		//			log.error( SystemMessages.PROPERTY_VALIDATION_CONTRACT.getReasonPhrase() + " :" + messageAppender + " Found :" + withSameContract );
		//		}

		return dataCarrier;
	}
}
