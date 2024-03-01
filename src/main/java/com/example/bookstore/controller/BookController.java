package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.findAllBooks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		return bookService.findBookById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
		return bookService.findBookById(id).map(book -> {
			book.setTitle(bookDetails.getTitle());
			book.setAuthor(bookDetails.getAuthor());
			book.setCategories(bookDetails.getCategories());
			Book updatedBook = bookService.saveBook(book);
			return ResponseEntity.ok(updatedBook);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		return bookService.findBookById(id).map(book -> {
			bookService.deleteBook(id);
			return ResponseEntity.ok().<Void>build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
