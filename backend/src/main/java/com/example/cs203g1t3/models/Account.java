package com.example.cs203g1t3.models;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.*;


import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "accounts")
public class Account {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY) 
    Long userId;


    private String password;

    @Column(name = "last_active")
    private LocalDateTime lastActive;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "address")
    private String address;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Account(String firstName,String lastName,String emailId){

    }
}
