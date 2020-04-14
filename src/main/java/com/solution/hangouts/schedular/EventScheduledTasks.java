package com.solution.hangouts.schedular;

import com.solution.hangouts.messaging.producer.PropertyQueueProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tharinda Wickramaarachchi
 */

@Component
@Slf4j
public class EventScheduledTasks
{
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat( "HH:mm:ss" );

	@Autowired
	private PropertyQueueProducer queueProducer;

	@Scheduled(fixedRate = 60000)
	public void reportCurrentTime()
	{
		//queueProducer.produceTextMessage( "The time is now " + dateFormat.format( new Date() ) );
		log.info( "The time is now {}", dateFormat.format( new Date() ) );
	}


}
