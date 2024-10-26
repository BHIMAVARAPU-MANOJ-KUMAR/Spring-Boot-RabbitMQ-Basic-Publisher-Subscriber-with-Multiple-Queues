package com.rabbitmqdemo.rabbitmqapp.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmqdemo.rabbitmqapp.dto.User;

@Service
public class JsonConsumerApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsumerApplication.class);
	
	@RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
	public void consumeMessage(User user) {
		LOGGER.info(String.format("Message Received - < %s >", user.toString()));
	}
}