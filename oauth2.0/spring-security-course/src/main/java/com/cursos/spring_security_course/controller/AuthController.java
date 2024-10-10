package com.cursos.spring_security_course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.spring_security_course.dto.AuthRequest;
import com.cursos.spring_security_course.dto.AuthResponse;
import com.cursos.spring_security_course.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${security.jwt.baseUrl}")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PreAuthorize("permitAll")
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
		AuthResponse jwtDTO = authService.login(authRequest);
		return ResponseEntity.ok(jwtDTO);
	}

	@PreAuthorize("permitAll")
	@GetMapping("/public")
	public String publicAccessEndpoint() {
		return "Endpoint publico";
	}
}
