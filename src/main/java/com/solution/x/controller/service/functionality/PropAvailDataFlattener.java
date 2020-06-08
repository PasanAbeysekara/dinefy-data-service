package com.solution.x.controller.service.functionality;

import com.solution.x.dao.Contract;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.concurrent.Callable;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/6/2020 12:04 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Component
@Slf4j
public class PropAvailDataFlattener implements Callable<String>
{
	private Contract contract;


	@Override
	public String call() throws Exception
	{
		Short bookableHorizon = contract.getBookableHorizon();

		Date validFrom = contract.getValidFrom();

		log.info( "@@@@@@@@@@@@@@@@2222222222222222222222222 2222222222222222222222  222222222222222@@@@@@@@@@" );
		log.info( "@@@@@@@@@@@@@@@@2222222222222222222222222 2222222222222222222222  222222222222222@@@@@@@@@@" );
		log.info( "@@@@@@@@@@@@@@@@2222222222222222222222222 2222222222222222222222  222222222222222@@@@@@@@@@" );
		log.info( "@@@@@@@@@@@@@@@@2222222222222222222222222 2222222222222222222222  222222222222222@@@@@@@@@@" );
		log.info( "@@@@@@@@@@@@@@@@2222222222222222222222222 2222222222222222222222  222222222222222@@@@@@@@@@" );


		return null;
	}
}
