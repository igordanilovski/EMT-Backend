package com.lab2.emtbackend.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_LIBRARIAN;

    @Override
    public String getAuthority() {
        return name();
    }
}
