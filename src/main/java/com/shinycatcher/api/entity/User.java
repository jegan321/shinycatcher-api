package com.shinycatcher.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.shinycatcher.api.dto.UserDto;

public class User {
	
	public Long userId;
	public String userName;
	public String userEmail;
	public String userStatus;
	public String userPassword;
	public String salt;
	public String sessionToken;
	public Long sessionTokenIssuedTime;
	public List<Entry> entries = new ArrayList<>();
	
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
		for (Entry entry : entries) {
			dto.entries.add(entry.toDto());
		}
		return dto;
	}
	

}
