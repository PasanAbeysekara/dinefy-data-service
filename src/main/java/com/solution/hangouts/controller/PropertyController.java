package com.solution.hangouts.controller;

import com.solution.hangouts.dao.PropertyDAO;
import com.solution.hangouts.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PropertyController
{
	@Autowired
	private PropertyRepository propertyRepository;

	@GetMapping("/prop")
	public ResponseEntity<List<PropertyDAO>> getProperty()
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set( "Access-Control-Allow-Origin","http://localhost:4200" );

		return ResponseEntity.ok()
				.headers( responseHeaders )
				.body( propertyRepository.findAll() );
	}

	@PostMapping("/prop")
	public ResponseEntity<PropertyDAO> saveProperty( @RequestBody PropertyDAO propertyDAO )
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set( "Access-Control-Allow-Origin","*" );

		return ResponseEntity.ok()
				.headers( responseHeaders )
				.body( propertyRepository.save( propertyDAO) );
	}

	@GetMapping("/prop-name")
	public ResponseEntity<List<String>> getPropertyNames()
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set( "Access-Control-Allow-Origin","http://localhost:4200" );

		return ResponseEntity.ok()
				.headers( responseHeaders )
				.body( propertyRepository.findAll().stream().map( PropertyDAO::getName ).collect( Collectors.toList()) );
	}
}
