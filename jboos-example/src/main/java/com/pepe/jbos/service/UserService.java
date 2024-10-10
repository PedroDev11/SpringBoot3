package com.pepe.jbos.service;

import com.pepe.jbos.dto.UserDTO;
import com.pepe.jbos.entity.UserEntity;
import com.pepe.jbos.handler.AppException;
import com.pepe.jbos.handler.DatabaseException;
import com.pepe.jbos.mapper.UserMapper;
import com.pepe.jbos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private UserEntity findUser(int idUser) {
        Optional<UserEntity> result = userRepository.findById(idUser);
        UserEntity usuario;

        if (result.isPresent()) {
            usuario = result.get();
        } else {
            throw new AppException("Usuario con el id: " + idUser + ", no encontrado", HttpStatus.NOT_FOUND);
        }
        return usuario;
    }

    private void saveUser(UserEntity userEntity) {
        try {
            userRepository.save(userEntity);
        } catch(DataIntegrityViolationException e) {
            throw new DatabaseException("No pueden existir datos repetidor");
        }
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> usersEntities = userRepository.findAll();
        return userMapper.toListUserDTO(usersEntities);
    }

    public UserDTO getUser(int idUser) {
        UserEntity userEntity = findUser(idUser);
        return userMapper.toUserDTO(userEntity);
    }

    public String addUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toUserEntity(userDTO);
        saveUser(userEntity);
        return "Usuario guardado correctamente";
    }

    public UserDTO updateUser(int idUser, UserDTO userDTO) {
        UserEntity userEntity = findUser(idUser);
        userEntity = userMapper.toUserEntity(userDTO);
        saveUser(userEntity);
        return userMapper.toUserDTO(userEntity);
    }

    public String deleteUser(int idUser) {
        UserEntity usuarioEntity = findUser(idUser);
        userRepository.delete(usuarioEntity);
        return "Usuario eliminado correctamente";
    }
}
