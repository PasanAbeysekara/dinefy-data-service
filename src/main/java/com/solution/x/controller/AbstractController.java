package com.solution.x.controller;

import com.solution.x.facade.Error;
import com.solution.x.facade.ResponseWrapper;
import com.solution.x.facade.SystemMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 */
public abstract class AbstractController<DAO>
{
	private static final short CAUSED_BY_DEEP = 5;

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
				.body( new ResponseWrapper<>( "NOT FOUND", "Requested data not found", null ) );
	}

	protected ResponseEntity<ResponseWrapper<DAO>> buildErrorResponse( SystemMessages message, Exception e )
	{
		Error error = new Error();
		error.setCode( message.code() );
		error.setMessage( message.getReasonPhrase() );

		if( e != null )
		{
			int level = 0;

			error.addMessage( level + " - " + e.getMessage() );
			Throwable cause = e.getCause();

			while( cause != null && level < CAUSED_BY_DEEP )
			{
				++level;

				if( cause instanceof ConstraintViolationException )
				{
					Set<ConstraintViolation<?>> constraintViolations = ( (ConstraintViolationException) cause ).getConstraintViolations();
					if( constraintViolations != null )
					{
						if( constraintViolations.stream().findFirst().isPresent() )
						{
							error.setMessage( "Constraint Violated : " + constraintViolations.stream().findFirst().get().getMessage() );
						}
					}
				}

				error.addMessage( level + " - " + cause.getMessage() );
				cause = cause.getCause();
			}
		}

		return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
				.headers( new HttpHeaders() )
				.body( new ResponseWrapper<>( "ERROR", message.getReasonPhrase(), error ) );
	}

}
