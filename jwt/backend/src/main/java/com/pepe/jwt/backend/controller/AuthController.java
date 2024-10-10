package com.pepe.jwt.backend.controller;

import com.pepe.jwt.backend.config.UserAuthProvider;
import com.pepe.jwt.backend.dto.CredentialsDTO;
import com.pepe.jwt.backend.dto.UserDTO;
import com.pepe.jwt.backend.dto.signUpDTO;
import com.pepe.jwt.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody CredentialsDTO credentialsDTO) {
        UserDTO user = userService.login(credentialsDTO);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody signUpDTO signUpDTO) {
        UserDTO user = userService.register(signUpDTO);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
