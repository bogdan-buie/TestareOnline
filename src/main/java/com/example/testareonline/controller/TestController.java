package com.example.testareonline.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/v1/test")
public class TestController {


    @GetMapping(path="/public")
    public String getPublic(){
        return "Welcome in public area";
    }

    @GetMapping(path="/private")
    public String getPrivate(){
        return "Welcome in private area ";
    }

    @GetMapping(path="/messages")
    public ResponseEntity<List<String>> messages(){
        return ResponseEntity.ok(Arrays.asList("first","second"));
    }



}
