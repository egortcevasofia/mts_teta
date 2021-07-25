package com.example.demo.domain;

import com.example.demo.annotation.TitleCase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public Course() {
    }

    public Course(Long id, @NotBlank(message = "Course author has to be filled") String author, @NotBlank(message = "Course title has to be filled") String title, List<Lesson> lessons, String description, Integer durationWeeks, BigDecimal avgRating, Set<Category> categories, Set<User> users, User updatedUser, LocalDateTime updatedTime, Boolean isDeleted) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(Integer durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    public BigDecimal getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(BigDecimal avgRating) {
        this.avgRating = avgRating;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public User getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(User updatedUser) {
        this.updatedUser = updatedUser;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
