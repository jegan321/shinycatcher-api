package com.shinycatcher.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shinycatcher.api.dto.UserDto;
import com.shinycatcher.api.service.UserService;

@RestController
public class UsersController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public Iterable<UserDto> getUsers(@RequestParam String userName) {
		return userService.getUsers(userName);
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public UserDto getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public void postUser(@RequestBody UserDto user) {
		userService.postUser(user);
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
	public void putUser(@RequestBody UserDto user) {
		userService.putUser(user);
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public void deleteUser(@RequestBody UserDto user) {
		userService.deleteUser(user);
	}

}
