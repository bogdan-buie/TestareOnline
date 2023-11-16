package com.example.testareonline.service;

import com.example.testareonline.entity.Question;
import com.example.testareonline.entity.QuestionWrapper;
import com.example.testareonline.entity.Quiz;
import com.example.testareonline.entity.Response;
import com.example.testareonline.interfaces.IQuizService;
import com.example.testareonline.repository.IQuestionRepository;
import com.example.testareonline.repository.IQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements IQuizService {
    @Autowired
    IQuizRepository iQuizRepository;
    @Autowired
    IQuestionRepository iQuestionRepository;


    @Override
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = iQuestionRepository.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        iQuizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id) {
        Optional<Quiz> quiz = iQuizRepository.findById(id);
        List<Question> questionFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for(Question q: questionFromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {
        Quiz quiz = iQuizRepository.findById(id).get();

        List<Question> questions = quiz.getQuestions();// intrebarile cu tot cu raspuns
        int correctAnswers = 0;
        int i =0;
        for(Response res : responses){
            if(res.getResponse().equals(questions.get(i).getRightAnswer())){
                correctAnswers ++;
            }
                i++;
        }
        return new ResponseEntity<>(correctAnswers, HttpStatus.OK);
    }
}
