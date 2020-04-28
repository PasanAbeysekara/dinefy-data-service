package com.solution.x.repo;

import com.solution.x.dao.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long>
{
	List<Organization> findByCode( @Param("code") String code );
}
