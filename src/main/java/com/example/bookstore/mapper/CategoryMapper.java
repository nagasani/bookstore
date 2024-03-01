package com.example.bookstore.mapper;

import com.example.bookstore.dto.CategoryDTO;
import com.example.bookstore.entity.Category;

public class CategoryMapper {

    public static CategoryDTO toDto(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public static Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}
