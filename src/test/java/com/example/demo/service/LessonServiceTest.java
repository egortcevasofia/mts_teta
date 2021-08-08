package com.example.demo.service;

import com.example.demo.dao.LessonRepository;
import com.example.demo.domain.Lesson;
import com.example.demo.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LessonServiceTest {
    private LessonService lessonService;
    private LessonRepository lessonRepositoryMock;


    @BeforeAll
    public void setUp() {
        lessonRepositoryMock = Mockito.mock(LessonRepository.class);
        lessonService = new LessonService(lessonRepositoryMock);
    }

    @AfterEach
    public void resetDb() {
        lessonRepositoryMock.deleteAll();
    }

    @Test
    void save() {
        Lesson lesson = new Lesson();
        lessonService.save(lesson);
        Mockito.verify(lessonRepositoryMock, Mockito.times(1)).save(lesson);
    }

    @Test
    void findById() {
        when(lessonRepositoryMock.findById(1L))
                .thenReturn(Optional.of(new Lesson(1L, "Java")));
        assertEquals("Java", lessonService.findById(1L).getTitle());
        assertThrows(NotFoundException.class, () -> lessonService.findById(2L));
    }

    @Test
    void findAllForLessonIdWithoutText() {
        lessonService.findAllForLessonIdWithoutText(1L);
        Mockito.verify(lessonRepositoryMock, Mockito.times(1)).findAllForLessonIdWithoutText(1L);
    }

    @Test
    void deleteLessonById() {
        lessonService.deleteLessonById(1L);
        Mockito.verify(lessonRepositoryMock, Mockito.times(1)).deleteById(1L);
    }
}