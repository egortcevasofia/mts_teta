package com.example.demo.controller;

import com.example.demo.domain.Course;
import com.example.demo.exception.InternalServerError;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.CourseCoverStorageService;
import com.example.demo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/courseprofile")
public class CourseImageController {


    private final CourseCoverStorageService courseCoverStorageService;
    private final CourseService courseService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public CourseImageController(CourseCoverStorageService courseCoverStorageService, CourseService courseService) {
        this.courseCoverStorageService = courseCoverStorageService;
        this.courseService = courseService;
    }


    @PostMapping("/cover")
    public String updateCoverImage(Course course,
                                   @RequestParam("cover") MultipartFile cover) {
        logger.info("File name {}, file content type {}, file size {}", cover.getOriginalFilename(), cover.getContentType(), cover.getSize());
        try {
            courseCoverStorageService.save(course.getId(), cover.getContentType(), cover.getInputStream());
        } catch (Exception ex) {
            logger.info("", ex);
            throw new InternalServerError();
        }
        return String.format("redirect:/course/%d", course.getId());
    }

    @GetMapping("/cover/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> avatarImage(@PathVariable("id") Long id) {
        String contentType = courseCoverStorageService.getContentTypeByCourseId(id)
                .orElseThrow(NotFoundException::new);
        byte[] data = courseCoverStorageService.getCourseImageByCourseId(id)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(data);
    }

    @ExceptionHandler
    public ResponseEntity<Void> notFoundExceptionHandler(NotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
