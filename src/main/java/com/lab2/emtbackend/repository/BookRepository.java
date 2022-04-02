package com.lab2.emtbackend.repository;

import com.lab2.emtbackend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
