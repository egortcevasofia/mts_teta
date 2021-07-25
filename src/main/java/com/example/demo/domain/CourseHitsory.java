package com.example.demo.domain;

import com.example.demo.annotation.TitleCase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses_history")
public class CourseHitsory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Course course;

    @NotBlank(message = "Course author has to be filled")
    @Column
    private String author;

    @NotBlank(message = "Course title has to be filled")
    @TitleCase
    @Column
    private String title;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    private String description;

    private Integer durationWeeks;

    private BigDecimal avgRating;

    @OneToMany
    private Set<Category> categories;

    @ManyToMany
    private Set<User> users;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private User updatedUser;

    private LocalDateTime updatedTime;

    private Boolean isDeleted;

    private LocalDateTime savedTime;

    public CourseHitsory() {
    }

    public CourseHitsory(Long id, @NotBlank(message = "Course author has to be filled") String author, @NotBlank(message = "Course title has to be filled") String title, List<Lesson> lessons, String description, Integer durationWeeks, BigDecimal avgRating, Set<Category> categories, Set<User> users, User updatedUser, LocalDateTime updatedTime, Boolean isDeleted) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.lessons = lessons;
        this.description = description;
        this.durationWeeks = durationWeeks;
        this.avgRating = avgRating;
        this.categories = categories;
        this.users = users;
        this.updatedUser = updatedUser;
        this.updatedTime = updatedTime;
        this.isDeleted = isDeleted;
    }
}
