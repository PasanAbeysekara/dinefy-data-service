package com.solution.hangouts.controller;

import com.solution.hangouts.util.HATEOASProvider;
import org.springframework.http.HttpHeaders;
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

}
