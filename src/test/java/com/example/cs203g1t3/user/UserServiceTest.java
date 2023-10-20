package com.example.cs203g1t3.user;

import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.repository.UserRepository;
import com.example.cs203g1t3.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private

    @Test
    void addUser_sameUsername_ReturnedUser(){
        User user = new User("S8217079D","junjie.liew2001@gmail.com","Ilovecs203!");


    }
}
