package com.example.cs203g1t3.controller;

import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    public UserService userService;

//    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserController(UserService us) {
        this.userService = us;
    }

    // Testing to get users -> Send a GET request here to check it's working.
    // Or just use PostgresSQL

    @GetMapping("/details/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
}

