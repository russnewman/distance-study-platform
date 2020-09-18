package com.netcracker.edu.distancestudyplatform.exception;

public class UserNotFoundException extends ResourceNotFoundException {
    private String identifier;

    public UserNotFoundException(String identifier) {
        this.identifier = identifier;
    }

    public UserNotFoundException(String message, String identifier) {
        super(message);
        this.identifier = identifier;
    }

    public UserNotFoundException(String message, Throwable cause, String identifier) {
        super(message, cause);
        this.identifier = identifier;
    }

    public UserNotFoundException(Throwable cause, String identifier) {
        super(cause);
        this.identifier = identifier;
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String identifier) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.identifier = identifier;
    }
}
