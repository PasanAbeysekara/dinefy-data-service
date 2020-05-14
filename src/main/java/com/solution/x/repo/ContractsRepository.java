package com.solution.x.repo;

import com.solution.x.dao.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface ContractsRepository extends JpaRepository<Contract, Long>
{
}
