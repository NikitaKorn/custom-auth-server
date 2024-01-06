package com.example.foodsubscription.exception;

import jakarta.validation.ValidationException;

public class CustomValidationException extends RuntimeException {
    private static final String PREFIX = "Validation exception: ";

    public CustomValidationException(String message){
        super(PREFIX + message);
        this.setStackTrace(new StackTraceElement[0]);
    }
}
