package com.example.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = {"http://localhost:4200","http://192.168.86.250:4200"})
public class BookSearchController 
{

	BookService bookservice;
	
	@GetMapping("/asdf")
	public Book getBooKs() {
		
		return new Book();
	}
	
}
