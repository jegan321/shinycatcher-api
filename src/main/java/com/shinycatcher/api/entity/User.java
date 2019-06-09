package com.shinycatcher.api.entity;

import org.springframework.data.annotation.Id;

import com.shinycatcher.api.dto.UserDto;

public class User {
	
	@Id
	public Long id;
	public String userName;
	public String userEmail;
	
	public User() {}
	
	public User(UserDto dto) {
		this.id = dto.id;
		this.userName = dto.userName;
		this.userEmail = dto.userEmail;
	}
	
	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.id = id;
		dto.userName = userName;
		dto.userEmail = userEmail;
		return dto;
	}

}
