package com.solution.hangouts.messaging.consumer;

import com.solution.hangouts.dao.Property;
import org.springframework.stereotype.Component;

@Component
public class PropertyQueueConsumer
{
	//@RabbitListener(queues = "queue-properties")
	public void consume( Property message )
	{
		System.out.println( "Consumed" + message );
	}
}
