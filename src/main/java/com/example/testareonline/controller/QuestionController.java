package com.example.testareonline.controller;

import com.example.testareonline.entity.Question;
import com.example.testareonline.service.QuestionService;


import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping(path = "/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        return questionService.deleteQuestion(id);
    }
}
