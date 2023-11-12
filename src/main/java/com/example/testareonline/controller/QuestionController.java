package com.example.testareonline.controller;

import com.example.testareonline.entity.Question;
import com.example.testareonline.service.QuestionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/all")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
}
