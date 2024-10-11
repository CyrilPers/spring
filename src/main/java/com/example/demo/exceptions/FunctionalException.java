package com.example.demo.exceptions;

public class FunctionalException extends Exception {

    public FunctionalException(String message) {
        super(message);
    }

    public FunctionalException(String message, Throwable cause) {
        super(message, cause);
    }
}
