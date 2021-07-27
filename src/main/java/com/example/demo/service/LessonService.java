package com.example.demo.service;

import com.example.demo.dao.LessonRepository;
import com.example.demo.domain.Lesson;
import com.example.demo.dto.LessonDto;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LessonService {
    private LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository repository) {
        this.lessonRepository = repository;
    }


    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<LessonDto> findAllForLessonIdWithoutText(Long id) {
        return lessonRepository.findAllForLessonIdWithoutText(id);
    }

    public void deleteLessonById(Long id) {
        lessonRepository.deleteById(id);
    }


}
