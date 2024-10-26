package com.rabbitmqdemo.rabbitmqapp.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmqdemo.rabbitmqapp.dto.User;

@Service
public class JsonPublisherApplication {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JsonPublisherApplication.class);
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.json.routing.key}")
	private String jsonRoutingKey;
	
	private final RabbitTemplate rabbitTemplate;
	
	private JsonPublisherApplication(RabbitTemplate template) {
		this.rabbitTemplate=template;
	}
	
	public void publishJsonMessage(User user) {
		LOGGER.info(String.format("Message sent - < %s >", user.toString()));
		rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
	}
}