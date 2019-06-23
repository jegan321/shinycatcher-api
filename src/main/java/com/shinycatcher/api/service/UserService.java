package com.shinycatcher.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.shinycatcher.api.dao.UserDao;
import com.shinycatcher.api.dto.UserDto;
import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.exception.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public List<UserDto> getUsers() {
		List<UserDto> userDtos = new ArrayList<>();
		Iterable<User> users = userDao.findAll();
		for (User user : users) {
			userDtos.add(user.toDto());
		}
		return userDtos;
	}
	
	public UserDto getUser(String userName) {
		try {
			return userDao.findByUserName(userName).toDto();
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException();
		}
	}
	
	public void postUser(UserDto userDto) {
		userDao.insert(new User(userDto));
	}
	
	public void putUser(UserDto userDto) {
		userDao.update(new User(userDto));
	}
	
	public void deleteUser(Long id) {
		User user = new User();
		user.userId = id;
		userDao.delete(user);
	}

}
