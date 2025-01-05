package org.example.Exceptions;

public class StoreAlreadyExistException extends RuntimeException{
    public StoreAlreadyExistException(String message) {
        super(message);
    }
}
