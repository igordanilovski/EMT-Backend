package com.lab2.emtbackend.service;

import com.lab2.emtbackend.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    List<Author> findById(Long id);
}
