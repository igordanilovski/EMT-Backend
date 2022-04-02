package com.lab2.emtbackend.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true)
    public String email;
    @Column(unique = true)
    public String username;
    public String password;
    public Role role;

    public User() {
    }
}
