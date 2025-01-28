package org.example.exception;

public class IllegalAmountOfMoneyException extends RuntimeException{
    public IllegalAmountOfMoneyException(String errorMessage) {
        super(errorMessage);
    }
}