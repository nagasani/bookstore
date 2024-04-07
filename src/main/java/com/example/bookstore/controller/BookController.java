package com.example.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.service.BookOfTheMomentService;
import com.example.bookstore.service.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = {"http://localhost:4200","http://192.168.86.250:4200"})
public class BookController {

	private final BookService bookService;
	private final BookOfTheMomentService bookOfTheMomentService;
	
	public BookController(BookService bookService,  BookOfTheMomentService bookOfTheMomentService) 
	{
		this.bookService = bookService;
		this.bookOfTheMomentService = bookOfTheMomentService;
	}

	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.findAllBooks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) 
	{
		System.out.println("test");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Book>(bookService.findBookById(id).get(), HttpStatus.OK);
		//return bookService.findBookById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/aspect/{id}")
	public ResponseEntity<Book> getBookByIdForAspect(@PathVariable Long id) 
	{
		System.out.println("test");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Book>(bookService.findBookById(id).get(), HttpStatus.OK);
		//return bookService.findBookById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) 
	{
		System.out.println("Here");		
		return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
		return bookService.findBookById(id).map(book -> {
			book.setTitle(bookDetails.getTitle());
			book.setAuthor(bookDetails.getAuthor());
			book.setCategories(bookDetails.getCategories());
			Book updatedBook = bookService.addBook(book);
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
	
	@GetMapping("/book-of-the-moment")
    public Book getBookOfTheMoment() {
        return bookOfTheMomentService.getCurrentBookOfTheMoment();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById2(@PathVariable Long id) 
	{
        BookDTO bookDTO = bookService.findBookById(id)
                .map(BookMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
	        return ResponseEntity.ok(bookDTO);
	}
}
