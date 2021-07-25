package com.example.demo.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ratings")
public class Rating {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private Set<User> users;

    @ManyToMany
    private Set<Course> courses;

    private Integer value;

    public Rating() {
    }

    public Rating(Long id, Set<User> users, Set<Course> courses, Integer value) {
        this.id = id;
        this.users = users;
        this.courses = courses;
        this.value = value;
    }
}
