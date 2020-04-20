package com.solution.hangouts.repo.sys;

import com.solution.hangouts.dao.sys.WeekDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface WeekDefinitionRepository extends JpaRepository<WeekDefinition, Short>
{
}
