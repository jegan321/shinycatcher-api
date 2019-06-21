package com.shinycatcher.api.dto;

public class UserDto {

	public Long userId;
	public String userName;
	public String userEmail;
	public String userStatus;
	
	public UserDto() {}
	
	public UserDto(Long userId, String userName, String userEmail, String userStatus) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userStatus = userStatus;
	}
	
}
