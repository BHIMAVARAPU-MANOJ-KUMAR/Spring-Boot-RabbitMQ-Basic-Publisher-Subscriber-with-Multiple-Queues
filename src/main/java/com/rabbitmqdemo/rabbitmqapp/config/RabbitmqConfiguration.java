package com.rabbitmqdemo.rabbitmqapp.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {

	@Value("${rabbitmq.queue.name}")
	private String queueName;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	
	@Value("${rabbitmq.routing.key}")
	private String routingKey;
	
	@Value("${rabbitmq.queue.json.name}")
	private String jsonQueueName;
	
	@Value("${rabbitmq.json.routing.key}")
	private String jsonRoutingKey;
	
    @Bean
    Queue queue() {
		return new Queue(queueName);
	}
    
    @Bean
    Queue jsonQueue() {
    	return new Queue(jsonQueueName);
    }
    
    @Bean
    TopicExchange topicExchange() {
    	return new TopicExchange(exchangeName);
    }
    
    @Bean
    Binding binding() {
    	return BindingBuilder.bind(queue()).to(topicExchange())
    			.with(routingKey);
    }
    
    @Bean
    Binding jsonBinding() {
    	return BindingBuilder.bind(jsonQueue()).to(topicExchange())
    			.with(jsonRoutingKey);
    }
    
    @Bean
	MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
    
    @Bean
	AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}