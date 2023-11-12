package com.example.testareonline.interfaces;

import com.example.testareonline.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User getUserById(Long id);
    void createUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);

}
