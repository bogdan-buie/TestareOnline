package com.example.testareonline.interfaces;

import com.example.testareonline.entity.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface IQuestionService {
    ResponseEntity<List<Question>> getAllQuestions();

    ResponseEntity<List<Question>> getQuestionsByCategory(String category) ;

    ResponseEntity<String> addQuestion(Question question);
    ResponseEntity<String> updateQuestion(Long id, Question question);
    ResponseEntity<String> deleteQuestion(Long id);
}
