package com.jasperstrijp.wolftracker.api.exceptions;

public class PackDoesNotExistException extends Exception {
    public PackDoesNotExistException() {
        super();
    }

    public PackDoesNotExistException(String message) {
        super(message);
    }

    public PackDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PackDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
