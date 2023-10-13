package com.example.cs203g1t3.services;

import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.DTO.LoginResponse;
import com.example.cs203g1t3.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private BCryptPasswordEncoder encoder;
    
    @Autowired
    public UserService(UserRepository userRepository,BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    public User getUser(Long userId) {
        // You can use your UserRepository or any data access method to fetch the user by userId
        Optional<User> user = userRepository.findById(userId);
        return user.get();
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with ID " + userId + "does not exists");
        }
        userRepository.deleteById(userId);
    }

    public LoginResponse loginUser(User user) {
        Optional<User> thisUser = userRepository.findByEmail(user.getEmail());
        if (thisUser.isPresent()) {
            User currUser = thisUser.get();
            String password = user.getPassword();
            String encodedPassword = currUser.getPassword();
            Boolean isPwdRight = encoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepository.findByEmailAndPassword(user.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Password does not match", false);
            }
        }else {
            return new LoginResponse("Email does not exist", false);
        }
    }

}
