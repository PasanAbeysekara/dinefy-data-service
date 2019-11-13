package com.solution.hangouts.repo;

import com.solution.hangouts.dao.OrganizationDAO;
import com.solution.hangouts.dao.PropertyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "properties", path = "properties")
public interface PropertyRepository extends JpaRepository<PropertyDAO, Long>
{
	List<PropertyDAO> findByCode( @Param("code") String code );
}
