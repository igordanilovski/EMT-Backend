package com.lab2.emtbackend.model.projections;

import lombok.Data;

@Data
public class JWTProjection {
    private final String jwt;

    public JWTProjection(String jwt) {
        this.jwt = jwt;
    }
}
