package com.lab2.emtbackend.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    String email;
    String password;

    public UserLoginDto() {
    }
}
