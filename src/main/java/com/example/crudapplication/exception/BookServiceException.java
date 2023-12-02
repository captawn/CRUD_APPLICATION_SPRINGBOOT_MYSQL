package com.example.crudapplication.exception;

public class BookServiceException extends RuntimeException {

    public BookServiceException(String message) {
        super(message);
    }

    public BookServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
