package com.example.cepapi.configuration.exception;

public class ApiNotFoundException extends RuntimeException{
    public ApiNotFoundException(String message) {
        super(message);
    }

    public ApiNotFoundException(String field, String parameter) {
    }
}
