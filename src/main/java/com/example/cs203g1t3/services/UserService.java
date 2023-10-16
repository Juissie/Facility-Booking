package com.example.cs203g1t3.services;

import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.DTO.LoginResponse;
import com.example.cs203g1t3.repository.UserRepository;

import jakarta.mail.internet.MimeMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.util.Date;
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

//    public void generateOneTimePassword(User user)
//            throws UnsupportedEncodingException, MessagingException {
//        String OTP = RandomStringUtils.randomAlphabetic(6);
//        String encodedOTP = encoder.encode(OTP);
//
//        user.setOneTimePassword(encodedOTP);
//        user.setOtpRequestedTime(new Date());
//
//        userRepository.save(user);
//
//        sendOTPEmail(user, OTP);
//    }
//
//    public void sendOTPEmail(User user, String OTP)
//            throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom("contact@shopme.com", "Shopme Support");
//        helper.setTo(user.getEmail());
//
//        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
//
//        String content = "<p>Hello " + user.getUsername() + "</p>"
//                + "<p>For security reason, you're required to use the following "
//                + "One Time Password to login:</p>"
//                + "<p><b>" + OTP + "</b></p>"
//                + "<br>"
//                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
//
//        helper.setSubject(subject);
//
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }
//
//    public void clearOTP(User user) {
//        user.setOneTimePassword(null);
//        user.setOtpRequestedTime(null);
//        userRepository.save(user);
//    }


    public void changePassword(User user, String newPassword) {
        // Encode the new password
        String encodedPassword = encoder.encode(newPassword);
        
        // Save the updated user with the new password
        userRepository.updatePassword(user.getUserID(), encodedPassword);
    }

    public boolean checkPassword(User user, String password) {
        // Encode the password
        String encodedPassword = encoder.encode(password);
        // Retrieve the id
        String username = user.getUsername();
        // Check if the id and password matches that of the database
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, encodedPassword);
        // Return whether it exist
        return userOptional.isPresent();
    }

}
