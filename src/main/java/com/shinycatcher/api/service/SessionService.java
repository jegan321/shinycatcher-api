package com.shinycatcher.api.service;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinycatcher.api.dao.UserDao;
import com.shinycatcher.api.dto.SessionDto;
import com.shinycatcher.api.dto.UserCredentialsDto;
import com.shinycatcher.api.entity.User;
import com.shinycatcher.api.exception.ResourceForbiddenException;
import com.shinycatcher.api.exception.ResourceUnauthorizedException;
import com.shinycatcher.api.util.Base64Encoder;
import com.shinycatcher.api.util.PasswordEncoder;
import com.shinycatcher.api.util.TokenGenerator;

@Service
public class SessionService {

	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;
	
	public SessionDto createSession(UserCredentialsDto userCredentials) {
		User storedUser = userService.findUserByName(userCredentials.userName);
		if (credentialsAreValid(userCredentials, storedUser)) {
			String token = TokenGenerator.generateRandomToken();
			userDao.updateSessionToken(token, System.currentTimeMillis(), userCredentials.userName);
			Long issuedTime = new DateTime().getMillis();
			return new SessionDto(token, issuedTime);
		} else {
			throw new ResourceForbiddenException("Invalid user credentials");
		}
	}
	
	private boolean credentialsAreValid(UserCredentialsDto userCredentials, User storedUser) {
		String storedPasswordBase64 = storedUser.userPassword;
		String storedSaltBase64 = storedUser.salt;
		byte[] storedPassword = Base64Encoder.decodeAsBytes(storedPasswordBase64);
		byte[] storedSalt = Base64Encoder.decodeAsBytes(storedSaltBase64);
		return PasswordEncoder.compare(userCredentials.userPassword, storedSalt, storedPassword);
	}
	
	public void validateSessionToken(String authorizationHeader, String storedToken, Long storedIssuedTime) {
		String inputToken = parseToken(authorizationHeader);
		if (!StringUtils.equals(storedToken, inputToken)) {
			throw new ResourceForbiddenException("Invalid session token");
		}
		DateTime expirationTime = new DateTime(storedIssuedTime).plusSeconds(10);
		DateTime now = new DateTime();
		if (now.isAfter(expirationTime)) {
			throw new ResourceUnauthorizedException("Session expired");
		}
	}
	
	private String parseToken(String authorizationHeader) {
		return StringUtils.substringAfter(authorizationHeader, "Bearer ");
	}
	
}
