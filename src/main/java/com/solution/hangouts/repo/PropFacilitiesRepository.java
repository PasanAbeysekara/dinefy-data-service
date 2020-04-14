package com.solution.hangouts.repo;

import com.solution.hangouts.dao.PropFacilities;
import com.solution.hangouts.dao.key.PropFacilityID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface PropFacilitiesRepository extends JpaRepository<PropFacilities, PropFacilityID>
{
	List<PropFacilities> findByPropFacilityIDPropId( @Param("propId") int propId );
}
