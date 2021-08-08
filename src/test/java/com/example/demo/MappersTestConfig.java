package com.example.demo;

import com.example.demo.dto.LessonMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MappersTestConfig {

    @Bean
    public LessonMapper lessonMapper() {
        return Mappers.getMapper(LessonMapper.class);
    }
}
