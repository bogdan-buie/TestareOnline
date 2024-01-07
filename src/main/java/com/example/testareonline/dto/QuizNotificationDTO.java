package com.example.testareonline.dto;

public class QuizNotificationDTO {
    private String message;
    private String nume;
    private String prenume;
    private String specializare;
    private double nota;

    public QuizNotificationDTO(String message, String nume, String prenume, String specializare, double nota) {
        this.message = message;
        this.nume = nume;
        this.prenume = prenume;
        this.specializare = specializare;
        this.nota = nota;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
