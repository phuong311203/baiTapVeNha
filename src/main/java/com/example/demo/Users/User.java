package com.example.demo.Users;

import com.example.demo.Company.Company;
import jakarta.persistence.*;
@Table(name = "Users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id") // Khóa ngoại để liên kết với Company
    private Company company;

    public User() {
    }

    public User(Integer id, String username, String email, Company company) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.company = company;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
