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
    private String login;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private Content avatar;

//    private User updatedUser;
//
//    private LocalDateTime updatedTime;

    private Boolean isAdmin;

    @ManyToMany(mappedBy = "users")
    private Set<Course> courses;

    public User() {
    }

    public User(Long id, String login, String password, String email, String firstName, String lastName, Content avatar, Boolean isAdmin, Set<Course> courses) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.isAdmin = isAdmin;
        this.courses = courses;
    }
}
