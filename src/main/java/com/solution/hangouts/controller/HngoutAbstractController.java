package com.solution.hangouts.controller;

import org.springframework.http.HttpHeaders;

public abstract class HngoutAbstractController
{

	protected void addCommonHeaders( HttpHeaders responseHeaders )
	{
		responseHeaders.add( "Access-Control-Allow-Origin", "http://localhost:4200" );
	}

}
