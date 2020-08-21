package com.budget.manager.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -3066144920466166379L;

    private String messageCode;

    public ResourceAlreadyExistsException(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

}
