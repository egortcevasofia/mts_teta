package com.example.demo.dto;

import com.example.demo.domain.Course;
import com.example.demo.domain.CourseHitsory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Mapper(componentModel = "spring")
public interface CourseMapper {

    //@Mapping(target = "course", source = "course.id")
    //@Mapping(target = "id", ignore = true)
    //CourseHitsory courseToCourseHistory(Course course, LocalDateTime dateTime);

    CourseDto courseToCourseDto(Course course);
    Course courseDtoToCourse(CourseDto courseDto);

}
