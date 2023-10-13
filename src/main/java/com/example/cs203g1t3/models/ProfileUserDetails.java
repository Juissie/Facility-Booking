package com.example.cs203g1t3.models;

public class ProfileUserDetails {

    private Long userID;
    private String username;
    private String email;
    private int creditScore;

    public ProfileUserDetails(Long userID,String username,String email, int creditScore){
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.creditScore = creditScore;
    }
}
