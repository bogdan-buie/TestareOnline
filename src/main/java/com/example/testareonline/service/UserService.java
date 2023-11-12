package com.example.testareonline.service;

import com.example.testareonline.entity.User;
import com.example.testareonline.interfaces.IUserService;
import com.example.testareonline.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id){
        boolean usersExists = userRepository.existsById(id);
        if(usersExists){
            return userRepository.findById(id).get();
        }else{
            throw new IllegalStateException(String.format("User with %s id does not exists", id));
        }
    }
    @Override
    public void createUser(User user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user){
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("User with id %s doesn't exist", id)));
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setFirstname(user.getFirstname());

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        userToUpdate.setPassword(hashedPassword);
        userRepository.save(userToUpdate);

    }

    @Override
    public void deleteUser(Long id){
        boolean usersExists = userRepository.existsById(id);
        if(usersExists){
            userRepository.deleteById(id);
        }else{
            throw new IllegalStateException(String.format("User with %s id does not exists", id));
        }
    }
}
