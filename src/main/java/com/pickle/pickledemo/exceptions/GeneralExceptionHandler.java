package com.pickle.pickledemo.exceptions;

import com.pickle.pickledemo.exceptions.user.UserNotFoundException;
import com.pickle.pickledemo.exceptions.user.UsersErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GeneralExceptionHandler {

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

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ResponseStatusException exp) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exp.getReason());
        error.setStatus(exp.getRawStatusCode());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,exp.getStatus());
    }

}
