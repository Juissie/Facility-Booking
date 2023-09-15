package com.example.cs203g1t3.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.repository.UserRepository;

@Service
public class UserService {


    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerCustomer(String username, String password, String email) {
        if (username == null  || password == null) {
            return null;
        } else {
            User customer = new User();
            customer.setUsername(username);
            customer.setPassword(password);
            customer.setEmail(email);
            return userRepository.save(customer);
        }
    }

//    public User authenticate(String name, String password) {
//        Optional<User> user = userRepository.findByUsernameAndPassword(name, password);
//        return user.orElse(null);
//    }
}
