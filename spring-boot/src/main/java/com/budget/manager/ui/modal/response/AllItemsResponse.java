package com.budget.manager.ui.modal.response;

import java.util.List;

public class AllItemsResponse {

    private List<ItemResponse> incomes;
    private List<ItemResponse> expenses;

    public List<ItemResponse> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<ItemResponse> incomes) {
        this.incomes = incomes;
    }

    public List<ItemResponse> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ItemResponse> expenses) {
        this.expenses = expenses;
    }
}
