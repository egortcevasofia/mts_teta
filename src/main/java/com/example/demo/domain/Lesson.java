package com.example.demo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Lob
    @Column
    private String description;

    @ManyToOne(optional = false)
    private Course course;

//    @OneToOne
//    @JoinColumn(name="user_id", nullable=false)
//    private User updatedUser;
//
//    private LocalDateTime updatedTime;



    public Lesson() {
    }

    public Lesson(Long id, String title, String description, Course course) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
