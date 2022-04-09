package com.lab2.emtbackend.dto;

import com.lab2.emtbackend.model.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookDto {
    @NotNull(message = "Name is required!")
    String name;
    @NotNull(message = "Category is required!")
    Category category;
    Long authorId;
    @NotNull(message = "Value bigger than 0 is required!")
    Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, Category category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }
}
