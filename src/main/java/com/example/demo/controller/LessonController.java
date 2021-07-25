package com.example.demo.controller;

import com.example.demo.dto.LessonDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    @GetMapping("/new")
    public String lessonForm(Model model, @RequestParam("course_id") long courseId) {
        model.addAttribute("lesson", new LessonDto(courseId));
        model.addAttribute("courseId", courseId);
        return "lesson_form";
    }
}
