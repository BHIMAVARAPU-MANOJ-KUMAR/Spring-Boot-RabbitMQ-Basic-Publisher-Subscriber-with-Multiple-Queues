package com.rabbitmqdemo.rabbitmqapp.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublisherApplication {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PublisherApplication.class);
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.key}")
	private String routingkey;
	
	private final RabbitTemplate rabbitTemplate;
	
	public PublisherApplication(RabbitTemplate template) {
		this.rabbitTemplate=template;
	}
	
	public void publishMessage(String message) {
		LOGGER.info(String.format("Message sent - < %s >", message));
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
	}
}