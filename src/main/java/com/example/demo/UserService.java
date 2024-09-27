package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveOrUpdateUser(User user) {
        if (user.getId() != null) {
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null) {
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
                userRepository.save(existingUser);
            }
        } else {
            userRepository.save(user);
        }
    }


    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
