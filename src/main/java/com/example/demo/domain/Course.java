package com.example.demo.domain;

import com.example.demo.annotation.TitleCase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course author has to be filled")
    @Column
    private String author;

    @NotBlank(message = "Course title has to be filled")
    @TitleCase
    @Column
    private String title;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @ManyToMany
    private Set<User> users;


    public Course() {
    }

    public Course(Long id) {
        this.id = id;
    }

    public Course(Long id, @NotBlank(message = "Course author has to be filled") String author, @NotBlank(message = "Course title has to be filled") String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public Course(Long id, @NotBlank(message = "Course author has to be filled") String author, @NotBlank(message = "Course title has to be filled") String title, List<Lesson> lessons, Set<User> users) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.lessons = lessons;
        this.users = users;
    }

    public Course(Long id, @NotBlank(message = "Course author has to be filled") String author, Set<User> users) {
        this.id = id;
        this.author = author;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
