package com.example.bookstore.mapper;

import java.util.stream.Collectors;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Book;

public class BookMapper {

    public static BookDTO toDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setCategoryIds(book.getCategories().stream().map(category -> category.getId()).collect(Collectors.toSet()));
        return dto;
    }

    public static Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        // Note: For author and categories, you'll likely need to fetch the entities from the database before setting them
        return book;
    }
}
