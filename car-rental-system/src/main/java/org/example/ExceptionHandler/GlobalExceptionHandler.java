package org.example.ExceptionHandler;

import org.example.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {
            StoreAlreadyExistException.class,
            VehicleAlreadyExistException.class,
            ResourceNotFoundException.class,
            UserAlreadyExistException.class,
            InvalidArgumentException.class
    })
    public ResponseEntity<Object> handleResourceAlreadyExistException(RuntimeException ex){

        Map<String , Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
