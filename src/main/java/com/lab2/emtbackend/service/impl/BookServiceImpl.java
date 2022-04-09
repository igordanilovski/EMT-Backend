package com.lab2.emtbackend.service.impl;

import com.lab2.emtbackend.dto.BookDto;
import com.lab2.emtbackend.model.Author;
import com.lab2.emtbackend.model.Book;
import com.lab2.emtbackend.model.exceptions.CustomInvalidActionException;
import com.lab2.emtbackend.model.exceptions.CustomNotFoundException;
import com.lab2.emtbackend.repository.AuthorRepository;
import com.lab2.emtbackend.repository.BookRepository;
import com.lab2.emtbackend.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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
    public Optional<Boolean> create(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(CustomNotFoundException::new);

        this.bookRepository.save(
                new Book(
                        bookDto.getName(),
                        bookDto.getCategory(),
                        author,
                        bookDto.getAvailableCopies()
                )
        );
        return Optional.of(true);
    }

    @Override
    public Optional<Boolean> edit(Long id, BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(CustomNotFoundException::new);

        this.bookRepository.findById(id)
                .ifPresentOrElse(bookToChangeObject -> { //if book with id is present already
                    bookToChangeObject.setName(bookDto.getName());
                    bookToChangeObject.setAvailableCopies(bookDto.getAvailableCopies());
                    bookToChangeObject.setCategory(bookDto.getCategory());
                    bookToChangeObject.setAuthor(author);

                    this.bookRepository.save(bookToChangeObject);
                }, () -> { //or else throw exception
                    throw new CustomNotFoundException();
                });
        return Optional.of(true);
    }

    @Override
    public Optional<Boolean> rentBook(Long id) throws CustomInvalidActionException, CustomNotFoundException {
        Book bookToRent = this.bookRepository.findById(id).orElseThrow(CustomNotFoundException::new);
        int availableBooks = bookToRent.getAvailableCopies();
        if (availableBooks != 0) {
            bookToRent.setAvailableCopies(availableBooks - 1);
            this.bookRepository.save(bookToRent);
            return Optional.of(true);
        } else {
            throw new CustomInvalidActionException();
        }
    }

    @Override
    public Optional<Boolean> deleteBook(Long id) {
        this.bookRepository.delete(this.bookRepository.findById(id).orElseThrow(CustomNotFoundException::new));
        return Optional.of(true);
    }
}
