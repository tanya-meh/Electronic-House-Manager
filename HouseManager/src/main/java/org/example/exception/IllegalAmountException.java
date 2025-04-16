package org.example.exception;

public class IllegalAmountException extends RuntimeException{
    public IllegalAmountException(String errorMessage) {
        super(errorMessage);
    }
}
