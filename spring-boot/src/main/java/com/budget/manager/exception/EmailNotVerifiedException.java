package com.budget.manager.exception;

public class EmailNotVerifiedException extends RuntimeException {
    private static final long serialVersionUID = 152270678992720640L;

    private String messageCode;

    public EmailNotVerifiedException(String messageCode) {
        this.messageCode = messageCode;

    }

    public String getMessageCode() {
        return messageCode;
    }
}
