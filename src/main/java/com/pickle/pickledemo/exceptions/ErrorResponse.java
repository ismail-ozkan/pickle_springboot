package com.pickle.pickledemo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponse {

    @Setter
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

    public ResponseEntity<ErrorResponse> setMessage(String message) {
        this.message = message;
        return null;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
