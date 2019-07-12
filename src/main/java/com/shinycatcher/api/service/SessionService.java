package com.shinycatcher.api.service;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.shinycatcher.api.dao.UserDao;
import com.shinycatcher.api.dto.SessionDto;
import com.shinycatcher.api.dto.UserCredentialsDto;
import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.exception.ResourceForbiddenException;
import com.shinycatcher.api.exception.ResourceNotFoundException;
import com.shinycatcher.api.util.Base64Encoder;
import com.shinycatcher.api.util.PasswordEncoder;
import com.shinycatcher.api.util.TokenGenerator;

@Service
public class SessionService {
	
	@Autowired
	UserDao userDao;
	
	public SessionDto createSession(UserCredentialsDto userCredentials) {
		User storedUser = findStoredUser(userCredentials.userName);
		if (credentialsAreValid(userCredentials, storedUser)) {
			String token = TokenGenerator.generateRandomToken();
			userDao.updateSessionToken(token, userCredentials.userName);
			Long expiration = new DateTime().plusSeconds(5).getMillis();
			return new SessionDto(token, expiration);
		} else {
			throw new ResourceForbiddenException("Invalid user credentials");
		}
	}
	
	private User findStoredUser(String userName) {
		try {
			return userDao.findByUserName(userName);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException();
		}
	}
	
	private boolean credentialsAreValid(UserCredentialsDto userCredentials, User storedUser) {
		String storedPasswordBase64 = storedUser.userPassword;
		String storedSaltBase64 = storedUser.salt;
		byte[] storedPassword = Base64Encoder.decodeAsBytes(storedPasswordBase64);
		byte[] storedSalt = Base64Encoder.decodeAsBytes(storedSaltBase64);
		return PasswordEncoder.compare(userCredentials.userPassword, storedSalt, storedPassword);
	}
	
}
