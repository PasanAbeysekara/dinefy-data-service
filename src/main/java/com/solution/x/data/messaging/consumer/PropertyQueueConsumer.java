package com.solution.x.data.messaging.consumer;

import com.solution.x.dao.Property;
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
