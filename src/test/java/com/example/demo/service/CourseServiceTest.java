package com.example.demo.service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseServiceTest {

    private CourseRepository courseRepositoryMock;
    private CourseService courseService;


    @BeforeAll
    public void setUp() {
        courseRepositoryMock = Mockito.mock(CourseRepository.class);
        courseService = new CourseService(courseRepositoryMock);
    }

    @AfterEach
    public void resetDb() {
        courseRepositoryMock.deleteAll();
    }

    @Test
    void findByTitleLike() {
        courseService.findByTitleLike("Вася");
        Mockito.verify(courseRepositoryMock, Mockito.times(1)).findByTitleLike("Вася%");
    }

    @Test
    void findById() {
        when(courseRepositoryMock.findById(1L))
                .thenReturn(Optional.of(new Course(1L, "Вася", "Java")));
        assertEquals("Вася", courseService.findById(1L).getAuthor());
    }

    @Test
    void save() {
        when(courseRepositoryMock.save(any(Course.class)))
                .thenReturn(new Course(1L, "Вася", "Java"));

        Course actualCourse = courseService.save(new Course(1L, "Вася", "Java"));
        assertEquals("Вася", actualCourse.getAuthor());

    }

    @Test
    void delete() {
        doNothing().when(courseRepositoryMock).deleteById(1L);
        courseService.delete(1L);
        verify(courseRepositoryMock, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void removeUserFromCourse() {
        Set<Course> courses = new HashSet<>();
        Set<User> users = new HashSet<>();
        User user = new User(1L, "Вася", courses);
        Course course = new Course(1L, "Вася", users);
        courses.add(course);
        users.add(user);
        courseService.removeUserFromCourse(user, course);
        assertEquals(0, user.getCourses().size());
        assertEquals(0, course.getUsers().size());
    }


}
