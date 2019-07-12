package com.shinycatcher.api.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinycatcher.api.dao.UserDao;
import com.shinycatcher.api.dto.SessionDto;
import com.shinycatcher.api.dto.UserCredentialsDto;
import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.util.Base64Encoder;
import com.shinycatcher.api.util.PasswordEncoder;

@Service
public class SessionService {
	
	@Autowired
	UserDao userDao;
	
	public SessionDto createSession(UserCredentialsDto userCredentials) {
		User user = userDao.findByUserName(userCredentials.userName);
		String storedPasswordBase64 = user.userPassword;
		String storedSaltBase64 = user.salt;
		byte[] storedPassword = Base64Encoder.decodeAsBytes(storedPasswordBase64);
		byte[] storedSalt = Base64Encoder.decodeAsBytes(storedSaltBase64);
		if (PasswordEncoder.compare(userCredentials.userPassword, storedSalt, storedPassword)) {
			System.out.println("LOGIN SUCCESS");
		} else {
			System.out.println("LOGIN FAIL");
		}
		return null;
	}

}
