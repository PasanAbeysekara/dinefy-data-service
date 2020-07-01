package com.solution.x.data.messaging.producer;

/**
 * @author Tharinda Wickramaarachchi
 */
public interface QueueMessageProducer<T>
{
	public void produceMessage( T message );
}
