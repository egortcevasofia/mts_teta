package com.example.demo.dto;

import com.example.demo.domain.Course;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@Component
@Mapper(componentModel = "spring")
public interface CourseMapper {


    CourseDto courseToCourseDto(Course course);
    Course courseDtoToCourse(CourseDto courseDto);

}
