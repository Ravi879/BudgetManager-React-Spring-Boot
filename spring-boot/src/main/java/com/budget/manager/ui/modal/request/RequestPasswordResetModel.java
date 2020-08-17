package com.budget.manager.ui.modal.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RequestPasswordResetModel {

    @NotBlank(message = "email.not.empty")
    @Size(max = 100, message = "email.max.size")
    @Pattern(regexp = "^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "email.invalid")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
