package com.solution.hangouts.repo;

import com.solution.hangouts.dao.Contract;
import com.solution.hangouts.dao.key.ContractID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface ContractsRepository extends JpaRepository<Contract, ContractID>
{
}
