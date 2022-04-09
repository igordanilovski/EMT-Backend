package com.lab2.emtbackend.service;

import com.lab2.emtbackend.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);
}
