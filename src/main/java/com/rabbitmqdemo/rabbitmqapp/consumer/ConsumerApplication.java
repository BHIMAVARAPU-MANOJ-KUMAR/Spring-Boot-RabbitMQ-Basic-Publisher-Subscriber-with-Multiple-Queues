package com.rabbitmqdemo.rabbitmqapp.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);
	
	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
	public void consumeMessage(String message) {
		LOGGER.info(String.format("Message Received - < %s >", message));
	}
}
