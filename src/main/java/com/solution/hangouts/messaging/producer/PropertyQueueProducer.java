package com.solution.hangouts.messaging.producer;

import com.solution.hangouts.dao.PropertyDAO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyQueueProducer implements QueueMessageProducer<PropertyDAO>
{
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${rabbit.queue.properties.name}")
	private String queueName;

	@Override
	public void produceMessage( PropertyDAO message )
	{
		System.out.println( "Queue Name detected  " + queueName );
		rabbitTemplate.convertAndSend( queueName, message );
	}


	public void produceTextMessage( String message )
	{
		System.out.println( "Queue Name detected  " + queueName );
		rabbitTemplate.convertAndSend( queueName, message );
	}
}
