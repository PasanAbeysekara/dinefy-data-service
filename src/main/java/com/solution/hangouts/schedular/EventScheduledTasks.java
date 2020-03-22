package com.solution.hangouts.schedular;

import com.solution.hangouts.messaging.producer.PropertyQueueProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class EventScheduledTasks
{
	private static final Logger log = LoggerFactory.getLogger( EventScheduledTasks.class );

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat( "HH:mm:ss" );

	@Autowired
	private PropertyQueueProducer queueProducer;

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime()
	{
		queueProducer.produceTextMessage( "The time is now " + dateFormat.format( new Date() ) );
		log.info( "The time is now {}", dateFormat.format( new Date() ) );
	}


}
