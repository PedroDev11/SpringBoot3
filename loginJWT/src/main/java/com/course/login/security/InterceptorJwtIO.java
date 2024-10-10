package com.course.login.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InterceptorJwtIO implements HandlerInterceptor {
	
	@Autowired
	private JwtIO jwtIO;
	
	@Value("${jms.jwt.token.auth.path}")
	private String AUTH_PATH;
	
	// Lo separa por comas que encuentre y con eso creamos nuestra lista de rutas excluidas
	@Value("#{'${jms.jwt.excluded.path}'.split(',')}")
	private List<String> excludedList;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean validate = false;
		
		String uri = request.getRequestURI();
		
		// Debemos validar si tiene o no tiene permisos para ingresar por que puede estar exlcuida
		// Con esto sabemos que si tiene esta ruta tiene permisos
		// Con esto, si el primero es true o el segundo es true entonces lo que me esta llegando tiene permisos y no hay 
		// que validar el token y va a poder seguir adelante
		if(uri.equals(AUTH_PATH) || excluded(uri)) {
			validate = true;
		} 
		
		if(!validate && request.getHeader("Authorization") != null && !request.getHeader("Authorization").isEmpty()) {
			String token = request.getHeader("Authorization").replace("Bearer", "");
			
			validate = !jwtIO.validateToken(token);
		}
		
		if(!validate) {
			// 401
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		
		return validate;
	}
	
	// Validamos que la ruta exista dentro de la lista excluded
	private boolean excluded(String path) {
		boolean result = false;
		
		
		// El "#" nos va a servir para nosotros saber si en este caso se tiene o no se tiene datos por defecto
		for(String pathExcluded: excludedList) {
			if(!pathExcluded.equals("#") && pathExcluded.equals(path)) {
				result = true;
			}
		}
		return result;
	}
}
