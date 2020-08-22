package com.budget.manager.exception;

public class EmailNotVerifiedException extends RuntimeException {
    private static final long serialVersionUID = 152270678992720640L;

    private String messageCode;
    private String errorInfo;

    public EmailNotVerifiedException(String messageCode, String errorInfo) {
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
