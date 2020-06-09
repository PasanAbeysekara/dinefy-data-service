package com.solution.x.repo;

import com.solution.x.dao.WidenPropData;
import com.solution.x.dao.key.WidenDataGridKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/10/2020 12:10 AM
 */
public interface AvailDataRepository extends JpaRepository<WidenPropData, WidenDataGridKey>
{
}
