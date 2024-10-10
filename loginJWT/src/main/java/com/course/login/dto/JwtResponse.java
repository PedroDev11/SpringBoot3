package com.course.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponse {

	@JsonProperty(value = "token_type")
	private String tokenType;
	
	@JsonProperty(value = "access_token")
	private String accessToken;
	
	@JsonProperty(value = "expires_in")
	private Integer expiresIn;
	
	@JsonProperty(value = "issued_at")
	private String issuedAt;
	
	@JsonProperty(value = "client_id")
	private String clientId;

	public JwtResponse(String tokenType, String accessToken, Integer expiresIn, String issuedAt, String clientId) {
		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.issuedAt = issuedAt;
		this.clientId = clientId;
	}
}
