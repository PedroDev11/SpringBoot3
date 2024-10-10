package com.pepe.jbos.mapper;

import com.pepe.jbos.dto.UserDTO;
import com.pepe.jbos.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(UserEntity userEntity);
    List<UserDTO> toListUserDTO(List<UserEntity> userEntities);
    UserEntity toUserEntity(UserDTO userDTO);
}
