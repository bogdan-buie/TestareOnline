package com.example.testareonline.mappers;


import com.example.testareonline.dto.SignUpDto;
import com.example.testareonline.dto.UserDto;
import com.example.testareonline.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
