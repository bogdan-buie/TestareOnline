package com.example.testareonline.service;

import com.example.testareonline.entity.Question;
import com.example.testareonline.interfaces.IQuestionService;
import com.example.testareonline.repository.IQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionService implements IQuestionService {
    private final IQuestionRepository iQuestionRepository;

    public QuestionService(IQuestionRepository iQuestionRepository) {
        this.iQuestionRepository = iQuestionRepository;
    }

    @Override
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(iQuestionRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(iQuestionRepository.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(iQuestionRepository.findByCategory(category), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> addQuestion(Question question) {
            iQuestionRepository.save(question);
            return new ResponseEntity<>("Added with success", HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<String> updateQuestion(Long id, Question question){
        Question questionToUpdate = iQuestionRepository.findById(id).get();
        questionToUpdate.setQuestionTitle(question.getQuestionTitle());
        questionToUpdate.setCategory(question.getCategory());
        questionToUpdate.setDifficultyLevel(question.getDifficultyLevel());
        questionToUpdate.setOption1(question.getOption1());
        questionToUpdate.setOption2(question.getOption2());
        questionToUpdate.setOption3(question.getOption3());
        questionToUpdate.setRightAnswer(question.getRightAnswer());

        iQuestionRepository.save(questionToUpdate);
        return new ResponseEntity<>("Question updated with success", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteQuestion(Long id){
        boolean questionExists = iQuestionRepository.existsById(id);
        if(questionExists){
            iQuestionRepository.deleteById(id);
            return new ResponseEntity<>("Question deleted with success", HttpStatus.OK);

        }else{
            return new ResponseEntity<>("Question does not exit", HttpStatus.OK);

        }
    }

    public ResponseEntity<Question> getQuestionById(Long id) {
        Optional<Question> optionalQuestion = iQuestionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            return ResponseEntity.ok(optionalQuestion.get());
        } else {
            return ResponseEntity.badRequest().body(new Question());
        }
    }
}
