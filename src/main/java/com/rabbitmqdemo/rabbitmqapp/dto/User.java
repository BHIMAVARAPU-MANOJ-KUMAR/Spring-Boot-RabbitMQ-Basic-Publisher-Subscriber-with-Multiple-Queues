package com.rabbitmqdemo.rabbitmqapp.dto;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String firstName;
	private String lastName;
}