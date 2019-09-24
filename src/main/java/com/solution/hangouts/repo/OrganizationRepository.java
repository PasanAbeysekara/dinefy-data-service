package com.solution.hangouts.repo;

import com.solution.hangouts.dao.OrganizationDAO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "organizations", path = "organization")
public interface OrganizationRepository extends PagingAndSortingRepository<OrganizationDAO, Long>
{
	List<OrganizationDAO> findByCode( @Param("code") String code );
}
