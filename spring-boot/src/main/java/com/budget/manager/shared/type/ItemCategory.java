package com.budget.manager.shared.type;

public enum ItemCategory {

    INCOME("income"),
    EXPENSE("expense");

    private String type;

    ItemCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String errorMessage) {
        this.type = errorMessage;
    }


}
