package com.example.cs203g1t3.models;
import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User{

    @Id
    @GeneratedValue//(strategy = GenerationType.IDENTITY)
    private Long userID;

//    Login Details
    private String username;
    private String password;
//
//    //Other non-important identifiers
     private String address;
     private String email;
     private Integer phoneNumber;
     private boolean accountStatus;
     private LocalDateTime lastActive;
//
//    //Variables to be used in the service later on
    private int creditScore;
    private int noOfBookingsLeft;
    private boolean isMember;

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
