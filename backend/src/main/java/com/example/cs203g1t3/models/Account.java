package com.example.cs203g1t3.models;

import java.time.LocalDateTime;
import org.springframework.data.annotation.*;
import jakarta.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
@Entity
public class Account {

    @Id 
    @Generated(value = { "Id" })
    private String userID;

    
    private String password;
    
    private boolean accountStatus;
    private LocalDateTime lastActive;

    @Column(name = "first_name")
    private String firstName;
    private String address;
    private String email;
    private String phoneNumber;


    
     //setter
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }
    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




    //getter
    public String getUserID() {
        return userID;
    }
    public String getPassword() {
        return password;
    }
    public boolean isAccountStatus() {
        return accountStatus;
    }
    public LocalDateTime getLastActive() {
        return lastActive;
    }
    public String getName() {
        return name;
    }
    public Address getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
}
