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
    	String firstEntryId = postEntry(firstEntry);
    	
//    	// Get entries for the user
//    	EntryDto[] entries = getEntries();
//    	assertThat(entries.length).isEqualTo(1);
//    	
//    	// Add some more entries
//    	EntryDto second = new EntryDto();
//    	second.ballId = 1L;
//    	second.captureMethodId = 1L;
//    	second.pokemonPokedexId = 1L;
//    	postEntry(second);
//    	EntryDto third = new EntryDto();
//    	third.ballId = 1L;
//    	third.captureMethodId = 1L;
//    	third.pokemonPokedexId = 1L;
//    	postEntry(third);
//
//    	// Make sure the user has 3 now
//    	entries = getEntries();
//    	assertThat(entries.length).isEqualTo(1);
//    	
//    	// Update the first one
//    	firstEntry.ballId = 2L;
//    	putEntry(firstEntry);
    	
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
		String location = responseEntity.getHeaders().get("location").get(0);
		return getEndOfUrl(location);
    }
    
    private void putEntry(EntryDto entry) {
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:"+port+"/users/test_user/entries/"+entry.entryId, 
				HttpMethod.PUT, new HttpEntity<EntryDto>(entry), String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    private void deleteEntry(Long entryId) {
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:"+port+"/users/test_user/entries/"+entryId, 
				HttpMethod.DELETE, null, String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    private EntryDto[] getEntries() {
		return restTemplate.getForObject("http://localhost:"+port+"/users/test_user/entries", EntryDto[].class);
    }
    
    private String getEndOfUrl(String url) {
    	String[] split = url.split("/");
    	return split[split.length-1];
    }

}
