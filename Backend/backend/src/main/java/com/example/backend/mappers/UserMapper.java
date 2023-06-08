package com.example.backend.mappers;

import com.example.backend.DTOs.UserDTO;
import com.example.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target="profile_name", source="userPostDTO.profile_name")
    @Mapping(target="surname", source="userPostDTO.surname")
    @Mapping(target="phone", source="userPostDTO.phone")
    @Mapping(target="email", source="userPostDTO.email")
    @Mapping(target="password", source="userPostDTO.password")
    User toUser(UserDTO userDTO);
}
