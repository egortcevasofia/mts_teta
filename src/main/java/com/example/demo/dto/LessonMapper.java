package com.example.demo.dto;

import com.example.demo.domain.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface LessonMapper {


    @Mapping(target = "description", source = "text")
    Lesson lessonDtoToLesson(LessonDto lessonDto);

    @Mapping(target = "text", source = "description")
    LessonDto lessonToLessonDTO(Lesson lesson);
}
