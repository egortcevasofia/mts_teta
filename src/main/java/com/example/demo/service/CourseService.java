package com.example.demo.service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.User;
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

    public List<Course>  findByTitleLike(String prefix) {
       return courseRepository.findByTitleLike(prefix + "%" );
    }


    public Course findById(long id) {
        return courseRepository.
                findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void delete(long id) {
       courseRepository.deleteById(id);
    }

    public void removeUserFromCourse(User user, Course course){
        user.getCourses().remove(course);
        course.getUsers().remove(user);
        courseRepository.save(course);
    }
}
