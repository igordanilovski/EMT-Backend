package com.lab2.emtbackend.repository;

import com.lab2.emtbackend.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
