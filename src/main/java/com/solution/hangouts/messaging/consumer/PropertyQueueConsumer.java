package com.solution.hangouts.messaging.consumer;

import com.solution.hangouts.dao.PropertyDAO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropertyQueueConsumer
{
	//@RabbitListener(queues = "queue-properties")
	public void consume( PropertyDAO message )
	{
		System.out.println( "Consumed" + message );
	}
}
