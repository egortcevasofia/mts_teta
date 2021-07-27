package com.example.demo.domain;

import javax.persistence.*;
import java.nio.file.Path;

@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentPath;

    public Content() {
    }

    public Content(Long id, String contentPath) {
        this.id = id;
        this.contentPath = contentPath;
    }
}
