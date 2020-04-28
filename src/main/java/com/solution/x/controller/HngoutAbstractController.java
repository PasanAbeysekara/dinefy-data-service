package com.solution.x.controller;

import com.solution.x.facade.ResponseWrapper;
import com.solution.x.util.HATEOASProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Tharinda Wickramaarachchi
 */
public abstract class HngoutAbstractController<DAO>
{
	protected HATEOASProvider hateoasProvider;

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
		return ResponseEntity.status( HttpStatus.NOT_FOUND ).headers( new HttpHeaders() ).body( new ResponseWrapper<>( "NOT FOUND", "Requested data not found", null ) );
	}

}
