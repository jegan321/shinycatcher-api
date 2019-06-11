package com.shinycatcher.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

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
import org.springframework.test.context.junit4.SpringRunner;

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
	public void test() throws JSONException {
		
		// Create user
		postUser(new UserDto(null, "jegan", "john@gmail.com"));
		
		// Get user
		UserDto user = getUserByUserName("jegan");
		
		// Test create worked
		assertThat(user.userName).isEqualTo("jegan");
		assertThat(user.userEmail).isEqualTo("john@gmail.com");
		assertThat(user.userId).isNotNull();

		// Update user
		putUser(new UserDto(user.userId, "jegan2", "john2@gmail.com"));
		
		// Test update worked
		user = getUserByUserName("jegan2");
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
		restTemplate.exchange("http://localhost:"+port+"/users", HttpMethod.POST, new HttpEntity<UserDto>(user), String.class);
	}
	
	private UserDto getUserByUserName(String userName) {
		UserDto[] response = restTemplate.getForObject("http://localhost:" + port + "/users?userName="+userName, UserDto[].class);
		assertThat(response.length).isEqualTo(1);
		return response[0];
	}
	
	private void assertUserDoesntExist(String userName) {
		UserDto[] response = restTemplate.getForObject("http://localhost:" + port + "/users?userName="+userName, UserDto[].class);
		assertThat(response.length).isEqualTo(0);
	}
	
	private void putUser(UserDto user) {
		restTemplate.exchange("http://localhost:"+port+"/users/"+user.userId, HttpMethod.PUT, new HttpEntity<UserDto>(user), String.class);
	}
	
	private void deleteUser(Long userId) {
		restTemplate.delete("http://localhost:"+port+"/users/"+userId);
	}

}
