package com.budget.manager.exception.model;

import com.budget.manager.shared.type.Errors;

import java.time.ZonedDateTime;
import java.util.List;

public class ErrorListMessages {

    private ZonedDateTime timestamp;
    private List<String> message;
    private Integer status;
    private String path;
    private String error;

    public ErrorListMessages(List<String> messages, Errors errors, Integer status, String path) {
        this.timestamp = ZonedDateTime.now();
        this.message = messages;
        this.status = status;
        this.path = path;
        this.error = errors.getError();
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
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

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
