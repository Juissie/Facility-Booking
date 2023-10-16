package com.example.cs203g1t3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidOtpException extends RuntimeException {

    public InvalidOtpException(String message) {
        super(message);
    }
}
