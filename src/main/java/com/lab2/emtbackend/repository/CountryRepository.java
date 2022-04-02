package com.lab2.emtbackend.repository;

import com.lab2.emtbackend.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
