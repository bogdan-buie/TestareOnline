package com.example.testareonline.service;

import com.example.testareonline.entity.Question;
import com.example.testareonline.entity.Quiz;
import com.example.testareonline.entity.Response;
import com.example.testareonline.repository.IResponseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    @Autowired
    IResponseRepository iResponseRepository;
    public ResponseEntity<String> addResponse(Response myResponse) {
        iResponseRepository.save(myResponse);
        return new ResponseEntity<>("Question added with success", HttpStatus.CREATED);
    }
}
