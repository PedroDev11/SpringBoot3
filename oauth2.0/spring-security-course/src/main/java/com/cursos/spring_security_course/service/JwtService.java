package com.cursos.spring_security_course.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cursos.spring_security_course.entity.User;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiration-minutes}")
	private Long EXPIRATION_MINUTES;
	
	@Value("${security.jwt.secret-key}")
	private String SECRET_KEY;

	public String generateToken(User user, Map<String, Object> extraClaims) {
		
		Date issuedAt = new Date(System.currentTimeMillis());
		
		// Lo mutiplicamos por 60 obtenemos los segundos y si lo multiplicamos los 1000 obtenemos los milisegundos
		Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));
		
		// Crea un constructor, es importante que pongamos los extraClaims
		// El compact, genera el token como tal
		return Jwts.builder()
			.setClaims(extraClaims)
			.setSubject(user.getName())
			.setIssuedAt(issuedAt)
			.setExpiration(expiration)
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.signWith(generateKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	// Usamos import java.security.Key para no enviar el string de la clave secreta como tal. El objeto Key equivale a mi clave
	// pero como HASH es decir, encriptada con un algoritmo de una sola via.
	private Key generateKey() {
		// DEcodificacion de una cadena base64
		byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
		
		return Keys.hmacShaKeyFor(secretAsBytes);
	}

    public String extractSubject(String jwt) {
		return extractAllClaims(jwt)
				.getSubject();
    }

	private Claims extractAllClaims(String jwt) {
		// Obtenemos cada una de las secciones del jwt.  parse()
		// setSigningKey -> Valida que el jwt tenga un formato correcto, valida que la fecha actual sea
		// menor a la fecha de expiracion y valida que la firma recibida sea valida
		// build -> regresa el jwt parser
		// parseClaimsJws -> si el token contiene las 3 partes (Header, Payload, Sign) utilizamos ese
		// metodo, y esto nos devuleve las 3 partes

		return Jwts.parser()
				.setSigningKey(generateKey())
				.build()
				.parseClaimsJws(jwt)
				.getBody();
	}
}
