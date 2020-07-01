package com.solution.x.data.controller.validator;

import com.solution.x.dao.Property;
import com.solution.x.data.global.DataCarrier;
import com.solution.x.data.global.SystemOperation;
import com.solution.x.data.util.ResponseWrapper;
import com.solution.x.data.util.SystemMessages;
import com.solution.x.repo.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/8/2020 11:40 PM
 */
@Component
@Slf4j
public class PropertyValidator
{
	@Autowired
	private PropertyRepository propertyRepository;

	public DataCarrier<ResponseEntity<ResponseWrapper<Property>>> validateCreate( Property property )
	{
		DataCarrier<ResponseEntity<ResponseWrapper<Property>>> dataCarrier = DataCarrier.<ResponseEntity<ResponseWrapper<Property>>>init().withSuccess();


		Integer withSameContract = propertyRepository.findPropWithSameContract( property.getCurrentContId() );

		if( withSameContract > 0 )
		{
			String messageAppender = "Contact ID = " + property.getCurrentContId();

			ResponseEntity<ResponseWrapper<Property>> responseEntity = ResponseEntity.status( HttpStatus.CONFLICT )
					.body( new ResponseWrapper<>( SystemOperation.VALIDATE.withError(), SystemMessages.PROPERTY_VALIDATION_CONTRACT, messageAppender ) );

			dataCarrier.setData( responseEntity );
			dataCarrier.setStatus( DataCarrier.CarrierStatus.ERROR );
			dataCarrier.setMessage( Objects.requireNonNull( responseEntity.getBody() ).getPrettyMessage() );

			log.error( SystemMessages.PROPERTY_VALIDATION_CONTRACT.getReasonPhrase() + " :" + messageAppender + " Found :" + withSameContract );
		}

		return dataCarrier;
	}
}
