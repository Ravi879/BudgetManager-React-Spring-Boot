package com.budget.manager.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2152219922352821559L;

    private String messageCode;

    public ResourceNotFoundException(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

}
