package com.example.foodsubscription.exception;

public class CorruptedTokenException extends Exception {
    private static final String PREFIX = "Corrupted token: ";

    public CorruptedTokenException(String message){
        super(PREFIX + message);
    }
}
