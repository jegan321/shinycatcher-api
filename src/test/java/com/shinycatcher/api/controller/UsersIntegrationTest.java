package com.shinycatcher.api.controller;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.shinycatcher.api.dto.UserDto;
import com.shinycatcher.api.dto.UsersDto;
import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.repository.UserRepository;

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
		String url = "http://localhost:" + port;
		
		// Create user
		UserDto user = new UserDto(null, "jegan", "john@gmail.com");
		restTemplate.exchange(url + "/users", HttpMethod.POST, new HttpEntity<UserDto>(user), String.class);
		
		// Get user
		UsersDto response = restTemplate.getForObject("http://localhost:" + port + "/users?userName=jegan", UsersDto.class);
		assertThat(response.users.size()).isEqualTo(1);
		assertThat(response.users.get(0).userName).isEqualTo("jegan");
		assertThat(response.users.get(0).userEmail).isEqualTo("john@gmail.com");
		assertThat(response.users.get(0).id).isNotNull();
		Long userId = response.users.get(0).id;

		// Update user
		UserDto updatedUser = new UserDto(userId, "jegan2", "john2@gmail.com");
		restTemplate.exchange(url + "/users/"+userId, HttpMethod.PUT, new HttpEntity<UserDto>(updatedUser), String.class);
		response = restTemplate.getForObject("http://localhost:" + port + "/users?userName=jegan2", UsersDto.class);
		assertThat(response.users.size()).isEqualTo(1);
		assertThat(response.users.get(0).userName).isEqualTo("jegan2");
		assertThat(response.users.get(0).userEmail).isEqualTo("john2@gmail.com");
		assertThat(response.users.get(0).id).isNotNull();
	}

}
