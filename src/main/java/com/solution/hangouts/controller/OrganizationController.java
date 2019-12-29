package com.solution.hangouts.controller;

import com.solution.hangouts.dao.OrganizationDAO;
import com.solution.hangouts.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrganizationController extends HngoutAbstractController
{
	@Autowired
	private	OrganizationRepository organizationRepository;

	@GetMapping("/org")
	public ResponseEntity<List<OrganizationDAO>> getProperty()
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set( "method-org", "test" );

		addCommonHeaders( responseHeaders );

		List<OrganizationDAO> orgList = organizationRepository.findAll();

		ResponseEntity<List<OrganizationDAO>> responseEntity = null;
		if( orgList == null || orgList.isEmpty() )
		{
			responseEntity = ResponseEntity.notFound().headers( responseHeaders ).build();
		}
		else
		{
			responseEntity = ResponseEntity.ok().headers( responseHeaders ).body( orgList );
		}


		return responseEntity;
	}

	@GetMapping("/org-name")
	public ResponseEntity<List<String>> getPropertyNames()
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set( "Access-Control-Allow-Origin", "http://localhost:4200" );

		return ResponseEntity.ok()
				.headers( responseHeaders )
				.body( organizationRepository.findAll().stream().map( OrganizationDAO::getName ).collect( Collectors.toList() ) );
	}
}
