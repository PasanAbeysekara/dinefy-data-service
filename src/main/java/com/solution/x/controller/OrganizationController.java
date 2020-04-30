package com.solution.x.controller;

import com.solution.x.dao.Organization;
import com.solution.x.dao.PropFacilities;
import com.solution.x.dao.Property;
import com.solution.x.repo.OrganizationRepository;
import com.solution.x.util.HATEOASProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class OrganizationController extends AbstractController<Organization>
{
	@Autowired
	private OrganizationRepository organizationRepository;

	/**
	 * Get All Organizations
	 *
	 * @return Organizations
	 */
	@GetMapping("/organizations")
	public ResponseEntity<List<Organization>> getProperty()
	{
		List<Organization> orgList = organizationRepository.findAll();

		ResponseEntity<List<Organization>> responseEntity = null;
		if( orgList.isEmpty() )
		{
			responseEntity = ResponseEntity.notFound().headers( addCommonHeaders( new HttpHeaders() ) ).build();
		}
		else
		{
			responseEntity = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) ).body( orgList );
		}

		return responseEntity;
	}

	/**
	 * Get Single Organization
	 *
	 * @param id Organization ID
	 * @return The Organization
	 */
	@GetMapping("/organizations/{id}")
	public ResponseEntity<Organization> getOrganization( @PathVariable("id") long id )
	{
		Optional<Organization> optionalOrg = organizationRepository.findById( id );

		ResponseEntity<Organization> response;

		if( optionalOrg.isPresent() )
		{
			Link selfRel = HATEOASProvider.organizationSelfLinkProvider( id );

			Organization organization = optionalOrg.get();
			organization.add( selfRel );

			for( Property property : organization.getProperties() )
			{
				Link propSelfLink = HATEOASProvider.propertySelfLinkProvider( id );
				property.add( propSelfLink );

				for( PropFacilities facility : property.getFacilities() )
				{
					int sysFacilityID = facility.getSysFacility().getFacilityId();

					Link selfRelSysFacility = HATEOASProvider.sysFacilitySelfLinkProvider( sysFacilityID );
					Link selfRelPropFacility = HATEOASProvider.propFacilitySelfLinkProvider( facility.getPropFacilityID().getPropId() );

					facility.getSysFacility().add( selfRelSysFacility );
					facility.add( selfRelPropFacility );
				}
			}

			response = ResponseEntity.ok().headers( addCommonHeaders( new HttpHeaders() ) ).body( organization );
		}
		else
		{
			response = buildNotFoundResponse();
		}

		return optionalOrg.map( org -> ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( org ) ).orElseGet( this::buildNotFoundResponse );
	}

	@GetMapping("/org-name")
	public ResponseEntity<List<String>> getPropertyNames()
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( organizationRepository.findAll().stream().map( Organization::getName ).collect( Collectors.toList() ) );
	}
}
