package com.example.demo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "themes")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private User updatedUser;

    private LocalDateTime updatedTime;

    @OneToMany
    private Set<Content> contents;

    @OneToMany
    private Set<Task> tasks;

    public Theme() {
    }

    public Theme(Long id, String title, String description, User updatedUser, LocalDateTime updatedTime, Set<Content> contents, Set<Task> tasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.updatedUser = updatedUser;
        this.updatedTime = updatedTime;
        this.contents = contents;
        this.tasks = tasks;
    }
}
