package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotPossibleDeleteException;
import com.example.demo.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.common.Constant.RoleName.ROLE_STUDENT;

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
        User userForDelete = userRepository.findById(id).orElseThrow(NotFoundException::new);

        if (!userForDelete.getCourses().isEmpty()) {
            throw new NotPossibleDeleteException();
        } else {
            userRepository.deleteById(id);
        }
    }

    public User saveStudent(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(new User(userDto.getId(),
                userDto.getUsername(),
                encoder.encode(userDto.getPassword()),
                new HashSet<Role>(Collections.singletonList(roleService.getByName(ROLE_STUDENT)))
        ));
    }

    public List<User> findUsersNotAssignedToCourse(Long courseId) {
        return userRepository.findUsersNotAssignedToCourse(courseId);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(NotFoundException::new);
    }

    public User updateUser(UserDto userDto) {
        if (!userRepository.existsById(userDto.getId())) {
            throw new NotFoundException();
        }

        return userRepository.save(new User(userDto.getId(),
                userDto.getUsername(),
                encoder.encode(userDto.getPassword()),
                userDto.getRoles()
        ));

    }

}

