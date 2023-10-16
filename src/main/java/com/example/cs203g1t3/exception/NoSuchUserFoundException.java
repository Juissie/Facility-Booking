package com.example.cs203g1t3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoSuchUserFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoSuchUserFoundException(String message) {
        super(message);
    }
}