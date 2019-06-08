package com.shinycatcher.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.exception.ResourceNotFoundException;
import com.shinycatcher.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User getUser(String userName) {
		Optional<User> user = userRepository.findByUserName(userName);
		return user.orElseThrow(ResourceNotFoundException :: new);
	}
	
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	public void postUser(User user) {
		userRepository.save(user);
	}

}
