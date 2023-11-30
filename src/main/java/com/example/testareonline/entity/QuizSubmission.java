package com.example.testareonline.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
@Entity
@Table(name = "quizSubmission")
public class QuizSubmission {
    @Id
    @SequenceGenerator(
            name = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "question_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String specializare;
    private String dateTime;

    @OneToMany
    private List<Response> responses; // lista de raspunsuri (id intrebare + raspunsul studentului)
    private double nota;

    public QuizSubmission(String firstName, String lastName, String specializare, String dateTime, List<Response> response) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specializare = specializare;
        this.dateTime = dateTime;
        this.responses = responses;
    }

    public QuizSubmission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> response) {
        this.responses = response;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
