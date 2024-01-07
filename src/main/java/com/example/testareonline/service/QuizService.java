package com.example.testareonline.service;

import com.example.testareonline.dto.QuizNotificationDTO;
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
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final SimpMessagingTemplate messagingTemplate;

    public QuizService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

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
        for (Question q : questionFromDb) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {
        Quiz quiz = iQuizRepository.findById(id).get();

        List<Question> questions = quiz.getQuestions();// intrebarile cu tot cu raspuns
        int correctAnswers = 0;
        int i = 0;
        for (Response res : responses) {
            if (res.getResponse().equals(questions.get(i).getRightAnswer())) {
                correctAnswers++;
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
        // Iteram prin fiecare întrebare și găsim răspunsul corect corespunzător
        for (Question question : questions) {
            for (Response res : studentResponses) {
                if (res.getQuestionId().equals(question.getId())) {
                    if (res.getResponse().equals(question.getRightAnswer())) {
                        correctAnswers++;
                    }
                    break;
                }
            }
        }

        double nota = ((double) correctAnswers / questions.size()) * 10;

        //System.out.println("Raspunsuri corecte " + correctAnswers);
        //System.out.println("Nr de intrebari  " + questions.size());
        quizSubmission.setNota(nota);
        quizSubmission.setResponses(studentResponses);
        iQuizSubmission.save(quizSubmission);

        QuizNotificationDTO qn = new QuizNotificationDTO("Quiz completed", quizSubmission.getLastName(), quizSubmission.getFirstName(), quizSubmission.getSpecializare(), nota);
        // Trimite notificare WebSocket către frontend
        messagingTemplate.convertAndSend("/topic/quizNotification", qn);


        return new ResponseEntity<Double>(nota, HttpStatus.OK);
    }

    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        try {
            return new ResponseEntity<>(iQuizRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getSecretQuizQuestions(Long id) {
        Optional<Quiz> quiz = iQuizRepository.findById(id);// identificare quiz dupa id
        List<Question> questionFromDb = quiz.get().getQuestions();
        return new ResponseEntity<>(questionFromDb, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteQuiz(Long id) {
        boolean quizExists = iQuizRepository.existsById(id);
        if (quizExists) {
            iQuizRepository.deleteById(id);
            return new ResponseEntity<>("Quiz deleted with success", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Quiz does not exit", HttpStatus.OK);

        }
    }
}
