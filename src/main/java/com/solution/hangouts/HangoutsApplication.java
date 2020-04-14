package com.solution.hangouts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Tharinda Wickramaarachchi
 */
@SpringBootApplication
@EnableScheduling
public class HangoutsApplication
{
	public static void main( String[] args )
	{
		SpringApplication.run( HangoutsApplication.class, args );
	}
}
