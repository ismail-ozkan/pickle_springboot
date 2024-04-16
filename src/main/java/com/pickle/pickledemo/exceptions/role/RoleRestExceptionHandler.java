package com.pickle.pickledemo.exceptions.role;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoleRestExceptionHandler {

    // global exception handler implementation, copy from controller class to here

    // with @ExceptionHandler annotation creating new method to show error when the user not found

    @ExceptionHandler
    public ResponseEntity<RoleErrorResponse> handleException(Exception exp) {
        RoleErrorResponse error = new RoleErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exp.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
