package com.lab2.emtbackend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
