package com.solution.hangouts.messaging.producer;

public interface QueueMessageProducer<T>
{
	public void produceMessage( T message );
}
