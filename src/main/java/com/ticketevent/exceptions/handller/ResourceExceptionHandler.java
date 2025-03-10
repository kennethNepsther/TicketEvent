package com.ticketevent.exceptions.handller;



import com.ticketevent.exceptions.error.StandardError;
import com.ticketevent.exceptions.error.ValidationError;
import com.ticketevent.exceptions.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException objectNotFoundException) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), objectNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequestException(BadRequestException badRequestException) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), badRequestException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException integrityViolationException) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(), integrityViolationException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException fieldValidationException) {
        ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Erro de validação de campo");
        for (FieldError x : fieldValidationException.getBindingResult().getFieldErrors()) {
            error.addErrors(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BadCredentialException.class)
    public ResponseEntity<StandardError> credentialInvalidException(BadCredentialException credentialInvalid) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), credentialInvalid.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(RestClientTimeOutException.class)
    public ResponseEntity<StandardError> restClientException(RestClientTimeOutException restClientTimeOutException) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.REQUEST_TIMEOUT.value(), restClientTimeOutException.getMessage());
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(errorMessage);
    }
/*
       @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<StandardError> userDisabledException(UserDisabledException userDisabled) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), userDisabled.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }*/


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> userNotFound(UserAlreadyExistsException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }


}
