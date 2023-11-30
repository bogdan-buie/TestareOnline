package com.example.testareonline.controller;
import com.example.testareonline.dto.QuizSubmissionDTO;
import com.example.testareonline.entity.QuestionWrapper;
import com.example.testareonline.entity.QuizSubmission;
import com.example.testareonline.entity.Response;
import com.example.testareonline.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/quiz")
public class QuizController {
    QuizService quizService;
    private ModelMapper modelMapper;

    public QuizController(QuizService quizService, ModelMapper modelMapper) {
        this.quizService = quizService;
        this.modelMapper = modelMapper;
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

    @PostMapping(path = "submit2/{id}")
    public ResponseEntity <Double> submit2Quiz(@PathVariable Long id, @RequestBody QuizSubmission quizSubmission){
        return quizService.calculate2Result(id, quizSubmission);
    }

    @PostMapping(path = "submit3/{id}")
    public ResponseEntity <Double> submit3Quiz(@PathVariable Long id, @RequestBody QuizSubmissionDTO quizSubmissionDTO){
        QuizSubmission qsm = modelMapper.map(quizSubmissionDTO, QuizSubmission.class);
        return quizService.calculate3Result(id, qsm);
    }

}
