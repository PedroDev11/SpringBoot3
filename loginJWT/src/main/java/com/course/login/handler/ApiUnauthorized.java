package com.course.login.handler;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnauthorized extends Exception {

	private static final long serialVersionUID = 1L;

	public ApiUnauthorized(String message) {
		super(message);
	}

}
