package com.shinycatcher.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinycatcher.api.dto.EntryDto;
import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.service.EntryService;
import com.shinycatcher.api.service.SessionService;
import com.shinycatcher.api.service.UserService;

@RestController
public class EntriesController {
	
	@Autowired
	UserService userService;
	@Autowired
	EntryService entryService;
	@Autowired
	SessionService sessionService;
	
	@RequestMapping(value="/users/{userName}/entries", method=RequestMethod.GET)
	public List<EntryDto> getEntries(@PathVariable String userName) {
		return userService.getUser(userName).entries;
	}
	
	@RequestMapping(value="/users/{userName}/entries", method=RequestMethod.POST)
	public void postEntry(@PathVariable String userName) {
		//TODO: create entry for the user with given userName
	}
	
	@RequestMapping(value="/users/{userName}/entries", method=RequestMethod.PUT)
	public void putEntry(@PathVariable String userName) {
		//TODO: update entry for the user with given userName
	}
	
	@RequestMapping(value="/users/{userName}/entries", method=RequestMethod.DELETE)
	public void deleteEntry(@RequestHeader("Authorization") String authorization, @PathVariable String userName) {
		//TODO: delete entry for the user with given userName
		
		User user = userService.findUserByName(userName);
		sessionService.validateSessionToken(authorization, user.sessionToken, user.sessionTokenIssuedTime);
	}

}
