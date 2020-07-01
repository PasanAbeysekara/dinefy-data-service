package com.solution.x.data.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/8/2020 9:24 PM
 */
@Component
@Slf4j
public class ApplicationEventListener
{

	@EventListener
	public void onApplicationEvent( ContextRefreshedEvent event )
	{
		log.info( "**************************** Increment counter ******************************** " );
		log.info( "**************************** Increment counter ******************************** " );
		log.info( "**************************** Increment counter ******************************** " );
		log.info( "**************************** Increment counter ******************************** " );
		log.info( "**************************** Increment counter ******************************** " );
		log.info( "**************************** Increment counter ******************************** " );
	}
}
