package com.budget.manager.ui.modal.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ResetPasswordModel {

    @NotBlank(message = "token.not.empty")
    private String token;

    @NotBlank(message = "password.not.empty")
    @Size(min = 5, max = 14, message = "password.size.between")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!#?&])[A-Za-z\\d@$!%*#?&]{5,14}$", message = "password.invalid")
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
