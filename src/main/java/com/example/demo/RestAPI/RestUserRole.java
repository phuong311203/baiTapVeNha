package com.example.demo.RestAPI;

import com.example.demo.Role.Role;
import com.example.demo.Role.RoleRepository;
import com.example.demo.Users.User;
import com.example.demo.Users.UserDTO;
import com.example.demo.Users.UserRepository;
import com.example.demo.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestUserRole {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserService userService;

    @Autowired
    public RestUserRole(UserRepository userRepository, RoleRepository roleRepository, UserService userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    // Sửa đổi ở đây để dùng UserDto
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // Mã hóa mật khẩu
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
