package com.budget.manager.ui.modal.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {

    @NotBlank(message = "first.name.not.empty")
    @Size(min = 2, max = 15, message = "first.name.size.between")
    @Pattern(regexp = "^[A-Za-z0-9]{2,15}$", message = "first.name.invalid")
    private String firstName;

    @NotBlank(message = "last.name.not.empty")
    @Size(min = 2, max = 15, message = "last.name.size.between")
    @Pattern(regexp =  "^[A-Za-z0-9]{2,15}$", message = "last.name.invalid")
    private String lastName;

    @NotBlank(message = "email.not.empty")
    @Size(max = 100, message = "email.max.size")
    @Pattern(regexp = "^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "email.invalid")
    private String email;

    @NotBlank(message = "password.not.empty")
    @Size(min = 5, max = 14, message = "password.size.between")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!#?&])[A-Za-z\\d@$!%*#?&]{5,14}$", message = "password.invalid")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
