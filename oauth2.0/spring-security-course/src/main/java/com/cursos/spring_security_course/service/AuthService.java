package com.cursos.spring_security_course.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.cursos.spring_security_course.dto.AuthRequest;
import com.cursos.spring_security_course.dto.AuthResponse;
import com.cursos.spring_security_course.entity.User;
import com.cursos.spring_security_course.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;

	public AuthResponse login(@Valid AuthRequest authRequest) {
		// Recibe el username y password y en base a eso hace el login
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				authRequest.getUsername(), authRequest.getPassword()
		);
		
		// El que se injecta aca es el provider manager, se le manda un objeto de tipo authentication
		authenticationManager.authenticate(authToken);
		
		// Obtenemos al usuario de la base de datos para crear el jwt
		// Omitimos le metodo orElseThrow, debido a que en la linea anterior si no encuentra al usuario, regresa un error, pero
		// Si es que si encuentra al usuario, es por que si existe y obtenemos todos sus datos
		User user = userRepository.findByUsername(authRequest.getUsername()).get();
		// .orElseThrow(() -> new RuntimeException("User not found"));
		
		String jwt = jwtService.generateToken(user, generateExtraClaims(user));
		
		return new AuthResponse(jwt);
	}

	private Map<String, Object> generateExtraClaims(User user) {
		// el rol del usuario y el nombre, claims adicionales
		Map<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("name", user.getName());
		extraClaims.put("role", user.getRole().name());
		extraClaims.put("permissions", user.getAuthorities());
		
		return extraClaims;		
	}
	
}
