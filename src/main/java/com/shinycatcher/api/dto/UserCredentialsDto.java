package com.shinycatcher.api.dto;

public class UserCredentialsDto {
	
	public String userName;
	public String userPassword;
	
	public UserCredentialsDto() {}
	
	public UserCredentialsDto(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}

}
