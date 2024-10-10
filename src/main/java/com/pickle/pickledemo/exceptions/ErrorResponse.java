package com.pickle.pickledemo.exceptions;

import org.springframework.http.ResponseEntity;

public class ErrorResponse {

    private int status;
    public String message;
    public long timeStamp;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public ResponseEntity<ErrorResponse> setMessage(String message) {
        this.message = message;
        return null;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
