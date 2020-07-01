package com.solution.x.data.controller.service;

import com.solution.x.data.facade.Error;
import com.solution.x.data.global.SystemOperation;
import com.solution.x.data.util.ErrorUtility;
import com.solution.x.data.util.ResponseWrapper;
import com.solution.x.data.util.SystemMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Tharinda Wickramaarachchi
 */
public abstract class AbstractService<DAO>
{
	protected HttpHeaders addCommonHeaders( HttpHeaders responseHeaders )
	{
		responseHeaders.add( "Access-Control-Allow-Origin", "http://localhost:4200" );

		return responseHeaders;
	}

	protected ResponseEntity<DAO> buildNotFoundResponse()
	{
		return ResponseEntity.notFound().headers( new HttpHeaders() ).build();
	}

	protected ResponseEntity<ResponseWrapper<DAO>> buildNotFoundResponseWrapped()
	{
		return ResponseEntity.status( HttpStatus.NOT_FOUND )
				.headers( new HttpHeaders() )
				.body( new ResponseWrapper<>( SystemOperation.READ.withError(), SystemMessages.NOT_FOUND, "" ) );
	}

	protected ResponseEntity<ResponseWrapper<DAO>> buildExceptionErrorResponse( SystemOperation operation, SystemMessages message, Exception e )
	{
		Error error = ErrorUtility.exceptionErrorMapper( message, e, true );

		return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
				.headers( new HttpHeaders() )
				.body( new ResponseWrapper<>( operation.withError(), message, error ) );
	}

}
