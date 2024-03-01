package com.example.bookstore.mapper;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.entity.Author;

public class AuthorMapper {

    public static AuthorDTO toDto(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBiography(author.getBiography());
        return dto;
    }

    public static Author toEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        author.setBiography(dto.getBiography());
        return author;
    }
}
