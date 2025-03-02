package com.ticketevent.exceptions.exception;

public class BadCredentialException extends RuntimeException{
    public BadCredentialException(String message) {
        super(message);
    }

}
