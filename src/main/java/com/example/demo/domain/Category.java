package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public Category() {
    }

    public Category(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
