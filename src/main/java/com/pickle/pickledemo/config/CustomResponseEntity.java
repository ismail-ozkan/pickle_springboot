package com.pickle.pickledemo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class CustomResponseEntity<T> {
    public static <T> ResponseEntity<T> ok(T responseObj) {
        var map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("result", responseObj);
        map.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity<>((T)map,HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> ok(T responseObj, HttpStatus status) {
        var map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("result", responseObj);
        map.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity<>((T)map,status);
    }
    public static <T> ResponseEntity<T> fail(T responseObj) {
        var map = new HashMap<String, Object>();
        map.put("fail", false);
        map.put("result", responseObj);
        map.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity<>((T)map,HttpStatus.BAD_REQUEST);
    }
}
