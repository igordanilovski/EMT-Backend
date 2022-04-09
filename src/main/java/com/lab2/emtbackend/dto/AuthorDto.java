package com.lab2.emtbackend.dto;

import com.lab2.emtbackend.model.Country;

/*
It looks like we don't need this :)
 */
public class AuthorDto {
    String firstName;
    String lastName;
    Country country;

    public AuthorDto() {
    }

    public AuthorDto(String firstName, String lastName, Country country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
}
