package com.example.demo.service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private final PasswordEncoder encoder;
    private RoleService roleService;

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

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public void saveStudent(UserDto userDto) {
        userRepository.save(new User(userDto.getId(),
                userDto.getUsername(),
                encoder.encode(userDto.getPassword()),
                new HashSet<Role>(Collections.singletonList(roleService.getByName("ROLE_STUDENT")))
        ));
    }

    public List<User> findUsersNotAssignedToCourse(Long courseId){
        return userRepository.findUsersNotAssignedToCourse(courseId);
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username).orElseThrow(NotFoundException::new);
    }




}
