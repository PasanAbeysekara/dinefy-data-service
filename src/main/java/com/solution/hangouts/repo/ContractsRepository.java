package com.solution.hangouts.repo;

import com.solution.hangouts.dao.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractsRepository extends JpaRepository<Contract, Long>
{
}
