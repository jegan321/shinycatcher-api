package com.shinycatcher.api.entity;

import org.springframework.data.annotation.Id;

import com.shinycatcher.api.dto.UserDto;

public class User {
	
	@Id
	public Long userId;
	public String userName;
	public String userEmail;
	public String userStatus;
	
	public User() {}
	
	public User(UserDto dto) {
		this.userId = dto.userId;
		this.userName = dto.userName;
		this.userEmail = dto.userEmail;
		this.userStatus = dto.userStatus;
	}
	
	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.userId = userId;
		dto.userName = userName;
		dto.userEmail = userEmail;
		dto.userStatus = userStatus;
		return dto;
	}

}
