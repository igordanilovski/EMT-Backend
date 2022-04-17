package com.lab2.emtbackend.dto;

import com.lab2.emtbackend.model.enums.Role;

/*
It looks like we don't need this :)
 */
public class UserDto {
    String email;
    String username;
    String password;
    Role role;

    public UserDto() {
    }

    public UserDto(String email, String username, String password, Role role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
