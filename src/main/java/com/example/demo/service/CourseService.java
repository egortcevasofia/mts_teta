package com.example.demo.service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course>  findByTitleWithPrefix(String prefix) {
       return courseRepository.findByTitleWithPrefix(prefix);
    }

    public Course findById(long id) {
        return courseRepository.
                findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void delete(long id) {
       courseRepository.delete(id);
    }
}
