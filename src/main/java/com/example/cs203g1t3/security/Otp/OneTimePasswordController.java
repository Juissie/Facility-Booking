package com.example.cs203g1t3.security.Otp;



import com.example.cs203g1t3.exception.InvalidOtpException;
import com.example.cs203g1t3.exception.NoSuchUserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/otp/")
public class OneTimePasswordController {

    private final OneTimePasswordService oneTimePasswordService;

    @Autowired
    public OneTimePasswordController(OneTimePasswordService oneTimePAsswordService) {
        this.oneTimePasswordService = oneTimePAsswordService;
    }

    @PostMapping("/generateOtp/{userId}")
    private void createOneTimePassword(@PathVariable Long userId) {
        oneTimePasswordService.generateOneTimePassword(userId);
    }

        @PostMapping("/validateOtp/{userId}")
    private Boolean validateOtp(@PathVariable Long userId, @RequestBody String otp){
            System.out.println(otp);
        int otpInt = Integer.parseInt(otp);
            System.out.println(otpInt);
        try{
            System.out.println("Hi!!");
            return oneTimePasswordService.checkOneTimePassword(userId,otpInt);
        } catch (RuntimeException e){
            throw new InvalidOtpException("OTP is not valid!");
        }

    }
}
