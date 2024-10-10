package com.pepe.jwt.backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pepe.jwt.backend.dto.UserDTO;
import com.pepe.jwt.backend.entities.User;
import com.pepe.jwt.backend.exceptions.AppException;
import com.pepe.jwt.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    @Autowired
    private UserRepository userRepository;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDTO userDTO) {
        Date now = new Date();
        // Corresponds to one hour
        Date validity = new Date(now.getTime() + 3_600_000);

        return JWT.create()
                .withIssuer(userDTO.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("firstName", userDTO.getFirstName())
                .withClaim("lastName", userDTO.getLastName())
                .withClaim("role", "ADMINISTRATOR")
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = verifier.verify(token);

        UserDTO user = new UserDTO(decodedJWT.getIssuer(),
                decodedJWT.getClaim("firstName").asString(),
                decodedJWT.getClaim("lastName").asString());

        User userEntity = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
