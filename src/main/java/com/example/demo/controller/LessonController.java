package com.example.demo.controller;

import com.example.demo.domain.Course;
import com.example.demo.domain.Lesson;
import com.example.demo.dto.CourseMapper;
import com.example.demo.dto.LessonDto;
import com.example.demo.dto.LessonMapper;
import com.example.demo.service.CourseService;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/lesson")
public class LessonController {
    private LessonService lessonService;
    private LessonMapper lessonMapper;
    private CourseService courseService;
    private CourseMapper courseMapper;


    @Autowired
    public LessonController(LessonService lessonService, LessonMapper lessonMapper, CourseService courseService, CourseMapper courseMapper) {
        this.lessonService = lessonService;
        this.lessonMapper = lessonMapper;
        this.courseService = courseService;
        this.courseMapper = courseMapper;

    }

    @GetMapping("/new")
    public String lessonForm(Model model, @RequestParam("course_id") long courseId) {
        model.addAttribute("lesson", new LessonDto(courseId));
        model.addAttribute("courseId", courseId);
        return "lesson_form";
    }

    @GetMapping("/{id}")
    public String lessonForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("lesson", lessonMapper.lessonToLessonDTO(lessonService.findById(id)));
        return "lesson_form";
    }

    @PostMapping
    public String submitLessonForm(LessonDto lessonDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "lesson_form";
        }
        Course course = courseService.findById(lessonDto.getCourseId());
        Lesson lesson = lessonMapper.lessonDtoToLesson(lessonDto);
        lesson.setCourse(course);
        lessonService.save(lesson);
        return String.format("redirect:/course/%d", lessonDto.getCourseId());
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(Model model, @PathVariable("id") Long id) {
        Course course = lessonService.findById(id).getCourse();
        lessonService.deleteLessonById(id);
        return String.format("redirect:/course/%d", course.getId());
    }
}



