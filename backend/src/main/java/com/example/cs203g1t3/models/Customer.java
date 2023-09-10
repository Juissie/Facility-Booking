package com.example.cs203g1t3.models;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.OneToOne;

public class Customer extends Account{

    @Autowired
    @OneToOne
    private Account account;

    private long creditNumber;

    public void makeBooking(){
        
    }
    
}
