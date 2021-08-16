package com.example.demo.service;

import com.example.demo.dao.CourseCoverRepository;
import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.CourseImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.*;


@Service
public class CourseCoverStorageService {
    private static final Logger logger = LoggerFactory.getLogger(AvatarStorageService.class);
    private final CourseCoverRepository courseCoverRepository;
    private final CourseRepository courseRepository;

    @Value("${file.storage.path}")
    private String path;

    @Autowired
    public CourseCoverStorageService(CourseCoverRepository courseCoverRepository, CourseRepository courseRepository) {
        this.courseCoverRepository = courseCoverRepository;
        this.courseRepository = courseRepository;
    }


    @Transactional
    public void save(Long id, String contentType, InputStream is) {
        Optional<CourseImage> opt = courseCoverRepository.findByCourse_Id(id);
        CourseImage courseImage;
        String filename;
        if (opt.isEmpty()) {
            filename = UUID.randomUUID().toString();
            Course course = courseRepository.findById(id)
                    .orElseThrow(IllegalArgumentException::new);
            courseImage = new CourseImage(null, contentType, filename, course);
        } else {
            courseImage = opt.get();
            filename = courseImage.getFilename();
            courseImage.setContentType(contentType);
        }
        courseCoverRepository.save(courseImage);

        try (OutputStream os = Files.newOutputStream(Path.of(path, filename), CREATE, WRITE, TRUNCATE_EXISTING)) {
            is.transferTo(os);
        } catch (Exception ex) {
            logger.error("Can't write to file {}", filename, ex);
            throw new IllegalStateException(ex);
        }
    }

    public Optional<String> getContentTypeByCourseId(Long id) {
        return courseCoverRepository.findByCourse_Id(id)
                .map(CourseImage::getContentType);
    }

    public Optional<byte[]> getCourseImageByCourseId(Long id) {
        return courseCoverRepository.findByCourse_Id(id)
                .map(CourseImage::getFilename)
                .map(filename -> {
                    try {
                        return Files.readAllBytes(Path.of(path, filename));
                    } catch (IOException ex) {
                        logger.error("Can't read file {}", filename, ex);
                        throw new IllegalStateException(ex);
                    }
                });
    }
}
