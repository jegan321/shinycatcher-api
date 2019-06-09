package com.shinycatcher.api.dto;

public class UserDto {

	public Long id;
	public String userName;
	public String userEmail;
	
	public UserDto() {}
	
	public UserDto(Long id, String userName, String userEmail) {
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;
	}
	
}
