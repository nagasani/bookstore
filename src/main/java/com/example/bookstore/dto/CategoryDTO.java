package com.example.bookstore.dto;

public class CategoryDTO {

    private Long id;
    private String name;

    // Constructors, Getters, and Setters

    public CategoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
