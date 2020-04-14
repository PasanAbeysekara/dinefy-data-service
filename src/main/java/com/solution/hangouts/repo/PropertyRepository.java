package com.solution.hangouts.repo;

import com.solution.hangouts.dao.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface PropertyRepository extends JpaRepository<Property, Long>
{
	List<Property> findByCode( @Param("code") String code );
}
