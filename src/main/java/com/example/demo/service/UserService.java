package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotPossibleDeleteException;
import com.example.demo.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleService = roleService;

    }


    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(usr -> new UserDto(usr.getId(), usr.getUsername(), "", usr.getRoles()))
                .collect(Collectors.toList());
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteById(long id) {
        if (!userRepository.findById(id).orElseThrow(NotFoundException::new).getCourses().isEmpty()) {
            throw new NotPossibleDeleteException();
        }
        userRepository.deleteById(id);
    }

    public void saveStudent(UserDto userDto) {
        if (userRepository.findUserByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        userRepository.save(new User(userDto.getId(),
                userDto.getUsername(),
                encoder.encode(userDto.getPassword()),
                new HashSet<Role>(Collections.singletonList(roleService.getByName("ROLE_STUDENT")))
        ));
    }

    public List<User> findUsersNotAssignedToCourse(Long courseId) {
        return userRepository.findUsersNotAssignedToCourse(courseId);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(NotFoundException::new);
    }

    public void updateUser(UserDto userDto) {
        if (userRepository.findById(userDto.getId()).isEmpty()) {
            throw new NotFoundException();
        }

        userRepository.save(new User(userDto.getId(),
                userDto.getUsername(),
                encoder.encode(userDto.getPassword()),
                userDto.getRoles()
        ));

    }


}
