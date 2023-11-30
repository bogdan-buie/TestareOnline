package com.example.testareonline.entity;

import jakarta.persistence.*;

@Entity
@Table(name="response")
public class Response {

    @Id
    @SequenceGenerator(
            name = "student_response_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_response_sequence",
            strategy = GenerationType.SEQUENCE
    )

    private Long id;
    private Long questionId;

    private String response;

    public Response() {
    }

    public Response(Long questionId, String response) {
        this.questionId = questionId;
        this.response = response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
