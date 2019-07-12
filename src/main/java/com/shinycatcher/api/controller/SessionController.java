package com.shinycatcher.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinycatcher.api.dto.SessionDto;
import com.shinycatcher.api.dto.UserCredentialsDto;
import com.shinycatcher.api.dto.UserDto;
import com.shinycatcher.api.service.SessionService;

@RestController
public class SessionController {
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping(value="/session", method=RequestMethod.POST)
	public SessionDto getUsers(@RequestBody UserCredentialsDto userCredentials) {
		return sessionService.createSession(userCredentials);
	}

}
