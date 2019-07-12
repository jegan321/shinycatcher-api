package com.shinycatcher.api.dto;

public class SessionDto {
	
	public String sessionToken;
	public Long sessionTokenExpiration;
	
	public SessionDto(String sessionToken, Long sessionTokenExpiration) {
		this.sessionToken = sessionToken;
		this.sessionTokenExpiration = sessionTokenExpiration;
	}

}
