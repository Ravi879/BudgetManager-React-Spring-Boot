package com.budget.manager.exception;

public class InvalidTokenException extends RuntimeException {

    private static final long serialVersionUID = 5105178238567655705L;

    private String messageCode;
    private String errorInfo;

    public InvalidTokenException(String messageCode, String errorInfo) {
        this.messageCode = messageCode;
        this.errorInfo = errorInfo;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

}
