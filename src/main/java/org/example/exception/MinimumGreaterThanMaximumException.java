package org.example.exception;

public class MinimumGreaterThanMaximumException extends RuntimeException{
    public MinimumGreaterThanMaximumException(String errorMessage) {
        super(errorMessage);
    }
}
