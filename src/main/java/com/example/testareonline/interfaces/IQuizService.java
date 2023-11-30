package com.example.testareonline.interfaces;

import com.example.testareonline.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IQuizService {
    ResponseEntity<String> createQuiz(String category, int numQ, String title) ;

    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id);

    ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) ;
    ResponseEntity<Double> calculate2Result(Long id, QuizSubmission quizSubmission);
}
