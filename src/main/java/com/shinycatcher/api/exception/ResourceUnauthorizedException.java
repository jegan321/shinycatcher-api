package com.shinycatcher.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Throwing this exception will return a 401 error to the browser
 * 
 * @author eganj
 */
@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Unauthorized")
public class ResourceUnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceUnauthorizedException(String message) {
		super(message);
	}

}
