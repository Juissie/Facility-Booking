package com.example.cs203g1t3.services;

import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.DTO.LoginDTO;
import com.example.cs203g1t3.DTO.UserDTO;
import com.example.cs203g1t3.DTO.LoginResponse;
import com.example.cs203g1t3.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private BCryptPasswordEncoder encoder;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Old implementation 
        // public User registerCustomer(String username, String password, String email) {
        //     if (username == null  || password == null) {
        //         return null;
        //     } else {
        //         User customer = new User();
        //         customer.setUsername(username);
        //         customer.setPassword(password);
        //         customer.setEmail(email);
        //         return userRepository.save(customer);
        //     }
        // }

    public void registerUser(User user) {
        Optional<User> usernameOptional = userRepository.findByUsername(user.getUsername());
        Optional<User> emailOptional = userRepository.findByEmail(user.getEmail());
        if (usernameOptional.isPresent()) {
            throw new IllegalStateException("Username taken");
        } else if (emailOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        userRepository.save(user); 
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with ID " + userId + "does not exists");
        }
        userRepository.deleteById(userId);
    }

//    public User authenticate(String name, String password) {
//        Optional<User> user = userRepository.findByUsernameAndPassword(name, password);
//        return user.orElse(null);
//    }

    public LoginResponse loginUser(User user) {
        User thisUser = userRepository.findByEmail(user.getEmail()).get();
        if (thisUser != null) {
            String password = user.getPassword();
            String encodedPassword = thisUser.getPassword();
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
            return new LoginResponse("Email not exits", false);
        }
    }

}