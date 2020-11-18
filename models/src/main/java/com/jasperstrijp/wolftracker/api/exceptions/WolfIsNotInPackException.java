package com.jasperstrijp.wolftracker.api.exceptions;

public class WolfIsNotInPackException extends Exception {
    public WolfIsNotInPackException() {
        super();
    }

    public WolfIsNotInPackException(String message) {
        super(message);
    }

    public WolfIsNotInPackException(String message, Throwable cause) {
        super(message, cause);
    }

    public WolfIsNotInPackException(Throwable cause) {
        super(cause);
    }
}
