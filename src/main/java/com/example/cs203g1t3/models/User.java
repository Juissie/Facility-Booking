package com.example.cs203g1t3.models;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.*;
//import javax.validation.constraints.*;

import javax.validation.constraints.Email;


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
    @NotBlank
//    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

//    //Other non-important identifiers
//     private String address;
     @Email
     private String email;
//     private Integer phoneNumber;
//     private boolean accountStatus;
//     private LocalDateTime lastActive;

//    //Variables to be used in the service later on
    private int creditScore;
//    private int noOfBookingsLeft;
//    private boolean isMember;

    //Email OTP variables
    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes
    @Column(name = "one_time_password")
    private String oneTimePassword;
    @Column(name = "otp_requested_time")
    private Date otpRequestedTime;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return userID;
    }

    public boolean isOTPRequired() {
        if (this.getOneTimePassword() == null) {
            return false;
        }

        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();

        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            // OTP expires
            return false;
        }

        return true;
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
    
}
