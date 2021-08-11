package com.example.demo.controller;

import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import com.example.demo.service.CourseService;
import com.example.demo.service.LessonService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;

import static com.example.demo.common.Constant.RoleName.ROLE_ADMIN;


@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final LessonService lessonService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);



    @Autowired
    public CourseController(CourseService courseService, LessonService lessonService, UserService userService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @GetMapping
    public String courseTable(HttpSession session, Model model, @RequestParam(name = "titlePrefix", required = false) String titlePrefix, Principal principal) {
        if (principal != null) {
            logger.info("Request from user '{}'", principal.getName());
        }
        if(titlePrefix == null) titlePrefix = "";
        model.addAttribute("courses", courseService.findByTitleLike(titlePrefix + "%"));
        model.addAttribute("activePage", "courses");
        return "courses";
    }




    @Transactional
    @GetMapping("/{id}")
    public String courseForm(Model model, @PathVariable("id") Long id) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        model.addAttribute("lessons",lessonService.findAllForLessonIdWithoutText(id));
        model.addAttribute("users",course.getUsers());
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

    @Secured(ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.delete(id);
        return "redirect:/course";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/assign")
    public String assignUserForm(Model model, HttpServletRequest request, @PathVariable("id") Long id) {
        model.addAttribute("courseId", id);
        if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("users", userService.findUsersNotAssignedToCourse(id));
        } else {
            User user = userService.findUserByUsername(request.getRemoteUser());
            model.addAttribute("users", Collections.singletonList(user));
        }
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

    // TODO: 29.07.2021 заменить всех юзеров на юзердто


}
