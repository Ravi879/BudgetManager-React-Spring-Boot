package com.budget.manager.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -3066144920466166379L;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

}
