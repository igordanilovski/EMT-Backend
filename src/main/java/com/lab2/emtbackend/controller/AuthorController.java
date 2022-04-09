package com.lab2.emtbackend.controller;

import com.lab2.emtbackend.model.Author;
import com.lab2.emtbackend.model.exceptions.CustomNotFoundException;
import com.lab2.emtbackend.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getBookById(@PathVariable Long id) {
        return this.authorService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseThrow(CustomNotFoundException::new);
    }
}
