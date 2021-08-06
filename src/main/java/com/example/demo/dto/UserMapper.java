package com.example.demo.dto;

import com.example.demo.domain.Lesson;
import com.example.demo.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User UserDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
