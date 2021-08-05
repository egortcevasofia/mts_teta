package com.example.demo.dto;

import com.example.demo.domain.Course;
import com.example.demo.domain.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class UserDto {

    private Long id;

    @NotNull
    private String username;

    private Set<Course> courses;

    private Set<Role> roles;

    @NotNull
    private String password;

    public UserDto() {
    }

    public UserDto(Long id, String username, Set<Course> courses, Set<Role> roles, String password) {
        this.id = id;
        this.username = username;
        this.courses = courses;
        this.roles = roles;
        this.password = password;
    }

    public UserDto(Long id, String username, String s, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public UserDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
