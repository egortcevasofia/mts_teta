package com.example.demo.controller;

import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import com.example.demo.service.CourseService;
import com.example.demo.service.LessonService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final LessonService lessonService;
    private final UserService userService;


    @Autowired
    public CourseController(CourseService courseService, LessonService lessonService, UserService userService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @GetMapping
    public String courseTable(Model model, @RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        if(titlePrefix == null) titlePrefix = "";
        model.addAttribute("courses", courseService.findByTitleLike(titlePrefix + "%"));
        model.addAttribute("activePage", "courses");
        return "courses";
    }




    @Transactional
    @RequestMapping("/{id}")
    public String courseForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("course", courseService.findById(id));
        model.addAttribute("lessons",lessonService.findAllForLessonIdWithoutText(id));
        model.addAttribute("users",courseService.findById(id).getUsers());
        return "course_form";
    }


    @PostMapping
    public String submitCourseForm(@Valid Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "course_form";
        }
        courseService.save(course);
        return "redirect:/course";
    }

    @GetMapping("/new")
    public String courseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course_form";
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.delete(id);
        return "redirect:/course";
    }

    @GetMapping("/{courseId}/assign")
    public String assignCourse(Model model, @PathVariable Long courseId) {
        model.addAttribute("users", userService.findUsersNotAssignedToCourse(courseId));
        return "assign_course";
    }

    @PostMapping("/{courseId}/assign")
    public String assignUserForm(@PathVariable("courseId") Long courseId,
                                 @RequestParam("userId") Long id) {
        User user = userService.findUserById(id);
        Course course = courseService.findById(courseId);
        course.getUsers().add(user);
        user.getCourses().add(course);
        courseService.save(course);
        return "redirect:/course/{courseId}";
    }


    @PostMapping("/{courseId}/remove/{userId}")
    public String removeUserForm(@PathVariable("courseId") Long courseId,
                                 @PathVariable("userId") Long id) {
        User user = userService.findUserById(id);
        Course course = courseService.findById(courseId);
        courseService.removeUserFromCourse(user,course);
        return "redirect:/course/{courseId}";
    }


}
