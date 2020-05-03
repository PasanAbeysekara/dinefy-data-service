package com.solution.x.repo;

import com.solution.x.dao.PropFacilities;
import com.solution.x.dao.key.PropFacilityID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface PropFacilitiesRepository extends JpaRepository<PropFacilities, PropFacilityID>
{
	List<PropFacilities> findByPropFacilityIdPropId( @Param("propId") int propId );
}
