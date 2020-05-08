package com.solution.x.repo;

import com.solution.x.dao.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface PropertyRepository extends JpaRepository<Property, Long>
{
	List<Property> findByCode( @Param("code") String code );

	@Query(value = "SELECT count(p) FROM Property p where p.currentContId =:id and p.currentContVersion = :version")
	Integer findPropWithSameContract( @Param("id") Integer currentContId, @Param("version") Short currentContVersion );
}
