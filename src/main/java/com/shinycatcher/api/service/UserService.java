package com.shinycatcher.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	
	public List<UserDto> getUsers(String userName) {
		List<UserDto> userDtos = new ArrayList<>();
		Iterable<User> users;
		if (StringUtils.isNotBlank(userName)) {
			users = userDao.findByUserName(userName);
		} else {
			users = userDao.findAll();
		}
		for (User user : users) {
			userDtos.add(user.toDto());
		}
		return userDtos;
	}
	
	public UserDto getUser(Long id) {
		try {
			return userDao.findById(id).toDto();
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
