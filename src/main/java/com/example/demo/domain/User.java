package com.example.demo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;

    @ManyToMany(mappedBy = "users")
    private Set<Course> courses;

//    @Column
//    private String login;
//
//    private String password;
//
//    private String email;
//
//    private String firstName;
//
//    private String lastName;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "content_id", referencedColumnName = "id")
//    private Content avatar;

//    private User updatedUser;
//
//    private LocalDateTime updatedTime;

//    private Boolean isAdmin;



    public User() {
    }

    public User(Long id, String username, Set<Course> courses) {
        this.id = id;
        this.username = username;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
