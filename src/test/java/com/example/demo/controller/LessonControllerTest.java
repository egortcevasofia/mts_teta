package com.example.demo.controller;

import com.example.demo.MappersTestConfig;
import com.example.demo.domain.Course;
import com.example.demo.domain.Lesson;
import com.example.demo.dto.*;
import com.example.demo.service.CourseService;
import com.example.demo.service.LessonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(LessonController.class)
@ContextHierarchy({
        @ContextConfiguration(classes = MappersTestConfig.class)
})
class LessonControllerTest {

    @MockBean
    private LessonService lessonService;

    @MockBean
    private CourseService courseService;
    @MockBean
    private CourseMapper courseMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void lessonForm() throws Exception {
        mockMvc.perform(
                get("/lesson/new")
                        .param("course_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("lesson_form"));
    }


    @Test
    void testLessonForm() throws Exception {
        LessonDto actualLesson = new LessonDto(1L, "Java");
        Lesson lesson = new Lesson(1L, "Java");
        when(lessonService.findById(any())).thenReturn(lesson);
        mockMvc.perform(get("/lesson/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("lesson_form"))
                .andExpect(model().attribute("lesson", actualLesson));
    }


    @Test
    void submitLessonForm() throws Exception {
        LessonDto actualLesson = new LessonDto(1L, "Title", "Text", 1L);

        when(courseService.findById(any(Long.class))).thenReturn(new Course());
        mockMvc.perform(post("/lesson")
                .with(csrf())
                .flashAttr("lessonDto", actualLesson))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/course/1"));
    }


    @Test
    void deleteLesson() throws Exception {
        Lesson actualLesson = new Lesson(1L, "title", "description", new Course(1L, "title", "Vasia"));
        when(lessonService.findById(any())).thenReturn(actualLesson);
        doNothing().when(lessonService).deleteLessonById(any());
        mockMvc.perform(delete("/lesson/{id}", 1L)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course/1"));
        Mockito.verify(lessonService, Mockito.times(1)).deleteLessonById(1L);
    }

}