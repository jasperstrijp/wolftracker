package com.jasperstrijp.wolftracker.api.exceptions;

public class WolfDoesNotExistException extends Exception {
    public WolfDoesNotExistException() {
        super();
    }

    public WolfDoesNotExistException(String message) {
        super(message);
    }

    public WolfDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public WolfDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
