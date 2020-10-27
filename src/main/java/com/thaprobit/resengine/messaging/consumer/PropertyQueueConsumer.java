package com.thaprobit.resengine.messaging.consumer;

import com.thaprobit.resengine.dao.Property;
import org.springframework.stereotype.Component;

/**
 * @author Tharinda Wickramaarachchi
 */
@Component
public class PropertyQueueConsumer
{
	//@RabbitListener(queues = "queue-properties")
	public void consume( Property message )
	{
		System.out.println( "Consumed" + message );
	}
}
