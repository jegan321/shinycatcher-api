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
import com.shinycatcher.api.util.Base64Encoder;
import com.shinycatcher.api.util.PasswordEncoder;
import com.shinycatcher.api.util.PasswordEncoder.EncodingResult;

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
		return findUserByName(userName).toDto();
	}
	
	public User findUserByName(String userName) {
		try {
			return userDao.findByUserName(userName);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException();
		}
	}
	
	public void postUser(UserDto userDto) {
		User user = new User(userDto);
		EncodingResult result = PasswordEncoder.encode(userDto.userPassword);
		String base64Password = Base64Encoder.encode(result.password);
		System.out.println("pass: " + base64Password);
		String base64Salt = Base64Encoder.encode(result.salt);
		System.out.println("salt: " + base64Salt);
		user.userPassword = base64Password;
		user.salt = base64Salt;
		userDao.insert(user);
	}
	
	public void putUser(UserDto userDto) {
		userDao.update(new User(userDto));
	}
	
	public void deleteUser(Long id) {
		User user = new User();
		user.userId = id;
		userDao.delete(user);
	}
	
	public void putUserPassword(String newPassword, String userName) {
		userDao.updatePassword(newPassword, userName);
	}
	
}
