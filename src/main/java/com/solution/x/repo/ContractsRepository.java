package com.solution.x.repo;

import com.solution.x.dao.Contract;
import com.solution.x.dao.key.ContractID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface ContractsRepository extends JpaRepository<Contract, ContractID>
{
}
