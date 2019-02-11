package com.library.model.client.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank
    @Size(min = 2, max = 100)
    private String username;
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;
    @NotBlank
    @Size(min = 2, max = 100)
    private String surname;
    @NotBlank
    @Size(min = 6, max = 30)
    private String password;
    @NotBlank
    private String phone;

    public String getUsername() {
        return username;
    }

    public RegisterRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public RegisterRequest setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegisterRequest setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
