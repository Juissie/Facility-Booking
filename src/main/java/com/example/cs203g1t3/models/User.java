package com.example.cs203g1t3.models;
import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "users", schema="public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

//    Login Details
    private String username;
    private String password;

//    //Other non-important identifiers
     private String address;
     private String email;
     private Integer phoneNumber;
     private boolean accountStatus;
     private LocalDateTime lastActive;

//    //Variables to be used in the service later on
    private int creditScore;
    private int noOfBookingsLeft;
    private boolean isMember;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // implement method
//    public boolean makeBooking() {
//        return true;
//    }
//
//    // implement method
//    public List<Booking> listBooking() {
//        return null;
//    }

//    @Override
//    // Password omitted
//    public String toString() {
//        return "Account [userID=" + userID + ", accountStatus=" + accountStatus + ", lastActive=" + lastActive
//                + ", name=" + username + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
//    }
    
}
