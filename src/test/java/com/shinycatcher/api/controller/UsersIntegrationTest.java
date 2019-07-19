package com.shinycatcher.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.json.JSONException;
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
public class UsersIntegrationTest {
	
	@Autowired
	UsersController usersController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
	@Test
	public void testUserCrudOperations() throws JSONException {
		
		// Create user
		postUser(new UserDto(null, "jegan", "john@gmail.com", "Basic", "password"));
		
		// Get user
		UserDto user = getUser("jegan");
		
		// Test create worked
		assertThat(user.userName).isEqualTo("jegan");
		assertThat(user.userEmail).isEqualTo("john@gmail.com");
		assertThat(user.userId).isNotNull();

		// Update user
		putUser(new UserDto(user.userId, "jegan2", "john2@gmail.com", "Basic", "password"));
		
		// Test update worked
		user = getUser("jegan2");
		assertThat(user.userName).isEqualTo("jegan2");
		assertThat(user.userEmail).isEqualTo("john2@gmail.com");
		assertThat(user.userId).isNotNull();
		assertUserDoesntExist("jegan");
		
		// Delete user
		deleteUser(user.userId);
		
		// Test delete worked
		assertUserDoesntExist("jegan");
		assertUserDoesntExist("jegan2");
	}
	
	private void postUser(UserDto user) {
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:"+port+"/users", 
				HttpMethod.POST, new HttpEntity<UserDto>(user), String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	private UserDto getUser(String userName) {
		return restTemplate.getForObject("http://localhost:" + port + "/users/"+userName, UserDto.class);
	}
	
	private void assertUserDoesntExist(String userName) {
		UserDto dto = restTemplate.getForObject("http://localhost:" + port + "/users/"+userName, UserDto.class);
		assertNull(dto.userName);
	}
	
	private void putUser(UserDto user) {
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:"+port+"/users/"+user.userId, 
				HttpMethod.PUT, new HttpEntity<UserDto>(user), String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	private void deleteUser(Long userId) {
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:"+port+"/users/"+userId, 
				HttpMethod.DELETE, null, String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
