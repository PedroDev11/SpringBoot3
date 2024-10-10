package com.course.login.security;

import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.course.login.util.GsonUtils;

import io.fusionauth.jwt.JWTUtils;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;

@Component
public class JwtIO {
	
	@Value("${jms.jwt.token.secret:secret}")
	private String SECRET;
	
	@Value("${jms.jwt.timezone:UTC}")
	private String TIMEZONE;
	
	@Value("${jms.jwt.token.expires-in:3600}")
	private int EXPIRES_IN;
	
	@Value("${jms.jwt.issuer:none}")
	private String ISSUER;
	
	public String generateToken(Object src) {
		// Serealizamos el objeto que nos pasen por parametro
		String subject = GsonUtils.serialize(src);
		
		// Generar la firma utilizando SHA-256
		Signer signer = HMACSigner.newSHA256Signer(SECRET);
		
		// Fecha de exp
		TimeZone tz = TimeZone.getTimeZone(TIMEZONE);
		ZonedDateTime zonedDateTime = ZonedDateTime.now(tz.toZoneId()).plusSeconds(EXPIRES_IN);
		
		// Generacion del token
		JWT jwt = new JWT()
				.setIssuer(ISSUER)
				.setIssuedAt(ZonedDateTime.now(tz.toZoneId()))
				.setSubject(subject)
				.setExpiration(zonedDateTime);
		
		return JWT.getEncoder().encode(jwt, signer);
	}
	
	public boolean validateToken(String token) {
		boolean result = false;
		
		
		try {
			JWT subject = decodeToken(token);
			result = subject.isExpired();
			
		} catch (Exception e) {
			result = true;
		}
		
		// Esto lo que nos indica que si ya expiro retorna false
		return result;
	}
	
	public String getPayload(String token) {
		JWT subject = decodeToken(token);
		// Contiene lo que nosotros serealizamos con el Gson
		return subject.subject;
	}
	
	private JWT decodeToken(String encodedToken) {
		/* Verifier verifier = HMACVerifier.newVerifier(SECRET);
		return JWT.getDecoder().decode(encodedToken, verifier); */
		JWT jwt = JWTUtils.decodePayload(encodedToken);
		return jwt;
	}
}
