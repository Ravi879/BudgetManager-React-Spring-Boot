package com.budget.manager.shared.type;

public enum Errors {

    RESOURCE_ALREADY_EXISTS("Resource already exists"),
    RESOURCE_NOT_FOUND("Resource note found"),
    BAD_CREDENTIALS("Bad credentials"),
    EMAIL_NOT_VERIFIED("Not verified"),
    VALIDATION_ERROR("Validation error"),
    INVALID_TOKEN("Invalid token");

    private String error;

    Errors(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

}
