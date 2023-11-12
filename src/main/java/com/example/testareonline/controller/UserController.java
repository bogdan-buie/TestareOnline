package com.example.testareonline.controller;

import com.example.testareonline.entity.User;
import com.example.testareonline.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/welcome")
    public String getPrivaye(){
        return "Zona nesecurizata";
    }
    @GetMapping(path="/all")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path="/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping(path = "/new")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    @PutMapping(path="/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        userService.updateUser(id, user);
    }

    @DeleteMapping(path="/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
