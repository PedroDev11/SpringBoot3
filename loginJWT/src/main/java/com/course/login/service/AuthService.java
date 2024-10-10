package com.course.login.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.course.login.dto.JwtResponse;
import com.course.login.dto.UsuarioDTO;
import com.course.login.security.JwtIO;
import com.course.login.util.DateUtils;

@Service
public class AuthService {
	
	@Autowired
	private JwtIO jwtIO;
	
	@Autowired
	private DateUtils dateUtils;
	
	@Value("${jms.jwt.token.expires-in:3600}")
	private int EXPIRES_IN;

	public JwtResponse login(String clientId, String clientSecret) {
		
		UUID uid = UUID.randomUUID();
		UsuarioDTO usuarioDTO = new UsuarioDTO(uid.toString(), "Pedro", "Rojas", "ADMIN", "Mexico");
		
		JwtResponse jwt = new JwtResponse("bearer", jwtIO.generateToken(usuarioDTO), EXPIRES_IN, dateUtils.getDateMillis() + "", clientId);
		return jwt;
	}
}
