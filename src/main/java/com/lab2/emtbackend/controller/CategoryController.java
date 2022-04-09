package com.lab2.emtbackend.controller;

import com.lab2.emtbackend.model.enums.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @GetMapping
    public List<Category> findAll() {
        return Arrays.stream(Category.values()).toList();
    }
}
