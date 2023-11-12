package com.example.testareonline.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/v1/test")
public class TestController {


    @GetMapping(path="/welcome")
    public String getPrivate(){
        return "Welcome";
    }


}
