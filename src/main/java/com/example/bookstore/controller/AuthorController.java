package com.example.bookstore.controller;

import com.example.bookstore.entity.Author;
import com.example.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return authorService.findAuthorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        return authorService.findAuthorById(id)
                .map(author -> {
                    authorService.deleteAuthor(id);
                    return ResponseEntity.ok().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
