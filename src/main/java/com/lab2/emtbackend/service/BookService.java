package com.lab2.emtbackend.service;

import com.lab2.emtbackend.model.Book;
import com.lab2.emtbackend.model.exceptions.InvalidActionException;
import com.lab2.emtbackend.model.exceptions.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Boolean> create();

    Optional<Boolean> edit();

    Optional<Boolean> rentBook(Long id) throws InvalidActionException, ChangeSetPersister.NotFoundException, NotFoundException;

    Optional<Boolean> deleteBook(Long id);
}
