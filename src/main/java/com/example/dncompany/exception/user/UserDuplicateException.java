package com.example.dncompany.exception.user;

public class UserDuplicateException extends RuntimeException {
    public UserDuplicateException(String message) {
        super(message);
    }
}
