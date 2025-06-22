package org.example.vendingmachineap3.exceptions;

public class NotEnoughMoneyInserted extends RuntimeException{

    public NotEnoughMoneyInserted(String message) {
        super(message);
    }
}
