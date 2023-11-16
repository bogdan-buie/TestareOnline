package com.example.testareonline.service;

import com.example.testareonline.entity.User;
import com.example.testareonline.interfaces.IUserService;
import com.example.testareonline.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository iUserRepository, PasswordEncoder passwordEncoder) {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<User> getUsers(){
        return iUserRepository.findAll();
    }

    @Override
    public User getUserById(Long id){
        boolean usersExists = iUserRepository.existsById(id);
        if(usersExists){
            return iUserRepository.findById(id).get();
        }else{
            throw new IllegalStateException(String.format("User with %s id does not exists", id));
        }
    }
    @Override
    public void createUser(User user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        iUserRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user){
        User userToUpdate = iUserRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("User with id %s doesn't exist", id)));
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setFirstname(user.getFirstname());

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        userToUpdate.setPassword(hashedPassword);
        iUserRepository.save(userToUpdate);

    }

    @Override
    public void deleteUser(Long id){
        boolean usersExists = iUserRepository.existsById(id);
        if(usersExists){
            iUserRepository.deleteById(id);
        }else{
            throw new IllegalStateException(String.format("User with %s id does not exists", id));
        }
    }
}
