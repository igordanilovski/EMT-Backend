package com.lab2.emtbackend.service.impl;

import com.lab2.emtbackend.model.Book;
import com.lab2.emtbackend.model.exceptions.InvalidActionException;
import com.lab2.emtbackend.model.exceptions.NotFoundException;
import com.lab2.emtbackend.repository.BookRepository;
import com.lab2.emtbackend.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Boolean> create() {
        //TODO: Implement this
        return Optional.of(true);
    }

    @Override
    public Optional<Boolean> edit() {
        //TODO: Implement this
        return Optional.of(true);
    }

    @Override
    public Optional<Boolean> rentBook(Long id) throws InvalidActionException, NotFoundException {
        Book bookToRent = this.bookRepository.findById(id).orElseThrow(NotFoundException::new);
        int availableBooks = bookToRent.getAvailableCopies();
        if (availableBooks != 0) {
            bookToRent.setAvailableCopies(availableBooks - 1);
            return Optional.of(true);
        } else {
            throw new InvalidActionException();
        }
    }

    @Override
    public Optional<Boolean> deleteBook(Long id) {
        this.bookRepository.delete(this.bookRepository.findById(id).orElseThrow(NotFoundException::new));
        return Optional.of(true);
    }
}
