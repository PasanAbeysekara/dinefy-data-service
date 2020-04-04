package com.solution.hangouts.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public abstract class HngoutAbstractController<E>
{

	protected HttpHeaders addCommonHeaders( HttpHeaders responseHeaders )
	{
		responseHeaders.add( "Access-Control-Allow-Origin", "http://localhost:4200" );

		return responseHeaders;
	}

	protected ResponseEntity<E> buildNotFoundResponse()
	{
		return ResponseEntity.notFound().headers( new HttpHeaders() ).build();
	}

}
