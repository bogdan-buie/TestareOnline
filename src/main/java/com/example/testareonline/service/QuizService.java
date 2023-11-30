package com.example.testareonline.service;

import com.example.testareonline.entity.*;
import com.example.testareonline.interfaces.IQuizService;
import com.example.testareonline.repository.IQuestionRepository;
import com.example.testareonline.repository.IQuizRepository;
import com.example.testareonline.repository.IQuizSubmission;
import com.example.testareonline.repository.IResponseRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Autowired
    IQuizSubmission iQuizSubmission;

    @Autowired
    IResponseRepository iResponseRepository;


    @Override
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = iQuestionRepository.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        iQuizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    public ResponseEntity<String> addQuestionToQuiz(Long quizId, Long questionId) {
        Quiz quiz = iQuizRepository.findById(quizId).get();
        Question question = iQuestionRepository.findById(questionId).orElseThrow(() -> new EntityNotFoundException("Question not found"));

        quiz.getQuestions().add(question);
        iQuizRepository.save(quiz);
        return new ResponseEntity<>("Question added with success", HttpStatus.CREATED);
    }


    public ResponseEntity<String> removeQuestionFromQuiz(Long quizId, Long questionId) {
        Quiz quiz = iQuizRepository.findById(quizId).get();
        Question question = iQuestionRepository.findById(questionId).orElseThrow(() -> new EntityNotFoundException("Question not found"));

        quiz.getQuestions().remove(question);
        iQuizRepository.save(quiz);

        return new ResponseEntity<>("Question removed with success", HttpStatus.OK);
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

    public ResponseEntity<Double> calculate2Result(Long id, QuizSubmission quizSubmission) {
        Quiz quiz = iQuizRepository.findById(id).get(); // identificarea quiz-ului pe care l-a dat studentul
        List<Question> questions = quiz.getQuestions();// intrebarile cu tot cu raspuns
        List<Response> studentResponses = quizSubmission.getResponses();

        int correctAnswers = 0;
        int i = 0;
        //atentie la ordinea raspunsurilor sa fie aceeasi cu a intrebarilor
        for (Response res : studentResponses) {
            if (res.getResponse().equals(questions.get(i).getRightAnswer())) {
                correctAnswers++;
            }
            // salvare raspunsuri
            i++;
        }

        double nota = correctAnswers / studentResponses.size() * 10;


        quizSubmission.setNota(nota);
        quizSubmission.setResponses(studentResponses);

        //iQuizSubmission.save(quizSubmission);

        return new ResponseEntity<Double>(nota, HttpStatus.OK);
    }

    public ResponseEntity<Double> calculate3Result(Long id, QuizSubmission quizSubmission) {
        Quiz quiz = iQuizRepository.findById(id).get(); // identificarea quiz-ului pe care l-a dat studentul
        List<Question> questions = quiz.getQuestions();// intrebarile cu tot cu raspuns
        List<Response> studentResponses = quizSubmission.getResponses();


        // salvare raspunsuri
        for (Response res : studentResponses) {
            iResponseRepository.save(res);
        }

        int correctAnswers = 0;
        int i = 0;
        //atentie la ordinea raspunsurilor sa fie aceeasi cu a intrebarilor
        for (Response res : studentResponses) {
            if (res.getResponse().equals(questions.get(i).getRightAnswer())) {
                correctAnswers++;
            }
            i++;
        }

        double nota = correctAnswers / studentResponses.size() * 10;
        quizSubmission.setNota(nota);
        quizSubmission.setResponses(studentResponses);
        iQuizSubmission.save(quizSubmission); // nu pot salva
        return new ResponseEntity<Double>(nota, HttpStatus.OK);
    }
}
