package com.shinycatcher.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.service.UserService;

@RestController
public class UsersController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public Iterable<User> getUsers() {
		return userService.getUsers();
	}
	
	@RequestMapping(value="/users/{userName}", method=RequestMethod.GET)
	public User getUser(@PathVariable String userName) {
		return userService.getUser(userName);
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public void postUser(@RequestBody User user) {
		userService.postUser(user);
	}

}
