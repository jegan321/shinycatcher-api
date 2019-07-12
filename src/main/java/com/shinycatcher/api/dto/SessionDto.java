package com.shinycatcher.api.dto;

public class SessionDto {
	
	public String sessionToken;
	public Long sessionTokenIssuedTime;
	
	public SessionDto(String sessionToken, Long sessionTokenIssuedTime) {
		this.sessionToken = sessionToken;
		this.sessionTokenIssuedTime = sessionTokenIssuedTime;
	}

}
