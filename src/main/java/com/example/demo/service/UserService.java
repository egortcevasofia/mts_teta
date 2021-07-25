package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsersNotAssignedToCourse(Long courseId){
        return userRepository.findUsersNotAssignedToCourse(courseId);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
