package com.cursos.spring_security_course.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cursos.spring_security_course.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
public class SecurityBeansInjector {
	@Autowired
	private UserRepository userRepository;
	
	// Injeccion a traves de parametros
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		// Se crea especificamente el provider manager que implementa la interfaz AuthenticationManager
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	// Injectar el AuthenticationProvider
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		// Con ese username me tengo que conectar a la base de datos
		// Ya no marca error por el el UserEntity implementa de UserDetails por lo cual esta expresion lambda regresa un
		// UserDetailsService o UserDetails
		return username -> {
			return userRepository.findByUsername(username)
					.orElseThrow(() -> new RuntimeException("User not found"));
		};
	}
}
