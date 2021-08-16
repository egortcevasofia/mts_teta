package com.example.demo.dao;

import com.example.demo.domain.CourseImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseCoverRepository extends JpaRepository<CourseImage, Long> {
    @Query("FROM CourseImage ci WHERE ci.course.id = :id")
    Optional<CourseImage> findByCourse_Id(Long id);
}
