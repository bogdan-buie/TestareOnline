package com.example.testareonline.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @SequenceGenerator(
            name = "quiz_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "quiz_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String Title;

    @ManyToMany
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(Long id, String title, List<Question> questions) {
        this.id = id;
        Title = title;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
