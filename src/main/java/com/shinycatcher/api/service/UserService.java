package com.shinycatcher.api.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.exception.ResourceNotFoundException;
import com.shinycatcher.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public Iterable<User> getUsers(String userName) {
		if (StringUtils.isNotBlank(userName)) {
			return userRepository.findByUserName(userName);
		} else {
			return userRepository.findAll();
		}
	}
	
	public User getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(ResourceNotFoundException :: new);
	}
	
	public void postUser(User user) {
		userRepository.save(user);
	}
	
	public void putUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

}
