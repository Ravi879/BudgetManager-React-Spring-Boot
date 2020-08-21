package com.budget.manager.exception;

public class InvalidTokenException extends RuntimeException {

    private static final long serialVersionUID = 5105178238567655705L;

    private String messageCode;

    public InvalidTokenException(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

}
