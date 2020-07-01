package com.solution.x.data.controller.service.functionality;

import com.solution.x.dao.Contract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	private ApplicationContext applicationContext;


	//TODO make it work : Its
	public void executeAsynchronously( Contract contract )
	{

		PropAvailDataExploder dataFlattener = new PropAvailDataExploder();
		dataFlattener.setContract( contract );
		applicationContext.getAutowireCapableBeanFactory().autowireBean( dataFlattener );

		asyncTaskExecutor.submit( dataFlattener );

		log.info( "PropAvailDataFlattener - Submitted for contract " + contract.getContractId() );
	}

	@Async("async-avail-data")
	@Transactional
	public void executeAsynchronouslyTx( Contract contract )
	{
		PropAvailDataExploder dataFlattener = new PropAvailDataExploder();
		dataFlattener.setContract( contract );

		applicationContext.getAutowireCapableBeanFactory().autowireBean( dataFlattener );

		try
		{
			dataFlattener.call();//TODO remove : Transactional issue , Need to use above method
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
