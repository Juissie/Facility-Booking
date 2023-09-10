package com.example.cs203g1t3.models;

import org.springframework.beans.factory.annotation.Autowired;

public class Customer extends Account{

    @Autowired
    private Account account;

    private long creditNumber;

    public void makeBooking(){
        
    }
    
}
