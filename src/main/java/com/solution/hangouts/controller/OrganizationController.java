package com.solution.hangouts.controller;

import com.solution.hangouts.dao.Organization;
import com.solution.hangouts.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class OrganizationController extends HngoutAbstractController<Organization>
{
	@Autowired
	private OrganizationRepository organizationRepository;

	@GetMapping("/organizations")
	public ResponseEntity<List<Organization>> getProperty()
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set( "method-org", "test" );

		addCommonHeaders( responseHeaders );

		List<Organization> orgList = organizationRepository.findAll();

		ResponseEntity<List<Organization>> responseEntity = null;
		if( orgList.isEmpty() )
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
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( organizationRepository.findAll().stream().map( Organization::getName ).collect( Collectors.toList() ) );
	}
}
