package com.shinycatcher.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinycatcher.api.dto.EntryDto;
import com.shinycatcher.api.service.UserService;

@RestController
public class EntriesController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/users/{userName}/entries", method=RequestMethod.GET)
	public List<EntryDto> getEntries(@PathVariable String userName) {
		return userService.getUser(userName).entries;
	}
	
	@RequestMapping(value="/users/{userName}/entries", method=RequestMethod.POST)
	public List<EntryDto> postEntry(@PathVariable String userName) {
		return userService.getUser(userName).entries;
	}
	

}
