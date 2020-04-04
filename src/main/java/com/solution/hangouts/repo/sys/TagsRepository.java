package com.solution.hangouts.repo.sys;

import com.solution.hangouts.dao.sys.Facilities;
import com.solution.hangouts.dao.sys.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tags,Integer>
{
}
