package com.example.demo.Users;

import com.example.demo.Role.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private Set<String> roles;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    }
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


    public Set<String> getRoles() {
        return roles;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
