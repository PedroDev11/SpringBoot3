package com.pepe.jwt.backend.service;

import com.pepe.jwt.backend.dto.CredentialsDTO;
import com.pepe.jwt.backend.dto.UserDTO;
import com.pepe.jwt.backend.dto.signUpDTO;
import com.pepe.jwt.backend.entities.User;
import com.pepe.jwt.backend.exceptions.AppException;
import com.pepe.jwt.backend.mapper.UserMapper;
import com.pepe.jwt.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserDTO login(CredentialsDTO credentialsDTO) {
        User user = userRepository.findByUsername(credentialsDTO.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        // Compare the given password with the password in the database
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.password()),
                user.getPassword())) {
            // If It's correct, I map the user from the database to a userDTO
            return userMapper.toUserDTO(user);

        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDTO register(signUpDTO signUpDTO) {
        Optional<User> oUser = userRepository.findByUsername(signUpDTO.username());
        if (oUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.singUpToUser(signUpDTO);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.password())));
        User savedUser = userRepository.save(user);

        return userMapper.toUserDTO(savedUser);
    }
}
