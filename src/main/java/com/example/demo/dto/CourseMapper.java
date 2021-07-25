package com.example.demo.dto;

import com.example.demo.domain.Course;
import com.example.demo.domain.CourseHitsory;

import java.time.LocalDateTime;

public interface CourseMapper {

    //@Mapping(target = "course", source = "course.id")
    //@Mapping(target = "id", ignore = true)
    CourseHitsory courseToCourseHistory(Course course, LocalDateTime dateTime);
}
