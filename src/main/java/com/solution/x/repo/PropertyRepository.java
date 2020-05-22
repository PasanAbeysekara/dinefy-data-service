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

	@Query(value = "SELECT count(p) FROM Property p where p.currentContId =:id")
	Integer findPropWithSameContract( @Param("id") Integer currentContId );

	@Query(value = "select nextval('{h-schema}property_prop_id_seq')", nativeQuery = true)
	public Long getNextVal();
}
