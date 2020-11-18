package com.jasperstrijp.wolftracker.api.exceptions;

public class WolfIsAlreadyInPackException extends Exception {
    public WolfIsAlreadyInPackException() {
        super();
    }

    public WolfIsAlreadyInPackException(String message) {
        super(message);
    }

    public WolfIsAlreadyInPackException(String message, Throwable cause) {
        super(message, cause);
    }

    public WolfIsAlreadyInPackException(Throwable cause) {
        super(cause);
    }
}
