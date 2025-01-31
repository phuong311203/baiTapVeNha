package com.example.demo.Users;

import com.example.demo.Role.Role;
import com.example.demo.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    

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
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        Iterable<User> iterable = userRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }


    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public void registerUser(User user) {
        Role userRole = roleRepository.findByName("USER");
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
    }
}
