package com.pickle.pickledemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

    // global exception handler implementation, copy from controller class to here

    // with @ExceptionHandler annotation creating new method to show error when the user not found
    @ExceptionHandler
    public ResponseEntity<UsersErrorResponse> handleException(UserNotFoundException exp) {
        UsersErrorResponse error = new UsersErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exp.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return a new ResponseEntity object
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UsersErrorResponse> handleException(Exception exp) {
        UsersErrorResponse error = new UsersErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exp.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
