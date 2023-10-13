package com.example.cs203g1t3.controller;

import com.example.cs203g1t3.models.ProfileUserDetails;
import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.services.UserService;
import com.example.cs203g1t3.DTO.UserDTO;
import com.example.cs203g1t3.DTO.LoginDTO;
import com.example.cs203g1t3.DTO.LoginResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//        User user = userService.getUser(userId);
        ProfileUserDetails profileUserDetails = userService.getProfileDetails(userId);
        return ResponseEntity.ok(profileUserDetails);
    }
}

