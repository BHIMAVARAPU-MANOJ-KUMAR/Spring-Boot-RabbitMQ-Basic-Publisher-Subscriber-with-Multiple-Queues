package com.rabbitmqdemo.rabbitmqapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmqdemo.rabbitmqapp.dto.User;
import com.rabbitmqdemo.rabbitmqapp.publisher.JsonPublisherApplication;
import com.rabbitmqdemo.rabbitmqapp.publisher.PublisherApplication;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
	
	private final PublisherApplication publisherApplication;
	
	private final JsonPublisherApplication jsonPublisherApplication;
	
	public MessageController(PublisherApplication application,
			JsonPublisherApplication application2) {
		this.publisherApplication=application;
		this.jsonPublisherApplication=application2;
	}
	
	@GetMapping("/notify")
	public ResponseEntity<String> sendMessage(@RequestParam(name = "message") String message) {
		publisherApplication.publishMessage(message);
		return ResponseEntity.ok("Message Successfully Sent.");
	}
	
	@PostMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestBody User user) {
		jsonPublisherApplication.publishJsonMessage(user);
		return ResponseEntity.ok("Message Successfully Sent.");
	}
}