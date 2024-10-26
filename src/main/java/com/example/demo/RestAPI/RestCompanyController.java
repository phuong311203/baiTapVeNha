package com.example.demo.RestAPI;


import com.example.demo.Company.Company;
import com.example.demo.Company.CompanyService;
import com.example.demo.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestCompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @GetMapping("/api/companies")
    public List<Company> getAllCompanies() {
        return companyService.getAll();
    }

    @PostMapping("/api/addCompany")
    public Company addCompany(@RequestBody Company company){
        return companyService.saveOrUpdate(company);
    }

    @PutMapping("api/companies/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        Company company = companyService.getById(id);
        if (company != null) {
            company.setCompanyName(companyDetails.getCompanyName());
            company.setUsers(companyDetails.getUsers());
            company.setNhanViens(companyDetails.getNhanViens());
            return companyService.saveOrUpdate(company);
        } else {
            return null;
        }
    }

    @DeleteMapping("api/companies/{id}")
    public String deleteCompany(@PathVariable Long id) {
        companyService.delete(id);
        return "Company with ID " + id + " has been deleted!";
    }

    @GetMapping("api/companies/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getById(id);
    }
}
