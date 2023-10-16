package com.example.cs203g1t3.security.Otp;

import com.example.cs203g1t3.exception.NoSuchUserFoundException;
import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.repository.UserRepository;
import com.example.cs203g1t3.security.Otp.OneTimePassword;
import com.example.cs203g1t3.security.Otp.OneTimePasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class OneTimePasswordService {

    private final Long expiryInterval = 5L * 60 * 1000;

    private final OneTimePasswordRepository oneTimePasswordRepository;

    private final UserRepository userRepository;

    private final static Integer LENGTH = 6;


    @Autowired
    public OneTimePasswordService(OneTimePasswordRepository oneTimePasswordRepository, UserRepository userRepository) {
        this.oneTimePasswordRepository = oneTimePasswordRepository;
        this.userRepository = userRepository;
    }

    //Clear any existing one time passwords
    public void clearOneTimePassword(Long userId){
        try{
            User user = userRepository.findById(userId).get();
            user.setOneTimePassword(null);
        } catch (NoSuchElementException e){
            throw new NoSuchUserFoundException("No Such User Found!");
        }
    }

    //Creates and set one time password within user object while returning the one time password
    public void generateOneTimePassword(Long userId) {
        OneTimePassword oneTimePassword = new OneTimePassword();

        oneTimePassword.setOneTimePasswordCode(createRandomOneTimePassword().get());
        oneTimePassword.setExpires(new Date(System.currentTimeMillis() + expiryInterval));
        try{
            User user = userRepository.findById(userId).get();
            user.setOneTimePassword(oneTimePassword);
            oneTimePasswordRepository.save(oneTimePassword);
        } catch (NoSuchElementException e){
            throw new NoSuchUserFoundException("No Such User Found!");
        }
    }
    //Supply generateOneTimePassword with LENGTH = 6 code
    public static Supplier<Integer> createRandomOneTimePassword() {
        return () -> {
            Random random = new Random();
            StringBuilder oneTimePassword = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                int randomNumber = random.nextInt(10);
                oneTimePassword.append(randomNumber);
            }
            return Integer.parseInt(oneTimePassword.toString().trim());
        };
    }

    //Checks the one time password code for validity and check for expiry
    public Boolean checkOneTimePassword(Long userId,int oneTimePasswordCode){
        User user = userRepository.findById(userId).get();
        OneTimePassword oneTimePassword = user.getOneTimePassword();
        Date now = new Date(System.currentTimeMillis());
        System.out.println("The username is " + user.getUsername());
        //Validate the code and checks for expiry
        if(oneTimePasswordCode == oneTimePassword.getOneTimePasswordCode()){
            System.out.println("The OTP is correct!");
            return oneTimePassword.getExpires().before(now);
        }
        return false;
    }
}
