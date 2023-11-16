package com.example.testareonline.interfaces;

import com.example.testareonline.entity.Question;
import com.example.testareonline.entity.QuestionWrapper;
import com.example.testareonline.entity.Quiz;
import com.example.testareonline.entity.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IQuizService {
    ResponseEntity<String> createQuiz(String category, int numQ, String title) ;

    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id);

    ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) ;
}
