package com.budget.manager.ui.modal.request;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ItemRequest {

    @NotBlank(message = "item.description.not.empty")
    @Size(min = 2, max = 100, message = "item.description.size")
    private String description;

    @NotNull(message = "item.amount.not.empty")
    @Range(min = 0, max = 1000000, message = "item.amount.size")
    private Integer amount;

    @NotNull(message = "item.date.not.empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
