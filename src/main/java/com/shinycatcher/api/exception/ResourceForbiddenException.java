package com.shinycatcher.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Throwing this exception will return a 403 error to the browser
 * 
 * @author eganj
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Forbidden")
public class ResourceForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceForbiddenException(String message) {
		super(message);
	}

}
