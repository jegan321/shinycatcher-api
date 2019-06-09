package com.shinycatcher.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinycatcher.api.dto.UserDto;
import com.shinycatcher.api.dto.UsersDto;
import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.exception.ResourceNotFoundException;
import com.shinycatcher.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UsersDto getUsers(String userName) {
		UsersDto usersDto = new UsersDto();
		Iterable<User> users;
		if (StringUtils.isNotBlank(userName)) {
			users = userRepository.findByUserName(userName);
		} else {
			users = userRepository.findAll();
		}
		for (User user : users) {
			usersDto.users.add(user.toDto());
		}
		return usersDto;
	}
	
	public UserDto getUser(Long id) {
		return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new).toDto();
	}
	
	public void postUser(UserDto userDto) {
		userRepository.save(new User(userDto));
	}
	
	public void putUser(UserDto userDto) {
		userRepository.save(new User(userDto));
	}
	
	public void deleteUser(UserDto userDto) {
		userRepository.delete(new User(userDto));
	}

}
