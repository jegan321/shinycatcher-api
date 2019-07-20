package com.shinycatcher.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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

import com.shinycatcher.api.dto.EntryDto;
import com.shinycatcher.api.dto.UserDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class EntriesIntegrationTest {
	
	@Autowired
	EntriesController entriesController;
	
	@Autowired
	UsersController usersController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void testEntryCrudOperations() {
    	
    	// Create a user for testing
    	postUser();
    	
    	// Add an entry
    	EntryDto firstEntry = new EntryDto();
    	firstEntry.ballId = 1L;
    	firstEntry.captureMethodId = 1L;
    	firstEntry.pokemonId = 1L;
    	firstEntry.pokedexId = 1L;
    	firstEntry.nickname = "First Entry";
    	String firstEntryUrl = postEntry(firstEntry);
    	
    	// Get entries for the user
    	List<EntryDto> entries = getEntries();
    	assertThat(entries.size()).isEqualTo(1);
    	
    	// Check the first entry is the one we inserted
    	assertThat(entries.get(0).nickname).isEqualTo("First Entry");
    	
    	// Add some more entries
    	EntryDto second = new EntryDto();
    	second.ballId = 1L;
    	second.captureMethodId = 1L;
    	second.pokemonId = 1L;
    	second.pokedexId = 1L;
    	second.nickname = "Second Entry";
    	String secondUrl = postEntry(second);
    	EntryDto third = new EntryDto();
    	third.ballId = 1L;
    	third.captureMethodId = 1L;
    	third.pokemonId = 1L;
    	third.pokedexId = 1L;
    	second.nickname = "Third Entry";
    	String thirdUrl = postEntry(third);

    	// Make sure the user has 3 now
    	entries = getEntries();
    	assertThat(entries.size()).isEqualTo(3);
    	
    	// Delete second and third
    	deleteEntry(secondUrl);
    	deleteEntry(thirdUrl);
    	
    	// Assert size is 1 again
    	entries = getEntries();
    	assertThat(entries.size()).isEqualTo(1);
    	
    	// Update the first one
    	firstEntry.nickname = "First Entry UPDATED";
    	putEntry(firstEntryUrl, firstEntry);
    	
    	// Make sure the nickname is updated
    	entries = getEntries();
    	assertThat(entries.get(0).nickname).isEqualTo("First Entry UPDATED");
    	
    }
    
    private void postUser() {  
    	UserDto user = new UserDto(null, "test_user", "test_user@gmail.com", "Basic", "password");
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:"+port+"/users", 
				HttpMethod.POST, new HttpEntity<UserDto>(user), String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    private String postEntry(EntryDto entry) {
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:"+port+"/users/test_user/entries", 
				HttpMethod.POST, new HttpEntity<EntryDto>(entry), String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		return responseEntity.getHeaders().get("location").get(0);
    }
    
    private void putEntry(String url, EntryDto entry) {
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<EntryDto>(entry), String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
    
    private void deleteEntry(String url) {
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    private List<EntryDto> getEntries() {
		UserDto user = restTemplate.getForObject("http://localhost:"+port+"/users/test_user", UserDto.class);
		return user.entries;
    }

}
