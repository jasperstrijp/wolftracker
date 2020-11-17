package com.jasperstrijp.wolftracker.api.exceptions;

public class InvalidWolfDataException extends Exception {
    public InvalidWolfDataException() {
        super();
    }

    public InvalidWolfDataException(String message) {
        super(message);
    }

    public InvalidWolfDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidWolfDataException(Throwable cause) {
        super(cause);
    }
}
