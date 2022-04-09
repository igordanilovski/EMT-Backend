package com.lab2.emtbackend.service;

import com.lab2.emtbackend.dto.BookDto;
import com.lab2.emtbackend.model.Book;
import com.lab2.emtbackend.model.exceptions.CustomInvalidActionException;
import com.lab2.emtbackend.model.exceptions.CustomNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Boolean> create(BookDto bookDto);

    Optional<Boolean> edit();

    Optional<Boolean> rentBook(Long id) throws CustomInvalidActionException, ChangeSetPersister.NotFoundException, CustomNotFoundException;

    Optional<Boolean> deleteBook(Long id);
}
