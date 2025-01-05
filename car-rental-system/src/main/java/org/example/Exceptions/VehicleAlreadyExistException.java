package org.example.Exceptions;

public class VehicleAlreadyExistException extends RuntimeException{
    public VehicleAlreadyExistException(String message) {
        super(message);
    }
}
