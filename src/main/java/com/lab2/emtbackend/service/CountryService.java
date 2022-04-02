package com.lab2.emtbackend.service;

import com.lab2.emtbackend.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
}
