package com.shinycatcher.api.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

	public Long userId;
	public String userName;
	public String userEmail;
	public String userStatus;
	public String userPassword;
	public List<EntryDto> entries = new ArrayList<>();
	
	public UserDto() {}
	
	public UserDto(Long userId, String userName, String userEmail, String userStatus) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userStatus = userStatus;
	}
	
}
