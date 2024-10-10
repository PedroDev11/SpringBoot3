package com.cursos.spring_security_course.config.security;

import com.cursos.spring_security_course.config.security.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.cursos.spring_security_course.util.Permission;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {
	// Lo que se va a injectar es el DaoAuthenticationProvider
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	// Configuracion basada en metodos seguros (EnableMethodSecurity)
	// mediante anotaciones en los controladores o en los repositorios siempre sea un componente spring
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
			//.authorizeHttpRequests(buildRequestMatchers());
			
		return http.build();
	}

	private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> buildRequestMatchers() {
		// csrf es una vulnerabilidad web, consiste en agarrar la sesion de un usuario y hace que un usuario haga acciones que
		// el no quiere realizar, acciones involuntarias.
		// Lo segundo, es que esta app no maneje sesiones, sino que maneje una sesion sin estado, ya que cada peticion que hagamos
		// va a ser autonoma e independiente gracias a nuestro token.
		// Como funciona el hasAuthority. Por debajo spring security lo que va hacer es obtener el usuario logeado (UserEntity)
		// y va a mandar a llamar su método getAuthorities y este devuleve la lista de authorities y en base a eso va a comparar
		// si tiene relacionado el permiso.

		// Registramos el filtro antes, porque los filtros tienen un orden y al final se
		// esta ejecutando el AuthorizationFilter y nos tenemos que asegurar que antes que pase este
		// filtro, se ejecute el JwtAuthFilter. El primer valor que le tenemos que pasar es el filtro
		// que vamos a agregar y luego le tengo que pasar la clase de la cual antes se tiene que ejecutar
		return authConfig -> {
			authConfig.requestMatchers(HttpMethod.POST, "/core/api/v1/publico/login").permitAll();
			authConfig.requestMatchers(HttpMethod.GET, "/core/api/v1/publico/public").permitAll();
			authConfig.requestMatchers("/error").permitAll();

			authConfig.requestMatchers(HttpMethod.GET, "/core/api/v1/publico/getAll").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
			authConfig.requestMatchers(HttpMethod.POST, "/core/api/v1/publico/addProduct").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());

			// authConfig.anyRequest().denyAll();
		};
	}
}
