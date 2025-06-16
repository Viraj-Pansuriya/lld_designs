package org.example.vendingmachineap3.exceptions;

public class NotEnoughStock extends RuntimeException{
    public NotEnoughStock(String message) {
       super(message);
    }
}
