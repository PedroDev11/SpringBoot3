package com.pepe.jwt.backend.mapper;

import com.pepe.jwt.backend.dto.UserDTO;
import com.pepe.jwt.backend.dto.signUpDTO;
import com.pepe.jwt.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    // Ignore the password because it has different format, it has a char array in the signup dto and a string
    // in the user object
    @Mapping(target = "password", ignore = true)
    User singUpToUser(signUpDTO signUpDTO);
}

