package com.budget.manager.exception;

public class EmailNotVerifiedException extends RuntimeException {
    private static final long serialVersionUID = 152270678992720640L;

    public EmailNotVerifiedException(String message) {
        super(message);
    }

}
