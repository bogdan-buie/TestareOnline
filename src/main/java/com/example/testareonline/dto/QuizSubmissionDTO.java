package com.example.testareonline.dto;

import com.example.testareonline.entity.Response;

import java.util.List;


public class QuizSubmissionDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String specializare;
    private String dateTime;
    private List<Response> responses; // lista de raspunsuri (id intrebare + raspunsul studentului)

    private double nota;

    public QuizSubmissionDTO() {
    }

    public QuizSubmissionDTO(Long id, String firstName, String lastName, String specializare, String dateTime, List<Response> responses, double nota) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specializare = specializare;
        this.dateTime = dateTime;
        this.responses = responses;
        this.nota = nota;
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

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
