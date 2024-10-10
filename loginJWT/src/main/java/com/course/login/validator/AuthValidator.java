package com.course.login.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.course.login.handler.ApiUnauthorized;

@Component
public class AuthValidator {

	private static final String CLIENT_CREDENTIALS = "client_credentials";

	public void validate(MultiValueMap<String, String> paramMap, String grantType) throws ApiUnauthorized {
		if (grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIALS)) {
			message("El campo grant_type es invalido");
		}

		// paramMap == null
		if (Objects.isNull(paramMap) || paramMap.getFirst("client_id").isEmpty()
				|| paramMap.getFirst("client_secret").isEmpty()) {
			message("client_id y/o client_secret son invalidos");
		}
	}

	private void message(String message) throws ApiUnauthorized {
		throw new ApiUnauthorized(message);
	}

}
