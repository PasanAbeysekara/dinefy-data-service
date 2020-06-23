package com.solution.x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Tharinda Wickramaarachchi
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ReservationEngine
{
	public static void main( String[] args )
	{
		SpringApplication.run( ReservationEngine.class, args );
	}
}
