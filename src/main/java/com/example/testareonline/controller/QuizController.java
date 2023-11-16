package com.example.testareonline.controller;

import com.example.testareonline.entity.Question;
import com.example.testareonline.entity.QuestionWrapper;
import com.example.testareonline.entity.Response;
import com.example.testareonline.service.QuizService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/quiz")
public class QuizController {
    QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestions(@PathVariable Long id){
        // nu se va trimite raspunnsul intrabarii catre client
        return quizService.getQuizQuestions(id);
    }
    @PostMapping(path =  "/create")
    public ResponseEntity<String>createQuiz(@RequestParam String category, @RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }
    @PostMapping(path = "submit/{id}")
    public ResponseEntity <Integer> submitQuiz(@PathVariable Long id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

}
