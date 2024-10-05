package com.example.demo.Company;

import com.example.demo.Users.User;
import com.example.demo.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  // Nhập đúng Model
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    UserService userService;

    @GetMapping("/addCompany")
    public String add(Model model) {
        model.addAttribute("company", new Company());
        return "addCompany";
    }

    @PostMapping("/addCompany")
    public void saveOrUpdate(@ModelAttribute("company") Company company) {

        List<User> users = userService.getAllUsers();
        company.setUsers(users);
        companyService.saveOrUpdate(company);
    }


    @GetMapping("/companies")
    public String trangChiTiet(Model model) {
        List<Company> companies = companyService.getAll();
        System.out.println("companies: " + companies);

        model.addAttribute("companies", companies);
        return "companies";
    }
}
