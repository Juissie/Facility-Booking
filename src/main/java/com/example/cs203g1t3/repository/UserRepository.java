package com.example.cs203g1t3.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cs203g1t3.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUsernameAndPassword(String username, String password);
}
