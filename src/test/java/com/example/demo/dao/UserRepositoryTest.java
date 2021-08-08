package com.example.demo.dao;

import com.example.demo.domain.Course;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findUsersNotAssignedToCourse() {

        Set<Course> courses = new HashSet<>();
        courses.add(new Course(1l));

        courseRepository.save(new Course(1L));

        userRepository.save(new User(123l, "username", courses));

        assertNotNull(userRepository.findUsersNotAssignedToCourse(1L));

    }


}