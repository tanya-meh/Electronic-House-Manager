package org.example.exception;

public class EntitiesNotConnectedException extends RuntimeException{
    public EntitiesNotConnectedException(String errorMessage) {
        super(errorMessage);
    }
}
