package com.example.demo.RestAPI;

import com.example.demo.Company.Company;
import com.example.demo.Company.CompanyService;
import com.example.demo.Users.User;
import com.example.demo.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestUserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<User> users(){
        List<User> users = userService.getAllUsers();
        return users;
    }


    @PostMapping("/api/addUser")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

}
