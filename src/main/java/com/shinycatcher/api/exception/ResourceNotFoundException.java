package com.shinycatcher.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Throwing this exception will return a 404 error to the browser
 * 
 * @author eganj
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such resource")
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
