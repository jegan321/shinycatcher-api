package com.shinycatcher.api.entity;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	private Long id;
	public String userName;
	public String userEmail;

}
