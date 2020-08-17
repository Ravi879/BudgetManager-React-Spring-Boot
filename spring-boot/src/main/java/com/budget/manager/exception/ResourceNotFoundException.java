package com.budget.manager.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2152219922352821559L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
