package com.solution.x.controller.service.functionality;

import com.solution.x.dao.Contract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/8/2020 11:42 PM
 */
@Component
@Slf4j
public class PropAvailDataAsyncExecutor
{
	@Autowired
	@Qualifier("async-avail-data")
	private AsyncTaskExecutor asyncTaskExecutor;


	public void executeAsynchronously( Contract contract )
	{
		asyncTaskExecutor.submit( new PropAvailDataFlattener( contract ) );
		log.info( "PropAvailDataFlattener - Submitted for contract " + contract.getContractId() );
	}
}
