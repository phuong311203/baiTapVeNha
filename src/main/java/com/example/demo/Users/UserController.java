package com.example.demo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class    UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "index";
    }


    @GetMapping("/add")
    public String addUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveOrUpdateUser(user);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/editUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.saveOrUpdateUser(user);
        return "redirect:/";
    }


    // Delete the user by ID
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
