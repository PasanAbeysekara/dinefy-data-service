package com.solution.hangouts.repo;

import com.solution.hangouts.dao.OrganizationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "organizations", path = "organizations")
public interface OrganizationRepository extends JpaRepository<OrganizationDAO, Long>
{
	List<OrganizationDAO> findByCode( @Param("code") String code );
}
