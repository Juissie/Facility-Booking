package com.example.cs203g1t3.security.Otp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OneTimePasswordResponse {

    private long userId;
    private int oneTimePasswordCode;
}
