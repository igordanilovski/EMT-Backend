package com.lab2.emtbackend.controller;

import com.lab2.emtbackend.dto.BookDto;
import com.lab2.emtbackend.model.Book;
import com.lab2.emtbackend.model.Country;
import com.lab2.emtbackend.model.exceptions.CustomNotFoundException;
import com.lab2.emtbackend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseThrow(CustomNotFoundException::new);
    }

    @PostMapping()
    public ResponseEntity<Boolean> save(@RequestBody BookDto bookDto) {
        return this.bookService.create(bookDto)
                .map(status -> ResponseEntity.ok().body(status))
                .orElseThrow(CustomNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
