package com.example.cs203g1t3.controller;

import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.services.UserService;
import com.example.cs203g1t3.DTO.UserDTO;
import com.example.cs203g1t3.DTO.LoginDTO;
import com.example.cs203g1t3.DTO.LoginResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class UserController {

    public UserService userService;

    private BCryptPasswordEncoder encoder;

    public UserController(UserService us,BCryptPasswordEncoder encoder) {
        this.userService = us;
        this.encoder = encoder;
    }

    // Testing to get users -> Send a GET request here to check it's working.
    // Or just use PostgresSQL
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register_page";
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userService.registerUser(user);
    }

    @GetMapping("/login") 
    public String getLoginPage() {
        return "login_page";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user)
    {
        LoginResponse loginResponse = userService.loginUser(user);
        return ResponseEntity.ok(loginResponse);
    }
}

