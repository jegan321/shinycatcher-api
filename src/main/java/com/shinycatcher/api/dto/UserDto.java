package com.shinycatcher.api.dto;

public class UserDto {

	public Long userId;
	public String userName;
	public String userEmail;
	
	public UserDto() {}
	
	public UserDto(Long userId, String userName, String userEmail) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
	}
	
}
