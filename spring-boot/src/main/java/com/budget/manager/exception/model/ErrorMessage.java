package com.budget.manager.exception.model;

import com.budget.manager.shared.type.Errors;

import java.time.ZonedDateTime;

public class ErrorMessage {

    private ZonedDateTime timestamp;
    private String message;
    private String error;
    private Integer status;
    private String path;

    public ErrorMessage(String message, String error, Integer status, String path) {
        this.timestamp = ZonedDateTime.now();
        this.message = message;
        this.error = error;
        this.status = status;
        this.path = path;
    }

    public ErrorMessage(String message, Errors error, Integer status, String path) {
        this.timestamp = ZonedDateTime.now();
        this.message = message;
        this.error = error.getError();
        this.status = status;
        this.path = path;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
