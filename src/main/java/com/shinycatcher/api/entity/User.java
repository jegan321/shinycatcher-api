package com.shinycatcher.api.entity;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class User {
	
	@Id
	private Long id;
	public String userName;
	public String userEmail;

}
