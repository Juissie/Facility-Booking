package com.example.cs203g1t3.models;
import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    //Login Details
    @Getter @Setter private String username;
    @Getter @Setter private String password;

    //Other non-important identifiers
    @Getter @Setter private String address;
    @Getter @Setter private String email;
    @Getter @Setter private Integer phoneNumber;
    @Getter @Setter private boolean accountStatus;
    @Getter @Setter private LocalDateTime lastActive;

    //Variables to be used in the service later on
    @Getter @Setter private int creditScore;
    @Getter @Setter private int noOfBookingsLeft;
    @Getter @Setter private boolean isMember;

    // implement method
    public boolean makeBooking() {
        return true;
    }

    // implement method
    public List<Booking> listBooking() {
        return null;
    }

    @Override
    // Password omitted
    public String toString() {
        return "Account [userID=" + userID + ", accountStatus=" + accountStatus + ", lastActive=" + lastActive
                + ", name=" + username + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }
    
}
