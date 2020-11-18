package com.jasperstrijp.wolftracker.api.exceptions;

public class InvalidPackDataException extends Exception {
    public InvalidPackDataException() {
        super();
    }

    public InvalidPackDataException(String message) {
        super(message);
    }

    public InvalidPackDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPackDataException(Throwable cause) {
        super(cause);
    }
}
