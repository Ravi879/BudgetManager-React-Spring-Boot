package com.budget.manager.exception;

public class InvalidTokenException extends RuntimeException {

    private static final long serialVersionUID = 5105178238567655705L;

    public InvalidTokenException(String message) {
        super(message);
    }

}
