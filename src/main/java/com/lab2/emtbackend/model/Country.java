package com.lab2.emtbackend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String continent;

    public Country() {
    }
}
