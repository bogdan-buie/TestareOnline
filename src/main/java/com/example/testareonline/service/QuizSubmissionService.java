package com.example.testareonline.service;

import com.example.testareonline.entity.QuizSubmission;
import com.example.testareonline.repository.IQuizSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizSubmissionService {
    @Autowired
    IQuizSubmission iQuizSubmission;


    public ResponseEntity<String> createQuizSubmission(QuizSubmission quizSubmission){
        QuizSubmission mySubmission = new QuizSubmission();
        mySubmission.setFirstName(quizSubmission.getFirstName());
        mySubmission.setLastName(quizSubmission.getLastName());
        mySubmission.setDateTime(quizSubmission.getDateTime());
        mySubmission.setResponses(quizSubmission.getResponses());
        mySubmission.setSpecializare(quizSubmission.getSpecializare());

        //iQuizSubmission.save(mySubmission);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

}
