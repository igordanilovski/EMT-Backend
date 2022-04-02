package com.lab2.emtbackend.service.impl;

import com.lab2.emtbackend.model.Country;
import com.lab2.emtbackend.repository.CountryRepository;
import com.lab2.emtbackend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return null;
    }
}
