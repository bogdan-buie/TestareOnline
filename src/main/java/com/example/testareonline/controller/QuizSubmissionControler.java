package com.example.testareonline.controller;


import com.example.testareonline.entity.QuizSubmission;
import com.example.testareonline.service.QuizSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/quizSubmission")
public class QuizSubmissionControler {
    QuizSubmissionService qss;

    public QuizSubmissionControler(QuizSubmissionService qss) {
        this.qss = qss;
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<QuizSubmission>> getQuizzes(){
        return qss.getAllSubmissions();
    }
}
