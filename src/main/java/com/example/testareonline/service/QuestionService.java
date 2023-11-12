package com.example.testareonline.service;

import com.example.testareonline.entity.Question;
import com.example.testareonline.repository.IQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final IQuestionRepository iQuestionRepository;

    public QuestionService(IQuestionRepository iQuestionRepository) {
        this.iQuestionRepository = iQuestionRepository;
    }

    public List<Question> getAllQuestions(){
        return iQuestionRepository.findAll();
    }
}
