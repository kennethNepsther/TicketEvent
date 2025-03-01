package com.ticketevent.exceptions.exception;

public class RestClientTimeOutException extends RuntimeException{
    public RestClientTimeOutException(String message) {
        super(message);
    }

}
