package com.example.cs203g1t3.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.services.UserService;

@RestController
public class UserController {

    public UserService userService;

    public UserController(UserService us) {
        this.userService = us;
    }

    // Testing to get users -> Send a GET request here to check it's working.
    // Or just use PostgreSQL
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
        userService.registerUser(user);
    }

    @GetMapping("/login") 
    public String getLoginPage() {
        return "login_page";
    }


}

