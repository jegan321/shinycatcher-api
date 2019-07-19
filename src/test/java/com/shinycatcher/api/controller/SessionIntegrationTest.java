package com.shinycatcher.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.shinycatcher.api.dto.SessionDto;
import com.shinycatcher.api.dto.UserCredentialsDto;
import com.shinycatcher.api.dto.UserDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class SessionIntegrationTest {
	
	@Autowired
	UsersController usersController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
	
	//TODO: finish
	@Test
	public void testSessions() {
		
		// Create user
		postUser(new UserDto(null, "auth_user", "auth_user@gmail.com", "Basic", "password"));
		
		postSession("auth_user", "password");
		
	}
	
	private void postUser(UserDto user) {
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:"+port+"/users", 
				HttpMethod.POST, new HttpEntity<UserDto>(user), String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	private SessionDto postSession(String userName, String userPassword) {
		UserCredentialsDto userCredentials = new UserCredentialsDto(userName, userPassword);
		return restTemplate.exchange("http://localhost:"+port+"/session", 
				HttpMethod.POST, new HttpEntity<UserCredentialsDto>(userCredentials), SessionDto.class).getBody();
	}

}
