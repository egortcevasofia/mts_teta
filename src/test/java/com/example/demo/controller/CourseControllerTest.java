package com.example.demo.controller;

import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import com.example.demo.dto.LessonDto;
import com.example.demo.service.CourseService;
import com.example.demo.service.LessonService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @MockBean
    private CourseService courseService;
    @MockBean
    private CourseRepository courseRepository;
    @MockBean
    private LessonService lessonService;
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void courseTable() throws Exception {
        ArrayList<Course> actualList = new ArrayList<>();
        when(courseService.findByTitleLike("")).thenReturn(actualList);
        mockMvc.perform(get("/course"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses"))
                .andExpect(model().attribute("courses", actualList));
    }

    @Test
    void courseForm() throws Exception {
        ArrayList<LessonDto> actualLessons = new ArrayList<>();
        actualLessons.add(new LessonDto(1L, "Title", 1L));
        Course actualCourse = new Course(1L, "Vasia", "Java");
        HashSet<User> actualUsers = new HashSet<>();
        actualUsers.add(new User());
        actualCourse.setUsers(actualUsers);

        when(courseService.findById(any(Long.class))).thenReturn(actualCourse);
        when(lessonService.findAllForLessonIdWithoutText(any(Long.class))).thenReturn(actualLessons);

        mockMvc.perform(
                get("/course/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("course_form"));
    }


    @Test
    void submitCourseForm() throws Exception {
        Course actualCourse = new Course(1L, "Vasia", "Java");

        mockMvc.perform(post("/course")
                .with(csrf())
                .flashAttr("course", actualCourse))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/course"))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    void testCourseForm() throws Exception {
        mockMvc.perform(get("/course/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("course_form"));
    }


    @Test
    void deleteCourse() throws Exception {
        doNothing().when(courseService).delete(anyInt());
        mockMvc.perform(delete("/course/{id}", 1L)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course"));
        Mockito.verify(courseService, Mockito.times(1)).delete(1L);
    }


    @Test
    void removeUserForm() throws Exception {
        Course actualCourse = new Course();
        doNothing().when(courseService).removeUserFromCourse(any(), any());

        mockMvc.perform(post("/course/{courseId}/remove/{userId}", 1, 1)
                .with(csrf())
                .flashAttr("course", actualCourse))
                .andExpect(redirectedUrl("/course/1"))
                .andExpect(status().is3xxRedirection());
    }
}